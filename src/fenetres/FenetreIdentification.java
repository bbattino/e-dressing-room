package fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreIdentification extends JFrame implements ActionListener{
	

	private static final long serialVersionUID = 1L;
	private JButton quitterButton = new JButton("Quitter");
	private JButton nouveauCompteButton = new JButton("Nouveau Compte");
	private ArrayList<JButton> utilisateurs =new ArrayList<JButton>();
	private JPanel bouttons = new JPanel();
	private JPanel utilisateurLabel= new JPanel();
	private static Logger logger;



	public FenetreIdentification() {
		logger = Logger.getLogger("com.foo.FenetreIdentification");

		setUndecorated(true);
        logger.info("Initialisation de la fenêtre d'identification");

		setVisible(true); // affichage
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		utilisateurs.add(new JButton("Jean"));
		utilisateurs.add(new JButton("Paul"));

		

		// Utilisation de BorderLayout
		Container contenu = getContentPane();
		contenu.setLayout(new BorderLayout());
		contenu.setBackground(new Color(255, 255, 255));

		
		//Ajouts des bouttons sur buttons
		
		bouttons.add(quitterButton);
		bouttons.add(nouveauCompteButton);
		bouttons.setBackground(new Color(255,255,255));

		//Ajouts sur le ContentPane
		contenu.add(bouttons,BorderLayout.PAGE_END);
		for(JButton utilisateurButton : utilisateurs)
			utilisateurLabel.add(utilisateurButton);
		utilisateurLabel.setBackground(new Color(255,255,255));
		contenu.add(utilisateurLabel,BorderLayout.PAGE_START);

		//Ajout des Listeners
		quitterButton.addActionListener(this);
		nouveauCompteButton.addActionListener(this);
		logger.info("Ajout des boutons");
		for(JButton bouton : utilisateurs)
			bouton.addActionListener(this);
		logger.info("Ajout des actionListener");
		
		//Ajout d'une image de fond
		JLabel image = new JLabel(new ImageIcon("data/identification.gif"));
		contenu.add(image,BorderLayout.CENTER);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
		System.out.println(image.getHeight());
		if(image.getHeight()==0)
			logger.warning("l'image n'a pas été chargée");
		else
			logger.info("l'image a été chargée");
		


		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
	}
	
	public void quitter(){

		System.exit(0);
	}
	
	public void nouveauCompte(){
		System.out.println("nouveau compte");

	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==quitterButton)
			quitter();
		if(e.getSource()==nouveauCompteButton)
			nouveauCompte();
		for(JButton bouton : utilisateurs){
			if(e.getSource()==bouton){
				new FenetreCatalogue();
				logger.info("Utilisateur choisi");
				dispose();
			}
		}
	}


}
