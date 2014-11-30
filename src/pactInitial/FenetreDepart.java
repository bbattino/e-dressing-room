package pactInitial;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FenetreDepart extends JFrame implements ActionListener{



		private static final long serialVersionUID = 1L;

		public FenetreDepart() {
			setTitle("Bienvenue"); // Titre
			setVisible(true); // affichage
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// Utilisation de BorderLayout
			Container contenu = getContentPane();
			contenu.setLayout(new BorderLayout());
			contenu.setBackground(new Color(255, 255, 255));

			JLabel image = new JLabel(new ImageIcon("data/bienvenue.png"));

			contenu.add(image, BorderLayout.CENTER);

			this.pack();
		}

		public void actionPerformed(ActionEvent e) {
			
		}

	}


