package fenetres;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FenetreGridBag extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private Container contenu = getContentPane();
	
	
	public FenetreGridBag(){
		
		setVisible(true);
		contenu.setLayout(new GridBagLayout());
		GridBagConstraints constrains = new GridBagConstraints();
		constrains.gridx=0;
		constrains.gridy=0;// Initialisation de la grille en 0,0
		contenu.add(new JButton("ok"));
		contenu.add(new JButton("ok2"));
		setSize(700,300);
		
	}

	
	

}
