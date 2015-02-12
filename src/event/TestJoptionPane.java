package event;

import fenetres.JOptionPanePerso;

public class TestJoptionPane {

	public static void main(String[] args) {
		String[] s= {"oui","non"};
		Runnable[] r = {new Runnable() {public void run() {	System.out.println("oui");}},
				new Runnable() {public void run() {	System.out.println("non");	}}};
		
		
		
		new JOptionPanePerso("salut", s, r);
	}

}
