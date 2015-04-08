package mfccBis;

import java.util.ArrayList;


public class Trame
{
	private byte[] trame;
	private MFCC c = new MFCC();
	private int longueur;  
	private int coeff1 = 1000, coeff2 = 700;
	private int nbFiltres = 26;
	private int nbFreq = nbFiltres + 2;
	private int fe = 16000;
	private int tailleDerivee = 2;
	
	public Trame(byte[] tab)
	{
		trame = tab;
		longueur=trame.length;
		longueur= (int) Math.pow(2,(int) (Math.log(longueur)/0.6931));
	}
	
	public void calculateMFCC(int index, ArrayList<Trame> trames)
	{
		//on crée un tableau de complexes, copie de trame
		//on pourrait aussi le faire dans le constructeur et modifier l'attribut mais moins clair
		Complex[] signal = new Complex[longueur];
		for(int i=0; i<longueur;i++)
			signal[i] = new Complex((double)trame[i], 0);
		
		//application de la dft par l'algorithme fft
		Complex[] transf = FFT.fft(signal);
		
		//on récupère les puissances
		double[] pow = new double[longueur];
		for(int j = 0; j<longueur; j++)
			pow[j] = transf[j].moduleCarre()/longueur;
		
		//application du banc de filtres mel entre 300 et 8000Hz
		double basse = toMel(300);
		double haute = toMel(8000);
		double[] frequences = new double[nbFreq];
		
		frequences[0] = basse; //fréquences en mel
		frequences[nbFreq-1] = haute;
		for(int k = 1; k < nbFreq - 1; k++)
			frequences[k] = basse + k*(haute-basse)/nbFreq; //on espace régulièrement en échelle mel
			
		for(int k = 0; k<nbFreq;k++) //on les convertit en Hz
			frequences[k] = toHz(frequences[k]);
		
		for(int k = 0; k<nbFreq;k++)
			frequences[k] = (int)Math.floor(513*frequences[k]/fe); //formule vue sur internet
		
		//on crée les filtres triangulaires. Ceux-ci se recoupent de moitié en échelle mel.
		double[][] filtres = new double[nbFiltres][257];
		for(int n = 0; n < nbFiltres-2; n++)
			for(int m = 0; m<257; m++)
			{
				if(m<frequences[n] || m>frequences[n+2])
					filtres[n][m] = 0;
		
				else if(m>frequences[n] && m<frequences[n+1])
					filtres[n][m] = (m-frequences[n])/(frequences[n+1]-frequences[n+1]);
		
				else
					filtres[n][m] = (frequences[n+2] - m)/(frequences[n+2]-frequences[n+1]);
			}
		
		//calcul des 26 coefficients correspondant chacun à un filtre
		double[] coeffs = new double[nbFiltres];
		double somme;
		for(int i = 0; i<26; i++)
		{
			somme = 0;
			for(int j=0; j<257;j++)
				somme += pow[j]*filtres[i][j];
			
			coeffs[i] = somme;
		}
		
		//on prend le logarithme
		for(int j=0; j<nbFiltres; j++)
			coeffs[j] = Math.log(coeffs[j]); //on pourrait tester avec une autre base de log
		
		//application de la DCT à cette suite de coefficients, ce qui nous donnera les MFCCs
		double[] tab = FFT.dct(coeffs);
		for(int i=0; i<13;i++)
			c.setCoord(i, tab[i]);
		
		//calcul des dérivées première et seconde
		double[] derivee1 = c.derive(tailleDerivee, trames, index);
		MFCC derivee1m = new MFCC(derivee1);
		double[] derivee2 = derivee1m.derive(tailleDerivee, trames, index);
		
		for(int n=0; n<13; n++)
		{
			c.setCoord(n+13, derivee1[n]);
			c.setCoord(n+26, derivee2[n]);
		}
		
		//fini !
	}
	
	public MFCC getMFCC()
	{
		return c;
	}
	
	public double toMel(double f)
	{
		return coeff1*Math.log(1+f/coeff2);
	}
	
	public double toHz(double m)
	{
		return coeff2*(Math.exp(m/coeff1)-1);
	}
}
