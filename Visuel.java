package sudoku_rc;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.util.Scanner;

/**Classe créant la fenetre de jeu et hebergeant les méthodes liées aux différentes actions du joueur.
 * @author Fanny
 **/
public class Visuel extends JFrame {
    public static Graphics g1 = null;
    static int [][] tableauVide = new int [9][9];
    static int [][] tableauTest = new int [9][9];
    static int [][] tableauEnCours;
    static boolean tableauACreer;
    
    /**Constructeur créant le fenetre "Jeu de Sudoku". 
     * @param largeur Largeur de la fenetre.
     * @param hauteur Hauteur de la fenetre.
     **/
    public Visuel(int largeur, int hauteur) {
        this.setTitle("Jeu de sudoku");
        this.setSize(largeur, hauteur);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    /**Methode lancant la création d'un champ de texte dans la case cliquée par le joueur.
     * La méthode prend en argument l'abscisse et l'ordonnée du clic pour en effectuer la conversion
     * vers l'abscisse et l'ordonnée du champ de texte à créer dans la case à modifier puis lance la création
     * du champ de texte.
     * @param grille L'instance comportant la grille du sudoku.
     * @param x Abscisse du clic.
     * @param y Ordonnée du clic.
     **/
    public static void entrerNombre(Grille grille, int x, int y) {
        grille.effacerGraphique(g1);
        int caseX = grille.convertirX(x);
        int caseY = grille.convertirY(y);
        int abscisseMin = grille.convertirAbscisseMin(caseX);
        int ordonneeMin = grille.convertirOrdonneeMin(caseY);
        ChampTexte saisie = new ChampTexte(abscisseMin, ordonneeMin, grille);
    }
    
    /**Methode permettant de lancer l'ajout d'une valeur dans l'instance de la grille affichée depuis la grille
     * tableauEnCours.
     * @param grille L'instance comportant la grille du sudoku.
     * @param nombre Le nombre à rentrer dans la grille.
     **/
    public static void grilleARemplir(Grille grille, int nombre) {
        grille.remplirGrille(tableauEnCours, nombre);
    }
    
    /**Methode lancant un reset de la grille.
     * La méthode lance un rafraichissement de la grille puis affecte/affiche la grille tableauEnCours.
     * @param grille L'instance comportant la grille du sudoku.
     **/
    public static void demarrage(Grille grille) {
        grille.effacerGraphique(g1);
        grille.recupererTableau(tableauEnCours);
    }
    
    /**Methode lancant la generation d'un sudoku partiellement rempli.
     * La méthode raffraichie la grille, lance la generation par la méthode GenererSudoku.genererAleatoire() puis l'affecte à la grille affichée.
     * On l'affecte également à tableauTest qui sera la base pour le sudoku partiellement rempli.
     * @param grille L'instance comportant la grille du sudoku.
     */
    public static void grilleGenerer(Grille grille) {
        grille.effacerGraphique(g1);
        int[][] tableauTemp = GenererSudoku.genererAleatoire();
        grille.recupererTableau(tableauTemp);
        tableauTest = grille.copierTableau(tableauTemp);
    }
    
     public static void grilleGenerer1(Grille grille) {
        grille.effacerGraphique(g1);
        int[][] tableauTemp = GenererSudoku.genererNiveau(0);
        grille.recupererTableau(tableauTemp);
        tableauTest = grille.copierTableau(tableauTemp);
    }
    
    public static void grilleGenerer2(Grille grille) {
        grille.effacerGraphique(g1);
        int[][] tableauTemp = GenererSudoku.genererNiveau(1);
        grille.recupererTableau(tableauTemp);
        tableauTest = grille.copierTableau(tableauTemp);
    }
    
    public static void grilleGenerer3(Grille grille) {
        grille.effacerGraphique(g1);
        int[][] tableauTemp = GenererSudoku.genererNiveau(2);
        grille.recupererTableau(tableauTemp);
        tableauTest = grille.copierTableau(tableauTemp);
    }
    
    public static void grilleGenerer4(Grille grille) {
        grille.effacerGraphique(g1);
        int[][] tableauTemp = GenererSudoku.genererNiveau(3);
        grille.recupererTableau(tableauTemp);
        tableauTest = grille.copierTableau(tableauTemp);
    }
    
    
    /**Methode lancant la resolution par algorithme d'un sudoku.
     * La methode raffraichie la grille, lance la resolution par la methode Algo_Sudoku.algoLancement(tableauEnCours) puis l'affecte
     * à la grille affichée.
     * @param grille L'instance comportant la grille du sudoku.
     */
    public static void grilleResolution(Grille grille) {
        grille.effacerGraphique(g1);
        int[][] tableauTemp = Algo_Sudoku.algoLancement(tableauEnCours);
        grille.recupererTableau(tableauTemp);
        //tableauEnCours = grille.copierTableau(tableauTemp);
    }
    
     /** Methode lancant la resolution par alogorithme d'un sudoku et récupérant la grille complétée
    */
    public static int[][]  grilleIndice(Grille grille) {
        int[][] tableauTemp = Algo_Sudoku.algoLancement(tableauEnCours);
        return tableauTemp;
     }
     
    
    /**Methode créant une 2e fenetre par dessus la premiere affichant un message informatif.
     * @param message Message à aficher.
     **/
    public static void afficherMessage(String message) {
        JFrame fenetreMessage = new JFrame();
        fenetreMessage.setTitle("Warning !");
        fenetreMessage.setSize(400,100);
        fenetreMessage.setVisible(true);
        fenetreMessage.setLocationRelativeTo(null);
        JLabel affiche = new JLabel(message);
        fenetreMessage.add(affiche);
    }
    
    /**Methode de lancement de la fenetre Jeu de Sudoku ainsi que ses composants.
     * La méthode va créer la fenetre et toutes ses composantes puis va ensuite lancer
     * Visuel.demarrage(grille) et boutons.boutonsDebut() pour initialiser le jeu.
     **/
    public static void dessiner() {
        Visuel fenetre = new Visuel (800, 800);
        Panneau panneau = new Panneau(800, 800);
        Grille grille = new Grille(500, 500);
        Boutons boutons = new Boutons(200, 200, grille);
        Titre titre = new Titre(800, 150);
        grille.paint(g1);
        Color fond = new Color(90, 0, 0);
        
        panneau.setBackground(fond);
        titre.setOpaque(false);
        panneau.add(titre, BorderLayout.PAGE_START);
        grille.setOpaque(false);
        panneau.add(grille, BorderLayout.LINE_START);
        boutons.setOpaque(false);
        panneau.add(boutons, BorderLayout.LINE_END);
        fenetre.setContentPane(panneau);
        
        
        tableauEnCours = grille.copierTableau(tableauVide);
        demarrage(grille);
        boutons.boutonsDebut();
        titre.ajouterTitre();
        fenetre.setVisible(true);
    }
}
