package NewTal;
import java.util.ArrayList;


public class Methodes1PPVCosinus 
{
	// toute la méthode est implémentée dans le meme classe
	// creer plusieurs classes pour chaque objet ici comme une classe base de données, une classe tableau d'analyse... rend les transferts entre classes inutilement diffi

	public static String[] B ; 
	// B est un tableau de taille N qui contiendra les mots de notre base de données 

	public static int N ;
		// valeur a changer. 
		// N est le nombre de lemmes ou de mots
	
	
	
	public static void remplirTableauB () 
	{
		ArrayList<String> listeFinale = new ArrayList<String>();
		listeFinale.add("panier");
		
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
				/**  phrase pour les boutons de l'interface  */
		
		toutesLesPhrases.add("commencer");
		toutesLesPhrases.add("on commence");
		toutesLesPhrases.add("commençons");
		toutesLesPhrases.add("allons y");
		toutesLesPhrases.add("J'aimerais commencer");
		toutesLesPhrases.add("J'aimerais bien commencer");
		toutesLesPhrases.add("Je veux commencer");
		toutesLesPhrases.add("Je veux bien commencer");
		toutesLesPhrases.add("Je voudrais commencer");
		toutesLesPhrases.add("Je voudrais bien commencer");
		toutesLesPhrases.add("Je souhaite commencer");
		toutesLesPhrases.add("Est ce que je peux commencer");
		toutesLesPhrases.add("fais moi commencer");
		
		toutesLesPhrases.add("a propos");
		toutesLesPhrases.add("voir a propos");
		toutesLesPhrases.add("aller dans a propos");
		toutesLesPhrases.add("J'aimerais aller dans a propos");
		toutesLesPhrases.add("J'aimerais bien aller dans a propos");
		toutesLesPhrases.add("Je veux aller dans a propos");
		toutesLesPhrases.add("Je veux bien aller dans a propos");
		toutesLesPhrases.add("Je voudrais aller dans a propos");
		toutesLesPhrases.add("Je voudrais bien aller dans a propos");
		toutesLesPhrases.add("Je souhaite aller dans a propos");
		
		toutesLesPhrases.add("quitter");
		toutesLesPhrases.add("J'aimerais quitter");
		toutesLesPhrases.add("J'aimerais bien quitter");
		toutesLesPhrases.add("Je veux quitter");
		toutesLesPhrases.add("Je veux bien quitter");
		toutesLesPhrases.add("Je voudrais quitter");
		toutesLesPhrases.add("Je voudrais bien quitter");
		toutesLesPhrases.add("Je souhaite quitter");
		toutesLesPhrases.add("Est ce que je peux quitter");
		toutesLesPhrases.add("fais moi quitter");
		
		toutesLesPhrases.add("arrêter");
		toutesLesPhrases.add("J'aimerais arrêter");
		toutesLesPhrases.add("J'aimerais bien arrêter");
		toutesLesPhrases.add("Je veux arrêter");
		toutesLesPhrases.add("Je veux bien arrêter");
		toutesLesPhrases.add("Je voudrais arrêter");
		toutesLesPhrases.add("Je voudrais bien arrêter");
		toutesLesPhrases.add("Je souhaite arrêter");
		toutesLesPhrases.add("Est ce que je peux arrêter");
		toutesLesPhrases.add("fais moi arrêter");
		
		toutesLesPhrases.add("zéro");
		toutesLesPhrases.add("un");
		toutesLesPhrases.add("deux");
		toutesLesPhrases.add("trois");
		toutesLesPhrases.add("quatre");
		toutesLesPhrases.add("cinq");
		toutesLesPhrases.add("six");
		toutesLesPhrases.add("sept");   //50
		toutesLesPhrases.add("huit");
		toutesLesPhrases.add("neuf");
		
		toutesLesPhrases.add("nouveau compte");   
		toutesLesPhrases.add("créer un nouveau compte");
		toutesLesPhrases.add("J'aimerais créer un nouveau compte");
		toutesLesPhrases.add("J'aimerais bien créer un nouveau compte");
		toutesLesPhrases.add("Je veux créer un nouveau compter");
		toutesLesPhrases.add("Je veux bien créer un nouveau compte");
		toutesLesPhrases.add("Je voudrais créer un nouveau compte");
		toutesLesPhrases.add("Je voudrais bien créer un nouveau compte");
		toutesLesPhrases.add("Je souhaite créer un nouveau compte");
		
		toutesLesPhrases.add("ouvrir un nouveau compte");
		toutesLesPhrases.add("J'aimerais ouvrir un nouveau compte");
		toutesLesPhrases.add("J'aimerais bien ouvrir un nouveau compte");
		toutesLesPhrases.add("Je veux ouvrir un nouveau compter");
		toutesLesPhrases.add("Je veux bien ouvrir un nouveau compte");
		toutesLesPhrases.add("Je voudrais ouvrir un nouveau compte");
		toutesLesPhrases.add("Je voudrais bien ouvrir un nouveau compte");
		toutesLesPhrases.add("Je souhaite ouvrir un nouveau compte");
		
		toutesLesPhrases.add("supprimer compte");
		toutesLesPhrases.add("supprimer le compte");
		toutesLesPhrases.add("J'aimerais supprimer le compte");
		toutesLesPhrases.add("J'aimerais bien supprimer le compte");
		toutesLesPhrases.add("Je veux supprimer le compter");
		toutesLesPhrases.add("Je veux bien supprimer le compte");
		toutesLesPhrases.add("Je voudrais supprimer le compte");
		toutesLesPhrases.add("Je voudrais bien supprimer le compte");
		toutesLesPhrases.add("Je souhaite supprimer le compte");		
		
		
		toutesLesPhrases.add("a");
		toutesLesPhrases.add("z");		//80
		toutesLesPhrases.add("e");
		toutesLesPhrases.add("r");
		toutesLesPhrases.add("t");
		toutesLesPhrases.add("y");
		toutesLesPhrases.add("u");
		toutesLesPhrases.add("i");
		toutesLesPhrases.add("o");
		toutesLesPhrases.add("p");
		toutesLesPhrases.add("q");
		toutesLesPhrases.add("s");
		toutesLesPhrases.add("d");
		toutesLesPhrases.add("f");
		toutesLesPhrases.add("g");
		toutesLesPhrases.add("h");
		toutesLesPhrases.add("j");
		toutesLesPhrases.add("k");
		toutesLesPhrases.add("l");
		toutesLesPhrases.add("m");
		toutesLesPhrases.add("w");
		toutesLesPhrases.add("x");
		toutesLesPhrases.add("c");
		toutesLesPhrases.add("v");
		toutesLesPhrases.add("b");
		toutesLesPhrases.add("n");  
		toutesLesPhrases.add("espace");		// 105
		toutesLesPhrases.add("effacer");
		toutesLesPhrases.add("efface");
		toutesLesPhrases.add("J'aimerais effacer");
		toutesLesPhrases.add("J'aimerais bien effacer");
		toutesLesPhrases.add("Je veux effacer");
		toutesLesPhrases.add("Je veux bien effacer");
		toutesLesPhrases.add("Je voudrais effacer");
		toutesLesPhrases.add("Je voudrais bien effacer");
		toutesLesPhrases.add("Je souhaite effacer");	
		
		toutesLesPhrases.add("retour");
		toutesLesPhrases.add("page précédente");
		toutesLesPhrases.add("revenir");
		toutesLesPhrases.add("revenir à la page précédente");
		toutesLesPhrases.add("J'aimerais revenir à la page précédente");
		toutesLesPhrases.add("J'aimerais bien revenir à la page précédente");
		toutesLesPhrases.add("Je veux revenir à la page précédente");
		toutesLesPhrases.add("Je veux bien revenir à la page précédente");
		toutesLesPhrases.add("Je voudrais revenir à la page précédente");
		toutesLesPhrases.add("Je voudrais bien revenir à la page précédente");
		toutesLesPhrases.add("Je souhaite revenir à la page précédente");
		toutesLesPhrases.add("Est ce que je peux revenir à la page précédente");
		
				
		toutesLesPhrases.add("précédent");
		toutesLesPhrases.add("vêtement précédent");
		toutesLesPhrases.add("voir le vêtement précédent");
		toutesLesPhrases.add("J'aimerais voir le vêtement précédent");
		toutesLesPhrases.add("J'aimerais bien voir le vêtement précédent");
		toutesLesPhrases.add("Je veux voir le vêtement précédent");
		toutesLesPhrases.add("Je veux bien voir le vêtement précédent");
		toutesLesPhrases.add("Je voudrais voir le vêtement précédent");
		toutesLesPhrases.add("Je voudrais bien voir le vêtement précédent");
		toutesLesPhrases.add("Je souhaite voir le vêtement précédent");
		toutesLesPhrases.add("Est ce que je peux voir le vêtement précédent");
		toutesLesPhrases.add("fais moi voir le vêtement précédent");
		
		toutesLesPhrases.add("suivant");
		toutesLesPhrases.add("vêtement suivant");
		toutesLesPhrases.add("voir le vêtement suivant");
		toutesLesPhrases.add("J'aimerais voir le vêtement suivant");
		toutesLesPhrases.add("J'aimerais bien voir le vêtement suivant");
		toutesLesPhrases.add("Je veux voir le vêtement suivant");
		toutesLesPhrases.add("Je veux bien voir le vêtement suivant");
		toutesLesPhrases.add("Je voudrais voir le vêtement suivant");
		toutesLesPhrases.add("Je voudrais bien voir le vêtement suivant");
		toutesLesPhrases.add("Je souhaite voir le vêtement suivant");
		toutesLesPhrases.add("Est ce que je peux voir le vêtement suivant");
		toutesLesPhrases.add("fais moi voir le vêtement suivant");		//150
		
		toutesLesPhrases.add("ok");
		toutesLesPhrases.add("valider");
		toutesLesPhrases.add("oui");
		toutesLesPhrases.add("ouais");
		toutesLesPhrases.add("ouais ouais");
		toutesLesPhrases.add("non");
		toutesLesPhrases.add("non non");
		
		
	
				/** phrase d'accès au catalogue  */
		
		toutesLesPhrases.add("Accéder au catalogue");		// 158
		toutesLesPhrases.add("Retourner au catalogue");
		toutesLesPhrases.add("Voir le catalogue");
		toutesLesPhrases.add("consulter le catalogue");
		toutesLesPhrases.add("aller sur le catalogue");
		toutesLesPhrases.add("aller au catalogue");
		toutesLesPhrases.add("J'aimerais accéder au catalogue");
		toutesLesPhrases.add("J'aimerais retourner au catalogue");
		toutesLesPhrases.add("J'aimerais voir le catalogue");
		toutesLesPhrases.add("J'aimerais aller voir le catalogue");
		toutesLesPhrases.add("J'aimerais consulter le catalogue");
		toutesLesPhrases.add("Je voudrais accéder au catalogue");
		toutesLesPhrases.add("Je voudrais retourner au catalogue");
		toutesLesPhrases.add("Je voudrais voir le catalogue");
		toutesLesPhrases.add("Je voudrais aller voir le catalogue");
		toutesLesPhrases.add("Je voudrais consulter le catalogue");
		toutesLesPhrases.add("Je veux accéder au catalogue");
		toutesLesPhrases.add("Je veux retourner au catalogue");
		toutesLesPhrases.add("Je veux voir le catalogue");
		toutesLesPhrases.add("Je veux aller voir le catalogue");
		toutesLesPhrases.add("Je veux consulter le catalogue");
		toutesLesPhrases.add("Je souhaite accéder au catalogue");
		toutesLesPhrases.add("Je souhaite retourner au catalogue");
		toutesLesPhrases.add("Je souhaite voir le catalogue");
		toutesLesPhrases.add("Je souhaite aller voir le catalogue");
		toutesLesPhrases.add("Je souhaite consulter le catalogue");
		toutesLesPhrases.add("J'aimerais bien accéder au catalogue");
		toutesLesPhrases.add("J'aimerais bien retourner au catalogue");
		toutesLesPhrases.add("J'aimerais bien voir le catalogue");
		toutesLesPhrases.add("J'aimerais bien aller voir le catalogue");
		toutesLesPhrases.add("J'aimerais bien consulter le catalogue");
		toutesLesPhrases.add("Je voudrais bien accéder au catalogue");
		toutesLesPhrases.add("Je voudrais bien retourner au catalogue");
		toutesLesPhrases.add("Je voudrais bien voir le catalogue");
		toutesLesPhrases.add("Je voudrais bien aller voir le catalogue");
		toutesLesPhrases.add("Je voudrais bien consulter le catalogue");
		toutesLesPhrases.add("Je veux bien accéder au catalogue");
		toutesLesPhrases.add("Je veux bien retourner au catalogue");
		toutesLesPhrases.add("Je veux bien voir le catalogue");
		toutesLesPhrases.add("Je veux bien aller voir le catalogue");
		toutesLesPhrases.add("Je veux bien consulter le catalogue");
		toutesLesPhrases.add("Est ce que je peux voir le catalogue");
		toutesLesPhrases.add("fais moi voir le catalogue"); 		// 200
		toutesLesPhrases.add("Est ce que je peux consulter le catalogue");
		toutesLesPhrases.add("fais moi consulter le catalogue");
		toutesLesPhrases.add("Est ce que je peux accéder au catalogue");
		toutesLesPhrases.add("fais moi accéder au catalogue");
		
		toutesLesPhrases.add("quitter le catalogue");
		toutesLesPhrases.add("J'aimerais quitter le catalogue");
		toutesLesPhrases.add("J'aimerais bien quitter le catalogue");
		toutesLesPhrases.add("Je veux quitter le catalogue");
		toutesLesPhrases.add("Je veux bien quitter le catalogue");
		toutesLesPhrases.add("Je voudrais quitter le catalogue");
		toutesLesPhrases.add("Je voudrais bien quitter le catalogue");
		toutesLesPhrases.add("Je souhaite quitter le catalogue");

		
				/**commande avec le panier  */
				 
		toutesLesPhrases.add("Ajouter au panier"); 		// 213
		toutesLesPhrases.add("J'aimerais ajouter au panier");
		toutesLesPhrases.add("J'aimerais bien ajouter au panier");
		toutesLesPhrases.add("Je veux ajouter au panier");
		toutesLesPhrases.add("Je veux bien ajouter au panier");
		toutesLesPhrases.add("Je voudrais ajouter au panier");
		toutesLesPhrases.add("Je voudrais bien ajouter au panier");
		toutesLesPhrases.add("Je souhaite ajouter au panier");
		
		toutesLesPhrases.add("Rajouter au panier");
		toutesLesPhrases.add("J'aimerais rajouter au panier");
		toutesLesPhrases.add("J'aimerais bien rajouter au panier");
		toutesLesPhrases.add("Je veux rajouter au panier");
		toutesLesPhrases.add("Je veux bien rajouter au panier");
		toutesLesPhrases.add("Je voudrais rajouter au panier");
		toutesLesPhrases.add("Je voudrais bien rajouter au panier");
		toutesLesPhrases.add("Je souhaite rajouter au panier");
		
		toutesLesPhrases.add("mettre dans le panier");
		toutesLesPhrases.add("J'aimerais mettre dans le panier");
		toutesLesPhrases.add("J'aimerais bien mettre dans le panier");
		toutesLesPhrases.add("Je veux mettre dans le panier");
		toutesLesPhrases.add("Je veux bien mettre dans le panier");
		toutesLesPhrases.add("Je voudrais mettre dans le panier");
		toutesLesPhrases.add("Je voudrais bien mettre dans le panier");
		toutesLesPhrases.add("Je souhaite mettre dans le panier");
		
		toutesLesPhrases.add("Accéder au panier");		// 237
		toutesLesPhrases.add("J'aimerais accéder au panier");
		toutesLesPhrases.add("J'aimerais bien accéder au panier");
		toutesLesPhrases.add("Je veux accéder au panier");
		toutesLesPhrases.add("Je veux bien accéder au panier");
		toutesLesPhrases.add("Je voudrais accéder au panier");
		toutesLesPhrases.add("Je voudrais bien accéder au panier");
		toutesLesPhrases.add("Je souhaite accéder au panier");
		
		toutesLesPhrases.add("Voir le panier");
		toutesLesPhrases.add("J'aimerais voir le panier");
		toutesLesPhrases.add("J'aimerais bien voir le panier");
		toutesLesPhrases.add("Je veux voir le panier");
		toutesLesPhrases.add("Je veux bien voir le panier");
		toutesLesPhrases.add("Je voudrais voir le panier"); 		//250
		toutesLesPhrases.add("Je voudrais bien voir le panier");
		toutesLesPhrases.add("Je souhaite voir le panier");
		toutesLesPhrases.add("J'aimerais aller voir le panier");
		toutesLesPhrases.add("J'aimerais bien aller voir le panier");
		toutesLesPhrases.add("Je veux aller voir le panier");
		toutesLesPhrases.add("Je veux bien aller voir le panier");
		toutesLesPhrases.add("Je voudrais aller voir le panier");
		toutesLesPhrases.add("Je voudrais bien aller voir le panier");
		toutesLesPhrases.add("Je souhaite aller voir le panier");
		toutesLesPhrases.add("Est ce que je peux voir le panier");
		
		toutesLesPhrases.add("Aller au panier");
		toutesLesPhrases.add("J'aimerais aller au panier");
		toutesLesPhrases.add("J'aimerais bien aller au panier");
		toutesLesPhrases.add("Je veux aller au panier");
		toutesLesPhrases.add("Je veux bien aller au panier");
		toutesLesPhrases.add("Je voudrais aller au panier");
		toutesLesPhrases.add("Je voudrais bien aller au panier");
		toutesLesPhrases.add("Je souhaite aller au panier");
		
		toutesLesPhrases.add("Consulter le panier");
		toutesLesPhrases.add("J'aimerais consulter le panier");
		toutesLesPhrases.add("J'aimerais bien consulter le panier");
		toutesLesPhrases.add("Je veux consulter le panier");
		toutesLesPhrases.add("Je veux bien consulter le panier");
		toutesLesPhrases.add("Je voudrais consulter le panier");
		toutesLesPhrases.add("Je voudrais bien consulter le panier");
		toutesLesPhrases.add("Je souhaite consulter le panier");
		toutesLesPhrases.add("Est ce que je peux consulter le panier");
		
		toutesLesPhrases.add("retirer du panier");
		toutesLesPhrases.add("J'aimerais retirer du panier");
		toutesLesPhrases.add("J'aimerais bien retirer du panier");
		toutesLesPhrases.add("Je veux retirer du panier");
		toutesLesPhrases.add("Je veux bien retirer du panier");
		toutesLesPhrases.add("Je voudrais retirer du panier");
		toutesLesPhrases.add("Je voudrais bien retirer du panier");
		toutesLesPhrases.add("Je souhaite retirer du panier");
		
		toutesLesPhrases.add("enlever du panier");
		toutesLesPhrases.add("J'aimerais enlever du panier");
		toutesLesPhrases.add("J'aimerais bien enlever du panier");
		toutesLesPhrases.add("Je veux enlever du panier");
		toutesLesPhrases.add("Je veux bien enlever du panier");
		toutesLesPhrases.add("Je voudrais enlever du panier");
		toutesLesPhrases.add("Je voudrais bien enlever du panier");
		toutesLesPhrases.add("Je souhaite enlever du panier");
		
		toutesLesPhrases.add("supprimer du panier");
		toutesLesPhrases.add("J'aimerais supprimer du panier");
		toutesLesPhrases.add("J'aimerais bien supprimer du panier");
		toutesLesPhrases.add("Je veux supprimer du panier");
		toutesLesPhrases.add("Je veux bien supprimer du panier");
		toutesLesPhrases.add("Je voudrais supprimer du panier");
		toutesLesPhrases.add("Je voudrais bien supprimer du panier");		//300
		toutesLesPhrases.add("Je souhaite supprimer du panier");
		
		// toutesLesPhrases.add("Payer"); 			L'interface graphique ne prend pas en compte le paiement
		// toutesLesPhrases.add("Payer le panier");
		// toutesLesPhrases.add("Régler ma commande");
		// toutesLesPhrases.add("paiement");
		
		
				/**commande pour visualiser un vêtement */
		
		toutesLesPhrases.add("pull");
		toutesLesPhrases.add("J'aimerais un pull");
		toutesLesPhrases.add("J'aimerais bien un pull");
		toutesLesPhrases.add("Je veux un pull");
		toutesLesPhrases.add("Je veux bien un pull");
		toutesLesPhrases.add("Je voudrais un pull");
		toutesLesPhrases.add("Je voudrais bien un pull");
		toutesLesPhrases.add("Je souhaite un pull");
		toutesLesPhrases.add("essayer un pull");
		toutesLesPhrases.add("J'aimerais essayer un pull");
		toutesLesPhrases.add("J'aimerais bien essayer un pull");
		toutesLesPhrases.add("Je veux essayer un pull");
		toutesLesPhrases.add("Je veux bien essayer un pull");
		toutesLesPhrases.add("Je voudrais essayer un pull");
		toutesLesPhrases.add("Je voudrais bien essayer un pull");
		toutesLesPhrases.add("Je souhaite essayer un pull");
		toutesLesPhrases.add("Est ce que je peux essayer ce pull");
		toutesLesPhrases.add("fais moi essayer ce pull");
		
		toutesLesPhrases.add("robe");
		toutesLesPhrases.add("J'aimerais essayer une robe");
		toutesLesPhrases.add("J'aimerais bien essayer une robe");
		toutesLesPhrases.add("Je veux essayer une robe");
		toutesLesPhrases.add("Je veux bien essayer une robe");
		toutesLesPhrases.add("Je voudrais essayer une robe");
		toutesLesPhrases.add("Je voudrais bien essayer une robe");
		toutesLesPhrases.add("Je souhaite essayer une robe");
		toutesLesPhrases.add("essayer une robe");
		toutesLesPhrases.add("J'aimerais une robe");
		toutesLesPhrases.add("J'aimerais bien une robe");
		toutesLesPhrases.add("Je veux une robe");
		toutesLesPhrases.add("Je veux bien une robe");
		toutesLesPhrases.add("Je voudrais une robe");
		toutesLesPhrases.add("Je voudrais bien une robe");
		toutesLesPhrases.add("Je souhaite une robe");
		toutesLesPhrases.add("Est ce que je peux essayer cette robe");
		toutesLesPhrases.add("fais moi essayer cette robe");
		
		toutesLesPhrases.add("tee shirt");
		toutesLesPhrases.add("J'aimerais un tee shirt");
		toutesLesPhrases.add("J'aimerais bien un tee shirt");
		toutesLesPhrases.add("Je veux un tee shirt");
		toutesLesPhrases.add("Je veux bien un tee shirt");
		toutesLesPhrases.add("Je voudrais un tee shirt");
		toutesLesPhrases.add("Je voudrais bien un tee shirt");
		toutesLesPhrases.add("Je souhaite un tee shirtl");
		toutesLesPhrases.add("essayer un tee shirt");
		toutesLesPhrases.add("J'aimerais essayer un tee shirt");
		toutesLesPhrases.add("J'aimerais bien essayer un tee shirt");
		toutesLesPhrases.add("Je veux essayer un tee shirt");
		toutesLesPhrases.add("Je veux bien essayer un tee shirt");		//350
		toutesLesPhrases.add("Je voudrais essayer un tee shirt");
		toutesLesPhrases.add("Je voudrais bien essayer un tee shirt");
		toutesLesPhrases.add("Je souhaite essayer un tee shirtl");
		toutesLesPhrases.add("Est ce que je peux essayer ce tee shirt");
		toutesLesPhrases.add("fais moi essayer ce tee shirt");
		
		toutesLesPhrases.add("pantalon ");
		toutesLesPhrases.add("J'aimerais un pantalon");
		toutesLesPhrases.add("J'aimerais bien un pantalon");
		toutesLesPhrases.add("Je veux un pantalon");
		toutesLesPhrases.add("Je veux bien un pantalon");
		toutesLesPhrases.add("Je voudrais un pantalon");
		toutesLesPhrases.add("Je voudrais bien un pantalon");
		toutesLesPhrases.add("Je souhaite un pantalon");
		toutesLesPhrases.add("essayer un pantalon");
		toutesLesPhrases.add("J'aimerais essayer un pantalon");
		toutesLesPhrases.add("J'aimerais bien essayer un pantalon");
		toutesLesPhrases.add("Je veux essayer un pantalon");
		toutesLesPhrases.add("Je veux bien essayer un pantalon");
		toutesLesPhrases.add("Je voudrais essayer un pantalon");
		toutesLesPhrases.add("Je voudrais bien essayer un pantalon");
		toutesLesPhrases.add("Je souhaite essayer un pantalon");
		toutesLesPhrases.add("Est ce que je peux essayer ce pantalon");
		toutesLesPhrases.add("fais moi essayer ce pantalon");
		
		
		// toutesLesPhrases.add("Changer la couleur"); 		pas de vetement avec differentes couleurs dans la base de données 
		//toutesLesPhrases.add("Changer la taille");		pas de prise en compte de la taille du vetement, il s'adapte automatiquement
		// toutesLesPhrases.add("Ce modèle est-il disponible dans d’autres coloris?");
		
		toutesLesPhrases.add("essayer");
		toutesLesPhrases.add("J'aimerais essayer");
		toutesLesPhrases.add("J'aimerais bien essayer");
		toutesLesPhrases.add("Je veux essayer");
		toutesLesPhrases.add("Je veux bien essayer");
		toutesLesPhrases.add("Je voudrais essayer");
		toutesLesPhrases.add("Je voudrais bien essayer");
		toutesLesPhrases.add("Je souhaite essayer");
		toutesLesPhrases.add("Est ce que je peux essayer");
		toutesLesPhrases.add("fais moi essayer");
		
		toutesLesPhrases.add("essayer le vêtement");
		toutesLesPhrases.add("J'aimerais essayer le vêtement");
		toutesLesPhrases.add("J'aimerais bien essayer le vêtement");
		toutesLesPhrases.add("Je veux essayer le vêtement");
		toutesLesPhrases.add("Je veux bien essayer le vêtement");
		toutesLesPhrases.add("Je voudrais essayer le vêtement");
		toutesLesPhrases.add("Je voudrais bien essayer le vêtement");
		toutesLesPhrases.add("Je souhaite essayer le vêtement");
		toutesLesPhrases.add("Est ce que je peux essayer le vêtement");
		toutesLesPhrases.add("fais moi essayer le vêtement");
		
		toutesLesPhrases.add("Changer de vêtement");
		toutesLesPhrases.add("J'aimerais changer de vêtement");
		toutesLesPhrases.add("J'aimerais bien changer de vêtement");
		toutesLesPhrases.add("Je veux changer de vêtement");
		toutesLesPhrases.add("Je veux bien changer de vêtement");
		toutesLesPhrases.add("Je voudrais changer de vêtement");
		toutesLesPhrases.add("Je voudrais bien changer de vêtement"); 		// 400
		toutesLesPhrases.add("Je souhaite changer de vêtement");
		
		toutesLesPhrases.add("Changer de pull");
		toutesLesPhrases.add("J'aimerais changer de pull");
		toutesLesPhrases.add("J'aimerais bien changer de pull");
		toutesLesPhrases.add("Je veux changer de pull");
		toutesLesPhrases.add("Je veux bien changer de pull");
		toutesLesPhrases.add("Je voudrais changer de pull");
		toutesLesPhrases.add("Je voudrais bien changer de pull");
		toutesLesPhrases.add("Je souhaite changer de pull");
		
		toutesLesPhrases.add("Changer de pantalon");
		toutesLesPhrases.add("J'aimerais changer de pantalon");
		toutesLesPhrases.add("J'aimerais bien changer de pantalon");
		toutesLesPhrases.add("Je veux changer de pantalon");
		toutesLesPhrases.add("Je veux bien changer de pantalon");
		toutesLesPhrases.add("Je voudrais changer de pantalon");
		toutesLesPhrases.add("Je voudrais bien changer de pantalon");
		toutesLesPhrases.add("Je souhaite changer de pantalon");
		
		toutesLesPhrases.add("Changer de robe");
		toutesLesPhrases.add("J'aimerais changer de robe");
		toutesLesPhrases.add("J'aimerais bien changer de robe");
		toutesLesPhrases.add("Je veux changer de robe");
		toutesLesPhrases.add("Je veux bien changer de robe");
		toutesLesPhrases.add("Je voudrais changer de robe");
		toutesLesPhrases.add("Je voudrais bien changer de robe");
		toutesLesPhrases.add("Je souhaite changer de robe");
		
		toutesLesPhrases.add("Changer de tee shirt");
		toutesLesPhrases.add("J'aimerais changer de tee shirt");
		toutesLesPhrases.add("J'aimerais bien changer de tee shirt");
		toutesLesPhrases.add("Je veux changer de tee shirt");
		toutesLesPhrases.add("Je veux bien changer de tee shirt");
		toutesLesPhrases.add("Je voudrais changer de tee shirt");
		toutesLesPhrases.add("Je voudrais bien changer de tee shirt");
		toutesLesPhrases.add("Je souhaite changer de tee shirt");
		
		toutesLesPhrases.add("retirer le vêtement");
		toutesLesPhrases.add("J'aimerais retirer le vêtement");
		toutesLesPhrases.add("J'aimerais bien retirer le vêtement");
		toutesLesPhrases.add("Je veux retirer le vêtement");
		toutesLesPhrases.add("Je veux bien retirer le vêtement");
		toutesLesPhrases.add("Je voudrais retirer le vêtement");
		toutesLesPhrases.add("Je voudrais bien retirer le vêtement");
		toutesLesPhrases.add("Je souhaite retirer le vêtement");
		toutesLesPhrases.add("retire moi le vêtement");

		toutesLesPhrases.add("Retirer le pull");
		toutesLesPhrases.add("J'aimerais retirer ce pull");
		toutesLesPhrases.add("J'aimerais bien retirer ce pull");
		toutesLesPhrases.add("Je veux retirer ce pull");
		toutesLesPhrases.add("Je veux bien retirer ce pull");
		toutesLesPhrases.add("Je voudrais retirer ce pull");
		toutesLesPhrases.add("Je voudrais bien retirer ce pull");
		toutesLesPhrases.add("Je souhaite retirer ce pull");  		// 450
		
		toutesLesPhrases.add("retirer ce pantalon");
		toutesLesPhrases.add("J'aimerais retirer ce pantalon");
		toutesLesPhrases.add("J'aimerais bien retirer ce pantalon");
		toutesLesPhrases.add("Je veux retirer ce pantalon");
		toutesLesPhrases.add("Je veux bien retirer ce pantalon");
		toutesLesPhrases.add("Je voudrais retirer ce pantalon");
		toutesLesPhrases.add("Je voudrais bien retirer ce pantalon");
		toutesLesPhrases.add("Je souhaite retirer ce pantalon");
		
		toutesLesPhrases.add("retirer ce robe");
		toutesLesPhrases.add("J'aimerais retirer ce robe");
		toutesLesPhrases.add("J'aimerais bien retirer ce robe");
		toutesLesPhrases.add("Je veux retirer ce robe");
		toutesLesPhrases.add("Je veux bien retirer ce robe");
		toutesLesPhrases.add("Je voudrais retirer ce robe");
		toutesLesPhrases.add("Je voudrais bien retirer ce robe");
		toutesLesPhrases.add("Je souhaite retirer ce robe");
		
		toutesLesPhrases.add("retirer ce tee shirt");
		toutesLesPhrases.add("J'aimerais retirer ce tee shirt");
		toutesLesPhrases.add("J'aimerais bien retirer ce tee shirt");
		toutesLesPhrases.add("Je veux retirer ce tee shirt");
		toutesLesPhrases.add("Je veux bien retirer ce tee shirt");
		toutesLesPhrases.add("Je voudrais retirer ce tee shirt");
		toutesLesPhrases.add("Je voudrais bien retirer ce tee shirt");
		toutesLesPhrases.add("Je souhaite retirer ce tee shirt");
		
		
		
		
		
		// toutesLesPhrases.add("Demander un vêtement");       la commande n'est plus utile car on oriente le projet pour des particuliers
		
		
		
		
		M = toutesLesPhrases.size();
			// M est le nombre de commandes vocales que l'on a dans la base de donnees
		
		
		remplirTableauB() ;
		tableauDeTableaux = new int[M][N];
		remplirTableauDeTableaux() ;
		
	}
	
	
	private static int[][] tableauDeTableaux ;
	
