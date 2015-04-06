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
        int sampleSizeInBits = 16, channels = 1;// avant channels 2 (stéréo)
        boolean signed = true, bigEndian = true;
        return new AudioFormat(sampleRate, sampleSizeInBits,
                                             channels, signed, bigEndian);
       
    }
    
  /*  public JavaSoundRecorder(){ // ajouté lors de l'intégration
    	
         wavFile = new File("lctdata/test"+i+".wav");

    	
    	Thread t=new Thread(){@Override public void run(){
    		try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
    		finish();}
    	};
    
		t.start();
    	this.start();
    	//finish();
    	i++;
    	new JavaSoundRecorder();
    }*/
    
    public JavaSoundRecorder(){ // ajouté lors de l'intégration
        wavFile = new File("data/commandeVocale.wav");
        Thread t=new Thread(){@Override public void run(){
    		try {Thread.sleep(6000);} catch (InterruptedException e) {e.printStackTrace();}
    		finish();}
    	};
    
		t.start();
		this.start();
	
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
            System.out.print("enregistrement...");
           // Main.setIndicateurocalBoolean(true);
            Main.setIndicateurocalState((char) 0);
            AudioSystem.write(new AudioInputStream(line), fileType, wavFile);
 
        } catch (LineUnavailableException ex) {ex.printStackTrace();
        } catch (IOException ioe) { ioe.printStackTrace();}
    }
 
    void finish() 
    {   
    	int indiceAction;
    	String parole = null;
        line.stop();
        line.close();
        System.out.println("   fin de l'enregistrement");
       // Main.setIndicateurocalBoolean(false);
        Main.setIndicateurocalState((char) 1);
        /****import DU main du module RVS***/
        if(Main.apiActivated){
			try {
				//parole = api.sendPost("lctdata/test"+i+".wav");
				parole = api.sendPostFromLCT("data/commandeVocale.wav");
			} catch (Exception e) {
				e.printStackTrace();
				/*Main.setIndicateurocalState((char) 2);
				System.err.println(Main.getIndicateurocalState());
				try{Thread.sleep(2000);}catch(Exception ex){}
				new JavaSoundRecorder(); */	//* Permet de continuer la reco vocale
											//* si une requete n'a pas fonctioné
			}
			if(parole!=null){
		ArrayList<String> commande = MethodeDeBase.creerCommande(parole); 

	int[] T = MethodeDeBase.TableauAnalyse(commande);
	int indiceCommande = MethodeDeBase.tableauLePlusProche(T);
	indiceAction = MethodeDeBase.correspondanceClasseAction(indiceCommande);
			}
			else indiceAction=-2;
	System.out.println(indiceAction);
	Main.actionEventAudio(indiceAction);
	new JavaSoundRecorder();
	//Main.actionEventAudio(indiceAction);
        }

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
