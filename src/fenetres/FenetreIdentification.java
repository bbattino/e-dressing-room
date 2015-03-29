package fenetres;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pactInitial.Main;

public class FenetreIdentification extends Fenetre implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton quitterButton = new JButton("Quitter");
	private JButton nouveauCompteButton = new JButton("Nouveau Compte");
	private ArrayList<JButton> utilisateurs = new ArrayList<JButton>();
	private JPanel bouttons = new JPanel();
	private JPanel utilisateurLabel = new JPanel();
	static JOptionPanePerso jopp;

	public FenetreIdentification() {
		Main.setCurentFenetre(this);

		/********************** Lecture des utilisateurs dans le fichier *****************/

		try {
			InputStream ips = new FileInputStream("users/utilisateurs.txt");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String userName;
			Main.UserNumber=0;
			while ((userName = br.readLine()) != null) {
				JButton bouton = new JButton(userName);
				bouton.setPreferredSize(new Dimension(200,100));
				bouton.setFont(new Font("Arial",Font.PLAIN,40));
				utilisateurs.add(bouton);
				Main.UserNumber++;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		setUndecorated(true);
		quitterButton.setPreferredSize(new Dimension(200,100));
		quitterButton.setFont(new Font("Arial",Font.PLAIN,40));
		nouveauCompteButton.setPreferredSize(new Dimension(500,100));
		nouveauCompteButton.setFont(new Font("Arial",Font.PLAIN,40));
		setVisible(true); // affichage
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Utilisation de BorderLayout
		Container contenu = getContentPane();
		contenu.setLayout(new BorderLayout());
		contenu.setBackground(new Color(255, 255, 255));

		// Ajouts des bouttons sur buttons
		
		bouttons.add(nouveauCompteButton);
		bouttons.add(quitterButton);
		bouttons.add(Main.getIndicateurVocal());
		bouttons.setBackground(new Color(255, 255, 255));

		// Ajouts sur le ContentPane
		contenu.add(bouttons, BorderLayout.PAGE_END);
		for (JButton utilisateurButton : utilisateurs)
			utilisateurLabel.add(utilisateurButton);
		utilisateurLabel.setBackground(new Color(255, 255, 255));
		contenu.add(utilisateurLabel, BorderLayout.PAGE_START);

		// Ajout des Listeners
		quitterButton.addActionListener(this);
		nouveauCompteButton.addActionListener(this);
		for (JButton bouton : utilisateurs)
			bouton.addActionListener(this);

		// Ajout d'une image de fond
		JLabel image = new JLabel(new ImageIcon("data/identification.gif"));
		contenu.add(image, BorderLayout.CENTER);
		File imageFile = new File("data/identification.gif");
		if (!imageFile.exists())
			System.err.println("Attention l'image n'existe pas !");

		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		addHandListener(nouveauCompteButton);
		addHandListener(quitterButton);
		for(JButton bouton : utilisateurs)
			addHandListener(bouton);
	}


	/******************* Nouveau Compte ********************/
	public void nouveauCompte() {new FenetreEntreeNom();this.dispose();}
	
	/****** methode appelée par la FenetreEntreeNom ******/
	public void nouveauCompte(String userName) {
		if(Main.UserNumber<6){
		if(checkIfAccountDoesNotExist(userName)){
		FileWriter writer = null;
		try {
			writer = new FileWriter("users/utilisateurs.txt", true);
			writer.write(userName+"\n" , 0, userName.length() + 1);
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
		creerPanier(userName);
		}

		else{
			String[] s ={"ok"};
			Runnable[] r = {new Runnable() {public void run() {jopp.dispose();}}};
			jopp=new JOptionPanePerso("","data/warning.png",s, r);
			

		}
		}else{
			String[] s ={"ok"};
			Runnable[] r = {new Runnable() {public void run() {jopp.dispose();}}};
			jopp=new JOptionPanePerso("","data/warning2.png",s, r);
		}
	}
	public boolean checkIfAccountDoesNotExist(String userName){
		try {
			InputStream ips = new FileInputStream("users/utilisateurs.txt");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String existingUser;
			while ((existingUser = br.readLine()) != null) {
				if(existingUser.equals(userName)){
					br.close();
					return false;
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return true;		
	}

	/***** Generation d'un fichier texte spécifique aux nouveux utilisateurs ******/
	public void creerPanier(String userName){
		File panier = new File("users/panier"+userName+".txt");
		try {
			panier.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == quitterButton)
			System.exit(0);
		else if (e.getSource() == nouveauCompteButton)
			nouveauCompte();
		for (JButton bouton : utilisateurs) {
			if (e.getSource() == bouton) {
				new FenetreCatalogue(bouton.getText());
				dispose();
			}
		}
	}
	
	@Override
	public void actionBouton(int numero){
		
		switch(numero){
		case 3 :
			if(utilisateurs.size()>0)
				utilisateurs.get(0).doClick();
			break;
		
		case 4:
			if(utilisateurs.size()>1)
				utilisateurs.get(1).doClick();
			break;
			
		case 5:
			if(utilisateurs.size()>2)
				utilisateurs.get(2).doClick();
			break;
		case 6:
			if(utilisateurs.size()>3)
				utilisateurs.get(3).doClick();
			break;
		case 7:
			if(utilisateurs.size()>4)
				utilisateurs.get(4).doClick();
			break;
		case 8:
			if(utilisateurs.size()>5)
				utilisateurs.get(5).doClick();
			break;
		
		case 9:
			nouveauCompteButton.doClick();
			break;
	
		case 22 : //ok jopp
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
