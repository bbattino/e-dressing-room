package pactInitial;

import fenetres.FenetreDepart;
import audio.LecteurAudio;

public class Main {
	public static boolean firstTShirt = true, firstRobe = true, firstPull = true, firstPantalon = true;

	public static void main(String[] args) {

		new LecteurAudio("welcome.wav");
		new FenetreDepart();
		
	}
}