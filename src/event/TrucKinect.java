package event;
 
import java.awt.Point;
 
import javax.swing.JButton;
import javax.swing.SwingUtilities;
 
public class TrucKinect {
	protected final JButton bouton;
 
	public TrucKinect(JButton bouton) {
		this.bouton = bouton;
	}
 
	public Point getPosition() {
		Point point = bouton.getMousePosition();
		if (point!=null) {
			SwingUtilities.convertPointToScreen(point,
					bouton);
		}
		return point;
	}
 
}