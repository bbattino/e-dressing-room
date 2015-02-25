package fenetres;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pactInitial.Main;

public class FenetreCatalogue extends Fenetre implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ArrayList<JButton> boutonListe = new ArrayList<JButton>();
	private JButton tShirtButton = new JButton("T Shirt");
	private JButton pullButton = new JButton("Pull");
	private JButton robeButton = new JButton("Robe");
	private JButton pantalonButton = new JButton("Pantalon");
	private JButton quitterButton = new JButton("Quitter");
	private JButton retourButton = new JButton("Retour");
	private JButton voirPanierButton = new JButton("Voir le Panier");
	private JButton supprimerButton = new JButton("Supprimer Compte");
	private JPanel catalogue = new JPanel();
	private String userName;
	private static JOptionPanePerso jopp;

	private JPanel bouttons = new JPanel();

	public FenetreCatalogue(String userName) {
		Main.setCurentFenetre(this);
		this.userName = userName;
		setUndecorated(true);
		setVisible(true); // affichage
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel image = new JLabel(new ImageIcon("data/vetements.jpg"));

		// Utilisation de BorderLayout
		Container contenu = getContentPane();
		contenu.setLayout(new BorderLayout());
		contenu.setBackground(new Color(255, 255, 255));

		// Ajouts des bouttons sur buttons
		bouttons.add(retourButton);
		bouttons.add(voirPanierButton);
		bouttons.add(supprimerButton);
		bouttons.add(quitterButton);
		bouttons.setBackground(new Color(255, 255, 255));
		boutonListe.add(tShirtButton);
		boutonListe.add(pullButton);
		boutonListe.add(robeButton);
		boutonListe.add(pantalonButton);

		// Ajout des Listeners
		for (JButton bouton : boutonListe)
			bouton.addActionListener(this);
		quitterButton.addActionListener(this);
		voirPanierButton.addActionListener(this);
		retourButton.addActionListener(this);
		supprimerButton.addActionListener(this);

		// Configuration du panneau catalogue

		catalogue.setLayout(new GridLayout(2, 2));
		catalogue.setBackground(new Color(255, 255, 255));
		catalogue.add(tShirtButton);
		catalogue.add(pullButton);
		catalogue.add(pantalonButton);
		catalogue.add(robeButton);

		// Ajouts sur le ContentPane
		contenu.add(image, BorderLayout.PAGE_START);
		contenu.add(bouttons, BorderLayout.PAGE_END);
		contenu.add(catalogue, BorderLayout.CENTER);

		this.setExtendedState(Frame.MAXIMIZED_BOTH);

		for (JButton bouton : boutonListe)
			addHandListener(bouton);
		addHandListener(retourButton);
		addHandListener(voirPanierButton);
		addHandListener(quitterButton);
		addHandListener(supprimerButton);

	}

	public void tShirt() {
		dispose();
		if (Main.firstTShirt) {
			Main.firstTShirt = false;
			FenetreChoixVetement fen = new FenetreChoixVetement(userName, "tshirt");
			fen.dispose();
		}
		new FenetreChoixVetement(userName, "tshirt");
	}

	public void robe() {
		dispose();
		if (Main.firstRobe) {
			Main.firstRobe = false;
			FenetreChoixVetement fen = new FenetreChoixVetement(userName, "robe");
			fen.dispose();
		}
		new FenetreChoixVetement(userName, "robe");
	}

	public void pantalon() {
		dispose();
		if (Main.firstPantalon) {
			Main.firstPantalon = false;
			FenetreChoixVetement fen = new FenetreChoixVetement(userName, "pantalon");
			fen.dispose();
		}
		new FenetreChoixVetement(userName, "pantalon");
	}

	public void pull() {
		dispose();
		if (Main.firstPull) {
			Main.firstPull = false;
			FenetreChoixVetement fen = new FenetreChoixVetement(userName, "pantalon");
			fen.dispose();
		}
		new FenetreChoixVetement(userName, "pull");
	}

	public void retour() {
		dispose();
		new FenetreIdentification();
	}

	public void voirPanier() {
		dispose();
		new FenetreVisualisation(userName);

	}

	/********* getUser renvoie l'arrayList des noms d'utilisateurs *********/
	public ArrayList<String> getUser() {
		ArrayList<String> usersList = new ArrayList<String>();
		try {
			InputStream ips = new FileInputStream("users/utilisateurs.txt");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String userName;
			while ((userName = br.readLine()) != null) {
				usersList.add(userName);
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return usersList;

	}

	public void supprimerPanier(String userName) { // Supprime le fichier
													// panierUser.txt
		File panier = new File("users/panier" + userName + ".txt");
		panier.delete();
	}
		
	public void confirmerSuppression(final String userName){
		
		String[] s = { "oui", "non" };
		Runnable[] r = { new Runnable() {
			public void run() {// cas OUI
				supprimer(userName);
				jopp.dispose();
			}
		}, 
				new Runnable() {
					public void run() {// Cas non
						jopp.dispose();
					}
				} }; 
		jopp = new JOptionPanePerso("Suppression", "data/attention.jpg", s, r);
		
	}

	/******* Methode génerale pour supprimer un utilisateur *****/
	public void supprimer(String userName) {

			ArrayList<String> usersList = this.getUser();
			try {
				new FileWriter(new File("users/utilisateurs.txt")).close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			FileWriter writer = null;
			try {
				writer = new FileWriter("users/utilisateurs.txt", true);
				for (int i = 0; i < usersList.size(); i++) {
					if (!(usersList.get(i).equals(userName))) {
						writer.write(usersList.get(i) + "\n");
					}
				}
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
			supprimerPanier(userName);
			dispose();
			new FenetreIdentification();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == tShirtButton)
			tShirt();
		else if (e.getSource() == robeButton)
			robe();
		else if (e.getSource() == pantalonButton)
			pantalon();
		else if (e.getSource() == pullButton)
			pull();
		else if (e.getSource() == retourButton)
			retour();
		else if (e.getSource() == quitterButton)
			System.exit(0);
		else if (e.getSource() == voirPanierButton)
			voirPanier();
		else if (e.getSource() == supprimerButton)
			confirmerSuppression(userName);

	}
	
	@Override
	public void actionBouton(int numeroBouton){
		switch(numeroBouton){
		case 0 : retourButton.doClick();
		break;
		case 1 : tShirtButton.doClick();
		break;
		case 2 : robeButton.doClick();
		break;
		case 3 : pullButton.doClick();
		break;
		case 4 : pantalonButton.doClick();
		break;
		case 5 : voirPanierButton.doClick();
		break;
		case 6 : supprimerButton.doClick();
		break;
		}
	}

}
