package fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import pactInitial.Main;

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
		Main.setCurentFenetre(this);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		
		JButton boutonVide1 = new JButton();
		JButton boutonVide2 = new JButton();
		boutonVide1.setBackground(new Color(255,255,255));
		boutonVide2.setBackground(new Color(255,255,255));
		

		
		alphabet.add(new JButton("a"));
		alphabet.add(new JButton("z"));
		alphabet.add(new JButton("e"));
		alphabet.add(new JButton("r"));
		alphabet.add(new JButton("t"));
		alphabet.add(new JButton("y"));
		alphabet.add(new JButton("u"));
		alphabet.add(new JButton("i"));
		alphabet.add(new JButton("o"));
		alphabet.add(new JButton("p"));
		
		alphabet.add(new JButton("q"));
		alphabet.add(new JButton("s"));
		alphabet.add(new JButton("d"));
		alphabet.add(new JButton("f"));
		alphabet.add(new JButton("g"));
		alphabet.add(new JButton("h"));
		alphabet.add(new JButton("j"));
		alphabet.add(new JButton("k"));
		alphabet.add(new JButton("l"));
		alphabet.add(new JButton("m"));

		alphabet.add(new JButton("w"));
		alphabet.add(new JButton("x"));
		alphabet.add(new JButton("c"));
		alphabet.add(new JButton("v"));
		alphabet.add(new JButton("b"));
		alphabet.add(new JButton("n"));

		alphabet.add(new JButton("Espace"));
		alphabet.add(new JButton("Efface"));

		alphabetPanel.setLayout(new GridLayout(3,10));
		for (int i = 0; i < 10; i++)
			alphabetPanel.add(alphabet.get(i));
		for(int i=10;i<20;i++)
			alphabetPanel.add(alphabet.get(i));
		alphabetPanel.add(boutonVide1);
		for (int i=20;i<26;i++)
			alphabetPanel.add(alphabet.get(i));
		alphabetPanel.add(boutonVide2);
		for (int i=26; i<28;i++)
			alphabetPanel.add(alphabet.get(i));


		this.setVisible(true);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(alphabetPanel, BorderLayout.CENTER);

		boutonsDuBas.setLayout(new FlowLayout());
		boutonsDuBas.add(terminerButton);
		boutonsDuBas.add(annulerButton);
		boutonsDuBas.add(quitterButton);

		getContentPane().add(boutonsDuBas, BorderLayout.PAGE_END);
		getContentPane().add(texte, BorderLayout.PAGE_START);
		
		for (JButton bouton : alphabet){
			bouton.addActionListener(this);
			addHandListener(bouton);
			}
		terminerButton.addActionListener(this);
		annulerButton.addActionListener(this);
		quitterButton.addActionListener(this);
		addHandListener(terminerButton);
		addHandListener(quitterButton);
		addHandListener(annulerButton);

	}

	public void deleteChar() {
		if(name.length()!=0){
		name=name.substring(0,name.length()-1);
		getContentPane().remove(texte);
		texte = new JTextArea("\n \t \t Votre nom est : " + name + " \n\n");
		getContentPane().add(texte,BorderLayout.PAGE_START);
		setSize(1001, 800);	this.setExtendedState(Frame.MAXIMIZED_BOTH);

		}

	}
	public void terminer(){
		FenetreIdentification fen = new FenetreIdentification();
		fen.nouveauCompte(name);
		fen.dispose();
		new FenetreIdentification();
		this.dispose();
		if(FenetreIdentification.jopp != null)
			FenetreIdentification.jopp.setVisible(true);
			
	}
	public void annuler(){
		new FenetreIdentification();
		this.dispose();
		
	}

	public void actionPerformed(ActionEvent e) {
		for (JButton button : alphabet) {
			if (e.getSource() == button) {
				if (button.getText().equals("Efface")) {
					deleteChar();
				} 
				else if(button.getText().equals("Espace")){
					name+=" ";
					getContentPane().remove(texte);
					texte = new JTextArea("\n \t \t Votre nom est : " + name + " \n\n");
					getContentPane().add(texte,BorderLayout.PAGE_START);
					setSize(1001, 800);	this.setExtendedState(Frame.MAXIMIZED_BOTH);
					
					
				}
				else if(name.equals("")|| name.substring(name.length()-1, name.length()).equals(" ")){
					name = name + button.getText().toUpperCase();
					getContentPane().remove(texte);
					texte = new JTextArea("\n \t \t Votre nom est : " + name + " \n\n");
					getContentPane().add(texte,BorderLayout.PAGE_START);
					setSize(1001, 800);	this.setExtendedState(Frame.MAXIMIZED_BOTH);
				}
				
				else {
					name = name + button.getText();
					getContentPane().remove(texte);
					texte = new JTextArea("\n \t \t Votre nom est : " + name + " \n\n");
					getContentPane().add(texte,BorderLayout.PAGE_START);
					setSize(1001, 800);	this.setExtendedState(Frame.MAXIMIZED_BOTH);
					
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
	
	@Override
	public void actionBouton(int numero){
		if(numero>=0 && numero<28)
			alphabet.get(numero).doClick();
		else{
			switch (numero) {
			case 28:
				terminerButton.doClick();
				break;
			case 29 : 
				annulerButton.doClick();
				break;

			}
		}
	}

	public static void main(String[] args) {
		new FenetreEntreeNom();
	}

}
