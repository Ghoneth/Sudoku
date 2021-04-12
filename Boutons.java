package sudoku_rc;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import java.util.Random;

/**Classe créant les boutons implementant l'interface ActionListener pour prendre en compte l'action de la souris
 * sur les boutons. 
 * @author Fanny
 **/
public class Boutons extends Panneau implements ActionListener {
    public JButton generer, generer1, generer2, generer3, generer4, indice, creer, resoudre, solveur, recommencer, tester, redemarrer;
    Grille grille;
    
     /**Constructeur créant le panneau contenant les boutons.
     * @param largeur Largeur du panneau.
     * @param hauteur Hauteur du panneau.
     * @param grille L'instance comportant la grille du sudoku.
     **/
    public Boutons(int largeur, int hauteur, Grille grille) {
        super(largeur, hauteur);
        this.grille = grille;
    }
    
    /**Methode de parametrage de bouton.
     * La méthode parametre le bouton puis lance addActionListener permettant de prendre en compte
     * les actions de la souris sur le bouton.
     * @param nom Nom du bouton.
     **/
    public void creerBouton(JButton nom){
        nom.setVerticalTextPosition(AbstractButton.CENTER);
        nom.setHorizontalTextPosition(AbstractButton.LEADING);
        nom.setMnemonic(KeyEvent.VK_D);
        nom.setActionCommand("disable");
        nom.addActionListener(this);
        add(nom);
    }
    
    /**Methode créant le bouton "Generer".
     **/
    public void creerBoutonGenerer() {
        generer = new JButton ("Générer une grille");
        creerBouton(generer);
    }
    
    public void creerBoutonGenerer1() {
        generer1 = new JButton ("Niveau 1");
        creerBouton(generer1);
    }
    
     public void creerBoutonGenerer2() {
        generer2 = new JButton ("Niveau 2");
        creerBouton(generer2);
    }
    
     public void creerBoutonGenerer3() {
        generer3 = new JButton ("Niveau 3");
        creerBouton(generer3);
    }
    
     public void creerBoutonGenerer4() {
        generer4 = new JButton ("Niveau 4");
        creerBouton(generer4);
    }
    
     /** Methode créant le bouton "Indice".
    **/
    
     public void creerBoutonIndice() {
        indice = new JButton ("Indice");
        creerBouton(indice);
    }
    
    /**Methode créant le bouton "Creer".
     **/
    public void creerBoutonCreer() {
        creer = new JButton ("Créer une grille");
        creerBouton(creer);
    }
    
    /**Methode créant le bouton "Resoudre moi-même".
     **/
    public void creerBoutonResoudre() {
        resoudre = new JButton ("Résoudre moi-même");
        creerBouton(resoudre);
    }
    
    /**Methode créant le bouton "Resoudre par algorithme".
     **/
    public void creerBoutonSolveur() {
        solveur = new JButton ("Résoudre par l'ordinateur");
        creerBouton(solveur);
    }
    
    /**Methode créant le bouton "Recommencer".
     **/
    public void creerBoutonRecommencer() {
        recommencer = new JButton ("Recommencer cette grille");
        creerBouton(recommencer);
    }
    
    /**Methode créant le bouton "Verifier".
     **/
    public void creerBoutonTester() {
        tester = new JButton ("Vérifier mon sudoku");
        creerBouton(tester);
    }
    
    /**Methode créant le bouton "Redemarrer".
     **/
    public void creerBoutonRedemarrer() {
        redemarrer = new JButton ("Redémarrer le jeu");
        creerBouton(redemarrer);
    }
    
    /**Methode créant les boutons du premier menu.
     * Lance les méthodes pour créer les boutons "Generer" et "Creer".
     **/
    public void boutonsDebut() {
        creerBoutonGenerer();
        creerBoutonGenerer1();
        creerBoutonGenerer2();
        creerBoutonGenerer3();
        creerBoutonGenerer4();
        creerBoutonCreer();
    }
    
    /**Methode créant les boutons du menu apres le choix "Generer".
     * Lance les méthodes pour créer les boutons "Resoudre moi-même", "Resoudre par algorithme" et
     * "Redemarrer". 
     **/
    public void boutonsGenerer() {
        creerBoutonResoudre();
        creerBoutonSolveur();
        creerBoutonRedemarrer();
    }
    
     public void boutonsGenerer1() {
    	creerBoutonResoudre();
        creerBoutonSolveur();
        creerBoutonRedemarrer();
    }
    
    public void boutonsGenerer2() {
    	creerBoutonResoudre();
        creerBoutonSolveur();
        creerBoutonRedemarrer();
    }
    
