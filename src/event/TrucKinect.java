package event;
 
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import pactInitial.Main;
import pactInitial.Send;
 
public class TrucKinect {
	protected final JButton bouton;
	protected static String fileHandPosition = "C:/Users/Utilisateur/Documents/Kinect Studio/Samples/ColorBasics-D2D - fonctionnel/HandPosition.txt";
	public TrucKinect(JButton bouton) {
		this.bouton = bouton;
	}
	
	/*public void requestHandPosition(){
		new Send("requestHand");
		String answer =Send.communicationString();
		System.out.println(answer);
		
		
	}*/
	/***********************************************************************************
	 * Retourne la position de la main non convertie par la Kinect pour utiliser des 
	 * coefficiants d'optimisation calculés par l'interface graphique
	 **********************************************************************************/
	 
	public Point getPositionNonConverted(){
		String s=this.lectureFichierMain(fileHandPosition);
		if(s==null)
			return null;
		else{
		float[] tab =this.decoupeNonConverted(s);
		
		return new Point((int) (Main.ax*tab[0]+Main.bx),(int) (Main.bx*tab[1]+Main.by));}
		
	}
	/******* Retourne la position de la main convertie en pixels par la Kinect
	 * Avec des coefficiants standards mais sensibles à la hauteur de la Kinect*****/
	public Point getPositionSimu() {
		/*Point point = bouton.getMousePosition();
		if (point!=null) {
			SwingUtilities.convertPointToScreen(point,
					bouton);
		}
		//return new Point(1360,800);
		return point;*/
		String s=this.lectureFichierMain(fileHandPosition);
		if(s==null)
			return null;
		else{
		int[] tab =this.decoupe(s);
		return new Point(tab[0],tab[1]);}
		
	}
	/******* Retourne la position de la souris********/
	public Point getPosition() {
		Point point = bouton.getMousePosition();
		if (point!=null) {
			SwingUtilities.convertPointToScreen(point,
					bouton);
		}
		return point;
	}
	
	public int[] decoupe(String s){
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
		int x= Integer.parseInt(xString);
		int y= Integer.parseInt(yString);
		int[] tab = {x,y};
		return tab;
		
	}
	public float[] decoupeNonConverted(String s){
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
	public String lectureFichierMain(String path){
		String coordonates = "";
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
		TrucKinect truc =new TrucKinect(null);
		String s=truc.lectureFichierMain(TrucKinect.fileHandPosition);
		truc.decoupe(s);
	}
 
}