package premalgo;

public class PremAlgo {
    int[][] grille;
    int iteration=0;
    int solution;
    long tSolution;
   
/** Constructeur contenant le sudoku, son nombre de solution et le temps de resolution pour la premiere solution.
 * @param sudokuPartiellementVide La grille 9x9 du sudoku.
 **/
    public PremAlgo (int[][] sudokuPartiellementVide) {
        grille=sudokuPartiellementVide;
        solution=0;
        tSolution=0;
    }
    
/** Methode simple permettant d'afficher un sudoku.
 **/
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
    
/** Méthode récursive résolvant un sudoku en testant chaque case une par une.
 * Pour chaque case de la grille 9x9 non vide, la méthode teste chaque nombre de 1 à 9 pour voir si il valide les conditions
 * d’un sudoku (pas dans ligne, colonne et bloc 3x3), s’il valide les conditions on continue à la case suivante et ainsi
 * de suite jusqu’à la fin de la grille. Si en revanche on ne trouve pas de nombre validant les conditions alors il
 * faut revenir en arrière pour tester l’incrémentation de la case d’avant et ainsi de suite. Si finalement on
 * revient au départ et qu’aucun nombre ne convient, alors il n’y a pas de solution. <p>
 * 
 * Lorsque la premiere solution est trouvé, la variable tSolution est définie. <p>
 * La méthode montre les itérations de recherche pour la premiere solution. <p>
 * La méthode traite la premiere solution comme une itération fausse et continue à chercher les autres solutions que l'on peut afficher si souhaité.
 * 
 * @return La valeur de retour n'est seulement utile que pour la récursivité, on ne s'en sert pas autrement.
 **/
    public int resolver () { 
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

/** Méthode testant si un algorithme rempli ou partiellement rempli est correct.
 * @return Vrai si le sudoku ne comporte pas deux fois le même nombre dans la même ligne/colonne/bloc 3x3, et
 * Faux si c'est le cas.
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
    
/** Main.
 * Création d'une instance comprenant le sudoku à resoudre, prise de mesure du temps avant la resolution puis
 * conclusion avec le delta de resolution ou avec le manque de solution.
 */
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
