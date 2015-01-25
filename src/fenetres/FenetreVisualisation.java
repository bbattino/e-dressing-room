package fenetres;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FenetreVisualisation extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton essayerButton = new JButton("Essayer");
	private JButton quitterButton = new JButton("Quitter");
	private JButton suivantButton = new JButton("suivant");
	private JButton precedentButton = new JButton("précedent");
	private JButton retourButton = new JButton("Retour");
	private ArrayList<JButton> listeBoutons = new ArrayList<JButton>();
	private JPanel bouttons = new JPanel();
	private ArrayList<JLabel> images = new ArrayList<JLabel>();
	private ArrayList<String> imagePath= new ArrayList<String>();
	private static int numeroChoix = 0;
	private Container contenu = getContentPane();
	private String userName,vetementType;
	private boolean panierVide = false;

	public FenetreVisualisation(String userName) {
		this.userName=userName;
		setUndecorated(true);
		setVisible(true); // affichage
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/******** Recherches de toutes les images du panier dans le dossier data ********/
		
		try {
			InputStream ips = new FileInputStream("users/panier"+userName+".txt");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String imgpath;
			if ((imgpath=br.readLine())==null)
				 panierVide=true;
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
		
		if (panierVide){
			dispose();
			new FenetreCatalogue(userName);
			JOptionPane.showMessageDialog(new JFrame(), "Votre Panier est vide");
		}
		
		if (numeroChoix>images.size()-1)
			numeroChoix=0;
		// Utilisation de BorderLayout
		contenu.setLayout(new BorderLayout());
		contenu.setBackground(new Color(255, 255, 255));

		// Ajouts des bouttons sur buttons

		bouttons.add(retourButton);
		bouttons.add(essayerButton);
		bouttons.add(quitterButton);
		bouttons.setBackground(new Color(255, 255, 255));

		listeBoutons.add(essayerButton);
		listeBoutons.add(quitterButton);
		listeBoutons.add(suivantButton);
		listeBoutons.add(precedentButton);
		listeBoutons.add(retourButton);
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
		new FenetreAffichage(imagePath.get(numeroChoix),userName,vetementType);
	}

	public void suivant() {
		numeroChoix = (numeroChoix == images.size() - 1) ? 0 : numeroChoix + 1;
		dispose();
		new FenetreVisualisation(userName);
	}

	public void precedent() {
		numeroChoix = (numeroChoix == 0) ? images.size() - 1 : numeroChoix - 1;
		dispose();
		new FenetreVisualisation(userName);
	}

	public void retour() {
		this.dispose();
		new FenetreCatalogue(userName);
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
	}
}
