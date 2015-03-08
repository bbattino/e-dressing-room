package fenetres;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pactInitial.Main;
import audio.LecteurAudio;

public class FenetreChoixVetement extends Fenetre implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton essayerButton = new JButton("Essayer"),
			quitterButton = new JButton("Quitter"),
			suivantButton = new JButton("suivant"),
			precedentButton = new JButton("précedent"),
			retourButton = new JButton("Retour"),
			panierButton = new JButton("panier");
	private ArrayList<JButton> listeBoutons = new ArrayList<JButton>();
	private JPanel bouttons = new JPanel();
	private ArrayList<JLabel> images = new ArrayList<JLabel>();
	private ArrayList<String> imagePath = new ArrayList<String>();
	private int numeroChoix = 0;
	private Container contenu = getContentPane();
	private String userName, vetementType;
	private static JOptionPanePerso jopp;

	public FenetreChoixVetement(String userName, String typeVetement) {
		Main.setCurentFenetre(this);
		quitterButton.setPreferredSize(new Dimension(300,100));
		suivantButton.setPreferredSize(new Dimension(300,100));
		precedentButton.setPreferredSize(new Dimension(300,100));
		retourButton.setPreferredSize(new Dimension(300,100));
		panierButton.setPreferredSize(new Dimension(300,100));
		essayerButton.setPreferredSize(new Dimension(300,100));
		this.userName = userName;
		this.vetementType = typeVetement;
		final String dossier = "data/catalogue/" + vetementType;
		setUndecorated(true);
		setVisible(true); // affichage
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		File dataFile = new File(dossier);
		File[] fichiersImage = dataFile.listFiles();

		for (int i = 0; i < fichiersImage.length; i++) {
			images.add(new JLabel(new ImageIcon(dossier + "/" + fichiersImage[i].getName())));
			imagePath.add(dossier + "/" + fichiersImage[i].getName());
		}
		// Utilisation de BorderLayout
		contenu.setLayout(new BorderLayout());
		contenu.setBackground(new Color(255, 255, 255));

		// Ajouts des bouttons sur buttons

		bouttons.add(retourButton);
		bouttons.add(essayerButton);
		bouttons.add(panierButton);
		bouttons.add(quitterButton);
		bouttons.setBackground(new Color(255, 255, 255));

		listeBoutons.add(essayerButton);
		listeBoutons.add(quitterButton);
		listeBoutons.add(suivantButton);
		listeBoutons.add(precedentButton);
		listeBoutons.add(retourButton);
		listeBoutons.add(panierButton);
		// Ajouts sur le ContentPane
		contenu.add(images.get(numeroChoix), BorderLayout.CENTER);
		contenu.add(bouttons, BorderLayout.PAGE_END);
		contenu.add(precedentButton, BorderLayout.LINE_START);
		contenu.add(suivantButton, BorderLayout.LINE_END);

		// Ajout des Listeners
		for (JButton bouton : listeBoutons)
			bouton.addActionListener(this);

		this.setExtendedState(Frame.MAXIMIZED_BOTH);

		addHandListener(essayerButton);
		addHandListener(panierButton);
		addHandListener(precedentButton);
		addHandListener(quitterButton);
		addHandListener(retourButton);
		addHandListener(suivantButton);

	}
	
	

	public void essayer() {
		dispose();
		Main.modifierEtatFenetreOpenGL(1);
		new FenetreAffichageSynthese(imagePath.get(numeroChoix), userName, vetementType);
	}

	public void suivant() {
		contenu.remove(images.get(numeroChoix));
		numeroChoix = (numeroChoix == images.size() - 1) ? 0 : numeroChoix + 1;
		contenu.add(images.get(numeroChoix), BorderLayout.CENTER);
		this.setSize(getMaximumSize());
		this.setExtendedState(Frame.MAXIMIZED_BOTH);

	}

	public void precedent() {
		contenu.remove(images.get(numeroChoix));
		numeroChoix = (numeroChoix == 0) ? images.size() - 1 : numeroChoix - 1;
		contenu.add(images.get(numeroChoix), BorderLayout.CENTER);
		this.setSize(getMaximumSize());
		this.setExtendedState(Frame.MAXIMIZED_BOTH);

	}

	public void retour() {
		this.dispose();
		new FenetreCatalogue(userName);
	}

	public void panier() {

		FileWriter writer = null;
		try {
			writer = new FileWriter("users/panier" + userName + ".txt", true);
			writer.write(imagePath.get(numeroChoix) + "\n");
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
		String[] s = { "ok" };
		Runnable[] r = { new Runnable() {
			public void run() {
				jopp.dispose();
			}
		} };
		new LecteurAudio("recharge.wav");
		jopp = new JOptionPanePerso("L'article a été ajouté au panier", "data/greenTick.jpg", s, r);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == quitterButton)			System.exit(0);
		else if (e.getSource() == essayerButton)	essayer();
		else if (e.getSource() == suivantButton)	suivant();
		else if (e.getSource() == precedentButton)	precedent();
		else if (e.getSource() == retourButton)		retour();
		else if (e.getSource() == panierButton)		panier();
	}

	@Override
	public void actionBouton(int numeroBouton) {
		switch (numeroBouton) {
		case 0:
			retourButton.doClick();
			break;
		case 1:
			suivantButton.doClick();
			break;
		case 2:
			precedentButton.doClick();
			break;
		case 3:
			panierButton.doClick();
			break;
		case 4:
			essayerButton.doClick();
			break;
		case 5: // ok de la jopp
			jopp.dispose();
			break;
		}
	}

}
