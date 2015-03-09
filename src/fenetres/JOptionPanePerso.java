package fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JTextArea;

public class JOptionPanePerso extends Fenetre {

static final long serialVersionUID = 1L;
//private JTextArea texte;
private JPanel boutonsPanel = new JPanel();
private ArrayList<JButton> boutonListe=new ArrayList<JButton>();
private ArrayList<Runnable> boutonActionListe= new ArrayList<Runnable>();
private JLabel image;

public JOptionPanePerso(String Message,String imagePath, String[] boutonsTitre, Runnable[] boutonsAction){
	//this.texte=new JTextArea(Message);
	//texte.setEditable(false);
	//setUndecorated(true);

	setLocation(100, 400);
	image = new JLabel(new ImageIcon(imagePath));
	Container contenu = getContentPane();
	contenu.setLayout(new BorderLayout());
	setVisible(true);
	setTitle("message");
	//contenu.add(texte,BorderLayout.PAGE_START);
	boutonsPanel.setLayout(new FlowLayout());
	for(int i=0;i<boutonsTitre.length;i++){
		JButton bouton = new JButton(boutonsTitre[i]);
		bouton.setPreferredSize(new Dimension(100,50));
		bouton.setFont(new Font("Arial",Font.PLAIN,40));
		bouton.addActionListener(this);
		boutonListe.add(bouton);
		boutonsPanel.add(bouton);
		boutonActionListe.add(boutonsAction[i]);
		addHandListener(bouton);
	}
	boutonsPanel.setBackground(new Color(255,255,255));
	contenu.add(boutonsPanel,BorderLayout.PAGE_END);
	contenu.add(image,BorderLayout.LINE_START);
	pack();
}

	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<boutonListe.size();i++){
			if (e.getSource()==boutonListe.get(i)){
				boutonActionListe.get(i).run();
				break;
			}
		}
	}

	//cette méthode ne sera jamais executée par la jopp
	@Override
	public void actionBouton(int numeroBouton) {}
}


