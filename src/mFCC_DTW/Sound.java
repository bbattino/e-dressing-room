package mFCC_DTW;

import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.*;

/*import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;

import javax.management.openmbean.ArrayType;
import javax.sound.sampled.spi.FormatConversionProvider;*/


public class Sound 
{
	private byte[] samples;
	private AudioFormat format;
	private static AudioInputStream stream;
	private static float fe;
	private   ArrayList<Trame> listeTrame = new ArrayList<Trame>();

	public Sound(String filename)
	{
		try {
			stream = AudioSystem.getAudioInputStream(new File(filename));
			format = stream.getFormat();
		//	samples = getSamples();
			fe = format.getSampleRate();
			samples=getSamples();
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		listeTrame=creationDeTrames();
	}
	
	private ArrayList<Trame> creationDeTrames() {
		System.out.println("creation");
		long indice=0;
		System.out.println(-indice+stream.getFrameLength()+" "+0.02*fe);
		System.out.println(samples.length);
		while(-indice+stream.getFrameLength()>0.02*fe){
			byte[] tab=new byte[(int) (0.02*fe)];
			
			for (int i=0;i<tab.length;i++)
				tab[i]=samples[(int) (indice+i)];
			listeTrame.add(new Trame(tab));
			indice+= (int) (0.02*fe);
		}
		return null;
	}
	
	public  ArrayList<Trame> getListTrame(){return listeTrame;}
	public static float getfe()
	{
		return fe;
	}
	
	public byte[] getSamples() //on récupère les échantillons
	{
		int length = (int) (stream.getFrameLength() * format.getFrameSize());
		byte[] samples = new byte[length];
		DataInputStream in = new DataInputStream(stream);
		
		try {
			if(samples!=null)  for (int i=0;i<samples.length;i++) if(samples[i]!=0)System.out.print(samples[i]+" "+i);
			System.out.println("");
			in.readFully(samples);
			
		} catch (IOException e) {
			if(samples!=null) for (int i=0;i<samples.length;i++) if(samples[i]!=0)System.out.print(samples[i]+" "+i);

			e.printStackTrace();
		}
		
		return samples;
	}
	
	public void fenetrer() 
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
		listeTrame=trames;
	}

	public static int getFrameLength() {
		return (int)stream.getFrameLength();
	
	}
}
