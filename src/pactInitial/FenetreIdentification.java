package pactInitial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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


	public FenetreIdentification() {
		setTitle("Identifiez vous"); // Titre
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
		for(JButton bouton : utilisateurs)
			bouton.addActionListener(this);
		
		//Ajout d'une image de fond
		JLabel image = new JLabel(new ImageIcon("data/identification.gif"));
		contenu.add(image,BorderLayout.CENTER);


		
		this.setExtendedState(this.MAXIMIZED_BOTH);
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
				dispose();
			}
		}
	}


}
