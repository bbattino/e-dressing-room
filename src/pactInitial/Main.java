package pactInitial;


public class Main {

	public static void main(String[] args) {
		
		java.net.URL urlSon = Main.class.getResource("superMario.wav");
		java.applet.AudioClip son = java.applet.Applet.newAudioClip(urlSon);
		son.play();
		new FenetreDepart();

	}

}
