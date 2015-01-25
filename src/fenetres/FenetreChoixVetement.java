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
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreChoixVetement extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton essayerButton = new JButton("Essayer");
	private JButton quitterButton = new JButton("Quitter");
	private JButton suivantButton = new JButton("suivant");
	private JButton precedentButton = new JButton("précedent");
	private JButton retourButton = new JButton("Retour");
	private JButton panierButton = new JButton("panier");
	private ArrayList<JButton> listeBoutons = new ArrayList<JButton>();
	private JPanel bouttons = new JPanel();
	private ArrayList<JLabel> images = new ArrayList<JLabel>();
	private ArrayList<String> imagePath= new ArrayList<String>();
	private static int numeroChoix = 0;
	private Container contenu = getContentPane();
	private String userName;

	public FenetreChoixVetement(String userName) {
		this.userName=userName;
		setUndecorated(true);
		setVisible(true); // affichage
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/******** Recherches de toutes les images du catalogue dans le dossier data ********/
		File dataFile = new File("data");
		File[] fichiersImage = dataFile.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {return name.startsWith("catalogue");}});

		for (int i = 0; i<fichiersImage.length;i++){
			images.add(new JLabel(new ImageIcon("data/"+fichiersImage[i].getName())));
			imagePath.add("data/"+fichiersImage[i].getName());
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

	}

	public void essayer() {

		dispose();
		new FenetreAffichage(imagePath.get(numeroChoix),userName);
	}

	public void suivant() {
		numeroChoix = (numeroChoix == images.size() - 1) ? 0 : numeroChoix + 1;
		dispose();
		new FenetreChoixVetement(userName);
	}

	public void precedent() {
		numeroChoix = (numeroChoix == 0) ? images.size() - 1 : numeroChoix - 1;
		dispose();
		new FenetreChoixVetement(userName);

	}

	public void retour() {
		this.dispose();
		new FenetreCatalogue(userName);
	}

	
	
	public void panier() {
		
		FileWriter writer = null;
		try {
			writer = new FileWriter("users/panier"+userName+".txt", true);
			writer.write(imagePath.get(numeroChoix)+"\n" );
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

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == quitterButton)
			System.exit(0);
		if (e.getSource() == essayerButton)
			essayer();
		if (e.getSource() == suivantButton)
			suivant();
		if (e.getSource() == precedentButton)
			precedent();
		if (e.getSource() == retourButton)
			retour();
		if (e.getSource() == panierButton)
			panier();

	}

}
