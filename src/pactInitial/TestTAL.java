package pactInitial;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import tAL.MethodeDeBase;
import fenetres.FenetreDepart;

public class TestTAL {
	
	public static void main(String[] args){
		new FenetreDepart();
		new TestTAL();


	
}
	public TestTAL(){
		String parole = JOptionPane.showInputDialog(null, "Commande vocale ?", "TAL",
				JOptionPane.QUESTION_MESSAGE);
		ArrayList<String> commande = MethodeDeBase.creerCommande(parole); 

	int[] T = MethodeDeBase.TableauAnalyse(commande);
	int indiceCommande = MethodeDeBase.tableauLePlusProche(T);
	int indiceAction = MethodeDeBase.correspondanceClasseAction(indiceCommande);
	Main.actionEventAudio(indiceAction);
	new TestTAL();

		
		
	}
}
