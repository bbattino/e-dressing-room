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

public class FenetreChoixVetement extends JFrame implements ActionListener{



		private static final long serialVersionUID = 1L;
		private JButton essayerButton = new JButton("Essayer");
		private JButton quitterButton = new JButton("Quitter");
		private JButton suivantButton = new JButton("suivant");
		private JButton precedentButton = new JButton("précedent");
		private ArrayList<JButton> listeBoutons = new ArrayList<JButton>();
		private JPanel bouttons = new JPanel();
		private ArrayList<JLabel> images = new ArrayList<JLabel>();

		public FenetreChoixVetement() {
			setTitle("Choisissez votre vêtement"); // Titre
			setVisible(true); // affichage
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			images.add(new JLabel(new ImageIcon("data/bienvenue.png")));
			images.add(new JLabel(new ImageIcon("data/bienvenue.png")));
			images.add(new JLabel(new ImageIcon("data/bienvenue.png")));
			

			// Utilisation de BorderLayout
			Container contenu = getContentPane();
			contenu.setLayout(new BorderLayout());
			contenu.setBackground(new Color(255, 255, 255));

			
			//Ajouts des bouttons sur buttons
			
			bouttons.add(essayerButton);
			bouttons.add(quitterButton);
			bouttons.setBackground(new Color(255,255,255));
			
			listeBoutons.add(essayerButton);
			listeBoutons.add(quitterButton);
			listeBoutons.add(suivantButton);
			listeBoutons.add(precedentButton);
			
			//Ajouts sur le ContentPane
			contenu.add(images.get(0), BorderLayout.CENTER);
			contenu.add(bouttons,BorderLayout.PAGE_END);
			contenu.add(precedentButton,BorderLayout.LINE_START);
			contenu.add(suivantButton,BorderLayout.LINE_END);

			//Ajout des Listeners
			for(JButton bouton : listeBoutons)
				bouton.addActionListener(this);
			
			this.pack();
		}
		
		public void essayer(){
			
			System.out.println("essayage");
		}
		
		public void credits(){
			System.out.println("crédits");

		}

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==quitterButton)
				System.exit(0);
			if(e.getSource()==essayerButton)
				essayer();
			
		}

	}


