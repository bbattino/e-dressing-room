package pactInitial;

import mFCC_DTW.Mot;
import rVS.Dictionary;

public class TestMFCCDTW {
	
	public static void main(String[] args){
		Mot motUtilisateur = new Mot("lctdata/0a.wav");
		System.out.println("Mot fini");
		
		// Initialise le dictionnaire
		Dictionary dictionary = new Dictionary();
		
		/*String result = Main.getdictionary().compareAll(motUtilisateur);
		int resInterface = Main.getdictionary().convertToInterface(result);
		Main.actionEventAudio(resInterface);*/
		
		//Main.actionEventAudio(dictionary.convertToInterface(dictionary.compareAll(motUtilisateur)));
		System.out.println(dictionary.convertToInterface(dictionary.compareAll(motUtilisateur)));
		
        }
	}


