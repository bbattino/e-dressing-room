package pactInitial;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreDepart extends JFrame implements ActionListener{



		private static final long serialVersionUID = 1L;
		private JButton commencerButton = new JButton("Commencer");
		private JButton creditsButton = new JButton("A propos");
		private JPanel bouttons = new JPanel();

		public FenetreDepart() {
			setTitle("Bienvenue"); // Titre
			setVisible(true); // affichage
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// Utilisation de BorderLayout
			Container contenu = getContentPane();
			contenu.setLayout(new BorderLayout());
			contenu.setBackground(new Color(255, 255, 255));

			JLabel image = new JLabel(new ImageIcon("data/bienvenue.png"));
			
			//Ajouts des bouttons sur buttons
			
			bouttons.add(commencerButton);
			bouttons.add(creditsButton);

			//Ajouts sur le ContentPane
			contenu.add(image, BorderLayout.CENTER);
			contenu.add(bouttons,BorderLayout.PAGE_END);

			this.pack();
		}

		public void actionPerformed(ActionEvent e) {
			
		}

	}


