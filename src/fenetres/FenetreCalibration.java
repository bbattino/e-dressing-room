package fenetres;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import audio.LecteurAudio;
import pactInitial.ICalibration;

public class FenetreCalibration extends JFrame implements ICalibration{

	private static final long serialVersionUID = 1L;
	private float xMax,yMax,xMin,yMin;
	private int xMaxEcran=1360 ,yMaxEcran=800;
	float ax,ay,bx,by;
	private String path = "data/handPosition.txt";


	public  FenetreCalibration() {
		//setUndecorated(true);
		setVisible(true); // affichage
		setSize(600,600);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JLabel image = new JLabel( new ImageIcon("data/bienvenue.png"));
		waitForHand();
	}
	@Override
	public float[] getFloatSeparated(String s) {
		
		
			String xString="";
			String yString="";
			String CurentWord = "";
			
			for (int i=0; i< s.length();i++){
				if(s.substring(i,i+1).equals(" ")){
					xString=CurentWord;
					yString=s.substring(1+i,s.length());
				
			}else{
				CurentWord+=s.substring(i,i+1);
			}
				
			}
			float x= Float.parseFloat(xString);
			float y= Float.parseFloat(yString);
			float[] tab = {x,y};
			return tab;
	}

	@Override
	public void calculateCoeff() {
		this.ax=(float) (-(xMaxEcran+0.0)/(xMax-xMin));
		this.bx=(float) -xMax*this.ax;
		this.ay=(float) (-(yMaxEcran+0.0)/(yMax-yMin));
		this.by=(float) -yMax*this.ay;
	}

	@Override
	public void waitForHand() {
		System.out.println("mu");
		new LecteurAudio(""); // mettez en haut gauche jusqu'au bip
		try {Thread.sleep(3000);} 
		catch (InterruptedException e) {e.printStackTrace();}
		float[] position = getFloatSeparated(lectureFichier());
		xMin=position[0];yMin=position[1];
		System.out.println(""+xMin+" "+yMin);
		
		new LecteurAudio("");//BIP
		System.out.println("fin bip");
		new LecteurAudio("");// mettez en bas droite jusqy'au bip
		try {Thread.sleep(10000);} 
		catch (InterruptedException e) {e.printStackTrace();}
		position = getFloatSeparated(lectureFichier());
		xMax=position[0];yMax=position[1];
		System.out.println(""+xMax+" "+yMax);
		
		calculateCoeff();
		System.out.println(""+ax+" "+ay+" "+bx+" "+by);
		
		
		
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
	
	public static void main(String[] args){
		new FenetreCalibration();
	}

}
