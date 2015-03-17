package mFCC_DTW;

public class MFCC
{
	
	public Complex[] c = new Complex[39];
	
	public MFCC(int samplePerFrame, float samplingRate, int numCepstra) {}
	
	
	public Complex getCoord(int i)
	{
		return c[i];
	}
	
	public void setCoord(int i, Complex valeur)
	{
		c[i] = valeur;
	}
	
	public double getDistance(MFCC c1)
	{
		double distance = 0;
		
		for (int i = 0; i < 39; i++)
			distance += this.getCoord(i).distancecar(c1.getCoord(i));
			
		return distance;	
	}
}
