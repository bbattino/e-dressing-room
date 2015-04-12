package fenetres;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
/*import java.awt.FlowLayout;
import java.awt.Frame;*/
import java.awt.Point;
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
	private JButton retourButton = new JButton("Retour"),
			quitterButton = new JButton("Quitter"),
			panierButton = new JButton("Ajouter au Panier");
	private ArrayList<JButton> listeBoutons = new ArrayList<JButton>();
	private JPanel bouttons = new JPanel();
	private Container contenu = getContentPane();
	private String userName, imagePath, vetementType;
	private static JOptionPanePerso jopp;

	public FenetreAffichageSynthese(String imagePath, String userName, String vetementType) {
		Main.setCurentFenetre(this);
		//cadrage();
		this.userName = userName;
		this.vetementType = vetementType;
		this.imagePath = imagePath;
		setUndecorated(true);
		setVisible(true); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		actionOpenGL(1);//afficher l'openGL

		// Utilisation de BorderLayout
		contenu.setLayout(new BorderLayout());
		contenu.setBackground(new Color(255, 255, 255));

		// Ajouts des bouttons sur buttons
		//bouttons.setLayout(new FlowLayout());
		retourButton.setPreferredSize(new Dimension(200,100));
		panierButton.setPreferredSize(new Dimension(200,100));
		quitterButton.setPreferredSize(new Dimension(200,100));
		bouttons.add(retourButton);
		bouttons.add(panierButton);
		bouttons.add(quitterButton);

		bouttons.setBackground(new Color(255, 255, 255));

		listeBoutons.add(retourButton);
		listeBoutons.add(panierButton);
		listeBoutons.add(quitterButton);

		new LecteurAudio("SolutionSon.wav");

		// contenu.add(image, BorderLayout.CENTER);
		contenu.add(bouttons, BorderLayout.PAGE_START);

		// Ajout des Listeners
		for (JButton bouton : listeBoutons)
			bouton.addActionListener(this);
		
		setLocation(0, 0);
		//setSize(1920,1080); taille martin
		setSize(1000,200);
		
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		addHandListener(panierButton);
		addHandListener(quitterButton);
		addHandListener(retourButton);

	}

	public void retour() {
		//actionOpenGL(0); // demande à l'open GL de fermer sa fenêtre
		//Main.modifierEtatFenetreOpenGL(0);
		Main.setOuverture(false);
		dispose();
		new FenetreChoixVetement(userName, vetementType);
	}
	
	public void cadrage(){
		JFrame droit = new JFrame();
		droit.setLocation(new Point(830,200));
		droit.setSize(400,600);
		droit.setVisible(true);
		droit.setUndecorated(true);
		droit.getContentPane().setBackground(new Color(255, 255, 255));
		
		/*JFrame gauche = new JFrame();
		droit.setLocation(new Point(0,200));
		droit.setSize(200,600);
		droit.setVisible(true);
		droit.setUndecorated(true);
		droit.getContentPane().setBackground(new Color(255, 255, 255));

		JFrame bas = new JFrame();
		droit.setLocation(new Point(0,700));
		droit.setSize(1000,200);
		droit.setVisible(true);
		droit.setUndecorated(true);
		droit.getContentPane().setBackground(new Color(255, 255, 255));*/


	}

	public void panier() {

		/**** Ecriture dans le fichier panier.txt de l'user ******/
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

	public void actionOpenGL(int action) {
		// méthode utilisant le module java/C++ pour
		// demander au module synthèse 3D d'executer une action
		// sur la fenêtre openGL
		// Les actions possibles sont : setVisible false,
		// setVisible true et eventuellement dispose
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == quitterButton)	{
			//Main.modifierEtatFenetreOpenGL(0);
			Main.setQuitterAffichage3D(true);
			Main.setOuverture(true);
			System.exit(0);
		}
		else if (e.getSource() == retourButton)		retour();
		else if (e.getSource() == panierButton)		panier();

	}
	
	@Override
	public void actionBouton(int numeroBouton){
		/*switch(numeroBouton){
		case 0 : retourButton.doClick();
			break;
		case 1 : panierButton.doClick();
			break;
		case 2 : // ok de la jopp
			jopp.dispose();
			break;
		}*/
		switch (numeroBouton) {
		case 10:
			retourButton.doClick();
			break;
		case 19:
			panierButton.doClick();
			break;
			
		case 22:
			jopp.dispose();
			break;

		default:
			System.err.println("commande "+numeroBouton+" non reconnue sur"+this.toString());
			break;
		}
	}

	public static void main(String args[]){
		new FenetreAffichageSynthese("", "", "");
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
