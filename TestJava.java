package premalgo;

public class PremAlgo {
    int[][] grille;
    int i=0;
    
    public PremAlgo (int[][] sudokuPartVide) {
        grille=sudokuPartVide;
    }
    
    public void afficherSudoku() {
        for (int ligne = 0; ligne < 9; ligne++) {
            System.out.print("{");
            for (int colonne = 0; colonne < 9; colonne++) {
                if (colonne==8) System.out.print(this.grille[ligne][colonne]);
                else System.out.print(this.grille[ligne][colonne]+ ", ");
            }
            System.out.println("}");
        }
    }

    public boolean Resolver () {
        for (int ligne = 0; ligne < 9; ligne++) {
            for (int colonne = 0; colonne < 9; colonne++) {
                if (this.grille[ligne][colonne]==0) {
                    for (int valeur = 1; valeur < 10; valeur++) {
                        this.grille[ligne][colonne]=valeur;
                        i++;
                        System.out.println();
                        System.out.println("Iteration n° : " + i);
                        this.afficherSudoku();
                        if (EstValide(ligne,colonne)) if (this.Resolver()) return true;
                        this.grille[ligne][colonne]=0;
                    }
                    return false;
                }
            }
        }
        return true;
    }
            
    public boolean EstValide (int ligne, int colonne) {
        int valeur = this.grille[ligne][colonne];
        for (int index=0; index<9; index++) {
            if (this.grille[ligne][index]==valeur) if (index!=colonne) return false;
            if (this.grille[index][colonne]==valeur) if (index!=ligne) return false;
        }
        int CLigne = ligne/3;
        int CColonne = colonne/3;
        for (int index=0+CLigne*3; index<3+CLigne*3; index++) {
            for (int jndex=0+CColonne*3; jndex<3+CColonne*3; jndex++) {
                if (this.grille[index][jndex]==valeur) if (index!=ligne && jndex!=colonne) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] sudokuPartVide = {
        { 0, 0, 0, 3, 2, 1, 0, 0, 9 },
        { 0, 8, 0, 6, 7, 4, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 4, 7 },
        { 4, 0, 6, 0, 0, 7, 0, 0, 2 },
        { 5, 0, 0, 2, 0, 0, 4, 0, 6 },
        { 0, 0, 8, 0, 0, 6, 0, 0, 5 },
        { 0, 4, 0, 0, 0, 2, 1, 0, 8 },
        { 1, 7, 0, 5, 6, 0, 0, 0, 0 },
        { 8, 6, 0, 4, 1, 9, 5, 0, 3 }};
        PremAlgo sudoku = new PremAlgo (sudokuPartVide);
        long tdebut = new java.util.Date().getTime();
        sudoku.afficherSudoku();
        if (sudoku.Resolver()) {
            System.out.println();
            System.out.println("L'une des solutions est : ");
            sudoku.afficherSudoku();
            System.out.println();
            System.out.println("L'algo a pris " + (new java.util.Date().getTime() - tdebut) + " milisecondes à resoudre le sudoku");
        } else {
            System.out.println ("Il n'y a pas de solution");
        }
    }
}
