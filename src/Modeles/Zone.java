package Modeles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class Zone extends JPanel implements MouseListener{

    /** Attributs **/
    /*Une zone a des coordonnées x et y (abscisse et ordonnée)
    * et peut etre un heliport ou avoir un artéfact ou etre vide*/

    private Coord coord;
    private Etat etat;
    private Artefact artefact;
    private Vector<Integer> idJoueurs;

    /** Constructeurs
     * @param x
     * @param y
     * @param etat
     * @param artefact **/
    public Zone(int x, int y, Etat etat, Artefact artefact){
        this.etat = etat;
        this.artefact = artefact;
        this.coord = new Coord(x, y);
        this.idJoueurs = new Vector<Integer>(0);
        addMouseListener(this);
    }

    /** Methodes **/
    public int x(){return this.coord.x();}
    public int y(){return this.coord.y();}
    public Coord coord(){return this.coord;}
    public Etat etat(){return this.etat;}
    public Artefact artefact(){return this.artefact;}

    public void setArtefact(Artefact artefact) {
        this.artefact = artefact;
    }

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

    public void inonde(){
        if (this.etat == Etat.Normale) this.etat = Etat.Inondee;
        else if (this.etat == Etat.Inondee) this.etat = Etat.Submergee;
    }

    public void asseche(){
        if (this.etat == Etat.Inondee) this.etat = Etat.Normale;
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.BLUE.darker());
        g.fillRect(0, 0, 99,99);
        if (this.etat==Etat.Inondee){g.setColor(Color.BLUE); g.fillRect(0, 0, 99,99);}
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

    public void mouseClicked(MouseEvent e) {
        System.out.print(this.x());
        System.out.println(this.y());
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void addJoueur(int k) {
        this.idJoueurs.add(k);
    }

    public void removeJoueur(int k) {
        this.idJoueurs.remove(k);
    }
}
