package pactInitial;

public interface ICalibration {
	
	public float[] getFloatSeparated(String string);
	public String calculateCoeff();
	public void waitForHand();
	public String lectureFichier();
}
