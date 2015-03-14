package fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
	private JButton terminerButton = new JButton("OK"),
					annulerButton = new JButton("Retour"),
					quitterButton = new JButton("Quiter");
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
		
		// 1ère ligne du clavier azert
		JButton a=new JButton("a");
		a.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(a);
		
		/*JButton b=new JButton("b");
		b.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(b);*/
		
		JButton z=new JButton("z");
		z.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(z);
		
		JButton e=new JButton("e");
		e.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(e);
		
		JButton r=new JButton("r");
		r.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(r);
		
		JButton t=new JButton("t");
		t.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(t);
		
		JButton y=new JButton("y");
		y.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(y);
		
		JButton u=new JButton("u");
		u.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(u);
		
		JButton ibuton=new JButton("i");
		ibuton.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(ibuton);
		
		JButton o=new JButton("o");
		o.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(o);
		JButton p=new JButton("p");
		p.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(p);
		
		//2ème ligne du clavier azerty
		
		JButton q=new JButton("q");
		q.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(q);
		JButton s=new JButton("s");
		s.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(s);
		JButton d=new JButton("d");
		d.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(d);
		JButton f=new JButton("f");
		f.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(f);
		JButton g=new JButton("g");
		g.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(g);
		JButton h=new JButton("h");
		h.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(h);
		JButton j=new JButton("j");
		j.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(j);JButton k=new JButton("k");
		k.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(k);JButton l=new JButton("l");
		l.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(l);JButton m=new JButton("m");
		m.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(m);
		

		//3ème ligne du clavier azerty
		
		JButton w=new JButton("w");
		w.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(w);
		JButton x=new JButton("x");
		x.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(x);
		JButton c=new JButton("c");
		c.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(c);
		
		JButton v=new JButton("v");
		v.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(v);
		JButton b=new JButton("b");
		b.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(b);
		JButton n=new JButton("n");
		n.setFont(new Font("Arial",Font.PLAIN,40));
		alphabet.add(n);
	

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
}
