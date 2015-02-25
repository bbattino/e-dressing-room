package fenetres;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


//import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JLabel;
import javax.swing.JPanel;

import pactInitial.Main;
import audio.LecteurAudio;

public class FenetreAffichageSynthese extends Fenetre implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton retourButton = new JButton("Retour");
	private JButton quitterButton = new JButton("Quitter");
	private JButton panierButton = new JButton("Ajouter au Panier");
	private ArrayList<JButton> listeBoutons = new ArrayList<JButton>();
	private JPanel bouttons = new JPanel();
	private Container contenu = getContentPane();
	private String userName, imagePath, vetementType;
	private static JOptionPanePerso jopp;

	public FenetreAffichageSynthese(String imagePath, String userName, String vetementType) {
		Main.setCurentFenetre(this);
		this.userName = userName;
		this.vetementType = vetementType;
		this.imagePath = imagePath;
		setUndecorated(true);
		setVisible(true); // affichage
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JLabel image = new JLabel(new ImageIcon(imagePath));

		// Utilisation de BorderLayout
		contenu.setLayout(new BorderLayout());
		contenu.setBackground(new Color(255, 255, 255));

		// Ajouts des bouttons sur buttons

		bouttons.add(retourButton);
		bouttons.add(panierButton);
		bouttons.add(quitterButton);

		bouttons.setBackground(new Color(255, 255, 255));

		listeBoutons.add(retourButton);
		listeBoutons.add(panierButton);
		listeBoutons.add(quitterButton);

		new LecteurAudio("SolutionSon.wav");

		// Ajouts sur le ContentPane
		// contenu.add(image, BorderLayout.CENTER);
		contenu.add(bouttons, BorderLayout.PAGE_END);

		// Ajout des Listeners
		for (JButton bouton : listeBoutons)
			bouton.addActionListener(this);

		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		addHandListener(panierButton);
		addHandListener(quitterButton);
		addHandListener(retourButton);

	}

	public void retour() {
		actionOpenGL("fermer"); // demande à l'open GL de fermer sa fenêtre
		dispose();
		new FenetreChoixVetement(userName, vetementType);
	}

	public void panier() {

		FileWriter writer = null;
		try {
			writer = new FileWriter("users/panier" + userName + ".txt", true);
			writer.write(imagePath + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		new LecteurAudio("recharge.wav");
		String[] s = { "ok" };
		Runnable[] r = { new Runnable() {
			public void run() {
				jopp.dispose();
			}
		} };
		new LecteurAudio("recharge.wav");
		jopp = new JOptionPanePerso("L'article a été ajouté au panier", "data/greenTick.jpg", s, r);

	}

	public void actionOpenGL(String action) {
		// méthode utilisant le module java/C++ pour
		// demander au module synthèse 3D d'executer une action
		// sur la fenêtre openGL
		// Les actions possibles sont : setVisible false,
		// setVisible true et eventuellement dispose
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == quitterButton)
			System.exit(0);
		else if (e.getSource() == retourButton)
			retour();
		else if (e.getSource() == panierButton)
			panier();

	}
	
	@Override
	public void actionBouton(int numeroBouton){
		switch(numeroBouton){
		case 0 : retourButton.doClick();
		break;
		}
	}

	public static void main(String args[]) {
		new FenetreAffichageSynthese("", "", "");
	}

}
