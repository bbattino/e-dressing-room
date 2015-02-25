package pactInitial;

import fenetres.Fenetre;
import fenetres.FenetreAffichageSynthese;
import fenetres.FenetreCatalogue;
import fenetres.FenetreChoixVetement;
import fenetres.FenetreCredit;
import fenetres.FenetreDepart;
import fenetres.FenetreIdentification;
import fenetres.FenetreVisualisation;
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
			break;
		case 1: // A propos
			if(curentFenetre instanceof FenetreDepart)
				curentFenetre.actionBouton(1);
			break;
		case 2: //quitter
			System.exit(0);
			break;
		
		case 3: //identification 1
			if(curentFenetre instanceof FenetreIdentification)
				curentFenetre.actionBouton(0);
			break;
		
		case 4: //identification 2
			if(curentFenetre instanceof FenetreIdentification)
				curentFenetre.actionBouton(1);
			break;
		case 5: //identification 3
			if(curentFenetre instanceof FenetreIdentification)
				curentFenetre.actionBouton(2);
			break;
		case 6: //identification 4
			if(curentFenetre instanceof FenetreIdentification)
				curentFenetre.actionBouton(3);
			break;
		case 7: //identification 5
			if(curentFenetre instanceof FenetreIdentification)
				curentFenetre.actionBouton(4);
			break;
		case 8: //identification 6
			if(curentFenetre instanceof FenetreIdentification)
				curentFenetre.actionBouton(5);
			break;
		case 9 : // nouveau Compte
			if(curentFenetre instanceof FenetreIdentification)
				curentFenetre.actionBouton(6);
			break;
		case 10 : // bouton retour
			if(curentFenetre instanceof FenetreCredit 
					||curentFenetre instanceof FenetreCatalogue 
					||curentFenetre instanceof FenetreChoixVetement 
					||curentFenetre instanceof FenetreAffichageSynthese
					||curentFenetre instanceof FenetreVisualisation)
				curentFenetre.actionBouton(0);
		}
	}
}