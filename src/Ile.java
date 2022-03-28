import javax.swing.*;
import java.util.Random;

public class Ile {

    private final int taille = 6;
    private Zone[][] grille = new Zone[this.taille][this.taille];
    private Joueur[] joueurs;
    private int tour;

    /**
     * Constructeurs
     **/
    public Ile() {
        for (int x=0; x<this.taille; x++){
            for (int y=0; y<this.taille; y++){
                if (x == 0 || x == 5){
                    if (y == 2 || y == 3) grille[x][y] = new Zone(x, y, Etat.Normale, Artefact.Vide);
                    else grille[x][y] = new Zone(x, y, Etat.Submergee, Artefact.Vide);
                } else if (x == 1 || x == 4){
                    if (y != 0 && y != 5) grille[x][y] = new Zone(x, y, Etat.Normale, Artefact.Vide);
                    else grille[x][y] = new Zone(x, y, Etat.Submergee, Artefact.Vide);
                } else {
                    grille[x][y] = new Zone(x, y, Etat.Normale, Artefact.Vide);
                }
            }
            tour = 0;
        }

    }
    /**
    public InitJoueurs(int nb){
        this.joueurs = new Joueur[nb];
        for (int k=0; k<nb; k++){
            this.joueurs[k] = new Joueur()
        }
    }
    **/
    /**
     * Methodes
     */
    public Zone zone(int x, int y){return grille[x][y];}
    public Zone[][] grille() {return this.grille;}
    public int taille() {return this.taille;}

    //petit bug
    public void finDeTour() {
        if (tour < 16) {
            Random random = new Random();
            int x1;
            int y1;
            int x2;
            int y2;
            int x3;
            int y3;
            do {
                x1 = random.nextInt(this.taille);
                y1 = random.nextInt(this.taille);
            } while (this.grille[x1][y1].etat() == Etat.Submergee);
            do {
                x2 = random.nextInt(this.taille);
                y2 = random.nextInt(this.taille);
            } while (this.grille[x2][y2].etat() == Etat.Submergee || (x2 == x1 && y2 == y1));
            do {
                x3 = random.nextInt(this.taille);
                y3 = random.nextInt(this.taille);
            } while (this.grille[x3][y3].etat() == Etat.Submergee || (tour < 15 && (x3 == x1 && y3 == y1) || (x3 == x2 && y3 == y2))
                     || (tour == 15 && (x3 == x1 && y3 == y1 && this.grille[x1][y1].etat() == Etat.Submergee))
                     || (tour == 15 && (x3 == x2 && y3 == y2 && this.grille[x2][y2].etat() == Etat.Submergee)));
            this.grille[x1][y1].inonde();
            this.grille[x2][y2].inonde();
            this.grille[x3][y3].inonde();
            tour += 1;
        }
    }

    public static void main (String[] args){
        Random random = new Random();
        System.out.print(random);
        System.out.println("coucou");
        System.out.print(random.nextInt(6));
        System.out.print(random.nextInt(6));
        System.out.println("coucou");
        Ile ile = new Ile();
        JFrame Ile_Interdite = new ZoneWindow(ile);
        Ile_Interdite.setSize(800,800);
        Ile_Interdite.setVisible(true);


    }
}


