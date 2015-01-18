package fenetres;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
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
		private JButton retourButton = new JButton("Retour");
		private JPanel catalogue = new JPanel();


		private JPanel bouttons = new JPanel();

		public FenetreCatalogue() {
			setUndecorated(true);
			setVisible(true); // affichage
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JLabel image = new JLabel(new ImageIcon("data/vetements.jpg"));

			// Utilisation de BorderLayout
			Container contenu = getContentPane();
			contenu.setLayout(new BorderLayout());
			contenu.setBackground(new Color(255, 255, 255));

			
			//Ajouts des bouttons sur buttons
			bouttons.add(retourButton);
			bouttons.add(quitterButton);
			bouttons.setBackground(new Color(255,255,255));
			boutonListe.add(tShirtButton);
			boutonListe.add(pullButton);
			boutonListe.add(robeButton);
			boutonListe.add(pantalonButton);



			//Ajout des Listeners
			for(JButton bouton : boutonListe )
				bouton.addActionListener(this);
			quitterButton.addActionListener(this);
			retourButton.addActionListener(this);
			
			//Configuration du panneau catalogue
			
			catalogue.setLayout(new GridLayout(2,2));
			catalogue.setBackground(new Color(255,255,255));
			catalogue.add(tShirtButton);
			catalogue.add(pullButton);
			catalogue.add(pantalonButton);
			catalogue.add(robeButton);
			
			//Ajouts sur le ContentPane
			contenu.add(image, BorderLayout.PAGE_START);
			contenu.add(bouttons,BorderLayout.PAGE_END);
			contenu.add(catalogue,BorderLayout.CENTER);

			

			
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
		}
		
		public void tShirt(){
			dispose();
			new FenetreChoixVetement();
		}
		public void robe(){
			System.out.println("Robe");
		}
		public void pantalon(){
			System.out.println("Pantalon");
		}
		public void pull(){
			System.out.println("Pull");
		}
		public void retour(){
			dispose();
			new FenetreIdentification();
		}

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==tShirtButton)
				tShirt();
			if(e.getSource()==robeButton)
				robe();
			if(e.getSource()==pantalonButton)
				pantalon();
			if(e.getSource()==pullButton)
				pull();


			if(e.getSource()== retourButton)
				retour();
			if(e.getSource()==quitterButton)
				System.exit(0);
			
		}

	}

