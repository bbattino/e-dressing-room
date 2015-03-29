package fenetres;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
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
import audio.LecteurAudio;

public class FenetreVisualisation extends Fenetre implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton essayerButton = new JButton("Essayer");
	private JButton supprimerButton = new JButton("Retirer du panier");
	private JButton quitterButton = new JButton("Quitter");
	private JButton suivantButton = new JButton("suivant");
	private JButton precedentButton = new JButton("précedent");
	private JButton retourButton = new JButton("Retour");
	private ArrayList<JButton> listeBoutons = new ArrayList<JButton>();
	private JPanel bouttons = new JPanel();
	private ArrayList<JLabel> images = new ArrayList<JLabel>();
	private ArrayList<String> imagePath = new ArrayList<String>();
	private int numeroChoix = 0;
	private Container contenu = getContentPane();
	private String userName, vetementType;
	private boolean panierVide = false;
	private static JOptionPanePerso jopp;

	public FenetreVisualisation(String userName) {
		Main.setCurentFenetre(this);
		essayerButton.setPreferredSize(new Dimension(200,100));
		essayerButton.setFont(new Font("Arial",Font.PLAIN,40));
		quitterButton.setPreferredSize(new Dimension(200,100));
		quitterButton.setFont(new Font("Arial",Font.PLAIN,40));
		supprimerButton.setPreferredSize(new Dimension(300,100));
		supprimerButton.setFont(new Font("Arial",Font.PLAIN,25));
		retourButton.setPreferredSize(new Dimension(200,100));
		retourButton.setFont(new Font("Arial",Font.PLAIN,40));
		suivantButton.setPreferredSize(new Dimension(200,100));
		suivantButton.setFont(new Font("Arial",Font.PLAIN,40));
		precedentButton.setPreferredSize(new Dimension(200,100));
		precedentButton.setFont(new Font("Arial",Font.PLAIN,30));
		
		this.userName = userName;
		setUndecorated(true);
		setVisible(true); // affichage
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/******** Recherches de toutes les images du panier dans le dossier data ********/

		try {
			InputStream ips = new FileInputStream("users/panier" + userName + ".txt");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String imgpath;
			if ((imgpath = br.readLine()) == null)
				panierVide = true;
			else {
				imagePath.add(imgpath);
				images.add(new JLabel(new ImageIcon(imgpath)));
			}
			while ((imgpath = br.readLine()) != null) {
				imagePath.add(imgpath);
				images.add(new JLabel(new ImageIcon(imgpath)));

			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		if (panierVide) {
			dispose();
			new FenetreCatalogue(userName);
			String[] s = { "ok" };
			Runnable[] r = { new Runnable() {
				public void run() {
					jopp.dispose();
				}
			} };
			new LecteurAudio("recharge.wav");
			jopp = new JOptionPanePerso("L'article a été ajouté au panier", "data/panierVide.png", s, r);
		} else {

			// Utilisation de BorderLayout
			contenu.setLayout(new BorderLayout());
			contenu.setBackground(new Color(255, 255, 255));

			// Ajouts des bouttons sur buttons

			bouttons.add(retourButton);
			bouttons.add(essayerButton);
			bouttons.add(quitterButton);
			bouttons.add(supprimerButton);
			bouttons.setBackground(new Color(255, 255, 255));

			listeBoutons.add(essayerButton);
			listeBoutons.add(quitterButton);
			listeBoutons.add(suivantButton);
			listeBoutons.add(precedentButton);
			listeBoutons.add(retourButton);
			listeBoutons.add(supprimerButton);
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
			addHandListener(precedentButton);
			addHandListener(quitterButton);
			addHandListener(retourButton);
			addHandListener(suivantButton);
			addHandListener(supprimerButton);
		}

	}

	public void essayer() {

		dispose();
		new FenetreAffichageSynthese(imagePath.get(numeroChoix), userName, vetementType);
	}

	public void suivant() {
		contenu.remove(images.get(numeroChoix));
		numeroChoix = (numeroChoix == images.size() - 1) ? 0 : numeroChoix + 1;
		contenu.add(images.get(numeroChoix));
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

	public void supprimer() {
		try {
			new FileWriter(new File("users/panier" + userName + ".txt")).close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		FileWriter writer = null;
		try {
			writer = new FileWriter("users/panier" + userName + ".txt", true);
			for (int i = 0; i < images.size(); i++) {
				if (i != numeroChoix) {
					writer.write(imagePath.get(i) + "\n");
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
		dispose();
		new FenetreVisualisation(userName);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == quitterButton)
			System.exit(0);
		else if (e.getSource() == essayerButton)
			essayer();
		else if (e.getSource() == suivantButton)
			suivant();
		else if (e.getSource() == precedentButton)
			precedent();
		else if (e.getSource() == retourButton)
			retour();
		else if (e.getSource() == supprimerButton)
			supprimer();
	}

	@Override
	public void actionBouton(int numeroBouton) {
		switch (numeroBouton) {
		case 10:
			retourButton.doClick();
			break;
		case 17:
			suivantButton.doClick();
			break;
		case 18:
			precedentButton.doClick();
			break;
		case 20:
			essayerButton.doClick();
			break;
		case 21:
			supprimerButton.doClick();
			break;
		case 22: // ok jopp
			jopp.dispose();
			break;
		}
	}

	@Override
	public void refreshIndicateurVocal() {
		this.bouttons.remove(Main.getIndicateurVocal());
		Main.refreshIndicateur();
		this.bouttons.add(Main.getIndicateurVocal());
		this.setSize(1364,799);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);	
		
	}
}
