package fenetres;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import audio.LecteurAudio;

public class FenetreAffichage extends JFrame implements ActionListener{



		private static final long serialVersionUID = 1L;
		private JButton retourButton = new JButton("Retour");
		private JButton quitterButton = new JButton("Quitter");
		private JButton panierButton = new JButton("Ajouter au Panier");
		private ArrayList<JButton> listeBoutons = new ArrayList<JButton>();
		private JPanel bouttons = new JPanel();
		private Container contenu = getContentPane();


		public FenetreAffichage(String imagePath) {
			setUndecorated(true);
			setVisible(true); // affichage
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JLabel image = new JLabel(new ImageIcon(imagePath));


			// Utilisation de BorderLayout
			contenu.setLayout(new BorderLayout());
			contenu.setBackground(new Color(255, 255, 255));

			
			//Ajouts des bouttons sur buttons
			
			bouttons.add(retourButton);
			bouttons.add(quitterButton);
			bouttons.add(panierButton);
			bouttons.setBackground(new Color(255,255,255));
			
			listeBoutons.add(retourButton);
			listeBoutons.add(quitterButton);
			listeBoutons.add(panierButton);
			
			new LecteurAudio("SolutionSon.wav");
			
			//Ajouts sur le ContentPane
			contenu.add(image, BorderLayout.CENTER);
			contenu.add(bouttons,BorderLayout.PAGE_END);

			//Ajout des Listeners
			for(JButton bouton : listeBoutons)
				bouton.addActionListener(this);
			
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			
		}
		
		public void retour(){
			
			dispose();
			new FenetreChoixVetement();
		}
		public void panier(){
			
		}
		
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==quitterButton)
				System.exit(0);
			if(e.getSource()==retourButton)
				retour();
			if(e.getSource()==panierButton)
				panier();
			
		}

	}

