package lCTAudio;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import java.util.ArrayList;
import java.util.List;

public class WaveData 
{
	private final int	EXTERNAL_BUFFER_SIZE = 128000;
	
	public WaveData(){}
	
	public List<Integer> getData()
	{
		ArrayList<Integer> Data = new ArrayList<Integer>();
		String	strFilename = "APP01.WAV";
		File	soundFile = new File(strFilename);
		
		  //We have to read in the sound file.
		AudioInputStream	audioInputStream = null;
		try
		{
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		AudioFormat	audioFormat = audioInputStream.getFormat();

		SourceDataLine	line = null;
		DataLine.Info	info = new DataLine.Info(SourceDataLine.class,
												 audioFormat);
		try
		{
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
		}
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		line.start();

		int	nBytesRead = 0;
		byte[]	abData = new byte[EXTERNAL_BUFFER_SIZE];
		byte[] rno = new byte [4];
		while (nBytesRead != -1)
		{
			try
			{
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				for(int i =0 ; i<abData.length ; i++)
				{
					if(i<40)
					{
						System.out.println(abData[i]);
						Data.add((int)abData[i]);
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			if (nBytesRead >= 0)
			{
				int	nBytesWritten = line.write(abData, 0, nBytesRead);
			}
		}

		line.drain();
		line.close();
		
		return Data;
	}
	
}
