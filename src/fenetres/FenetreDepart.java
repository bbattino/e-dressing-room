package fenetres;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.logging.*;


public class FenetreDepart extends Fenetre implements ActionListener{



		private static final long serialVersionUID = 1L;
		private JButton commencerButton = new JButton("Commencer");
		private JButton quitterButton = new JButton("Quitter");
		private JButton creditsButton = new JButton("A propos");
		private JPanel bouttons = new JPanel();
		private static Logger logger;

		public FenetreDepart() {
			super();
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
			addHandListener(commencerButton,0);
			addHandListener(creditsButton,0);
			addHandListener(quitterButton,0);
			
			
			

		}
		
		public void commencer(){
			
			new FenetreIdentification();
			dispose();
		}
		
		public void credits(){
			new FenetreCredit(this);
			setVisible(false);
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

	}


