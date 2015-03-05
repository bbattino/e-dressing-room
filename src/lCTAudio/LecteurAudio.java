package lCTAudio;


public class LecteurAudio {
	
	public LecteurAudio(String TitreAudio){
		
		java.net.URL urlSon = LecteurAudio.class.getResource(TitreAudio);
		java.applet.AudioClip son = java.applet.Applet.newAudioClip(urlSon);
		son.play();
	}

}
