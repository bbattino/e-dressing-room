package fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import pactInitial.Main;

public class FenetreCredit extends Fenetre implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton retourButton = new JButton("Retour");
	private JButton quitterButton = new JButton("Quitter");
	private Container contenu;
	private Logger logger;
	private JPanel bouttons = new JPanel();
	private FenetreDepart fentreDepart;
	private JTextArea texte = new JTextArea("Nous sommes le groupe 1.2");
	

	public FenetreCredit(FenetreDepart fentreDepart) {
		Main.setCurentFenetre(this);
		this.fentreDepart=fentreDepart;

		logger = Logger.getLogger("com.foo.FenetreCredits");
		logger.setLevel(Level.OFF);
		
		texte.setFont(new Font("Century", Font.BOLD, 12));
		


		setUndecorated(true);
		logger.info("Initialisation de la fenêtre de credits \n");
		setVisible(true); // affichage
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel image = new JLabel(new ImageIcon("data/photoGroupe.png"));

		// Utilisation de BorderLayout
		contenu = getContentPane();
		contenu.setLayout(new BorderLayout());
		contenu.setBackground(new Color(255, 255, 255));

		// Ajouts des bouttons sur buttons

		bouttons.add(retourButton);
		bouttons.add(quitterButton);
		bouttons.setBackground(new Color(255, 255, 255));
		logger.info("Ajout des boutons \n");

		// Ajouts sur le ContentPane
		contenu.add(texte,BorderLayout.PAGE_START);
		contenu.add(image, BorderLayout.CENTER);
		contenu.add(bouttons, BorderLayout.PAGE_END);

		// Ajout des Listeners
		retourButton.addActionListener(this);
		quitterButton.addActionListener(this);
		logger.info("Ajout des actionListeners pour les boutons \n");

		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.addHandListener(quitterButton);
		this.addHandListener(retourButton);

	}
	public void retour(){
		fentreDepart.setVisible(true);
		dispose();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==quitterButton)
			System.exit(0);
		else if (e.getSource()==retourButton)
			retour();

	}
}
