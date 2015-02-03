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

	public JButton mainSurBoutton() {
		
		for (JButton bouton : boutons)
			if(mainSurBoutton(bouton)!=null)
				return bouton;
			
		return null;
	}
	
	public JButton mainSurBoutton(JButton bouton){
		Dimension tailleBouton =bouton.getSize();
		Point positionBouton = bouton.getLocationOnScreen();
		Rectangle boutonRectangle = new Rectangle(positionBouton,tailleBouton);
		
		
		return (boutonRectangle.contains(getPositionMain()))? bouton:null;
		
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
