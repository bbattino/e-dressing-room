package event;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MainDansLaZoneTest extends JButton {
 
	private static final long serialVersionUID = 1L;
	protected static final long TIME = 300; 
	private static final long PERIOD = 100; 
	private Rectangle rectangle; // stocke la zone (pour la dessiner)
	private volatile Point mousePoint; // stocke la position de la souris = la position de la main
	private AtomicBoolean mainDansLaZone=new AtomicBoolean(); // un état pour savoir qu'on a valider "main dans la zone pendant 3 secondes"
 
	// sert à afficher un compte à rebours (pour la démo)
	private volatile int counter;
	private Timer counterTimer;
	private TimerTask counterTask;
	private AtomicBoolean counterStarted=new AtomicBoolean();
	private boolean started;
	protected static MainDansLaZoneTest button;
 
 
	public MainDansLaZoneTest(Rectangle rectangle, String msg) {
		super(msg);
		this.counterTimer = new Timer();
		this.rectangle=rectangle;
		// simulation de la détection de la main dans la zone = tes classes C++ de gestion de la kinect
		addMouseMotionListener(new MouseAdapter() {
 
			@Override
			public void mouseMoved(MouseEvent e) {
				if ( !e.getPoint().equals(mousePoint) ) {
					mousePoint = e.getPoint();
					repaint();}}
			@Override
			public void mouseExited(MouseEvent e) {
				if ( mousePoint!=null ) {
					mousePoint=null;
					stopCounter();
					repaint();}}});} 
	
	public void setZone(Rectangle rectangle){
		this.rectangle= rectangle;
	}
 
	public Point getMousePoint() {
		Point point = super.getMousePosition();
		if ( point!=null && rectangle.contains(point) ) {startCounter();}
		else {stopCounter();}
		return point;
	}
 
	private void startCounter() {
		if ( counterStarted.compareAndSet(false, true) ) {
			counter=(int)TIME/1000;
			counterTask = new TimerTask() {
 
				@Override
				public void run() {counter--;
					if ( counter==0 ) {cancel();}
					repaint();}};
			counterTimer.schedule(counterTask, 1000, 1000);
			repaint();}}
 
	private void stopCounter() {
		if ( counterStarted.compareAndSet(true, false) ) {
			if ( counterTask!=null) counterTask.cancel();
			counter=0;repaint();}}
 
	public void setStarted(boolean started) {
		if ( this.started!=started) {
			this.started=started;
			if ( !started ) {stopCounter();}
			repaint();}}
 
	public void setMainDansLaZone(boolean mainDansLaZone) {
		if ( this.mainDansLaZone.compareAndSet(!mainDansLaZone, mainDansLaZone) ) {
			repaint();}}
 
	public static void main(String[] args) {run();}
 
	private static void run() {
 
		JFrame frame = new JFrame("Démo");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
 
		Rectangle zone = new Rectangle( 0, 0, 150, 100);
 
		button = new MainDansLaZoneTest(zone,"ok"); // simulation de kinect
 
		final MainDansLaZoneEventProducer eventProducer = new MainDansLaZoneEventProducer(
				new TrucKinect(button) {
					@Override
					public Point getPosition() {
						return button.getMousePoint();
					}
		}, zone, TIME, PERIOD);
 
		eventProducer.addListener(new Listener());
		
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(button);
		System.out.println(button.getBounds());
		Rectangle rec = button.getBounds();
		
		
		button.setZone(rec);
		frame.getContentPane().remove(button);
		button = new MainDansLaZoneTest(rec, "ok");
		frame.getContentPane().add(button);
		frame.repaint();
 
		final JCheckBox checkBox = new JCheckBox("Ecoute active");
		checkBox.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				if ( checkBox.isSelected() ) {
					eventProducer.start();
					button.setStarted(true);
				}
				else {
					eventProducer.stop();
					button.setStarted(false);
				}				
			}
		});
		
		frame.getContentPane().add(checkBox);
  
		// important : il faut démarrer le composant et l'arrêter
		// ici on démarre quand la fenêtre est ouverte, et on arrête quand elle est fermée
		frame.addWindowListener(new WindowAdapter() {
 
			@Override
			public void windowOpened(WindowEvent e) {
				eventProducer.start();
				button.setStarted(true);
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