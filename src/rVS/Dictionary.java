package rVS;

import java.util.Enumeration;
import java.util.Hashtable;

import mFCC_DTW.Mot;


public class Dictionary {
	private Hashtable<Mot, String> dataBase=new Hashtable<>(); // Notre base de données : les mots et leur chaine de caractères
	
	/* Constructeur */
	public Dictionary(){
		Hashtable<Mot, String> initialisation = new Hashtable<Mot, String>();
		initialisation.put(new Mot("lctdata/0b.wav"), "commencer");
		initialisation.put(new Mot("lctdata/0a.wav"), "commencer"); // Devra être répété pour chaque mot de la base de donnée, chaque enregistrement
		
		this.dataBase = initialisation;
	}
	
	/* Compare un enregistrement à tous les mots de la base de données */
	public String compareAll(Mot other){
		double ref = Float.POSITIVE_INFINITY; // Variable qui sert de reference pour calculer la distance minimale, on l'initialise la plus grande possible
		double distCurrent = 0;
		String result = ""; // Le futur résultat retourné
		
		/* Comparaison avec tous les mots de dataBase */
		Enumeration<Mot> enumMot = dataBase.keys();
		//System.out.println("distance test "+enumMot.hasMoreElements()+" taille"+enumMot.nextElement());

		while(enumMot.hasMoreElements()){
			Mot motCurr = enumMot.nextElement(); // On récupère le prochain mot
			distCurrent = motCurr.calculDistanceMot(other); // On calcule sa distance au mot voulu
			System.out.println("distance"+distCurrent);
			
			/* Mmet à jour distance afin d'obtenir la valeur minimale */
			if(distCurrent < ref){
				ref = distCurrent;
				result = getString(motCurr);
			}
		}
		
		return result;
	}
	
	/* Renvoie la chaine de caractère associé à un mot de la base de données */
	public String getString(Mot mot){
		String res = dataBase.get(mot);
		return res;
	}
	
	/* Permet d'initialiser la base de données */
	public void setString(Mot mot, String nom){
		dataBase.put(mot,  nom);
	}
	
	/* Convertit le résultat (string) en instruction pour l'interface graphique */
	public int convertToInterface(String name){
		int res;
		switch(name){
		case "commencer" :
			res = 0; break;
		case "retour" :
			res = 10; break;
		case "quitter" :
			res = 2; break;
		case "3" :
			res = 5; break;
		case "pull" :
			res = 13; break;
		case "suivant" :
			res = 17; break;
		case "essayer" :
			res = 20; break;
		case "ok" : 
			res = 22; break;
		default :
			res = -1; System.out.println("commande :"+name);break; // Insérer ici un code d'erreur
		}
		
		return res;
	}
}
