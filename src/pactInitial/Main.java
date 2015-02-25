package pactInitial;

import fenetres.Fenetre;
import fenetres.FenetreDepart;
import fenetres.JOptionPanePerso;
import audio.LecteurAudio;

public class Main {
	public static boolean firstTShirt = true, firstRobe = true, firstPull = true, firstPantalon = true;
	private static Fenetre curentFenetre;
	public static JOptionPanePerso curentJoptionPanePerso;

	public static void main(String[] args) {

		new LecteurAudio("welcome.wav");
		new FenetreDepart();
		
	}
	
	public static Fenetre getCurentFenetre(){return curentFenetre;}
	public static void setCurentFenetre(Fenetre fenetre){curentFenetre=fenetre;}
	public static JOptionPanePerso getJOptionPanePerso(){return curentJoptionPanePerso;}
	public static void setJOptionPanePerso(JOptionPanePerso jopp){curentJoptionPanePerso=jopp;}
	
	public static void actionEventAudio(int numeroAction){
		
		switch(numeroAction){
		case 0 : // commencer
			if(curentFenetre instanceof FenetreDepart)
				curentFenetre.actionBouton(0);
		
		}
	
	}
	
}