package event;

import java.awt.Point;

import javax.swing.JButton;


public class TrucKinect {
	protected MainDansLaZoneTest button;
	protected JButton bouton;
	
	public TrucKinect(MainDansLaZoneTest button){this.button=button;}
	public TrucKinect(JButton bouton){this.bouton=bouton;}

	public Point getPosition() {
		return bouton.getMousePosition();
		
	}

}
