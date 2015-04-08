package mfccBis;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Date;

import javax.sound.sampled.*;
import javax.sound.sampled.spi.FormatConversionProvider;


public class Sound 
{
	private byte[] samples;
	private AudioFormat format;
	private AudioInputStream stream;
	private float fe;

	public Sound(String filename)
	{
		try {
			stream = AudioSystem.getAudioInputStream(new File(filename));
			format = stream.getFormat();
			samples = getSamples(stream);
			fe = format.getSampleRate();
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public float getfe()
	{
		return fe;
	}
	
	public byte[] getSamples(AudioInputStream stream) //on récupère les échantillons
	{
		int length = (int) (stream.getFrameLength() * format.getFrameSize());
		byte[] samples = new byte[length];
		DataInputStream in = new DataInputStream(stream);
		
		try {
			in.readFully(samples);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return samples;
	}
	
	public ArrayList<Trame> fenetrer()
	{
		int N = (int)Math.floor(fe*0.02); //nombre d'échantillon par fenêtre
		int L = (int)stream.getFrameLength()*format.getFrameSize(); //longueur totale sans 0-padding
		int l = L;
		ArrayList<Trame> trames = new ArrayList<Trame>();
		
		//on préfère avoir N pair
		if(N%2 != 0)
			N++;
		
		//on copie samples dans un tableau de la bonne longueur que l'on complète avec des 0
		while(l%N != 0)
		{
			l+=format.getFrameSize();
		}
		
		byte[] echantillons = new byte[l];
		for(int n = 0; n < l; n++)
			if(n<L)
				echantillons[n] = samples[n];
			else
				echantillons[n] = 0;
			
		
		//application de la fenêtre de Hamming, décalée de N/2 ) à chaque fois
		int nombreFenetres = l/N;
		int decalage = 0;
		byte[] trameCourante = new byte[N*format.getFrameSize()]; //on modifie toujours la même pour économiser de la mémoire
		for(int i = 0; i < nombreFenetres; i++)
		{
			for(int j = 0; j < N - format.getFrameSize(); j++)
			{
				for(int k = 0; k < format.getFrameSize(); k++)
					trameCourante[j + k] = (byte) ((0.54 - 0.46*Math.cos(2*Math.PI*fe*j))*echantillons[j+decalage+k]);
			}
			
			decalage += N/2;
			trames.add(new Trame(trameCourante));
		}
		
		return trames;
	}
	
	public AudioInputStream getStream()
	{
		return stream;
	}

}
