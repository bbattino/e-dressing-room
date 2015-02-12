package pactInitial;

import javax.swing.SwingUtilities;

import fenetres.FenetreDepart;
import audio.LecteurAudio;


public class Main {
	public static boolean firstTShirt=true, firstRobe=true, firstPull=true, firstPantalon=true;

	public static void main(String[] args) {
		
		Runnable run = new Runnable() {
			
			public void run() {
				new LecteurAudio("bienvenue.wav");
				new FenetreDepart();
				
			}
		};
		SwingUtilities.invokeLater(run);
		
		//new FenetreCatalogue(null);

	}
	
	
	

}