    public void boutonsGenerer3() {
    	creerBoutonResoudre();
        creerBoutonSolveur();
        creerBoutonRedemarrer();
    }
    
    public void boutonsGenerer4() {
    	creerBoutonResoudre();
        creerBoutonSolveur();
        creerBoutonRedemarrer();
    }
    
    /**Methode créant les boutons du menu apres le choix "Creer".
     * Lance les méthodes pour créer les boutons "Resoudre par algorithme", "Recommencer" et
     * "Redemarrer". 
     **/
    public void boutonsCreer() {
        creerBoutonSolveur();
        creerBoutonRecommencer();
        creerBoutonRedemarrer();
    }
    
    /**Methode créant les boutons du menu apres le choix "Resoudre par moi-même".
     * Lance les méthodes pour créer les boutons "Tester", "Resoudre par algorithme", "Recommencer" et
     * "Redemarrer". 
     **/
    public void boutonsResoudre() {
    	creerBoutonIndice();
        creerBoutonTester();
        creerBoutonSolveur();
        creerBoutonRecommencer();
        creerBoutonRedemarrer();
    }
    
    /**Methode effaçant tous les boutons et rafraichissant le panneau. 
     **/
    public void effacerBoutons() {
        Component[] composantListe = getComponents();
        for (Component c: composantListe) {
            remove(c);
        }
        revalidate();
        repaint();
    }
    
    /**Methode implementant l'appui sur le bouton "Generer".
     * La méthode efface tous les boutons, puis lance la création des boutons suite à l'appui sur le bouton
     * "Generer". Est lancé ensuite la méthode Visuel.grilleGenerer(grille) pour generer la grille du sudoku. On affecte
     * à tableauEnCours la grille générée.
     **/
    public void generer() {
        effacerBoutons();
        boutonsGenerer();
        Visuel.grilleGenerer(grille);
        Visuel.tableauEnCours = grille.copierTableau(Visuel.tableauTest);
        Visuel.tableauACreer = false;
    }
    
     public void generer1() {
        effacerBoutons();
        boutonsGenerer1();
        Visuel.grilleGenerer1(grille);
        Visuel.tableauEnCours = grille.copierTableau(Visuel.tableauTest);
        Visuel.tableauACreer = false;
    }
    
      public void generer2() {
        effacerBoutons();
        boutonsGenerer2();
        Visuel.grilleGenerer2(grille);
        Visuel.tableauEnCours = grille.copierTableau(Visuel.tableauTest);
        Visuel.tableauACreer = false;
    }
    
      public void generer3() {
        effacerBoutons();
        boutonsGenerer3();
        Visuel.grilleGenerer3(grille);
        Visuel.tableauEnCours = grille.copierTableau(Visuel.tableauTest);
        Visuel.tableauACreer = false;
    }
    
      public void generer4() {
        effacerBoutons();
        boutonsGenerer4();
        Visuel.grilleGenerer4(grille);
        Visuel.tableauEnCours = grille.copierTableau(Visuel.tableauTest);
        Visuel.tableauACreer = false;
    }
    
    
    /**Methode implementant l'appui sur le bouton "Créer".
     * La méthode efface tous les boutons, puis lance la création des boutons suite à l'appui sur le bouton
     * "Créer". On affecte à tableauEnCours une grille vide et on autorise la modification de la grille affichée.
     **/
    public void creer() {
        effacerBoutons();
        boutonsCreer();
        Visuel.tableauEnCours = grille.copierTableau(Visuel.tableauVide);
        Visuel.tableauACreer = true;
        grille.peutRemplir = true;
    }
    
    /**Methode implementant l'appui sur le bouton "Resoudre par moi-même".
     * La méthode efface tous les boutons, puis lance la création des boutons suite à l'appui sur le bouton
     * "Resoudre par moi-même". On autorise la modification de la grille affichée.
     **/
    public void resoudre() {
        effacerBoutons();
        boutonsResoudre();
        grille.peutRemplir = true;
    }
    
    /**Methode implementant l'appui sur le bouton "Resoudre par algorithme".
     * La méthode efface tous les boutons, puis lance la création des boutons suite à l'appui sur le bouton
     * "Resoudre par algorithme". Un test est fait, si tableauACreer est Vrai alors le joueur a pu modifier la grille et on doit
     * la reset au sudoku de base. On lance ensuite la méthode Visuel.grilleResolution(grille) pour resoudre le sudoku.
     **/
    public void solveur(){
        grille.peutRemplir = false;
        effacerBoutons();
        creerBoutonRedemarrer();
        if (Visuel.tableauACreer == false) {
            Visuel.tableauEnCours = grille.copierTableau(Visuel.tableauTest);
            grille.recupererTableau(Visuel.tableauEnCours);
        }
        Visuel.grilleResolution(grille);
    }
    
