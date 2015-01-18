package pactInitial;

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

public class FenetreDepart extends JFrame implements ActionListener{



		private static final long serialVersionUID = 1L;
		private JButton commencerButton = new JButton("Commencer");
		private JButton quitterButton = new JButton("Quitter");
		private JButton creditsButton = new JButton("A propos");
		private JPanel bouttons = new JPanel();

		public FenetreDepart() {
			setUndecorated(true);
			setVisible(true); // affichage
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JLabel image = new JLabel(new ImageIcon("data/bienvenue.png"));

			// Utilisation de BorderLayout
			Container contenu = getContentPane();
			contenu.setLayout(new BorderLayout());
			contenu.setBackground(new Color(255, 255, 255));

			
			//Ajouts des bouttons sur buttons
			
			bouttons.add(commencerButton);
			bouttons.add(creditsButton);
			bouttons.add(quitterButton);
			bouttons.setBackground(new Color(255,255,255));

			//Ajouts sur le ContentPane
			contenu.add(image, BorderLayout.CENTER);
			contenu.add(bouttons,BorderLayout.PAGE_END);

			//Ajout des Listeners
			commencerButton.addActionListener(this);
			creditsButton.addActionListener(this);
			quitterButton.addActionListener(this);

			this.setExtendedState(Frame.MAXIMIZED_BOTH);
		}
		
		public void commencer(){
			
			new FenetreIdentification();
			dispose();
		}
		
		public void credits(){
			System.out.println("crédits");

		}

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==commencerButton)
				commencer();
			if(e.getSource()==creditsButton)
				credits();
			if(e.getSource()==quitterButton)
				System.exit(0);
			
		}

	}


