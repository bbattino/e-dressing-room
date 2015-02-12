package event;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import event.MainDansLaZoneEventProducer.IMainDansLaZoneListener;
import event.MainDansLaZoneEventProducer.Type;
 
public class Test {
 
	public static void main(String[] args) {
 
		JFrame frame = new JFrame();
		frame.setVisible(true);
		final JButton bouton = new JButton("ok");
		bouton.addActionListener(new ActionListener() {
 
			public void actionPerformed(ActionEvent e) {
				System.out.println("OK : le bouton a été actionné");
			}
		});
		frame.getContentPane().add(bouton);
		frame.pack();
 
		final MainDansLaZoneEventProducer eventProducer = new MainDansLaZoneEventProducer(
				new TrucKinect(bouton),
				 1000, 100); // buttonBounds ce sont les bounds du bouton (à priori dans l'écran)
 
		// alternative 1 : classe anonyme
		eventProducer.addListener(new IMainDansLaZoneListener() {
			public void mainDansLaZone(Type type) {
				switch( type ) {
			    case ENTER: // on entre la zone
			    	System.out.println("enter");
			        bouton.getModel().setArmed(true);
			        bouton.getModel().setPressed(true);
			        break;
			    case HIT: // si la main est restée 3 secondes
			    	System.out.println("hit");
			        bouton.getModel().setArmed(false);
			        bouton.getModel().setPressed(false);
			        bouton.doClick(); // je clique le bouton
			        break;
			     case EXIT: // si la main sort (attention avec ce que j'ai fait, elle sort toujours, même si elle est restée 3 secondes...
				    	System.out.println("exit");
			        bouton.getModel().setArmed(false);
			        bouton.getModel().setPressed(false);      
			        break;		
			}
		}});
 
		//ou alors alternative 2 : classe concrete Listener
		// eventProducer.addListener(new Listener(bouton));
 
		eventProducer.start();
 
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				eventProducer.stop();
			}
		});
 
	}	
}