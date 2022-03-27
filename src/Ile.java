import javax.swing.*;
import java.util.Random;

public class Ile {

    private final int taille = 6;
    private Zone[][] grille = new Zone[this.taille][this.taille];

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
        }

    }

    /**
     * Methodes
     */
    public Zone zone(int x, int y){return grille[x][y];}
    public Zone[][] grille() {return this.grille;}
    public int taille() {return this.taille;}

    public void finDeTour(){
        Random random = new Random();

        int x1 = random.nextInt(this.taille);
        int y1 = random.nextInt(this.taille);
        this.grille[x1][y1].inonde();
    }

    public static void main (String[] args){
        System.out.println("coucou");
        Ile ile = new Ile();
        ile.finDeTour();
        ile.finDeTour();
        ile.finDeTour();
        JFrame Ile_Interdite = new ZoneWindow(ile);
        Ile_Interdite.setSize(800,800);
        Ile_Interdite.setVisible(true);
    }


}


