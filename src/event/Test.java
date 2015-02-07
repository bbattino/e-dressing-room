package event;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JFrame;
import event.MainDansLaZoneEventProducer.Type;

public class Test {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setVisible(true);
		final JButton bouton = new JButton("ok");
		frame.getContentPane().add(bouton);
		frame.pack();
		
		MainDansLaZoneEventProducer eventProducer = new MainDansLaZoneEventProducer(
				new TrucKinect(bouton) {
					@Override
					public Point getPosition() {
						return bouton.getMousePosition();
					} // c'est l'instance de votre classe avec la méthode getPosition()
		}, new Rectangle(bouton.getLocationOnScreen(), bouton.getSize()), 1000, 100); // buttonBounds ce sont les bounds du bouton (à priori dans l'écran)
 
		
		eventProducer.addListener(new Listener(){
			public void mainDansLaZone(Type type) {
				switch( type ) {
			    case ENTER: // on entre la zone
			        bouton.getModel().setArmed(true);
			        bouton.getModel().setPressed(true);
			        break;
			    case HIT: // si la main est restée 3 secondes
			        bouton.getModel().setArmed(false);
			        bouton.getModel().setPressed(false);
			        bouton.doClick(); // je clique le bouton
			        System.out.println("ok");
			        break;
			     case EXIT: // si la main sort (attention avec ce que j'ai fait, elle sort toujours, même si elle est restée 3 secondes...
			        bouton.getModel().setArmed(false);
			        bouton.getModel().setPressed(false);      
			        break;		
			}
		}});
		
	}	
}