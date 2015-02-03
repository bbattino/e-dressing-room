package event;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.JButton;

public class ProducteurEvent implements IGestionEvenement{
	
	private ArrayList<JButton> boutons;
	private TimerTask timertask = new TimerTask() {
		
		@Override
		public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("3 secondes écoulées");
			
		}
	};

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
		this.timertask.run();
	}

	public void stopTimer() {
		this.timertask.cancel();
	}

	public void action() {
		// TODO Auto-generated method stub
		
	}

}
