/*
  J'ai copié et modifié la méthode resolver() car je n'ai besoin que de savoir s'il y a 0, 1 ou plusieurs solutions.
  Je n'ai pas pu utiliser l'ensemble des classes parce que ça ne compilait pas, c'est pourquoi j'ai travaillé sur
  ma propre classe pour avancer.
  Il faudrait maintenant qu'on arrive à fusionner les différentes classes en définissant qui crée les différents objets
  et dans quel ordre pour avoir une version utilisable.
  Dans mon objet, pour récupérer une grille jouable il faut appeler la méthode demandeSudoku() qui prend un niveau de
  difficulté et retourne une table (int[][]).
  
  

  améliorations possibles ou à venir :
   - avoir un vrai niveau de difficulté : le nombre de cases n'est pas forcément une bonne mesure de la difficulté
   - améliorer la fonction de mélange
*/


import java.util.Random;
import java.util.Scanner;

public class GenererSudokuIsa{
	public int [][] grille;
    	int solution=1;
    	

	public GenererSudokuIsa(){ // 
		this.grille = new int[9][9];
	}
        

	/**
	  valide que la grille est correcte
	  @return booléen vrai si la grille est valide
	**/
	public boolean estValide () { 
         for (int ligne = 0; ligne < 9; ligne++) {
            for (int colonne = 0; colonne < 9; colonne++) {
                if (this.grille[ligne][colonne]!=0) {
                    int valeur = this.grille[ligne][colonne];
                    for (int index=0; index<9; index++) {
                        if (this.grille[ligne][index]==valeur) if (index!=colonne) return false;
                        if (this.grille[index][colonne]==valeur) if (index!=ligne) return false;
                    }
                    int cLigne = ligne/3;
                    int cColonne = colonne/3;
                    for (int index=0+cLigne*3; index<3+cLigne*3; index++) {
                        for (int jndex=0+cColonne*3; jndex<3+cColonne*3; jndex++) {
                            if (this.grille[index][jndex]==valeur) if (index!=ligne && jndex!=colonne) return false;
                        }
                    }
                }
            }
         }
        return true;
    }
   
    
    /**
      utilisé pour copier la grille de travail
      @param g la grille à copier dans la grille de travail de l'objet
    **/
    public void affecte(int [][]g) {
    	for (int ligne = 0; ligne < 9; ligne++) {
            for (int colonne = 0; colonne < 9; colonne++) {
            	this.grille[ligne][colonne] = g[ligne][colonne];
            }
        }
    }
    
    /**
      permet de remettre à zéro le décompte des solutions (on appelle cette méthode plusieurs fois)
    **/
    public void initResolver() {
    	solution = 0;
    }
    /**
      version simplifiée du résolveur : on n'a besoin que de savoir s'il y a exactement 1 solution
      donc pas de stockage des solutions, et retour systématique quand il y en a plus d'une
      Travail directement sur this.grille
    **/
    public void resolverNombre() { 
        if (solution > 1) {
        	return;
        }
        for (int ligne = 0; ligne < 9; ligne++) {
            for (int colonne = 0; colonne < 9; colonne++) {
                if (this.grille[ligne][colonne]==0) {
                    for (int valeur = 1; valeur < 10; valeur++) {
                        this.grille[ligne][colonne]=valeur;
                        if (estValide()) this.resolverNombre(); //Recursivité, test de la prochaine case
                        if (ligne==9 && colonne==9) return;
                        this.grille[ligne][colonne]=0;
                    }
                    return;
                }
            }
        }
        solution++; //Incrémentation du nombre de solution
        return;
    }
	/**  
	  mélange la grille initiale en intervertissant des lignes, des colonnes et des groupes de nombres 
	**/	
        public void melange(){
		int i, j,k,l,m,n ;
		Random rand = new Random();
		k = rand.nextInt(3);		// J'intervertis deux lignes du premier bloc
		l = rand.nextInt(3);
		for (j=0; j<9; j+=1){
			m=this.grille[k][j];
			n=this.grille[l][j];
			this.grille[k][j]=n;
			this.grille[l][j]=m;
		}
		k = rand.nextInt(3);		// J'intervertis deux colonnes du premier bloc
		l = rand.nextInt(3);
		for (j=0; j<9; j+=1){
			m=this.grille[j][k];
			n=this.grille[j][l];
			this.grille[j][k]=n;
			this.grille[j][l]=m;
		}
		k = 3+rand.nextInt(3);		// J'intervertis deux lignes du bloc intermédiaire
		l = 3+rand.nextInt(3);
		for (j=0; j<9; j+=1){
			m=this.grille[k][j];
			n=this.grille[l][j];
			this.grille[k][j]=n;
			this.grille[l][j]=m;
		}	
		k = 3+rand.nextInt(3);		// J'intervertis deux colonnes du bloc intermédiaire
		l = 3+rand.nextInt(3);
		for (j=0; j<9; j+=1){
			m=this.grille[j][k];
			n=this.grille[j][l];
			this.grille[j][k]=n;
			this.grille[j][l]=m;
		}
		k = 6+rand.nextInt(3);		// J'intervertis deux lignes du dernier bloc
		l = 6+rand.nextInt(3);
		for (j=0; j<9; j+=1){
			m=this.grille[k][j];
			n=this.grille[l][j];
			this.grille[k][j]=n;
			this.grille[l][j]=m;
		}
		k = 6+rand.nextInt(3);		// J'intervertis deux colonnes du dernier bloc
		l = 6+rand.nextInt(3);
		for (j=0; j<9; j+=1){
			m=this.grille[j][k];
			n=this.grille[j][l];
			this.grille[j][k]=n;
			this.grille[j][l]=m;
		}
		k = 1+rand.nextInt(9);		// J'intervertis deux groupes de nombres
		l = 1+rand.nextInt(9);
		for (i=0; i<9; i+=1){
			for (j=0; j<9; j+=1){
				if (this.grille[i][j]==k){
					this.grille[i][j]=0;
				}
				if (this.grille[i][j]==l){
					this.grille[i][j]=k;
				}
				if (this.grille[i][j]==0){
					this.grille[i][j]=l;
				}
			}
		}
	}
        
