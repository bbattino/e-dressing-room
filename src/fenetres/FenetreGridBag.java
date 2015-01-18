package fenetres;

import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FenetreGridBag extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private Container contenu = getContentPane();
	
	
	public FenetreGridBag(){
		setVisible(true);
		contenu.setLayout(new GridBagLayout());
		contenu.add(new JButton("ok"));
		
	}

	
	

}
