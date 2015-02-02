package fenetres;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import audio.LecteurAudio;

public class FenetreChoixVetement extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton essayerButton = new JButton("Essayer");
	private JButton quitterButton = new JButton("Quitter");
	private JButton suivantButton = new JButton("suivant");
	private JButton precedentButton = new JButton("pr�cedent");
	private JButton retourButton = new JButton("Retour");
	private JButton panierButton = new JButton("panier");
	private ArrayList<JButton> listeBoutons = new ArrayList<JButton>();
	private JPanel bouttons = new JPanel();
	private ArrayList<JLabel> images = new ArrayList<JLabel>();
	private ArrayList<String> imagePath = new ArrayList<String>();
	private static int numeroChoix = 0;
	private Container contenu = getContentPane();
	private String userName, vetementType;

	public FenetreChoixVetement(String userName, String typeVetement) {
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
		// pour eviter les d�passement d'arrayList eventuels (si il n'y a pas le
		// m�me nombre de v�tements dans toutes les categories)
		if (numeroChoix > images.size() - 1)
			numeroChoix = 0;
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

	}

	public void essayer() {

		dispose();
		new FenetreAffichage(imagePath.get(numeroChoix), userName, vetementType);
	}

	public void suivant() {
		contenu.remove(images.get(numeroChoix));
		numeroChoix = (numeroChoix == images.size() - 1) ? 0 : numeroChoix + 1;
		//dispose();
		//new FenetreChoixVetement(userName, vetementType);
		contenu.add(images.get(numeroChoix),BorderLayout.CENTER);
		this.setSize(getMaximumSize());
		this.setExtendedState(Frame.MAXIMIZED_BOTH);

	}

	public void precedent() {
		numeroChoix = (numeroChoix == 0) ? images.size() - 1 : numeroChoix - 1;
		dispose();
		new FenetreChoixVetement(userName, vetementType);

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
		new LecteurAudio("recharge.wav");
		JOptionPane.showMessageDialog(new JFrame(), "L'article a �t� ajout� au panier");

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
		else if (e.getSource() == panierButton)
			panier();

	}

}
