package fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FenetreIdentification extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton quitterButton = new JButton("Quitter");
	private JButton nouveauCompteButton = new JButton("Nouveau Compte");
	private ArrayList<JButton> utilisateurs = new ArrayList<JButton>();
	private JPanel bouttons = new JPanel();
	private JPanel utilisateurLabel = new JPanel();

	public FenetreIdentification() {

		/********************** Lecture des utilisateurs dans le fichier *****************/

		try {
			InputStream ips = new FileInputStream("users/utilisateurs.txt");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String userName;
			while ((userName = br.readLine()) != null) {
				utilisateurs.add(new JButton(userName));
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		setUndecorated(true);

		setVisible(true); // affichage
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Utilisation de BorderLayout
		Container contenu = getContentPane();
		contenu.setLayout(new BorderLayout());
		contenu.setBackground(new Color(255, 255, 255));

		// Ajouts des bouttons sur buttons

		bouttons.add(quitterButton);
		bouttons.add(nouveauCompteButton);
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
	}

	public void quitter() {

		System.exit(0);
	}

	/******************* Nouveau Compte ********************/
	public void nouveauCompte() {
		String userName = JOptionPane.showInputDialog(null, "Comment vous appelez vous ?", "Identification !",
				JOptionPane.QUESTION_MESSAGE);
		String[] ouiNon = { "oui", "non" };
		if (!(userName == null)) {
			int reponse = JOptionPane.showOptionDialog(null, "Etes vous sur de vouloir créer le profil " + userName
					+ " ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, ouiNon,
					ouiNon[1]);
			if (reponse == 0) {
				nouveauCompte(userName);
				dispose();
				new FenetreIdentification();
			}
		}
	}

	public void nouveauCompte(String userName) {
		FileWriter writer = null;
		try {
			writer = new FileWriter("users/utilisateurs.txt", true);
			writer.write("\n" + userName, 0, userName.length() + 1);
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

	/* Generation d'un fichier texte spécifique aux nouveux utilisateurs */
	public void creerPanier(String userName){
		File panier = new File("users/panier"+userName+".txt");
		try {
			System.out.print("ok");
			panier.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == quitterButton)
			quitter();
		if (e.getSource() == nouveauCompteButton)
			nouveauCompte();
		for (JButton bouton : utilisateurs) {
			if (e.getSource() == bouton) {
				new FenetreCatalogue();
				dispose();
			}
		}
	}

}
