import java.awt.*;
import javax. swing .*;
        
public class Fenetre extends JPanel {
     
    public void paint(Graphics g) {
        final int MARGE_HAUT = 200;
        final int MARGE_GAUCHE = 100;
        final int PAS = 45;
        for (int coord = 0; coord < 10 ; coord ++) {
            g.drawLine(MARGE_GAUCHE + PAS*coord,MARGE_HAUT, MARGE_GAUCHE + PAS*coord, MARGE_HAUT + PAS*9);
            g.drawLine(MARGE_GAUCHE,MARGE_HAUT + PAS*coord, MARGE_GAUCHE + PAS*9, MARGE_HAUT + PAS*coord); }
    }
    
    public static void main(String [] arg) {   
        final int HAUTEUR = 750;
        final int LARGEUR = 800;
        JFrame window = new JFrame();
        window.getContentPane().add(new Fenetre());
        window.setTitle("Jeu de sudoku");
	window.setSize(LARGEUR,HAUTEUR);
        window.setVisible(true);
        window.setResizable(false);
        window.setBackground(Color.gray);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


