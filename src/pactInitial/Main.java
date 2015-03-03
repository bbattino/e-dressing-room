package pactInitial;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import fenetres.Fenetre;
import fenetres.FenetreAffichage;
import fenetres.FenetreAffichageSynthese;
import fenetres.FenetreCatalogue;
import fenetres.FenetreChoixVetement;
import fenetres.FenetreCredit;
import fenetres.FenetreDepart;
import fenetres.FenetreEntreeNom;
import fenetres.FenetreIdentification;
import fenetres.FenetreVisualisation;
import audio.LecteurAudio;

public class Main {
	public static boolean firstTShirt = true, firstRobe = true, firstPull = true, firstPantalon = true;
	public static boolean handListenerActivated = false;
	private static Fenetre curentFenetre;
	public static int UserNumber;
	private static String filePathOpenGL = "data/commSyntese3D.txt";

	public static void main(String[] args) {

		new LecteurAudio("welcome.wav");
		new FenetreDepart();
		
	}
	
	
	public static Fenetre getCurentFenetre(){return curentFenetre;}
	public static void setCurentFenetre(Fenetre fenetre){curentFenetre=fenetre;}
	
public static void modifierEtatFenetreOpenGL(int i){
		
		try {
			new FileWriter(new File(filePathOpenGL)).close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		FileWriter writer = null;
		try {
			writer = new FileWriter(filePathOpenGL, true);
			writer.write(""+i);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void actionEventAudio(int numeroAction){
		
		switch(numeroAction){
		case 10 : // bouton retour
			if(curentFenetre instanceof FenetreCredit 
					||curentFenetre instanceof FenetreCatalogue 
					||curentFenetre instanceof FenetreChoixVetement 
					||curentFenetre instanceof FenetreAffichageSynthese
					||curentFenetre instanceof FenetreAffichage				
					||curentFenetre instanceof FenetreVisualisation)
				curentFenetre.actionBouton(0);
			else if(curentFenetre instanceof FenetreEntreeNom) // redondance de "annuler"
				curentFenetre.actionBouton(29);
			break;
		case 22 : //ok
			if(curentFenetre instanceof FenetreAffichageSynthese
					||curentFenetre instanceof FenetreAffichage)
				curentFenetre.actionBouton(2);
			else if(curentFenetre instanceof FenetreChoixVetement)
				curentFenetre.actionBouton(5);
			else if(curentFenetre instanceof FenetreIdentification)
				curentFenetre.actionBouton(7);
			else if(curentFenetre instanceof FenetreVisualisation)
				curentFenetre.actionBouton(5);
			else if(curentFenetre instanceof FenetreEntreeNom) //redondance du "terminer"
				curentFenetre.actionBouton(28);
			break;
		case 19 : // "panier" voir panier
			if(curentFenetre instanceof FenetreChoixVetement)
				curentFenetre.actionBouton(3);
			else if(curentFenetre instanceof FenetreAffichageSynthese
					||curentFenetre instanceof FenetreAffichage)
				curentFenetre.actionBouton(1);
			break;
		case 17 : // suivant
			if(curentFenetre instanceof FenetreChoixVetement
					|| curentFenetre instanceof FenetreVisualisation)
				curentFenetre.actionBouton(1);
			break;
		case 18 : // precedent
			if(curentFenetre instanceof FenetreChoixVetement
					|| curentFenetre instanceof FenetreVisualisation)
				curentFenetre.actionBouton(2);
			break;
			
		case 20 : // essayer
			if(curentFenetre instanceof FenetreChoixVetement)
				curentFenetre.actionBouton(4);
			else if(curentFenetre instanceof FenetreVisualisation)
				curentFenetre.actionBouton(3);
			break;
		
			
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
		
		case 11 : // bouton TShirt
			if(curentFenetre instanceof FenetreCatalogue)
				curentFenetre.actionBouton(1);
			break;
		case 12 : // bouton Robe
			if(curentFenetre instanceof FenetreCatalogue)
				curentFenetre.actionBouton(2);
			break;
		case 13 : // bouton Pull
			if(curentFenetre instanceof FenetreCatalogue)
				curentFenetre.actionBouton(3);
			break;
		case 14 : // bouton pantalon
			if(curentFenetre instanceof FenetreCatalogue)
				curentFenetre.actionBouton(4);
			break;
		case 15 : // voire Panier
			if(curentFenetre instanceof FenetreCatalogue)
				curentFenetre.actionBouton(5);
			break;
		case 16 : // supprimer profil
			if(curentFenetre instanceof FenetreCatalogue)
				curentFenetre.actionBouton(6);
			break;
		
	
		case 21 : //"retirer du panier"
			if(curentFenetre instanceof FenetreVisualisation)
				curentFenetre.actionBouton(4);
			break;
			
			/*** action des joptionpane ***/
		
			
		case 23 : // oui
			if(curentFenetre instanceof FenetreCatalogue)
				curentFenetre.actionBouton(7);
			break;
		case 24 : //non
			if(curentFenetre instanceof FenetreCatalogue)
				curentFenetre.actionBouton(8);
			break;
			
			/**** lettres de l'aphabet *****/
			
		case 25 : //a
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(0);
			break;
		case 26 : //z
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(1);
			break;
		case 27 : //e
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(2);
			break;
		case 28 : //r
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(3);
			break;
		case 29 : //t
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(4);
			break;
		case 30 : //y
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(5);
			break;
		case 31 : //u
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(6);
			break;
		case 32 : //i
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(7);
			break;
		case 33 : //o
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(8);
			break;
		case 34 : //p
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(9);
			break;
		case 35 : //q
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(10);
			break;
		case 36 : //s
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(11);
			break;
		case 37 : //d
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(12);
			break;
		case 38 : //f
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(13);
			break;
		case 39 : //g
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(14);
			break;
		case 40 : //h
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(15);
			break;
		case 41 : //j
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(16);
			break;
		case 42 : //k
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(17);
			break;
		case 43 : //l
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(18);
			break;
		case 44 : //m
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(19);
			break;
		case 45 : //W
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(20);
			break;
		case 46 : //x
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(21);
			break;
		case 47 : //c
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(22);
			break;
		case 48 : //v
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(23);
			break;
		case 49 : //b
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(24);
			break;
		case 50 : // n
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(25);
			break;
		case 51 : // espace
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(26);
			break;
		case 52 : // efface
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(27);
			break;

		case 53 : // terminer
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(28);
			break;
		case 54 : // annuler
			if(curentFenetre instanceof FenetreEntreeNom)
				curentFenetre.actionBouton(29);
			break;
				

		}
	}
}