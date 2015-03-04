package tAL;

import java.util.ArrayList;

import pactInitial.Main;


public class MainTAL {
	
	public static void main(String[] args) 
	{	
		// acquérir le point WAV
		//l'envoyer à l'API google
		//on recoit une phrase que l'on nomme parole
		
	String parole="je voudrais commencer";
	
	
	int[] T = new int[MethodeDeBase.N]; 
	// T est le tableau d'analyse du mot qui contiendra les nombres d'occurences des mots de la phrase a analyser

	
	ArrayList<String> commande = MethodeDeBase.creerCommande(parole); 
	/** commande est la arraylist de mots de la phrase a analyser*/
	
	T = MethodeDeBase.TableauAnalyse(commande);
	
	int indiceCommande = MethodeDeBase.tableauLePlusProche(T);
	
	int indiceAction = MethodeDeBase.correspondanceClasseAction(indiceCommande);
		//System.out.print(indiceAction);
	Main.actionEventAudio(indiceAction);

	}

	
}
