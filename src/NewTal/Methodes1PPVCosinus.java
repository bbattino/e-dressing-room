package NewTal;
import java.util.ArrayList;



public class Methodes1PPVCosinus 
{
	// toute la méthode est impléentée dans l meme classe
	// creer plusieurs classes pour chaque objet ici comme une classe base de données, une classe tableau d'analyse... rend les transferts entre classes inutilement diffi

	public static String[] B ; 
	// B est un tableau de taille N qui contiendra les mots de notre base de données 

	protected static int N ;
		// valeur a changer. 
		// N est le nombre de lemmes ou de mots
	

	
	public static void remplirTableauB () 
	{
		ArrayList<String> listeFinale = new ArrayList<String>();
		listeFinale.add("je");
		
		for ( String phrase : toutesLesPhrases)
		{
			ArrayList<String> mot = creerCommande(phrase);
			
			for(String motDeVoc : mot)
			{
				boolean testappartenance = false;
				for(String motExistant : listeFinale)
				{
					if((motExistant.equals(motDeVoc)))
						testappartenance = true ;
				}
				if (!testappartenance) 
					listeFinale.add(motDeVoc);
			}
		}
		B = new String[listeFinale.size()];
		for (int i=0 ; i < listeFinale.size() ; i++) 
			B[i] = listeFinale.get(i);
		
		N = B.length ;
		
	}
	
	
	
	public static ArrayList<String> toutesLesPhrases= new ArrayList<String>();
		// private normalement mais pour y accéder dans les classes de création de tableaudetableaux
	
	protected static int M ;
		// M est le nombre de commandes vocales que l'on a dans la base de donnees
	
	
	public Methodes1PPVCosinus()
	{
		// phrase pour les boutons de l'interface
		toutesLesPhrases.add("commencer");
		toutesLesPhrases.add("on commence");
		toutesLesPhrases.add("quitter");
		toutesLesPhrases.add("retour");
		toutesLesPhrases.add("page précédente");
		toutesLesPhrases.add("précédent");
		toutesLesPhrases.add("suivant");
		toutesLesPhrases.add("ok");
		toutesLesPhrases.add("trois");
		
		// phrase d'acc�s au catalogue 
		toutesLesPhrases.add("Accéder au catalogue");
		toutesLesPhrases.add("Retourner au catalogue");
		toutesLesPhrases.add("Voir le catalogue");
		toutesLesPhrases.add("consulter le catalogue");
		toutesLesPhrases.add("J'aimerais accéder au catalogue");
		toutesLesPhrases.add("J'aimerais retourner au catalogue");
		toutesLesPhrases.add("J'aimerais voir le catalogue");
		toutesLesPhrases.add("J'aimerais consulter le catalogue");
	
		
		//commande avec le panier
		toutesLesPhrases.add("Ajouter au panier");
		toutesLesPhrases.add("retirer du panier");
		toutesLesPhrases.add("enlever du panier");
		toutesLesPhrases.add("Accéder au panier");
		toutesLesPhrases.add("Voir le panier");
		toutesLesPhrases.add("Consulter le panier");
		toutesLesPhrases.add("Payer");
		toutesLesPhrases.add("Payer le panier");
		toutesLesPhrases.add("Régler ma commande");
		toutesLesPhrases.add("paiement");
		
		
		//commande pour visualiser un v�tement
		toutesLesPhrases.add("Demander un vêtement");
		toutesLesPhrases.add("Changer de vêtement");
		toutesLesPhrases.add("Changer la couleur");
		toutesLesPhrases.add("Changer la taille");
		toutesLesPhrases.add("Ce modèle est-il disponible dans d'autres coloris?");
		toutesLesPhrases.add("essayer");
		toutesLesPhrases.add("essayer le vêtement");
		toutesLesPhrases.add("Changer la taille");
		toutesLesPhrases.add("pull");
		toutesLesPhrases.add("Changer de pull");
		toutesLesPhrases.add("essayer le pull");
		
		
		
		M = toutesLesPhrases.size();
			// M est le nombre de commandes vocales que l'on a dans la base de donnees
		
		
		remplirTableauB() ;
		tableauDeTableaux = new int[N][M];
		remplirTableauDeTableaux() ;
		
	}
	
	
	private static int[][] tableauDeTableaux ;
	
	public static void remplirTableauDeTableaux()
	{
		for (int j=0;j<toutesLesPhrases.size();j++)
			{
				int[] T = TableauAnalyse(creerCommande(toutesLesPhrases.get(j)));
				tableauDeTableaux[j]=T;
			}
	}
			
	
	
