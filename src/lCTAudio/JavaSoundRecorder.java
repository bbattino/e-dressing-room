package lCTAudio;

import javax.sound.sampled.*;

import pactInitial.API;
import pactInitial.Main;
import mFCC_DTW.Mot;
import rVS.Dictionary;
import tAL.MethodeDeBase;

import java.io.*;
import java.util.ArrayList;
 
/**
 * A sample program is to demonstrate how to record sound in Java
 * author: www.codejava.net
 */
public class JavaSoundRecorder {
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
     TargetDataLine line;
     File wavFile;
     private static int i=0;
     private API api = new API();


    AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8, channels = 2;
        boolean signed = true, bigEndian = true;
        return new AudioFormat(sampleRate, sampleSizeInBits,
                                             channels, signed, bigEndian);
       
    }
    
    public JavaSoundRecorder(){ // ajouté lors de l'intégration
    	
         wavFile = new File("lctdata/test"+i+".wav");

    	
    	Thread t=new Thread(){
    		public void run(){
    			try {	Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
    			finish();}
    	};
    
		t.start();
    	this.start();
    	finish();
    	i++;
    	new JavaSoundRecorder();
    }
 
    
    void start() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
 
            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) { System.out.println("Line not supported"); System.exit(0);}
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing
            System.out.println("enregistrement n°"+i);
            AudioSystem.write(new AudioInputStream(line), fileType, wavFile);
 
        } catch (LineUnavailableException ex) {ex.printStackTrace();
        } catch (IOException ioe) { ioe.printStackTrace();}
    }
 
    void finish() 
    {   
    	String parole = null;
        line.stop();
        line.close();
        System.out.println("fin du n°"+i);
        /****import DU main du module RVS***/
        if(Main.apiActivated){
			try {
				parole = api.sendPost("lctdata/test"+i+".wav");
			} catch (Exception e) {
				e.printStackTrace();
			}
		ArrayList<String> commande = MethodeDeBase.creerCommande(parole); 

	int[] T = MethodeDeBase.TableauAnalyse(commande);
	int indiceCommande = MethodeDeBase.tableauLePlusProche(T);
	int indiceAction = MethodeDeBase.correspondanceClasseAction(indiceCommande);
	Main.actionEventAudio(indiceAction);}

        if(Main.mfccActivated){
		Mot motUtilisateur = new Mot("lctdata/test"+i+".wav");
		
		// Initialise le dictionnaire
		Dictionary dictionary = Main.getdictionary();
		
		/*String result = Main.getdictionary().compareAll(motUtilisateur);
		int resInterface = Main.getdictionary().convertToInterface(result);
		Main.actionEventAudio(resInterface);*/
		
		Main.actionEventAudio(dictionary.convertToInterface(dictionary.compareAll(motUtilisateur)));
        }
    }
    public static void main(String[] args){
    	new JavaSoundRecorder();
    }
}