    /**Methode implementant l'appui sur le bouton 'Recommencer".
     * Si tableauACreer est faux, le joueur est dans le cas d'un sudoku qu'il doit resoudre, on reset alors la grille
     * sur tableauTest qui est la base du sudoku partiellement rempli. Si tableauAcreer est vrai, alors le joueur est
     * en train de créer un sudoku, on reset alors la grille sur une grille vide. On lance ensuite la methode
     * demarrage pour raffraichir le sudoku affiché. 
     **/
    public void recommencer() {
        if(Visuel.tableauACreer == false)  {
            Visuel.tableauEnCours = grille.copierTableau(Visuel.tableauTest);
        }
        else if(Visuel.tableauACreer == true) {
            Visuel.tableauEnCours = grille.copierTableau(Visuel.tableauVide);
        }
        Visuel.demarrage(grille);
    }
    
    /**Methode implementant l'appui sur le bouton "Verifier".
     * La méthode va lancer la méthode Algo_Sudoku.estValide(tableauuEnCours, false) pour tester la validité du sudoku. Si le sudoku est faux, un message s'affiche
     * puis le joueur peut continuer. Si le sudoku est juste, un message s'affiche puis on le traite comme un appui sur le bouton
     * "Redemarrer".
     **/
    public void tester() {
        boolean estCorrect = false;
        String message;
        estCorrect = Algo_Sudoku.estValide(Visuel.tableauEnCours, false);
        if (estCorrect == true) {
            message = "Félicitations, votre grille est correcte !";
            Visuel.afficherMessage(message);
            effacerBoutons();
            creerBoutonRedemarrer();}
        else {
            message = "Votre grille est fausse.";
            Visuel.afficherMessage(message);}
    }
    
    /**Methode implementant l'appui sur le bouton "Redemarrer".
     * La méthode efface tous les boutons, puis lance la création des boutons suite à l'appui sur le bouton
     * "Redemarrer". On n'autorise plus la modification de la grille, on affecte des grilles vides à toutes nos variables
     * et on lance la méthode Visuel.demarrage(grille) pour reset la grille affichée sur la fenetre.
     **/
    public void redemarrer() {
        effacerBoutons();
        boutonsDebut();
        grille.peutRemplir = false;
        Visuel.tableauEnCours = grille.copierTableau(Visuel.tableauVide);
        Visuel.tableauTest = grille.copierTableau(Visuel.tableauVide);
        Visuel.demarrage(grille);
    }
    
     /** Methode implementant l'appui sur le bouton "Indice"
     *  La méthode cherche une case vide dans la grille en cours et affiche en message la valeur correspondante.
     **/
     public void indice() {
       int [][] encours = Visuel.tableauEnCours;
       int [][] Sol = Visuel.grilleIndice(grille);
       int k, i, j, l, c;
       String message;
       k = -1;
       i=-1;
       j=-1;
       Random rand = new Random();
       while (k!=0){	
        	// On sélectionne au hasard une case vide
        		i = rand.nextInt(9);
            		j = rand.nextInt(9);
            		k = encours[i][j];
            	}
       l = i + 1;
       c = j + 1;
       message = "En ligne " + l +" colonne " + c + " la valeur est " + Sol[i][j] +".";
       Visuel.afficherMessage(message);
       
    }
    
    /**Methode implementant l'action de la souris sur les boutons.
     * La methode prend en entrée l'appui d'un bouton, regarde sur quel bouton le joueur a appuyé puis
     * lance la méthode correspondante.
     * @param e Action de la souris sur un bouton.
     **/
    @Override public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == generer) {
            generer();
        }
        else if (source == generer1) {
            generer1();
        }
        else if (source == generer2) {
            generer2();
        }
        else if (source == generer3) {
            generer3();
        }
        else if (source == generer4) {
            generer4();
        }
        else if (source == indice){
        	indice();
        }
        else if (source == creer) {
            creer();
        }
        else if (source == resoudre) {
            resoudre();
        }
        else if (source == solveur) {
            solveur();
        }
        else if (source == recommencer) {
            recommencer();
        }
        else if (source == tester) {
            tester();
        }
        else if (source == redemarrer) {
            redemarrer();
        }
    }
}
