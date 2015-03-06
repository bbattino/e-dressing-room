package mFCC_DTW;
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
			samples = getSamples();
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
	
	public byte[] getSamples() //on récupère les échantillons
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
		int N = (int)Math.floor(fe*0.02); //taille d'une trame (~20 ms)
		int l = (int)stream.getFrameLength();
		int i = 0;
		int m = (int)format.getFrameSize();
		ArrayList<Trame> trames = new ArrayList<Trame>(); 
		
		while(i < l)
		{
			byte[] tabl = new byte[N];
			
			for(int j = 0; j < N; j++)
			{
				for(int k = 0; k < format.getFrameSize(); k++)
				
				
					tabl[k] = (byte) ((0.54 - 0.46*Math.cos(2*Math.PI*fe*j))*samples[i*(N/2)+j*m+k]); 
				
			}
			
			Trame trameCourante = new Trame(tabl);
			trames.add(trameCourante);
			
			i += Math.round(N/2) ;
		}
		
		return trames;	
	}

}
