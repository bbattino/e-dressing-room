package mFCC_DTW;
import MFCC;


public class Trame
{
	private byte[] trame;
	private MFCC c;
	
	public Trame(byte[] tab)
	{
		trame = tab;
	}
	
	public MFCC calculateMFCC(){}
	
	public MFCC getMFCC()
	{
		return c;
	}
}
