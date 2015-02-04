package event;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainDansLaZoneTest extends JPanel {
 
	private static final long serialVersionUID = 1L;
	protected static final long TIME = 3000; // le temps d'attente = 3 secondes
	private static final long PERIOD = 100; // la période d'observation (toutes les 100 ms)
 
	private final Rectangle rectangle; // stocke la zone (pour la dessiner)
	private volatile Point mousePoint; // stocke la position de la souris = la position de la main
	private AtomicBoolean mainDansLaZone=new AtomicBoolean(); // un état pour savoir qu'on a valider "main dans la zone pendant 3 secondes"
 
	// sert à afficher un compte à rebours (pour la démo)
	private volatile int counter;
	private Timer counterTimer;
	private TimerTask counterTask;
	private AtomicBoolean counterStarted=new AtomicBoolean();
	private boolean started;
	protected static MainDansLaZoneTest panel;
 
 
	public MainDansLaZoneTest(Rectangle rectangle) {
		this.counterTimer = new Timer();
		this.rectangle=rectangle;
		// simulation de la détection de la main dans la zone = tes classes C++ de gestion de la kinect
		addMouseMotionListener(new MouseAdapter() {
 
			@Override
			public void mouseMoved(MouseEvent e) {
				if ( !e.getPoint().equals(mousePoint) ) {
					mousePoint = e.getPoint();
					repaint();
				}
			}
 
			@Override
			public void mouseExited(MouseEvent e) {
				if ( mousePoint!=null ) {
					mousePoint=null;
					stopCounter();
					repaint();
				}
			}
		});
	}
 
 
	public Point getMousePoint() {
		Point point = super.getMousePosition();
		if ( point!=null && rectangle.contains(point) ) {
			startCounter();
		}
		else {
			stopCounter();
		}
		return point;
	}
 
	private void startCounter() {
		if ( counterStarted.compareAndSet(false, true) ) {
			counter=(int)TIME/1000;
			counterTask = new TimerTask() {
 
				@Override
				public void run() {
					counter--;
					if ( counter==0 ) cancel();
					repaint();
				}
			};
			counterTimer.schedule(counterTask, 1000, 1000);
			repaint();
		}
	}
 
	private void stopCounter() {
		if ( counterStarted.compareAndSet(true, false) ) {
			if ( counterTask!=null) counterTask.cancel();
			counter=0;
			repaint();
		}
	}
 
	public void setStarted(boolean started) {
		if ( this.started!=started) {
			this.started=started;
			if ( !started ) {
				stopCounter();
			}
			repaint();
		}
	}
 
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if ( started ) {
			if ( mousePoint!=null && rectangle.contains(mousePoint) ) {
				if ( mainDansLaZone.get() ) {
					g.setColor(Color.GREEN); // on affiche la zone en vert quand la souris est dans la zone et qu'on reçu l'événement
				}
				else {
					g.setColor(Color.YELLOW); // on affiche la zone en jaunne quand la souris est dans la zone
				}
			}
			else {
				g.setColor(Color.CYAN);
			}
			g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
			if ( mousePoint!=null ) {
				g.setColor(Color.BLACK);
				g.drawLine(mousePoint.x-1, mousePoint.y, mousePoint.x+1, mousePoint.y);
				g.drawLine(mousePoint.x, mousePoint.y-1, mousePoint.x, mousePoint.y+1);
			}
			if ( counter>0 ) {
				g.setColor(Color.BLACK);
				String counterString = String.valueOf(counter);
				int size = g.getFont().getSize();
				FontMetrics fm = g.getFontMetrics();
				Rectangle2D stringBounds = fm.getStringBounds(counterString, g);
				double scale = rectangle.getHeight()/size;
				AffineTransform scaleTransform = AffineTransform.getScaleInstance(scale, scale);
				stringBounds = scaleTransform.createTransformedShape(stringBounds).getBounds2D();
				Rectangle2D bounds = new Rectangle2D.Double(0, 0, rectangle.width, rectangle.height);
				AffineTransform transform = AffineTransform.getTranslateInstance(rectangle.x + (int)((bounds.getWidth()-stringBounds.getWidth())/2), rectangle.y /*+ (int)((bounds.getHeight()-stringBounds.getHeight())/2)*/);
				transform.concatenate(scaleTransform);
				((Graphics2D)g).setTransform(transform);
				g.drawString(counterString, 0, fm.getAscent()-fm.getDescent());
			}
		}
		else {
			g.setColor(Color.DARK_GRAY);
			g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		}
	}
 
	public void setMainDansLaZone(boolean mainDansLaZone) {
		if ( this.mainDansLaZone.compareAndSet(!mainDansLaZone, mainDansLaZone) ) {
			repaint();
		}
	}
 
	public static void main(String[] args) {
 
		run();
	}
 
	private static void run() {
 
		// création de l'UI
		JFrame frame = new JFrame("Démo");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
 
		Rectangle zone = new Rectangle( 300, 100, 150, 100); // zone d'observation
 
		panel = new MainDansLaZoneTest(zone); // simulation de kinect
 
		MainDansLaZoneEventProducer eventProducer = new MainDansLaZoneEventProducer(
				new TrucKinect() {
					@Override
					public Point getPosition() {
						return panel.getMousePoint();
					}
		}, zone, TIME, PERIOD);
 
		// enregistrement de l'écouteur
		eventProducer.addListener(new Listener());
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panel, BorderLayout.CENTER);
 
		JCheckBox checkBox = new JCheckBox("Ecoute active");
		checkBox.addChangeListener(e-> {
			if ( checkBox.isSelected() ) {
				eventProducer.start();
				panel.setStarted(true);
			}
			else {
				eventProducer.stop();
				panel.setStarted(false);
			}
		});
		mainPanel.add(checkBox, BorderLayout.SOUTH);
 
		frame.getContentPane().add(mainPanel);
 
		// important : il faut démarrer le composant et l'arrêter
		// ici on démarre quand la fenêtre est ouverte, et on arrête quand elle est fermée
		frame.addWindowListener(new WindowAdapter() {
 
			@Override
			public void windowOpened(WindowEvent e) {
				eventProducer.start();
				panel.setStarted(true);
				checkBox.setSelected(true);
			}
 
			@Override
			public void windowClosed(WindowEvent e) {
				eventProducer.stop();
				System.exit(0);
			}
 
		});
 
		frame.setVisible(true);
 
	}
 
}