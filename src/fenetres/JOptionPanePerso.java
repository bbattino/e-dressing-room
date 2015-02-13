package fenetres;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JOptionPanePerso extends Fenetre {

static final long serialVersionUID = 1L;
private JTextArea texte;
private JPanel boutonsPanel = new JPanel();
private ArrayList<JButton> boutonListe=new ArrayList<JButton>();
private ArrayList<Runnable> boutonActionListe= new ArrayList<Runnable>();

public JOptionPanePerso(String Message, String[] boutonsTitre, Runnable[] boutonsAction){
	this.texte=new JTextArea(Message);
	Container contenu = getContentPane();
	contenu.setLayout(new BorderLayout());
	setVisible(true);
	setTitle("message");
	contenu.add(texte,BorderLayout.PAGE_START);
	boutonsPanel.setLayout(new FlowLayout());
	for(int i=0;i<boutonsTitre.length;i++){
		JButton bouton = new JButton(boutonsTitre[i]);
		bouton.addActionListener(this);
		boutonListe.add(bouton);
		boutonsPanel.add(bouton);
		boutonActionListe.add(boutonsAction[i]);
		addHandListener(bouton);
	}
	contenu.add(boutonsPanel,BorderLayout.PAGE_END);
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
}