	public static void remplirTableauDeTableaux()
	{
		for (int j=0;j<M;j++)
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
			else if (caractere.equals("-"))
			{
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
		if ( !currentWord.equals("") )
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
		
		switch(indice)
		{
		case 0 :
			return (0) ;
			
		case 1 :
			return (0) ;
			
		case 2 :
			return (0) ;
			
		case 3 :
			return (0) ;
			
		case 4 :
			return (0) ;
			
		case 5 :
			return (0) ;
			
		case 6 :
			return (0) ;
			
		case 7 :
			return (0) ;
			
		case 8 :
			return (0) ;	
		
		case 9 :
			return (0) ;	
		
		case 10 :
			return (0) ;	
		
		case 11 :
			return (0) ;	
		
		case 12 :
			return (0) ;	
		
		case 13 :
			return (1) ;	
		
		case 14 :
			return (1) ;	
		
		case 15 :
			return (1) ;	
		
		case 16 :
			return (1) ;	
		
		case 17 :
			return (1) ;	
		
		case 18 :
			return (1) ;	
		
		case 19 :
			return (1) ;	
		
		case 20 :
			return (1) ;	
		
		case 21 :
			return (1) ;	
		
		case 22 :
			return (1) ;	
		
		case 23 :
			return (2) ;	
		
		case 24 :
			return (2) ;	
		
		case 25 :
			return (2) ;	
		
		case 26 :
			return (2) ;	
		
		case 27 :
			return (2) ;
		
		case 28 :
			return (2) ;
		
		case 29 :
			return (2) ;
			
		case 30 :
			return (2) ;
			
		case 31 :
			return (2) ;
			
		case 32 :
			return (2) ;
			
		case 33 :
			return (2) ;
			
		case 34 :
			return (2) ;
			
		case 35 :
			return (2) ;
			
		case 36 :
			return (2) ;
			
		case 37 :
			return (2) ;
			
		case 38 :
			return (2) ;	
		
		case 39 :
			return (2) ;
			
		case 40 :
			return (2) ;
			
		case 41 :
			return (2) ;
			
		case 42 :
			return (2) ;
			
		case 44 :
			return (3) ;
			
		case 45 :
			return (4) ;
			
		case 46 :
			return (5) ;
			
		case 47 :
			return (6) ;
			
		case 48 :
			return (7) ;	
		
		case 49 :
			return (8) ;
			
		case 53 :
			return (9) ;
			
		case 54 :
			return (9) ;
			
		case 55 :
			return (9) ;
			
		case 56 :
			return (9) ;
			
		case 57 :
			return (9) ;
			
		case 58 :
			return (9) ;	
		
		case 59 :
			return (9) ;
			
		case 60 :
			return (9) ;
			
		case 61 :
			return (9) ;
			
		case 62 :
			return (9) ;
			
		case 63 :
			return (9) ;
			
		case 64 :
			return (9) ;
			
		case 65 :
			return (9) ;
			
		case 66 :
			return (9) ;
			
		case 67 :
			return (9) ;
			
		case 68 :
			return (9) ;	
		
		case 69 :
			return (9) ;	
			
		case 70 :
			return (16) ;
			
		case 71 :
			return (16) ;
			
		case 72 :
			return (16) ;
			
		case 73 :
			return (16) ;
			
		case 74 :
			return (16) ;
			
		case 75 :
			return (16) ;
			
		case 76 :
			return (16) ;
			
		case 77 :
			return (16) ;
			
		case 78 :
			return (16) ;	
		
		case 79 :
			return (25) ;	
			
		case 80 :
			return (26) ;
			
		case 81 :
			return (27) ;
			
		case 82 :
			return (28) ;
			
		case 83 :
			return (29) ;
			
		case 84 :
			return (30) ;
			
		case 85 :
			return (31) ;
			
		case 86 :
			return (32) ;
			
		case 87 :
			return (33) ;
			
		case 88 :
			return (34) ;	
		
		case 89 :
			return (35) ;	
		
		case 90 :
			return (36) ;
			
		case 91 :
			return (37) ;
			
		case 92 :
			return (38) ;
			
		case 93 :
			return (39) ;
			
		case 94 :
			return (40) ;
			
		case 95 :
			return (41) ;
			
		case 96 :
			return (42) ;
			
		case 97 :
			return (43) ;
			
		case 98 :
			return (44) ;	
		
		case 99 :
			return (45) ;
			
		case 100 :
			return (46) ;
			
		case 101 :
			return (47) ;
			
		case 102 :
			return (48) ;
			
		case 103 :
			return (49) ;
			
		case 104 :
			return (50) ;
			
		case 105 :
			return (51) ;
			
		case 106 :
			return (52) ;
			
		case 107 :
			return (52) ;
			
		case 108 :
			return (52) ;	
		
		case 109 :
			return (52) ;	
		
		case 110 :
			return (52) ;	
		
		case 111 :
			return (52) ;	
		
		case 112 :
			return (52) ;	
		
		case 113 :
			return (52) ;	
		
		case 114 :
			return (52) ;	
		
		case 115 :
			return (10) ;	
		
		case 116 :
			return (10) ;	
		
		case 117 :
			return (10) ;	
		
		case 118 :
			return (10) ;	
		
		case 119 :
			return (10) ;	
		
		case 120 :
			return (10) ;	
		
		case 121 :
			return (10) ;	
		
		case 122 :
			return (10) ;	
		
		case 123 :
			return (10) ;	
		
		case 124 :
			return (10) ;	
		
		case 125 :
			return (10) ;	
		
		case 126 :
			return (10) ;	
		
		case 127 :
			return (18) ;
		
		case 128 :
			return (18) ;
		
		case 129 :
			return (18) ;
			
		case 130 :
			return (18) ;
			
		case 131 :
			return (18) ;
			
		case 132 :
			return (18) ;
			
		case 133 :
			return (18) ;
			
		case 134 :
			return (18) ;
			
		case 135 :
			return (18) ;
			
		case 136 :
			return (18) ;
			
		case 137 :
			return (18) ;
			
		case 138 :
			return (18) ;	
		
		case 139 :
			return (17) ;
			
		case 140 :
			return (17) ;
			
		case 141 :
			return (17) ;
			
		case 142 :
			return (17) ;
			
		case 143 :
			return (17) ;
			
		case 144 :
			return (17) ;
			
		case 145 :
			return (17) ;
			
		case 146 :
			return (17) ;
			
		case 147 :
			return (17) ;
			
		case 148 :
			return (17) ;	
		
		case 149 :
			return (17) ;
			
		case 150 :
			return (17) ;
			
		case 151 :
			return (22) ;
			
		case 152 :
			return (22) ;
			
		case 153 :
			return (23) ;
			
		case 154 :
			return (23) ;
			
		case 155 :
			return (23) ;
			
		case 156 :
			return (24) ;
			
		case 157 :
			return (24) ;
			
		case 158 :
			return (53) ;	
		
		case 159 :
			return (53) ;
			
		case 160 :
			return (53) ;
			
		case 161 :
			return (53) ;
			
		case 162 :
			return (53) ;
			
		case 163 :
			return (53) ;
			
		case 164 :
			return (53) ;
			
		case 165 :
			return (53) ;
			
		case 166 :
			return (53) ;
			
		case 167 :
			return (53) ;
			
		case 168 :
			return (53) ;	
		
		case 169 :
			return (53) ;	
			
		case 170 :
			return (53) ;
			
		case 171 :
			return (53) ;
			
		case 172 :
			return (53) ;
			
		case 173 :
			return (53) ;
			
		case 174 :
			return (53) ;
			
		case 175 :
			return (53) ;
			
		case 176 :
			return (53) ;
			
		case 177 :
			return (53) ;
			
		case 178 :
			return (53) ;	
		
		case 179 :
			return (53) ;	
			
		case 180 :
			return (53) ;
			
		case 181 :
			return (53) ;
			
		case 182 :
			return (53) ;
			
		case 183 :
			return (53) ;
			
		case 184 :
			return (53) ;
			
		case 185 :
			return (53) ;
			
		case 186 :
			return (53) ;
			
		case 187 :
			return (53) ;
			
		case 188 :
			return (53) ;	
		
		case 189 :
			return (53) ;	
		
		case 190 :
			return (53) ;
			
		case 191 :
			return (53) ;
			
		case 192 :
			return (53) ;
			
		case 193 :
			return (53) ;
			
		case 194 :
			return (53) ;
			
		case 195 :
			return (53) ;
			
		case 196 :
			return (53) ;
			
		case 197 :
			return (53) ;
			
		case 198 :
			return (53) ;	
		
		case 199 :
			return (53) ;
			
		case 200 :
			return (53) ;
			
		case 201 :
			return (53) ;
			
		case 202 :
			return (53) ;
			
		case 203 :
			return (53) ;
			
		case 204 :
			return (53) ;
			
		case 205 :
			return (10) ;
			
		case 206 :
			return (10) ;
			
		case 207 :
			return (10) ;
			
		case 208 :
			return (10) ;	
		
		case 209 :
			return (10) ;	
		
		case 210 :
			return (10) ;	
		
		case 211 :
			return (10) ;	
		
		case 212 :
			return (10) ;	
		
		case 213 :
			return (19) ;	
		
		case 214 :
			return (19) ;	
		
		case 215 :
			return (19) ;	
		
		case 216 :
			return (19) ;	
		
		case 217 :
			return (19) ;	
		
		case 218 :
			return (19) ;	
		
		case 219 :
			return (19) ;	
		
		case 220 :
			return (19) ;	
		
		case 221 :
			return (19) ;	
		
		case 222 :
			return (19) ;	
		
		case 223 :
			return (19) ;	
		
		case 224 :
			return (19) ;	
		
		case 225 :
			return (19) ;	
		
		case 226 :
			return (19) ;	
		
		case 227 :
			return (19) ;
		
		case 228 :
			return (19) ;
		
		case 229 :
			return (19) ;
			
		case 230 :
			return (19) ;
			
		case 231 :
			return (19) ;
			
		case 232 :
			return (19) ;
			
		case 233 :
			return (19) ;
			
		case 234 :
			return (19) ;
			
		case 235 :
			return (19) ;
			
		case 236 :
			return (19) ;
			
		case 237 :
			return (15) ;
			
		case 238 :
			return (15) ;	
		
		case 239 :
			return (15) ;
			
		case 240 :
			return (15) ;
			
		case 241 :
			return (15) ;
			
		case 242 :
			return (15) ;
			
		case 243 :
			return (15) ;
			
		case 244 :
			return (15) ;
			
		case 245 :
			return (15) ;
			
		case 246 :
			return (15) ;
			
		case 247 :
			return (15) ;
			
		case 248 :
			return (15) ;	
		
		case 249 :
			return (15) ;
			
		case 250 :
			return (15) ;
			
		case 251 :
			return (15) ;
			
		case 252 :
			return (15) ;
			
		case 253 :
			return (15) ;
			
		case 254 :
			return (15) ;
			
		case 255 :
			return (15) ;
			
		case 256 :
			return (15) ;
			
		case 257 :
			return (15) ;
			
		case 258 :
			return (15) ;	
		
		case 259 :
			return (15) ;
			
		case 260 :
			return (15) ;
			
		case 261 :
			return (15) ;
			
		case 262 :
			return (15) ;
			
		case 263 :
			return (15) ;
			
		case 264 :
			return (15) ;
			
		case 265 :
			return (15) ;
			
		case 266 :
			return (15) ;
			
		case 267 :
			return (15) ;
			
		case 268 :
			return (15) ;	
		
		case 269 :
			return (15) ;	
			
		case 270 :
			return (15) ;
			
		case 271 :
			return (15) ;
			
		case 272 :
			return (15) ;
			
		case 273 :
			return (15) ;
			
		case 274 :
			return (15) ;
			
		case 275 :
			return (15) ;
			
		case 276 :
			return (15) ;
			
		case 277 :
			return (15) ;
			
		case 278 :
			return (21) ;	
		
		case 279 :
			return (21) ;	
			
		case 280 :
			return (21) ;
			
		case 281 :
			return (21) ;
			
		case 282 :
			return (21) ;
			
		case 283 :
			return (21) ;
			
		case 284 :
			return (21) ;
			
		case 285 :
			return (21) ;
			
		case 286 :
			return (21) ;
			
		case 287 :
			return (21) ;
			
		case 288 :
			return (21) ;	
		
		case 289 :
			return (21) ;	
		
		case 290 :
			return (21) ;
			
		case 291 :
			return (21) ;
			
		case 292 :
			return (21) ;
			
		case 293 :
			return (21) ;
			
		case 294 :
			return (21) ;
			
		case 295 :
			return (21) ;
			
		case 296 :
			return (21) ;
			
		case 297 :
			return (21) ;
			
		case 298 :
			return (21) ;	
		
		case 299 :
			return (21) ;
			
		case 301 :
			return (21) ;
			
		case 302 :
			return (13) ;
			
		case 303 :
			return (13) ;
			
		case 304 :
			return (13) ;
			
		case 305 :
			return (13) ;
			
		case 306 :
			return (13) ;
			
		case 307 :
			return (13) ;
			
		case 308 :
			return (13) ;	
		
		case 309 :
			return (13) ;	
		
		case 310 :
			return (13) ;	
		
		case 311 :
			return (13) ;	
		
		case 312 :
			return (13) ;	
		
		case 313 :
			return (13) ;	
		
		case 314 :
			return (13) ;	
		
		case 315 :
			return (13) ;	
		
		case 316 :
			return (13) ;	
		
		case 317 :
			return (13) ;	
		
		case 318 :
			return (13) ;	
		
		case 319 :
			return (13) ;	
		
		case 320 :
			return (12) ;	
		
		case 321 :
			return (12) ;	
		
		case 322 :
			return (12) ;	
		
		case 323 :
			return (12) ;	
		
		case 324 :
			return (12) ;	
		
		case 325 :
			return (12) ;	
		
		case 326 :
			return (12) ;	
		
		case 327 :
			return (12) ;
		
		case 328 :
			return (12) ;
		
		case 329 :
			return (12) ;
			
		case 330 :
			return (12) ;
			
		case 331 :
			return (12) ;
			
		case 332 :
			return (12) ;
			
		case 333 :
			return (12) ;
			
		case 334 :
			return (12) ;
			
		case 335 :
			return (12) ;
			
		case 336 :
			return (12) ;
			
		case 337 :
			return (12) ;
			
		case 338 :
			return (11) ;	
		
		case 339 :
			return (11) ;
			
		case 340 :
			return (11) ;
			
		case 341 :
			return (11) ;
			
		case 342 :
			return (11) ;
			
		case 343 :
			return (11) ;
			
		case 344 :
			return (11) ;
			
		case 345 :
			return (11) ;
			
		case 346 :
			return (11) ;
			
		case 347 :
			return (11) ;
			
		case 348 :
			return (11) ;	
		
		case 349 :
			return (11) ;
			
		case 350 :
			return (11) ;
			
		case 351 :
			return (11) ;
			
		case 352 :
			return (11) ;
			
		case 353 :
			return (11) ;
			
		case 354 :
			return (11) ;
			
		case 355 :
			return (11) ;
			
		case 356 :
			return (14) ;
			
		case 357 :
			return (14) ;
			
		case 358 :
			return (14) ;	
		
		case 359 :
			return (14) ;
			
		case 360 :
			return (14) ;
			
		case 361 :
			return (14) ;
			
		case 362 :
			return (14) ;
			
		case 363 :
			return (14) ;
			
		case 364 :
			return (14) ;
			
		case 365 :
			return (14) ;
			
		case 366 :
			return (14) ;
			
		case 367 :
			return (14) ;
			
		case 368 :
			return (14) ;	
		
		case 369 :
			return (14) ;	
			
		case 370 :
			return (14) ;
			
		case 371 :
			return (14) ;
			
		case 372 :
			return (14) ;
			
		case 373 :
			return (14) ;
			
		case 374 :
			return (20) ;
			
		case 375 :
			return (20) ;
			
		case 376 :
			return (20) ;
			
		case 377 :
			return (20) ;
			
		case 378 :
			return (20) ;	
		
		case 379 :
			return (20) ;	
			
		case 380 :
			return (20) ;
			
		case 381 :
			return (20) ;
			
		case 382 :
			return (20) ;
			
		case 383 :
			return (20) ;
			
		case 384 :
			return (20) ;
			
		case 385 :
			return (20) ;
			
		case 386 :
			return (20) ;
			
		case 387 :
			return (20) ;
			
		case 388 :
			return (20) ;	
		
		case 389 :
			return (20) ;	
		
		case 390 :
			return (20) ;
			
		case 391 :
			return (20) ;
			
		case 392 :
			return (20) ;
			
		case 393 :
			return (20) ;
			
		case 394 :
			return (10) ;
			
		case 395 :
			return (10) ;
			
		case 396 :
			return (10) ;
			
		case 397 :
			return (10) ;
			
		case 398 :
			return (10) ;	
		
		case 399 :
			return (10) ;
			
		case 400 :
			return (10) ;
			
		case 401 :
			return (10) ;
			
		case 402 :
			return (10) ;
			
		case 403 :
			return (10) ;
			
		case 404 :
			return (10) ;
			
		case 405 :
			return (10) ;
			
		case 406 :
			return (10) ;
			
		case 407 :
			return (10) ;
			
		case 408 :
			return (10) ;	
		
		case 409 :
			return (10) ;	
		
		case 410 :
			return (10) ;	
		
		case 411 :
			return (10) ;	
		
		case 412 :
			return (10) ;	
		
		case 413 :
			return (10) ;	
		
		case 414 :
			return (10) ;	
		
		case 415 :
			return (10) ;	
		
		case 416 :
			return (10) ;	
		
		case 417 :
			return (10) ;	
		
		case 418 :
			return (10) ;	
		
		case 419 :
			return (10) ;	
		
		case 420 :
			return (10) ;	
		
		case 421 :
			return (10) ;	
		
		case 422 :
			return (10) ;	
		
		case 423 :
			return (10) ;	
		
		case 424 :
			return (10) ;	
		
		case 425 :
			return (10) ;	
		
		case 426 :
			return (10) ;	
		
		case 427 :
			return (10) ;
		
		case 428 :
			return (10) ;
		
		case 429 :
			return (10) ;
			
		case 430 :
			return (10) ;
			
		case 431 :
			return (10) ;
			
		case 432 :
			return (10) ;
			
		case 433 :
			return (10) ;
			
		case 434 :
			return (10) ;
			
		case 435 :
			return (10) ;
			
		case 436 :
			return (10) ;
			
		case 437 :
			return (10) ;
			
		case 438 :
			return (10) ;	
		
		case 439 :
			return (10) ;
			
		case 440 :
			return (10) ;
			
		case 441 :
			return (10) ;
			
		case 442 :
			return (10) ;
			
		case 443 :
			return (10) ;
			
		case 444 :
			return (10) ;
			
		case 445 :
			return (10) ;
			
		case 446 :
			return (10) ;
			
		case 447 :
			return (10) ;
			
		case 448 :
			return (10) ;	
		
		case 449 :
			return (10) ;
			
		case 450 :
			return (10) ;
			
		case 451 :
			return (10) ;
			
		case 452 :
			return (10) ;
			
		case 453 :
			return (10) ;
			
		case 454 :
			return (10) ;
			
		case 455 :
			return (10) ;
			
		case 456 :
			return (10) ;
			
		case 457 :
			return (10) ;
			
		case 458 :
			return (10) ;	
		
		case 459 :
			return (10) ;
			
		case 460 :
			return (10) ;
			
		case 461 :
			return (10) ;
			
		case 462 :
			return (10) ;
			
		case 463 :
			return (10) ;
			
		case 464 :
			return (10) ;
			
		case 465 :
			return (10) ;
			
		case 466 :
			return (10) ;
			
		case 467 :
			return (10) ;
			
		case 468 :
			return (10) ;	
		
		case 469 :
			return (10) ;	
			
		case 470 :
			return (10) ;
			
		case 471 :
			return (10) ;
			
		case 472 :
			return (10) ;
			
		case 473 :
			return (10) ;
			
		case 474 :
			return (10) ;
			
		case 475 :
			return (10) ;
			
			
			
		
		default :
			return(-1);
			
		
		
		}
		
		
				
	}
}
