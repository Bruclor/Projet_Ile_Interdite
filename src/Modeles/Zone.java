package Modeles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

/* =============================================
 * =                                           =
 * =              CLASSE ZONE                  =
 * =                                           =
 * =============================================
 */

public class Zone extends JPanel implements MouseListener {

    /*
      ===========================================
      =                ATTRIBUTS                =
      ===========================================
     */

    private Coord coord;                                         //Coordonnées de la zone
    private Etat etat;                                           //Etat de la zone
    private Artefact artefact;                                   //Artefact présent sur la zone
    private Vector<Joueur> joueurs;                              //Joueurs présents sur la zone

    /*
      ===========================================
      =              CONSTRUCTEUR               =
      ===========================================
     */

    /** -- Construit une zone du jeu
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
        this.joueurs = new Vector<Joueur>();
        addMouseListener(this);
    }

    /*
      ===========================================
      =                GETTER                   =
      ===========================================
     */

    /** -- Coordonnée x de la zone
     *
     * @return Coordonnée x
     **/
    public int x(){return this.coord.x();}

    /** -- Coordonnée y de la zone
     *
     * @return Coordonnée y
     **/
    public int y(){return this.coord.y();}

    /** -- Coordonnées de la zone
     *
     * @return Coordonnée
     **/
    public Coord coord(){return this.coord;}

    /** -- Etat de la zone
     *
     * @return Etat
     **/
    public Etat etat(){return this.etat;}

    /** -- Artefact de la zone
     *
     * @return Artefact de la zone
     **/
    public Artefact artefact(){return this.artefact;}

    /** -- Joueurs sur la zone
     *
     * @return joueurs de la zone
     **/
    public Vector<Joueur> getJoueurs(){return this.joueurs;}

    /** -- Affiche une zone en chaine de caractere
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

    /** -- Representation graphique de la zone
     *
     * @param g Un graphic
     **/
    public void paintComponent(Graphics g){
        g.setColor(Color.BLUE.darker());
        g.fillRect(0, 0, 99,99);
        if (this.etat()!=Etat.Submergee) {
            if (this.etat == Etat.Inondee) {
                g.setColor(new Color(100, 120, 240));
                g.fillRect(0, 0, 99, 99);
            } else if (this.etat == Etat.Normale) {
                g.setColor(Color.GREEN.darker().darker());
                g.fillRect(0, 0, 99, 99);
            }

            if (this.artefact != Artefact.Vide) {
                if (this.artefact == Artefact.Heliport) {
                    g.setColor(Color.YELLOW);
                    g.fillOval(5, 5, 80, 80);
                    g.setColor(Color.WHITE.darker().darker());
                    g.fillOval(10, 10, 70, 70);
                    g.setColor(Color.YELLOW);
                    g.fillRect(35, 40, 20, 10);
                    g.fillRect(30, 25, 10, 40);
                    g.fillRect(50, 25, 10, 40);
                } else {
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

            }
            for (int id = 0; id < this.joueurs.size(); id++) {
                g.setColor(joueurs.get(id).couleur());
                g.fillOval(id * 20 + 5, 85 - (id + 1) * 20, 20, 20);
            }
        }
    }

    /*
      ===========================================
      =                SETTER                   =
      ===========================================
     */

    /** -- Place un artefact sur la zone
     *
     * @param artefact un artefact
     **/
    public void setArtefact(Artefact artefact) {
        this.artefact = artefact;
    }

    /** -- Inonde la zone
     **/
    public void inonde(){
        if (this.etat == Etat.Normale) this.etat = Etat.Inondee;
        else if (this.etat == Etat.Inondee) this.etat = Etat.Submergee;
    }

    /** -- Asseche la zone
     **/
    public void asseche(){
        if (this.etat == Etat.Inondee) this.etat = Etat.Normale;
    }

    /** -- Ajoute un joueur dans la zone
     *
     * @param j joueur
     **/
    public void addJoueur(Joueur j) {
        this.joueurs.add(j);
    }

    /** -- retire un joueur dans la zone
     *
     * @param j joueur
     **/
    public void removeJoueur(Joueur j) {
        this.joueurs.remove(j);
    }

    /*
      ===========================================
      =              IMPLEMENTS                 =
      ===========================================
     */

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


}
