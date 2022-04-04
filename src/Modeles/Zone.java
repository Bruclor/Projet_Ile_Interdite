package Modeles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

/**
 * Une zone dans une grille avec des coordonnées
 * La zone peut etre normale, inondée ou submergée
 * La zone peut avoir un artéfact, un heliport ou rien
 **/
public class Zone extends JPanel implements MouseListener{

    /*******************/
    /**   Attributs   **/
    /*******************/

    private Coord coord;
    private Etat etat;
    private Artefact artefact;
    private Vector<Integer> idJoueurs;

    /*******************/
    /** Constructeur  **/
    /*******************/

    /** Constructeurs
     *
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @param etat Etat Normal, Inondee ou Submergee
     * @param artefact Artefact (Air, Terre, Feu, Eau), Heliport ou Vide
     **/
    public Zone(int x, int y, Etat etat, Artefact artefact){
        this.etat = etat;
        this.artefact = artefact;
        this.coord = new Coord(x, y);
        this.idJoueurs = new Vector<Integer>(0);
        addMouseListener(this);
    }

    /*******************/
    /**    Getter     **/
    /*******************/

    /**
     * Getter x
     *
     * @return Coordonnée x
     **/
    public int x(){return this.coord.x();}

    /**
     * Getter y
     *
     * @return Coordonnée y
     **/
    public int y(){return this.coord.y();}

    /**
     * Getter coord
     *
     * @return Coordonnée
     **/
    public Coord coord(){return this.coord;}

    /**
     * Getter etat
     *
     * @return Etat
     **/
    public Etat etat(){return this.etat;}

    /**
     * Getter artefact
     *
     * @return Coordonnée x
     **/
    public Artefact artefact(){return this.artefact;}

    /*******************/
    /**    Méthode    **/
    /*******************/

    /**
     * Setter Artefact
     *
     *
     * @param artefact artefact
     **/
    public void setArtefact(Artefact artefact) {
        this.artefact = artefact;
    }

    /**
     * Affiche une zone en chaine de caractere
     *
     * @return chaine de carctere
     **/
    public String toString(){
        String res = "";
        if (this.artefact == Artefact.Eau) res += "E";
        else if (this.artefact == Artefact.Terre) res += "T";
        else if (this.artefact == Artefact.Feu) res += "F";
        else if (this.artefact == Artefact.Air) res += "A";
        else if (this.artefact == Artefact.Heliport) res += "H";
        else res += "V";
        if (this.etat == Etat.Normale) res += "N";
        else if (this.etat == Etat.Inondee) res += "I";
        else res += "S";
        return res;
    }

    /**
     * Inonde la zone
     **/
    public void inonde(){
        if (this.etat == Etat.Normale) this.etat = Etat.Inondee;
        else if (this.etat == Etat.Inondee) this.etat = Etat.Submergee;
    }

    /**
     * asseche la zone
     **/
    public void asseche(){
        if (this.etat == Etat.Inondee) this.etat = Etat.Normale;
    }

    /**
     * Dessine la zone avec les artefacts et les joueurs dedans
     *
     * @param g le graphique où dessiner
     **/
    public void paintComponent(Graphics g){
        g.setColor(Color.BLUE.darker());
        g.fillRect(0, 0, 99,99);
        if (this.etat==Etat.Inondee){g.setColor(new Color (100, 120, 240)); g.fillRect(0, 0, 99,99);}
        else if(this.etat==Etat.Normale){g.setColor(Color.ORANGE.darker()); g.fillRect(0, 0, 99,99);}
        if (this.artefact!=Artefact.Vide) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 25, 25);
            if (this.artefact == Artefact.Air) {
                g.setColor(Color.GRAY);
            } else if (this.artefact == Artefact.Eau) {
                g.setColor(Color.BLUE.brighter());
            } else if (this.artefact == Artefact.Feu) {
                g.setColor(Color.RED);
            } else if (this.artefact == Artefact.Terre) {
                g.setColor(Color.GREEN);
            }
            g.fillOval(2, 2, 21, 21);

        }
        for (int id=0; id<this.idJoueurs.size(); id++){
            if (idJoueurs.get(id) == 0) {g.setColor(Color.RED);}
            else if (idJoueurs.get(id) == 1) {g.setColor(Color.CYAN);}
            else if (idJoueurs.get(id) == 2) {g.setColor(Color.YELLOW.darker());}
            else if (idJoueurs.get(id) == 3) {g.setColor(Color.GREEN.darker());}
            g.fillOval(id*30, 90-id*30, 25, 25);
        }
    }

    /**
     * affiche les coordonnées de la zone si on clique dessus
     *
     * @param e evenement
     **/
    public void mouseClicked(MouseEvent e) {
        System.out.print(this.x());
        System.out.println(this.y());
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    /**
     * ajoute un joueur dans la zone
     *
     * @param k id du joueur
     **/
    public void addJoueur(int k) {
        this.idJoueurs.add(k);
    }

    /**
     * retire un joueur dans la zone
     *
     * @param k id du joueur
     **/
    public void removeJoueur(int k) {
        this.idJoueurs.remove(k);
    }
}
