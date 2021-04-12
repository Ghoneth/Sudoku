package sudoku_rc;
import java.util.Random;
import java.util.Scanner;

/** Classe generant un sudoku avec une seule solution.
 * @author Isabelle
 */
public class GenererSudoku extends TroisAlgo {
    
    /**Constructeur contenant le sudoku ainsi que son nombre de solution.
     * @param grille La grille 9x9 que l'on veut definir pour le sudoku.
     */
    public GenererSudoku(int [][] grille){
        super (grille);
    }

    /** Génère un tableau contenant les entiers de 0 à 9 dans le désordre sauf 0
     **/
    public int [] permutation(){
    	int [] liste = new int [10];
    	Random rand = new Random();
    	liste[0] = 0;
    	boolean perm;
    	int i, k;
    	i = 1;
    	while (i<10){
    		perm =true;
    		k=rand.nextInt(9)+1;
    		for(int j=0; j<i; j++){
    			if (liste[j]==k){ perm = false;}
    		}
    		if (perm) {
    			liste[i]=k;
    			i=i+1;
    		}
    	}
    	return liste;
    }
    
    /**Méthode mélangeant un sudoku valide.
    * A partir d'une grille valide, la méthode intervertit des lignes, colonnes et groupes de nombres
    * tout en gardant le caractère valide du sudoku. 
    */
    public void melange(){
        int i, j,k,l,m,n, f, b;
        Random rand = new Random();

        // Je permute les chiffres de la grille à l'aide de la méthode permutation	
        int [] liste = this.permutation();
        for (i=0; i<9; i+=1){		
               for (j=0; j<9; j+=1){
                      k=this.grille[i][j];
                      this.grille[i][j]= liste[k];
               }
        }

        // j'intervertis deux lignes d'un bloc au hasard, plusieurs fois
        for (f=0; f<8; f++) {
            b = rand.nextInt(3)*3; // bloc
            k = b+rand.nextInt(3);		// deux lignes de ce bloc
            l = b+rand.nextInt(3);
            for (j=0; j<9; j+=1){	// échange
                m=this.grille[k][j];
                n=this.grille[l][j];
                this.grille[k][j]=n;
                this.grille[l][j]=m;
            }
        }
        // j'intervertis deux colonnes d'un bloc au hasard, plusieurs fois
        for (f=0; f<8; f++) {
            b = rand.nextInt(3)*3; // bloc
            k = b+rand.nextInt(3);		// deux colonnes de ce bloc
            l = b+rand.nextInt(3);
            for (i=0; i<9; i+=1){	// échange
                m=this.grille[i][k];
                n=this.grille[i][l];
                this.grille[i][k]=n;
                this.grille[i][l]=m;
            }
        }

     }
    /** Méthode qui supprime des éléments aléatoires dans une grille tout en gardant sa validité.
     * La méthode supprime des elements aleatoires et verifie à chaque suppression grace à
     * l'algorithme de resolution que le sudoku ne possède qu'une seule solution. La méthode
     * abandonne la suppression d'un element si on dépasse les 30 essais à un certain rang.
     * @param m Nombre de case qui doit rester rempli.
     */
    public void sudokualeatoire(int m){
        int n, i, j, k, essai;
        n=81;
        essai=0;
        Random rand = new Random();
        while (n > m){
            do {
                i = rand.nextInt(9);
                j = rand.nextInt(9);
                k = this.grille[i][j];
            } while (k==0);
            this.solution=0;
            this.grille[i][j]=0;
            boolean affichageIteration=false;
            boolean uneSeuleSolution=false;
            boolean affichageSolutionUn =false;
            boolean affichageSolutions =false;
            boolean testPourGenerer = true;
            CreationContraintes();
            ResolutionSudoku(affichageIteration,uneSeuleSolution,affichageSolutionUn,affichageSolutions,testPourGenerer);
            if (this.solution != 1){
                this.grille[i][j] = k;
                essai++;
            }
            else {
                n = n-1;
                essai =0;
            }
            if (essai==30) return;
       }
   }
    
    /** Methode de lancement de la generation d'un sudoku partiellement rempli.
    * Création d'une instance comprenant un sudoku valide, mélange de la grille puis
    * suppression d'elements dans la grille.
    * @return Le sudoku partiellement rempli ne possédant qu'une seule solution.
    */
    public static int[][] genererAleatoire() {
            int [][] G1 = {{6, 1, 3, 8, 4, 9, 2, 5, 7},
                        {2, 9, 8, 6, 5, 7, 3, 4, 1},
                        {7, 5, 4, 3, 1, 2, 6, 8, 9},
                        {4, 7, 1, 2, 3, 8, 5, 9, 6},
                        {5, 6, 9, 1, 7, 4, 8, 3, 2},
                        {3, 8, 2, 9, 6, 5, 7, 1, 4},
                        {9, 4, 5, 7, 2, 3, 1, 6, 8},
                        {1, 3, 7, 4, 8, 6, 9, 2, 5},
                        {8, 2, 6, 5, 9, 1, 4, 7, 3}};
            GenererSudoku G = new GenererSudoku(G1);
            G.melange();
            G.sudokualeatoire(26);
            return G.grille;
    }
    
       /**Méthode retournant une grille de sudoku à partir d'un niveau de difficulté
     * Utilise un ensemble de grilles pré-existantes (10 pour chaque niveau) et
     * effectue des transformations dessus pour les changer en une autre grille
     * @return La grille de sudoku
     * @param niveau le niveau de difficulté (0-3)
     */
    public static int[][] genererNiveau(int niveau) {
        PreGrille PG = new PreGrille();
        int [][] GN = PG.grille(niveau);
        GenererSudoku G = new GenererSudoku(GN);
        G.melange();
        return G.grille;
    }
}