        /**
          sélectionne le nombre de cases remplies en fonction du niveau de difficulté
        **/
	public void niveau(int n){
		if (n == 1) { sudokualeatoire(35); }
		if (n == 2) { sudokualeatoire(30);}
		if (n == 3) { sudokualeatoire(28);}
		if (n == 4) { sudokualeatoire(26);}
		
	}
        
       
	/**
	  méthode d'affichage, pour test / debug
	**/
	public void affiche(){
		for (int i=0; i<9; i+=1){
			for (int j=0; j<9; j+=1){
				if (this.grille[i][j]==0){
                                    System.out.print("  ");
				}
				else {
                                    System.out.print(this.grille[i][j]+" ");
				}
			}
			System.out.println(" ");
		}
	}	

        /**
          génère une grille de sudoku avec une solution unique
          part d'une grille pleine et supprime au hasard des éléments en vérifiant à chaque fois
          que la grille n'a qu'une solution.
          abandonne si on dépasse un nombre d'essai max
          @param m le nombre d'éléments souhaités dans le sudoku
        **/
        public void sudokualeatoire(int m){
        	int n, i, j, k, essai;
        	n=81;
        	essai = 0;
    		Random rand = new Random();
    		while (n > m){
    			i = rand.nextInt(9);
			j = rand.nextInt(9);
			k = this.grille[i][j];
			initResolver();
			if (k!=0){
				this.grille[i][j]=0;
				resolverNombre();
               	}
               	if (this.solution != 1){
               		this.grille[i][j] = k;
               		essai++;
               		}
               	else {
               		n = n-1;
               		essai = 0;
               	}
               	if (essai == 30) {
               		return;
               	}
               }
               }
        
        /**
          méthode pour demander un sukodu d'un niveau donné
          @param niveau le niveau de difficulté du sudoku demandé
          @return un tableau d'entiers qui est un sudoku jouable
        **/
        public int [][] demandeSudoku(int niveau) {
        	int [][] G1 = {{6, 1, 3, 8, 4, 9, 2, 5, 7},
                            {2, 9, 8, 6, 5, 7, 3, 4, 1},
                            {7, 5, 4, 3, 1, 2, 6, 8, 9},
                            {4, 7, 1, 2, 3, 8, 5, 9, 6},
                            {5, 6, 9, 1, 7, 4, 8, 3, 2},
                            {3, 8, 2, 9, 6, 5, 7, 1, 4},
                            {9, 4, 5, 7, 2, 3, 1, 6, 8},
                            {1, 3, 7, 4, 8, 6, 9, 2, 5},
                            {8, 2, 6, 5, 9, 1, 4, 7, 3}};
              	
		// on stocke la grille de départ
		this.affecte(G1);
		// on "brasse" cette grille
		this.melange();
		// on supprime un certain nombre de cases
		this.niveau(niveau);
		return this.grille;
        
        }

        /**
          exemple de génération d'un sudoku avec solution unique
        **/
        public static void main(String args[]) {
        	int [][] resultat;
        	
		GenererSudokuIsa G = new GenererSudokuIsa();
        	resultat = G.demandeSudoku(4);
        	G.affiche(); // juste pour voir le résultat
        	
        }
}