	public static ArrayList<String> creerCommande(String phrase)
	{
		ArrayList<String> liste = new ArrayList<String>();
		int longueur =phrase.length();
		String currentWord = "";
		for (int i=0 ; i<longueur ; i++)
		{
			String caractere=phrase.substring(i , i+1).toLowerCase();
			if(caractere.equals(" "))
			{
				if(!(currentWord.equals(" ")))
				{
				liste.add(currentWord);
				currentWord="";
				}
			}
			else if (caractere.equals("'"))
			{
				currentWord+=caractere;
				liste.add(currentWord);
				currentWord="";
			}
			else if (caractere.equals("."))
			{
				liste.add(currentWord);
				currentWord="";
			}
			else if (caractere.equals("!"))
			{
				liste.add(currentWord);
				currentWord="";
			}
			else if (caractere.equals("?"))
			{
				liste.add(currentWord);
				currentWord="";
			}
			else if (caractere.equals(";"))
			{
				liste.add(currentWord);
				currentWord="";
			}
			else if (caractere.equals(","))
			{
				liste.add(currentWord);
				currentWord="";
			}
			else if (caractere.equals(":"))
			{
				liste.add(currentWord);
				currentWord="";
			}
			else
			{
				currentWord+=caractere;
			}
				
		}
		liste.add(currentWord);
		
		return liste;
	}


	
	public static void initT(int[] T)
	{
		//initialise les cases du tableau T a zero pour éviter les mauvaises surprises
		for (int i=0 ; i<N ; i++ )
			T[i] = 0;
	}
	
	
	public static int reconnaissanceMot(String mot)
	{
		// cette méthode permet de savoir si un mot est dans la base de donnees B 
		// elle retourne le numero de la case dans laquelle est le lemme
		// si le mot n'est pas dans le mot renvoie N
		
		int i = 0;
		
		while (!mot.toLowerCase().equals(B[i].toLowerCase()) && i!=(N-1))
			i+=1;
		
		if( i==(N-1) )
			i= (!mot.toLowerCase().equals(B[i].toLowerCase()))? N : (N-1) ;
		
		return i;
	}

	
	public static void incrementationTableau(int i, int[] tab)
	{
		if (i>=0 && i<N)
				tab[i] += 1;
	}
	
	
	public static int[] TableauAnalyse(ArrayList<String> commande) 
	{
		 int[] tableau = new int[N];
		initT(tableau);
		for (String mot: commande)
			incrementationTableau(reconnaissanceMot(mot),tableau);	
		return tableau;
	}

	
	public static double comparateurDeDeuxTableaux (int[] tab1, int[] tab2)
	{
		double normTab1 = 0;
		double normTab2 = 0;
		double ps = 0;
		
		int i;
		for (i=0 ; i<tab1.length ; i++)
			 normTab1 += ((tab1[i])*(tab1[i]));
		
		int j;
		for (j=0 ; j<tab2.length ; j++)
			 normTab2 += ((tab2[j])*(tab2[j]));
		
		int k;
		for (k=0 ; k<N ; k++)
			 ps += ((tab1[k])*(tab2[k]));
		
		return ((ps+0.0)/(Math.sqrt(normTab1*normTab2)));
			// renvoie le ps norme
	}
	
	
	public static int tableauLePlusProche(int[] tabl)
	{
		int i;
		int indicePlusProche=0; 
			// indice du tableau de la base de données le plus proche du tableau tabl
		double psPlusGrand;
			//ps norme de tabl et du tableau le plus proche de tabl
		psPlusGrand = comparateurDeDeuxTableaux(tabl , tableauDeTableaux[0]);
		
		for (i=1 ; i<M ; i++)
			if (psPlusGrand < comparateurDeDeuxTableaux(tabl , tableauDeTableaux[i]) )
			{
				psPlusGrand = comparateurDeDeuxTableaux(tabl, tableauDeTableaux[i]); 
				indicePlusProche = i ;
			}	
		
		return (indicePlusProche);
		
	}
	
	
	
	public static int correspondanceClasseAction(int indice)
	{
		int indiceClasseAction;
		
		switch(indice)
		{
		case 0 :
			indiceClasseAction = 0;
		break;
		
		case 1 :
			indiceClasseAction = 0;
		break;
		
		case 3 :
			indiceClasseAction = 10;
		break;
		
		case 2 :
			indiceClasseAction = 2;
		break;
		
		case 8 :
			indiceClasseAction = 5;
		break;
		
		case 7 :
			indiceClasseAction = 22;
		break;
		
		case 6 :
			indiceClasseAction = 17;
		break;
		
		case 35 :
			indiceClasseAction = 13;
		break;
		
		case 32 :
			indiceClasseAction = 20;
		break;
		
		case 33 :
			indiceClasseAction = 20;
		break;
		
		case 37 :
			indiceClasseAction = 20;
		break;
		
		
		default :
			indiceClasseAction = -1;
			
		
		
		}
		
		return (indiceClasseAction);
				
	}
}
