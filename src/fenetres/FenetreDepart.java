package fenetres;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.logging.*;


public class FenetreDepart extends JFrame implements ActionListener{



		private static final long serialVersionUID = 1L;
		private JButton commencerButton = new JButton("Commencer");
		private JButton quitterButton = new JButton("Quitter");
		private JButton creditsButton = new JButton("A propos");
		private JPanel bouttons = new JPanel();
		private static Logger logger;

		public FenetreDepart() throws InterruptedException {
			logger = Logger.getLogger("com.foo.FenetreDepart");

			setUndecorated(true);
	        logger.info("Initialisation de la fen�tre de d�part");
			setVisible(true); // affichage
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ImageIcon imageIcone = new ImageIcon("data/bienvenue.png");
			JLabel image = new JLabel(imageIcone);
			System.out.println(image.getSize());
			System.out.println(image.getHeight());
			System.out.println(imageIcone.getImageObserver());
			

			// Utilisation de BorderLayout
			Container contenu = getContentPane();
			contenu.setLayout(new BorderLayout());
			contenu.setBackground(new Color(255, 255, 255));

			
			//Ajouts des bouttons sur buttons
			
			bouttons.add(commencerButton);
			bouttons.add(creditsButton);
			bouttons.add(quitterButton);
			bouttons.setBackground(new Color(255,255,255));
			logger.info("Ajout des boutons");

			//Ajouts sur le ContentPane
			contenu.add(image, BorderLayout.CENTER);
			contenu.add(bouttons,BorderLayout.PAGE_END);

			//Ajout des Listeners
			commencerButton.addActionListener(this);
			creditsButton.addActionListener(this);
			quitterButton.addActionListener(this);
			logger.info("Ajout des actionListeners pour les boutons");

			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			Thread.sleep(150);
			if(image.getHeight()==0)
				logger.warning("l'image n'a pas �t� charg�e");
			else
				logger.info("l'image a �t� charg�e");
			
			

		}
		
		public void commencer(){
			
			new FenetreIdentification();
			dispose();
		}
		
		public void credits(){
			System.out.println("cr�dits");

		}

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==commencerButton)
				logger.info("Clique sur Commencer");
				commencer();
			if(e.getSource()==creditsButton)
				credits();
			if(e.getSource()==quitterButton)
				System.exit(0);
			
		}

	}


