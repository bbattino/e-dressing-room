package event;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JButton;

public class ProducteurEvent implements IGestionEvenement{
	
	private ArrayList<JButton> boutons;

	public Point getPositionMain() {
		return null;
		
	}

	public boolean mainSurBoutton() {
		
		for (JButton bouton : boutons)
			mainSurBoutton(bouton);
			
		return false;
	}
	
	public boolean mainSurBoutton(JButton bouton){
		Dimension tailleBouton =bouton.getSize();
		Rectangle boutonRectangle = new Rectangle(new Point(),tailleBouton);
		
		return false;
		
	}

	public void launchTimer() {
		// TODO Auto-generated method stub
		
	}

	public void stopTimer() {
		// TODO Auto-generated method stub
		
	}

	public void action() {
		// TODO Auto-generated method stub
		
	}

}
