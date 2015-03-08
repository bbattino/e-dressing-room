import org.math.plot.*;
import java.io.*;

import javax.swing.JFrame;

public class ReadExample
{
	public static void main(String[] args)
	{
		double[] x = new double[100];
		double[] y = new double[100];
		
		try
		{
			// Open the wav file specified as the first argument
			WavFile wavFile = WavFile.openWavFile(new File("APP01.WAV"));

			// Display information about the wav file
			wavFile.display();

			// Get the number of audio channels in the wav file
			int numChannels = wavFile.getNumChannels();

			// Create a buffer of 100 frames
			double[] buffer = new double[100 * numChannels];

			int framesRead;
			int totalFramesRead=0;
			double min = Double.MAX_VALUE;
			double max = Double.MIN_VALUE;

			do
			{
				// Read frames into buffer
				framesRead = wavFile.readFrames(buffer, 100);
				totalFramesRead=totalFramesRead+framesRead;

				// Loop through frames and look for minimum and maximum value
				for (int s=0 ; s<framesRead * numChannels ; s++)
				{					
					if (buffer[s] > max) max = buffer[s];
					if (buffer[s] < min) min = buffer[s];
					if(totalFramesRead>100000 && totalFramesRead<100200)
					{
						System.out.println(buffer[s]);
						y[s]=buffer[s];
					}
					
				}
			}
			while (framesRead != 0);
			
			System.out.println(totalFramesRead);

			// Close the wavFile
			wavFile.close();

			// Output the minimum and maximum value
			System.out.printf("Min: %f, Max: %f\n", min, max);
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
		
		for(int i =0; i<100 ; i++)
		{
			x[i]=i;
		}
		
		 Plot2DPanel plot = new Plot2DPanel();
		 
		  // add a line plot to the PlotPanel
		  plot.addLinePlot("my plot", x, y);
		 
		  // put the PlotPanel in a JFrame, as a JPanel
		  JFrame frame = new JFrame("a plot panel");
		  frame.setContentPane(plot);
		  frame.setVisible(true);
	}
}

