package rVS;

import mFCC_DTW.Mot;


public class MainTest {

	public static void main(String[] args) {
		// Enregistre le son émis par l'utilisateur
		Mot motUtilisateur = new Mot("nomDuSon");
		
		// Initialise le dictionnaire
		Dictionary dictionary = new Dictionary();
		
		String result = dictionary.compareAll(motUtilisateur);
		int resInterface = dictionary.convertToInterface(result);
		System.out.println(resInterface);
	}

}
