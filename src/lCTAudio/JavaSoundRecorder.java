package lCTAudio;

import javax.sound.sampled.*;

import pactInitial.Main;
import mFCC_DTW.Mot;
import rVS.Dictionary;

import java.io.*;
 
/**
 * A sample program is to demonstrate how to record sound in Java
 * author: www.codejava.net
 */
public class JavaSoundRecorder {
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
     TargetDataLine line;
     File wavFile;
     private static int i=0;

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
    			try {	Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
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
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing
 
            AudioSystem.write(new AudioInputStream(line), fileType, wavFile);
 
        } catch (LineUnavailableException ex) {ex.printStackTrace();
        } catch (IOException ioe) { ioe.printStackTrace();}
    }
 
    void finish() 
    {        
        line.stop();
        line.close();
        System.out.println("lctdata/test"+i+".wav");
        /****import DU main du module RVS***/
        if(Main.mfccActivated){
		Mot motUtilisateur = new Mot("lctdata/test"+i+".wav");
		
		// Initialise le dictionnaire
		Dictionary dictionary = new Dictionary();
		
		String result = dictionary.compareAll(motUtilisateur);
		int resInterface = dictionary.convertToInterface(result);
		Main.actionEventAudio(resInterface);
        }
    }
    public static void main(String[] args){
    	new JavaSoundRecorder();
    }
}
