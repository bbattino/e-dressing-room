package mFCC_DTW;

import java.util.ArrayList;


public class Mot
{
	private final ArrayList<MFCC> coeffs = new ArrayList<MFCC>();
	private int length;
	private Sound son;
	
	public Mot(String filename)
	{
		Sound son = new Sound(filename);
		ArrayList<Trame> trames = new ArrayList<Trame>();
		son.getSamples();
		//son.fenetrer();

		trames = son.getListTrame();

		for(int i = 0; i < trames.size(); i++){System.out.println("avant");
			coeffs.add(trames.get(i).calculateMFCC(i,son));System.out.println((i+0.0)/trames.size());}
		length=coeffs.size();
		//System.out.println(trames.size());

	}
	public Sound getSon() {return this.son;}
	
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
		//System.out.println("l1= "+l1+" l2="+l2);
		double[][] Dloc = new double[l2][l1];
		double[][] Dcum = new double[l2][l1];
		
		for(int i = 0; i < l2; i++)
			for(int j = 0; j < l1; j++)
				Dloc[i][j] = this.getCoeff(i).getDistance(m2.getCoeff(j)); //Dloc calcul�e
		
		//System.out.println(Dloc[0][0]);
		Dcum[0][0] = Dloc[0][0];
		
		for (int i = 1; i < l2; i++)
			Dcum[i][0] = Dcum[i-1][0] + Dloc[i][0];
		
		for (int j = 1; j < l1; j++)
			Dcum[0][j] = Dcum[0][j-1] + Dloc[0][j];
		
		//Dcum initialis�e
		
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
				
				//minimum calcul�
				
				Dcum[i][j] = min;
			}
		
		return Dcum[l2][l1]/(l1+l2-2);
	}
}
