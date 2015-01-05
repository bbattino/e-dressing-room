package pactInitial;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreCatalogue extends JFrame implements ActionListener{



		private static final long serialVersionUID = 1L;
		private ArrayList<JButton> boutonListe = new ArrayList<JButton>();
		private JButton tShirtButton = new JButton("T Shirt");
		private JButton pullButton = new JButton("Pull");
		private JButton robeButton = new JButton("Robe");
		private JButton pantalonButton = new JButton("Pantalon");
		private JButton quitterButton= new JButton("Quitter");


		private JPanel bouttons = new JPanel();

		public FenetreCatalogue() {
			setTitle("Catalogue"); // Titre
			setVisible(true); // affichage
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JLabel image = new JLabel(new ImageIcon("data/bienvenue.png"));

			// Utilisation de BorderLayout
			Container contenu = getContentPane();
			contenu.setLayout(new BorderLayout());
			contenu.setBackground(new Color(255, 255, 255));

			
			//Ajouts des bouttons sur buttons
			
			bouttons.add(quitterButton);
			bouttons.setBackground(new Color(255,255,255));
			boutonListe.add(tShirtButton);
			boutonListe.add(pullButton);
			boutonListe.add(robeButton);
			boutonListe.add(pantalonButton);




			//Ajouts sur le ContentPane
			contenu.add(image, BorderLayout.PAGE_START);
			contenu.add(bouttons,BorderLayout.PAGE_END);

			//Ajout des Listeners
			commencerButton.addActionListener(this);
			creditsButton.addActionListener(this);

			
			this.pack();
		}
		
		public void commencer(){
			
			System.out.println("on commence");
			new FenetreIdentification();
		}
		
		public void credits(){
			System.out.println("crédits");

		}

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==commencerButton)
				commencer();
			if(e.getSource()==creditsButton)
				credits();
			
		}

	}


