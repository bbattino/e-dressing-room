package tAL;

import java.util.ArrayList;

public class CreateurTableauDeTableaux {

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		String parole="essayer le pull";
		
		
		int[] T = new int[MethodeDeBase.N]; 
		// T est le tableau d'analyse du mot qui contiendra les nombres d'occurences des mots de la phrase a analyser

		
		ArrayList<String> listeMots = MethodeDeBase.creerCommande(parole); 
		/** commande est la arraylist de mots de la phrase a analyser*/
	
		for(String mot:listeMots)
			System.out.println(mot);
		
		
		T = MethodeDeBase.TableauAnalyse(listeMots);
		
		System.out.print("{" + T[0]);
		
		for(int i=1; i<T.length;i++)
			System.out.print("," + T[i]);
		System.out.print("}");
	}

}
