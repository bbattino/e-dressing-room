package event;
 
import javax.swing.JButton;
 
import event.MainDansLaZoneEventProducer.IMainDansLaZoneListener;
import event.MainDansLaZoneEventProducer.Type;
 
public class Listener implements IMainDansLaZoneListener {
 
	private JButton bouton;
 
	public Listener(JButton bouton) {
		this.bouton=bouton;
	}
 
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
	        break;
	     case EXIT: // si la main sort (attention avec ce que j'ai fait, elle sort toujours, même si elle est restée 3 secondes...
	        bouton.getModel().setArmed(false);
	        bouton.getModel().setPressed(false);      
	        break;		
		}
	}
 
}