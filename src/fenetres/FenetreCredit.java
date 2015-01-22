package fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreCredit extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton retourButton = new JButton("Retour");
	private JButton quitterButton = new JButton("Quitter");
	private Container contenu;
	private Logger logger;
	private JPanel bouttons = new JPanel();
	private FenetreDepart fentreDepart;
	

	public FenetreCredit(FenetreDepart fentreDepart) {
		
		this.fentreDepart=fentreDepart;

		logger = Logger.getLogger("com.foo.FenetreCredits");

		setUndecorated(true);
		logger.info("Initialisation de la fenêtre de départ \n");
		setVisible(true); // affichage
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel image = new JLabel(new ImageIcon("data/tShirtMis.jpg"));

		// Utilisation de BorderLayout
		contenu = getContentPane();
		contenu.setLayout(new BorderLayout());
		contenu.setBackground(new Color(255, 255, 255));

		// Ajouts des bouttons sur buttons

		bouttons.add(retourButton);
		bouttons.add(quitterButton);
		bouttons.setBackground(new Color(255, 255, 255));
		logger.info("Ajout des boutons \n");

		// Ajouts sur le ContentPane
		contenu.add(image, BorderLayout.CENTER);
		contenu.add(bouttons, BorderLayout.PAGE_END);

		// Ajout des Listeners
		retourButton.addActionListener(this);
		quitterButton.addActionListener(this);
		logger.info("Ajout des actionListeners pour les boutons \n");

		this.setExtendedState(Frame.MAXIMIZED_BOTH);

	}
	public void retour(){
		fentreDepart.setVisible(true);
		dispose();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==quitterButton)
			System.exit(0);
		else if (e.getSource()==retourButton)
			retour();

	}
}
