package fenetres;

import javax.swing.JFrame;

import java.awt.BorderLayout;
//import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pactInitial.Main;


public class FenetreDepart extends Fenetre implements ActionListener{

		private static final long serialVersionUID = 1L;
		private JButton commencerButton = new JButton("Commencer"),
				quitterButton = new JButton("Quitter"),
				creditsButton = new JButton("A propos");
		private JPanel bouttons = new JPanel(),
						checkboxes = new JPanel();
		
		/*private Checkbox handListenerBox= new Checkbox("HandListener", false),
						talBox			= new Checkbox("TAL", false),
						*///audioBox		= new Checkbox("Audio",false);

		public FenetreDepart() {
			Main.setCurentFenetre(this);
			commencerButton.setPreferredSize(new Dimension(300,100));
			commencerButton.setFont(new Font("Arial",Font.PLAIN,40));
			quitterButton.setPreferredSize(new Dimension(300,100));
			quitterButton.setFont(new Font("Arial",Font.PLAIN,40));
			creditsButton.setPreferredSize(new Dimension(300,100));
			creditsButton.setFont(new Font("Arial",Font.PLAIN,40));
			setUndecorated(true);
			setVisible(true); // affichage
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JLabel image = new JLabel( new ImageIcon("data/bienvenue.png"));
			//File imageFile = new File("data/bienvenue.png");
			

			// Utilisation de BorderLayout
			Container contenu = getContentPane();
			contenu.setLayout(new BorderLayout());
			contenu.setBackground(new Color(255, 255, 255));

			//Ajouts des bouttons sur buttons
			
			bouttons.add(commencerButton);
			bouttons.add(creditsButton);
			bouttons.add(quitterButton);
			bouttons.add(Main.getIndicateurVocal());
			bouttons.setBackground(new Color(255,255,255));
			
			/*checkboxes.add(handListenerBox);
			checkboxes.add(talBox);
			checkboxes.add(audioBox);
			checkboxes.setBackground(new Color(255,255,255));*/


			//Ajouts sur le ContentPane
			contenu.add(image, BorderLayout.CENTER);
			contenu.add(bouttons,BorderLayout.PAGE_END);
			contenu.add(checkboxes,BorderLayout.LINE_END);

			//Ajout des Listeners
			commencerButton.addActionListener(this);
			creditsButton.addActionListener(this);
			quitterButton.addActionListener(this);


			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			addHandListener(commencerButton);
			addHandListener(creditsButton);
			addHandListener(quitterButton);
			
		}
		
		public void commencer(){
			
			new FenetreIdentification();
			dispose();
			//Main.setBooleans(handListenerBox.getState(), talBox.getState(), audioBox.getState());
			//Main.runWithBoolean();
		}
		
		public void credits(){
			new FenetreCredit(this);
			setVisible(false);
		}
		
		@Override
		public void actionBouton(int numeroBouton){
			switch(numeroBouton){
			case 0 : commencerButton.doClick();
			break;
			case 1 : creditsButton.doClick();
			break;
			}
			
		}

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==commencerButton) 		commencer();
			else if(e.getSource()==creditsButton)	credits();
			else if(e.getSource()==quitterButton)	{Main.setQuitterAffichage3D(true);System.exit(0);}
			}

		public static void main(String[] args) throws InterruptedException{
			/*FenetreDepart t =new FenetreDepart();
			int x=0;
			int y=0;
			while(true){
				Point p = t.getMousePosition();
				if(p!=null){
				if(p.x>x) {x=p.x;System.out.println("x="+x);}
				if(p.y>y) {y=p.y;System.out.println("y="+y);}}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}*/
			
			new FenetreDepart();
			Thread.sleep(3000);
			Main.setIndicateurocalBoolean(false);
			System.out.println("false");
			Thread.sleep(2000);
			Main.setIndicateurocalBoolean(true);
		}

		@Override
		public void refreshIndicateurVocal() {
			this.bouttons.remove(Main.getIndicateurVocal());
			Main.refreshIndicateur();
			this.bouttons.add(Main.getIndicateurVocal());
			this.setSize(1364,799);
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			
		}
		
	}


