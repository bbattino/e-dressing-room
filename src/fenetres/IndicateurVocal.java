package fenetres;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pactInitial.Main;

public class IndicateurVocal extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel recordingImage = new JLabel( new ImageIcon("data/capturing.png"));
	private JLabel analysingingImage = new JLabel( new ImageIcon("data/analysing.png"));
	private JLabel errorImage = new JLabel( new ImageIcon("data/false.png"));
	//private boolean recording;
	
	
	public IndicateurVocal(){
		super();
		//this.recording=recording;
		//System.out.println(Main.getIndicateurocalState());
		setPreferredSize(new Dimension(50,30));
		//if (this.recording) this.add(recordingImage);
		if (Main.getIndicateurocalState()==0) this.add(recordingImage);
		else if (Main.getIndicateurocalState()==1) this.add(analysingingImage);
		else this.add(errorImage);
		
	}
	public void setState(char state){
		Main.setIndicateurocalState(state); 
		this.removeAll();
		if (state==0) this.add(recordingImage);
		else if(state==1) this.add(analysingingImage);
		else this.add(errorImage);
		
	}
	

	public static void main(String[] args) throws InterruptedException {
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout());
		f.setVisible(true);
		IndicateurVocal iv = new IndicateurVocal();
		f.add(iv,BorderLayout.PAGE_END);
		f.setSize(500,500);
		Thread.sleep(2000);
		System.out.print("o");
		f.remove(iv);
		iv.setState((char) 1);
		f.add(iv,BorderLayout.PAGE_END);
		f.setSize(501,501);
		System.out.print("o");
		Thread.sleep(2000);
		System.out.print("o");
		f.remove(iv);
		iv.setState((char) 2);
		f.add(iv,BorderLayout.PAGE_END);
		f.setSize(500,500);

		
	}
	

}
