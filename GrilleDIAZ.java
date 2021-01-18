package grillediaz;
import java.util.Random;
import java.util.Scanner;

public class GrilleDIAZ{

	public int [][] grille;
		
	public GrilleDIAZ(){// Le constructeur
		this.grille = new int[9][9];
		int i, j;
		for (i=0; i<9; i+=1){
			for(j=0; j<9; j+=1){
				this.grille[i][j]=0;
			}
		}
	}
	
	public void set(int [][]G) {// Méthode qui permet de remplir une grille à partir d'un tableau
		int i, j;
		for (i=0; i<9; i+=1){
			for(j=0; j<9; j+=1){
				this.grille[i][j]=G[i][j];
			}
		}
	}
	
	public void sudokualéatoire(){ // Programme qui génère un sudoku aléatoire
		int n, i, j, k;
		n = 0;
		Random rand = new Random();
		while (n<36){
			i = rand.nextInt(9); // i désigne le numéro de la ligne
			j = rand.nextInt(9); // j désigne le numéro de la colonne
			k = 1 + rand.nextInt(9); // k désigne le chiffre à inscrire dans la case
			if (this.grille[i][j]==0){
				this.grille[i][j]=k;
			}
			if (this.estValide()){
				n = n + 1;
			}
                        else {
				this.grille[i][j]=0;
			}
		}
	}
		
	public void sudoku(int n){ // Programme qui génère un sudoku aléatoire avec solution, c'est-à-dire par élimination d'éléments dans une grille valide
		int m, i, j;
		m = 81;
		Random rand = new Random();
		while (m>n){
			i = rand.nextInt(9);
			j = rand.nextInt(9);
			if (this.grille[i][j]!=0){
					this.grille[i][j]=0;
					m=m-1;
			}
		}
	}
	
	public void niveau(int n){ // Programme qui génère un sudoku du niveau de difficulté n
		if (n == 1) { sudoku(35); }
		if (n == 2) { sudoku(30);}
		if (n == 3) { sudoku(28);}
		if (n == 4) { sudoku(26);}
	}
		
	
	public void mélange(){// A partir d'une grille valide, je construis une nouvelle grille valide
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
        
	public static void main(String args[]){
		int [][] G1 = {{6, 1, 3, 8, 4, 9, 2, 5, 7},{2, 9, 8, 6, 5, 7, 3, 4, 1},{7, 5, 4, 3, 1, 2, 6, 8, 9},{4, 7, 1, 2, 3, 8, 5, 9, 6},{5, 6, 9, 1, 7, 4, 8, 3, 2},{3, 8, 2, 9, 6, 5, 7, 1, 4},{9, 4, 5, 7, 2, 3, 1, 6, 8},{1, 3, 7, 4, 8, 6, 9, 2, 5},{8, 2, 6, 5, 9, 1, 4, 7, 3}};
		int n;
		Scanner sc = new Scanner(System.in);
		GrilleDIAZ G2;
		G2 = new GrilleDIAZ();
		/*G2.set(G1);
		G2.mélange();
		System.out.println("Entrez un niveau de difficulté entre 1 et 4 : ");
		n = sc.nextInt();
		G2.niveau(n);*/
		G2.sudokualéatoire();
		G2.affiche();
                System.out.println(G2.estValide());
	}
}