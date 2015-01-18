package pactInitial;

import fenetres.FenetreDepart;
import audio.LecteurAudio;


public class Main {

	public static void main(String[] args) {
		
		new LecteurAudio("bienvenue.wav");
		new FenetreDepart();

	}

}
