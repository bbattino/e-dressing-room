package fenetres;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pactInitial.Main;
import pactInitial.TestPixel;

import java.util.logging.*;


public class FenetreDepart extends Fenetre implements ActionListener{

		private static final long serialVersionUID = 1L;
		private JButton commencerButton = new JButton("Commencer"),
				quitterButton = new JButton("Quitter"),
				creditsButton = new JButton("A propos");
		private JPanel bouttons = new JPanel();
		private static Logger logger;

		public FenetreDepart() {
			Main.setCurentFenetre(this);
			commencerButton.setPreferredSize(new Dimension(300,100));
			quitterButton.setPreferredSize(new Dimension(300,100));
			creditsButton.setPreferredSize(new Dimension(300,100));
			logger = Logger.getLogger("com.foo.FenetreDepart");
			logger.setLevel(Level.OFF);
			setUndecorated(true);
	        logger.info("Initialisation de la fenêtre de départ \n");
			setVisible(true); // affichage
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JLabel image = new JLabel( new ImageIcon("data/bienvenue.png"));
			File imageFile = new File("data/bienvenue.png");
			if(imageFile.exists())
				logger.info("l'image a été chargée \n");
			else
				logger.warning("image non chargée \n");
				

			// Utilisation de BorderLayout
			Container contenu = getContentPane();
			contenu.setLayout(new BorderLayout());
			contenu.setBackground(new Color(255, 255, 255));

			//Ajouts des bouttons sur buttons
			
			bouttons.add(commencerButton);
			bouttons.add(creditsButton);
			bouttons.add(quitterButton);
			bouttons.setBackground(new Color(255,255,255));
			logger.info("Ajout des boutons \n");

			//Ajouts sur le ContentPane
			contenu.add(image, BorderLayout.CENTER);
			contenu.add(bouttons,BorderLayout.PAGE_END);

			//Ajout des Listeners
			commencerButton.addActionListener(this);
			creditsButton.addActionListener(this);
			quitterButton.addActionListener(this);
			logger.info("Ajout des actionListeners pour les boutons \n");

			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			addHandListener(commencerButton);
			addHandListener(creditsButton);
			addHandListener(quitterButton);
			
		}
		
		public void commencer(){
			
			new FenetreIdentification();
			dispose();
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
			
			if(e.getSource()==commencerButton){
				logger.info("Clique sur Commencer \n");
				commencer();
			}
			else if(e.getSource()==creditsButton){
				logger.info("Clique sur crédit");
				credits();
			}
			else if(e.getSource()==quitterButton)
				System.exit(0);
			}

		public static void main(String[] args){
			FenetreDepart t =new FenetreDepart();
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
			}
		}
	}


