package fenetres;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FenetreEntreeNom extends Fenetre {

	private static final long serialVersionUID = 1L;
	private ArrayList<JButton> alphabet = new ArrayList<JButton>();
	private JPanel alphabetPanel = new JPanel(), boutonsDuBas = new JPanel();
	JButton terminerButton = new JButton("Terminé");
	JButton annulerButton = new JButton("Annuler");
	JButton quitterButton = new JButton("Quiter");
	String name = "";
	JTextArea texte = new JTextArea("\n \t \t Votre nom est : " + name + " \n\n");

	public FenetreEntreeNom() {
		setSize(1000, 800);
		alphabet.add(new JButton("a"));
		alphabet.add(new JButton("b"));
		alphabet.add(new JButton("c"));
		alphabet.add(new JButton("d"));
		alphabet.add(new JButton("e"));
		alphabet.add(new JButton("f"));
		alphabet.add(new JButton("g"));
		alphabet.add(new JButton("h"));
		alphabet.add(new JButton("i"));
		alphabet.add(new JButton("j"));
		alphabet.add(new JButton("k"));
		alphabet.add(new JButton("l"));
		alphabet.add(new JButton("m"));
		alphabet.add(new JButton("n"));
		alphabet.add(new JButton("o"));
		alphabet.add(new JButton("p"));
		alphabet.add(new JButton("q"));
		alphabet.add(new JButton("r"));
		alphabet.add(new JButton("s"));
		alphabet.add(new JButton("t"));
		alphabet.add(new JButton("u"));
		alphabet.add(new JButton("v"));
		alphabet.add(new JButton("w"));
		alphabet.add(new JButton("x"));
		alphabet.add(new JButton("y"));
		alphabet.add(new JButton("z"));

		alphabet.add(new JButton("A"));
		alphabet.add(new JButton("B"));
		alphabet.add(new JButton("C"));
		alphabet.add(new JButton("D"));
		alphabet.add(new JButton("E"));
		alphabet.add(new JButton("F"));
		alphabet.add(new JButton("G"));
		alphabet.add(new JButton("H"));
		alphabet.add(new JButton("I"));
		alphabet.add(new JButton("J"));
		alphabet.add(new JButton("K"));
		alphabet.add(new JButton("L"));
		alphabet.add(new JButton("M"));
		alphabet.add(new JButton("N"));
		alphabet.add(new JButton("O"));
		alphabet.add(new JButton("P"));
		alphabet.add(new JButton("Q"));
		alphabet.add(new JButton("R"));
		alphabet.add(new JButton("S"));
		alphabet.add(new JButton("T"));
		alphabet.add(new JButton("U"));
		alphabet.add(new JButton("V"));
		alphabet.add(new JButton("W"));
		alphabet.add(new JButton("X"));
		alphabet.add(new JButton("Y"));
		alphabet.add(new JButton("Z"));

		alphabet.add(new JButton("-"));
		alphabet.add(new JButton("0"));
		alphabet.add(new JButton("1"));
		alphabet.add(new JButton("2"));
		alphabet.add(new JButton("3"));
		alphabet.add(new JButton("4"));
		alphabet.add(new JButton("5"));
		alphabet.add(new JButton("6"));
		alphabet.add(new JButton("7"));
		alphabet.add(new JButton("8"));
		alphabet.add(new JButton("9"));

		alphabet.add(new JButton("é"));
		alphabet.add(new JButton("è"));
		alphabet.add(new JButton("ê"));
		alphabet.add(new JButton("ë"));
		alphabet.add(new JButton("ç"));

		alphabet.add(new JButton(" "));
		alphabet.add(new JButton("DEL"));

		alphabetPanel.setLayout(new GridLayout(6, 14));
		for (int i = 0; i < alphabet.size(); i++)
			alphabetPanel.add(alphabet.get(i));

		this.setVisible(true);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(alphabetPanel, BorderLayout.CENTER);

		boutonsDuBas.setLayout(new FlowLayout());
		boutonsDuBas.add(terminerButton);
		boutonsDuBas.add(annulerButton);
		boutonsDuBas.add(quitterButton);

		getContentPane().add(boutonsDuBas, BorderLayout.PAGE_END);
		texte.setSize(100, 00);
		getContentPane().add(texte, BorderLayout.PAGE_START);
		
		for (JButton bouton : alphabet)
			bouton.addActionListener(this);
		terminerButton.addActionListener(this);
		annulerButton.addActionListener(this);
		quitterButton.addActionListener(this);

	}

	public void deleteChar() {
		if(name.length()!=0){
		String name2 = "";
		for(int i=0;i<name.length()-1;i++)
			name2+=name.charAt(i);
		name=name2;
		getContentPane().remove(texte);
		texte = new JTextArea("\n \t \t Votre nom est : " + name + " \n\n");
		getContentPane().add(texte,BorderLayout.PAGE_START);
		setSize(1001, 800);	setSize(1000, 800);
		}

	}
	public void terminer(){
		
	}
	public void annuler(){
		
	}

	public void actionPerformed(ActionEvent e) {
		for (JButton button : alphabet) {
			if (e.getSource() == button) {
				if (button.getText().equals("DEL")) {
					deleteChar();
				} else {
					name = name + button.getText();
					getContentPane().remove(texte);
					texte = new JTextArea("\n \t \t Votre nom est : " + name + " \n\n");
					getContentPane().add(texte,BorderLayout.PAGE_START);
					setSize(1001, 800);	setSize(1000, 800);


					
				}
				return;
			}
		}
		if(e.getSource()==terminerButton){
			terminer();return;}
		if (e.getSource()==annulerButton){
			annuler();return;}
		if(e.getSource()==quitterButton)
			System.exit(0);
		
		

	}

	public static void main(String[] args) {
		new FenetreEntreeNom();
	}

}
