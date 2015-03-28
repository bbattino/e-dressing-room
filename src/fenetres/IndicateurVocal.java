package fenetres;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IndicateurVocal extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel recordingImage = new JLabel( new ImageIcon("data/capturing.png"));
	private JLabel analysingingImage = new JLabel( new ImageIcon("data/analysing.png"));
	private boolean recording;


	
	public IndicateurVocal(boolean recording){
		super();
		this.recording=recording;
		setPreferredSize(new Dimension(50,50));
		if (this.recording) this.add(recordingImage);
		else this.add(analysingingImage);
		
	}
	public void setState(boolean recording){
		this.recording = recording; 
		this.removeAll();
		if (this.recording) this.add(recordingImage);
		else this.add(analysingingImage);
		
	}
	

	public static void main(String[] args) throws InterruptedException {
		JFrame f = new JFrame();
		f.setVisible(true);
		IndicateurVocal iv = new IndicateurVocal(true);
		f.add(iv);
		f.setSize(500,500);
		Thread.sleep(2000);
		System.out.print("o");
		f.remove(iv);
		iv.setState(false);
		f.add(iv);
		f.setSize(501,501);
		System.out.print("o");
		Thread.sleep(2000);
		System.out.print("o");
		f.remove(iv);
		iv.setState(true);
		f.add(iv);
		f.setSize(500,500);

		
	}
	

}
