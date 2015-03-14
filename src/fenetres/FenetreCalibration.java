package fenetres;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFrame;

import pactInitial.ICalibration;

public class FenetreCalibration extends JFrame implements ICalibration{

	private static final long serialVersionUID = 1L;
	private float xHaut,yHaut,xBas,yBas;
	private String path;


	@Override
	public float[] getFloatSeparated(String string) {
		
		
		
		return null;
	}

	@Override
	public void calculateCoeff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitForHand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String lectureFichier() {
		String coordonates="";
		try {
			InputStream ips = new FileInputStream(path);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			coordonates = br.readLine();
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}	
		return coordonates;
	}

}
