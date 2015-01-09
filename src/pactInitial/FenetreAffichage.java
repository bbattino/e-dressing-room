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

public class FenetreAffichage extends JFrame implements ActionListener{



		private static final long serialVersionUID = 1L;
		private JButton retourButton = new JButton("Retour");
		private JButton quitterButton = new JButton("Quitter");
		private ArrayList<JButton> listeBoutons = new ArrayList<JButton>();
		private JPanel bouttons = new JPanel();
		private Container contenu = getContentPane();


		public FenetreAffichage(String imagePath) {
			setTitle("Essayage Virtuel"); // Titre
			setVisible(true); // affichage
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JLabel image = new JLabel(new ImageIcon(imagePath));


			// Utilisation de BorderLayout
			contenu.setLayout(new BorderLayout());
			contenu.setBackground(new Color(255, 255, 255));

			
			//Ajouts des bouttons sur buttons
			
			bouttons.add(retourButton);
			bouttons.add(quitterButton);
			bouttons.setBackground(new Color(255,255,255));
			
			listeBoutons.add(retourButton);
			listeBoutons.add(quitterButton);
			
			//Ajouts sur le ContentPane
			contenu.add(image, BorderLayout.CENTER);
			contenu.add(bouttons,BorderLayout.PAGE_END);

			//Ajout des Listeners
			for(JButton bouton : listeBoutons)
				bouton.addActionListener(this);
			
			this.setExtendedState(this.MAXIMIZED_BOTH);
			
		}
		
		public void retour(){
			
			dispose();
			new FenetreChoixVetement();
		}
		
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==quitterButton)
				System.exit(0);
			if(e.getSource()==retourButton)
				retour();
			
		}

	}


