package pactInitial;

public interface ICalibration {
	
	public float[] getFloatSeparated(String string);
	public void calculateCoeff();
	public void waitForHand();
	public String lectureFichier();
}
