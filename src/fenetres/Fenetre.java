package fenetres;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;;

public abstract class Fenetre extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ArrayList<JButton> listeBoutons = new ArrayList<JButton>();
	private JPanel bouttons = new JPanel();
	private Container contenu = getContentPane();



	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
