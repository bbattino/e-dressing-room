package mFCC_DTW;

public class Trame
{
	private byte[] trame;
	private MFCC c;
	
	public Trame(byte[] tab)
	{
		trame = tab;
	}
	
	public MFCC calculateMFCC(int indiceTrame,Sound son)
	{
		Complex[] mfccIntermediaire = new Complex[13]; 
		
		//MFCC mfcc = new MFCC(Sound.getFrameLength(),Sound.getfe(),13);
		
		//Calcul de la FFT de la trame
		
		//Complex S = new Complex(0, 0) ;
		Complex[] tab = new Complex[trame.length];
		for(int i=0;i<trame.length;i++){
			tab[i]=new Complex(trame[i],0);
		}
		Complex[] S = FFT.fft(tab);
						
		//Calcul des coefficients MFCC
		{
			
		for(int n = 1; n<14; n++)
			{	
				int N= Sound.getFrameLength();
			
				Complex[] logModuleS = new Complex[N];
				for(int j = 0; j < N; j++)
					logModuleS[j] = new Complex(Math.log(S[j].module()),0).fois(1.0/N);
				
				for(int i = 0; i < 13; i++)
				{
					mfccIntermediaire[i] = FFT.fftinverse(logModuleS)[i];
				
				    mfccIntermediaire[i] = new Complex((1.0+7.5*Math.sin(Math.PI*i/(13.0))),0).fois(mfccIntermediaire[i]);
				}
				//calcul des dérivées
				
				Complex nul = new Complex(0,0);
				Complex[] derivee = new Complex[13];
				for(int j = 0; j < 13; j++)
					derivee[j] = nul;
				
				for(int k = -3; k < 3; k++)
				{
					for(int i = 0; i < 13; i++)
						derivee[i] = derivee[i].plus(son.getListTrame().get(indiceTrame+k).getMFCC().getCoord(i).fois(k));
				}
				//la derivee devrait contenir la dérivée première
				
				Complex[] derivee2 = new Complex[13];
				for(int j = 0; j < 13; j++)
					derivee2[j] = nul;
				
				for(int k = -3; k < 3; k++)
				{
					for(int i = 0; i < 13; i++)
						derivee2[i] = derivee2[i].plus(son.getListTrame().get(indiceTrame+k).getMFCC().getCoord(i).fois(k));
				}
				
				
			//Construction du vecteur contenant les mfcc, leurs dérivées temporelles première et seconde
				
				for (int i=0; i<12; i++)
				{
					c.setCoord(i, mfccIntermediaire[i]);
				
					c.setCoord(i+13, derivee[i]);
				
					c.setCoord(i+26, derivee2[i]);
				}
				
			/*	for(int i=0; i<N; i++) 
				{
					Complex comp = new Complex(0, 0);
					c.setCoord(i, comp);
				    
					
				}	
			*/
			} 
		}
		return c;

		
		//Calcul des dérivées temporelles première et seconde de la trame
	}
	
	public MFCC getMFCC()
	{
		return c;
	}
}
