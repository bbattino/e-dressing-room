package event;

import java.awt.Point;
import javax.swing.JButton;


public class TrucKinect {
	protected JButton bouton;
	
	public TrucKinect(JButton bouton){this.bouton=bouton;}

	public Point getPosition() {
		return bouton.getMousePosition();
		
	}

}
