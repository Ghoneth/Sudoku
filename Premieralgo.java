package premalgo;

public class PremAlgo {
    int[][] grille;
    int iteration=0;
    int solution;
    long tSolution;
    
    public PremAlgo (int[][] sudokuPartiellementVide) { //Constructeur contenant le sudoku et son nomre de solution
        grille=sudokuPartiellementVide;
        solution=0;
        tSolution=0;
    }
    
    public void afficherSudoku() { //Methode permettant d'afficher un sudoku
        for (int ligne = 0; ligne < 9; ligne++) {
            System.out.print("{");
            for (int colonne = 0; colonne < 9; colonne++) {
                if (colonne==8) System.out.print(this.grille[ligne][colonne]);
                else System.out.print(this.grille[ligne][colonne]+ ", ");
            }
            System.out.println("}");
        }
    }    

    public int resolver () { //Méthode récursive résolvant un sudoku en testant chaque case une par une
        for (int ligne = 0; ligne < 9; ligne++) {
            for (int colonne = 0; colonne < 9; colonne++) {
                if (this.grille[ligne][colonne]==0) {
                    for (int valeur = 1; valeur < 10; valeur++) {
                        this.grille[ligne][colonne]=valeur;
                        if (solution==0) {
                            iteration++;
                            System.out.println();
                            System.out.println("Iteration n° : " + iteration + " de la solution n° 1"); //Affichage des nombres trouvés au fur et à mesure pour la premiere solution
                            this.afficherSudoku();
                        }
                        if (estValide()) this.resolver(); //Recursivité, test de la prochaine case
                        if (ligne==9 && colonne==9) return 0;
                        this.grille[ligne][colonne]=0;
                    }
                    return 0;
                }
            }
        }
        solution++; //Incrémentation du nombre de solution
        if (solution==1) {
            System.out.println();
            System.out.println("La solution la plus rapidement trouvée est :");
            this.afficherSudoku();
            System.out.println();
            this.tSolution=new java.util.Date().getTime();
        }
        if (solution>1) {
            System.out.println("Il existe une " + this.solution+ "e solution");
            //this.afficherSudoku();  //Affichage des autres solutions si voulu
            //System.out.println();
        }
        return 0;
    }

    public boolean estValide () { //Méthode testant si un algorithme rempli ou partiellement rempli est correct
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

    public static void main(String[] args) {
        int[][] sudokuPartiellementVide = {
            {0, 0, 5, 4, 1, 2, 0, 0, 0},
            {0, 0, 0, 0, 5, 7, 1, 3, 4},
            {0, 0, 0, 8, 0, 0, 0, 5, 0},
            {0, 1, 0, 0, 4, 0, 0, 9, 0},
            {5, 9, 0, 0, 7, 0, 0, 0, 8},
            {4, 0, 8, 0, 6, 5, 0, 0, 0},
            {0, 5, 0, 7, 0, 4, 8, 6, 0},
            {0, 7, 0, 0, 0, 0, 5, 0, 0},
            {8, 6, 0, 5, 9, 1, 0, 7, 0}};
        
        long tDebut = new java.util.Date().getTime();
        PremAlgo sudoku = new PremAlgo (sudokuPartiellementVide);
        System.out.println("Sudoku partiellement rempli : ");
        sudoku.afficherSudoku();
        System.out.println();
        
        sudoku.resolver();
        System.out.println();
        
        if (sudoku.solution>0) {
            System.out.println("L'algo a pris " + (sudoku.tSolution - tDebut) + " milisecondes à trouver (et surtout afficher) la premiere solution");
        } else {
            System.out.println ("Il n'y a pas de solution");
        }
    }
}
