package mfccBis;

import java.util.ArrayList;


public class MFCC
{
	private double[] c = new double[39];
	
	public MFCC() {}
	
	public MFCC(double[] tab)
	{
		int l =tab.length;
		for(int k=0; k<l; k++)
			this.setCoord(k, tab[k]);
	}
	
	public double getCoord(int i)
	{
		return c[i];
	}
	
	public void setCoord(int i, double valeur)
	{
		c[i] = valeur;
	}
	
	public double getDistance(MFCC c1)
	{
		double distance = 0;
		
		for (int i = 0; i < 39; i++)
			distance += (this.getCoord(i)-c1.getCoord(i))*(this.getCoord(i)-c1.getCoord(i));
			
		return distance;	
	}
	
	public double[] derive(int taille, ArrayList<Trame> trames, int index)
	{
		int somme1;
		int somme2;
		double[] derivee = new double[13];
		
		if(index<taille || index > trames.size() - taille)
		{
			for (int i =0;i<13;i++)
				derivee[i]=0;
			return derivee;
		}
		
		for(int n=0; n< 13; n++)
		{
			somme1 = 0;
			somme2 = 0;
		
			for(int i=1; i<taille; i++)
				somme1 += i*(trames.get(index+i).getMFCC().getCoord(n)-trames.get(index-i).getMFCC().getCoord(n));
			
			for(int j=1; j<taille; j++)
				somme2 += j*j;
			
			derivee[n] = somme1/(2*somme2);
		}
		
		return derivee;
	}
}
