package mFCC_DTW;
import java.util.ArrayList;


public class Mot
{
	private final ArrayList<MFCC> coeffs = new ArrayList<MFCC>();
	private int length;
	
	public Mot(String filename)
	{
		Sound son = new Sound(filename);
		ArrayList<Trame> trames;
		
		son.getSamples();
		trames = son.fenetrer();
		for(int i = 0; i < trames.size(); i++)
			coeffs.add(trames.get(i).calculateMFCC());
	}
	
	public MFCC getCoeff(int i)
	{
		return coeffs.get(i);
	}
	
	public int length()
	{
		return length;
	}
	
	public double calculDistanceMot(Mot m2)
	{
		int l1 = this.length();
		int l2 = m2.length();
		
		double[][] Dloc = new double[l2][l1];
		double[][] Dcum = new double[l2][l1];
		
		for(int i = 0; i < l2; i++)
			for(int j = 0; j < l1; j++)
				Dloc[i][j] = this.getCoeff(i).getDistance(m2.getCoeff(j)); //Dloc calculée
		
		Dcum[0][0] = Dloc[0][0];
		
		for (int i = 1; i < l2; i++)
			Dcum[i][0] = Dcum[i-1][0] + Dloc[i][0];
		
		for (int j = 1; j < l1; j++)
			Dcum[0][j] = Dcum[0][j-1] + Dloc[0][j];
		
		//Dcum initialisée
		
		for (int j = 1; j < l1; j++)
			for (int i = 1; i < l2; i++)
			{
				double min = Double.POSITIVE_INFINITY;
				if (Dcum[i-1][j] + Dloc[i][j] < min)
					min = Dcum[i-1][j] + Dloc[i][j];
				
				if (Dcum[i][j-1] + Dloc[i][j] < min)
					min = Dcum[i][j-1] + Dloc[i][j];
				
				if (Dcum[i-1][j-1] + 2*Dloc[i][j] < min)
					min = Dcum[i-1][j-1] + 2*Dloc[i][j];
				
				//minimum calculé
				
				Dcum[i][j] = min;
			}
		
		return Dcum[l2][l1]/(l1+l2-2);
	}
}
