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

public class FenetreCatalogue extends Fenetre implements ActionListener{



		private static final long serialVersionUID = 1L;
		private ArrayList<JButton> boutonListe = new ArrayList<JButton>();
		private JButton tShirtButton = new JButton("T Shirt");
		private JButton pullButton = new JButton("Pull");
		private JButton robeButton = new JButton("Robe");
		private JButton pantalonButton = new JButton("Pantalon");
		private JButton quitterButton= new JButton("Quitter");
		private JButton retourButton = new JButton("Retour");
		private JButton voirPanierButton = new JButton("Voir le Panier");
		private JButton supprimerButton = new JButton("Supprimer Compte");
		private JPanel catalogue = new JPanel();
		private String userName;


		private JPanel bouttons = new JPanel();

		public FenetreCatalogue(String userName) {
			this.userName =userName;
			setUndecorated(true);
			setVisible(true); // affichage
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JLabel image = new JLabel(new ImageIcon("data/vetements.jpg"));

			// Utilisation de BorderLayout
			Container contenu = getContentPane();
			contenu.setLayout(new BorderLayout());
			contenu.setBackground(new Color(255, 255, 255));

			
			//Ajouts des bouttons sur buttons
			bouttons.add(retourButton);
			bouttons.add(voirPanierButton);
			bouttons.add(supprimerButton);
			bouttons.add(quitterButton);
			bouttons.setBackground(new Color(255,255,255));
			boutonListe.add(tShirtButton);
			boutonListe.add(pullButton);
			boutonListe.add(robeButton);
			boutonListe.add(pantalonButton);



			//Ajout des Listeners
			for(JButton bouton : boutonListe )
				bouton.addActionListener(this);
			quitterButton.addActionListener(this);
			voirPanierButton.addActionListener(this);
			retourButton.addActionListener(this);
			supprimerButton.addActionListener(this);
			
			//Configuration du panneau catalogue
			
			catalogue.setLayout(new GridLayout(2,2));
			catalogue.setBackground(new Color(255,255,255));
			catalogue.add(tShirtButton);
			catalogue.add(pullButton);
			catalogue.add(pantalonButton);
			catalogue.add(robeButton);
			
			//Ajouts sur le ContentPane
			contenu.add(image, BorderLayout.PAGE_START);
			contenu.add(bouttons,BorderLayout.PAGE_END);
			contenu.add(catalogue,BorderLayout.CENTER);

			
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			System.out.println("c1 "+tShirtButton.getHeight());
			//while(tShirtButton.getHeight()==0); 
			System.out.println("c2 "+tShirtButton.getHeight());

			for (JButton bouton : boutonListe)
				addHandListener(bouton,0,0,1366,1000);
			addHandListener(retourButton,0,0,1000,1000);
			addHandListener(voirPanierButton,0,0,1000,1000);
			addHandListener(quitterButton,0,0,1000,1000);
			addHandListener(supprimerButton,0,0,1000,1000);
			System.out.println(contenu.getSize());

		}
		
		public void tShirt(){
			dispose();
			new FenetreChoixVetement(userName,"tshirt");
		}
		public void robe(){
			dispose();
			new FenetreChoixVetement(userName,"robe");		
		}
		public void pantalon(){
			dispose();
			new FenetreChoixVetement(userName,"pantalon");		
		}
		public void pull(){
			dispose();
			new FenetreChoixVetement(userName,"pull");		
		}
		public void retour(){
			dispose();
			new FenetreIdentification();
		}
		public void voirPanier(){
			dispose();
			new FenetreVisualisation(userName);
			
		}
		/********* getUser renvoie l'arrayList des noms d'utilisateurs*********/
		public ArrayList<String> getUser(){
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
		
		public void supprimerPanier(String userName){ //Supprime le fichier panierUser.txt
			File panier = new File("users/panier" + userName + ".txt");
			panier.delete();
		}
		
		/******* Methode génerale pour supprimer un utilisateur*****/
		public void supprimer(String userName){
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
			
			if(e.getSource()==tShirtButton)
				tShirt();
			else if(e.getSource()==robeButton)
				robe();
			else if(e.getSource()==pantalonButton)
				pantalon();
			else if(e.getSource()==pullButton)
				pull();
			else if(e.getSource()== retourButton)
				retour();
			else if(e.getSource()==quitterButton)
				System.exit(0);
			else if(e.getSource()==voirPanierButton)
				voirPanier();
			else if (e.getSource()==supprimerButton)
				supprimer(userName);
			
		}

	}


