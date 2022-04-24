package Modeles;

import Vue.Panneau;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/* =============================================
 * =                                           =
 * =              CLASSE ZONE                  =
 * =                                           =
 * =============================================
 */

public class Zone extends Panneau {

    /*
      ===========================================
      =                ATTRIBUTS                =
      ===========================================
     */

    private Coord coord;                                         //Coordonnées de la zone
    private Etat etat;                                           //Etat de la zone
    private Artefact artefact;                                   //Artefact présent sur la zone
    private Vector<Joueur> joueurs;                              //Joueurs présents sur la zone
    private boolean isSelected;

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
        super(Color.WHITE);
        this.etat = etat;
        this.artefact = artefact;
        this.coord = new Coord(x, y);
        this.joueurs = new Vector<Joueur>();
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
        g.setColor(Color.BLUE.darker());                               //Par defaut la case est submergée (Bleu foncé)
        g.fillRect(0, 0, 99,99);                     //La taille d'une case : 100x100
        if (this.etat()!=Etat.Submergee) {                             //Si la case n'est pas submergée
            if (this.etat == Etat.Inondee) {                           //Cas Inondee :
                g.setColor(new Color(100, 120, 240));                  //Un bleu un peu plus clair...
                g.fillRect(0, 0, 99, 99);                    //...sur l'ensemble de la case
            } else if (this.etat == Etat.Normale) {                    //Cas normale :
                g.setColor(Color.GREEN.darker().darker());                      //Couleur verte...
                g.fillRect(0, 0, 99, 99);                    //... sur l'ensemble de la case
            }

            if (this.artefact != Artefact.Vide) {
                if (this.artefact == Artefact.Heliport) {              //S'il y a un héliport...
                    g.setColor(Color.YELLOW);                               //On dessine un héliport...
                    g.fillOval(5, 5, 80, 80);
                    g.setColor(Color.WHITE.darker().darker());
                    g.fillOval(10, 10, 70, 70);
                    g.setColor(Color.YELLOW);
                    g.fillRect(35, 40, 20, 10);
                    g.fillRect(30, 25, 10, 40);
                    g.fillRect(50, 25, 10, 40);
                }

            }
            for (int id = 0; id < this.joueurs.size(); id++) {        //Pour chaque joueur présents sur la case...
                g.setColor(joueurs.get(id).couleur());                       //On recupere sa couleur (mise en attribut de 'joueur')
                g.fillOval(id * 20 + 5, 85 - (id + 1) * 20, 20, 20); //On dessine un rond de cette couleur
            }

        }
        if (this.isSelected){
            g.setColor(Color.GREEN);
            g.drawRect(0, 0, 92, 88);
            g.drawRect(1, 1, 90, 86);
            g.drawRect(2, 2, 88, 84);
        }
    }

    /*
      ===========================================
      =                SETTER                   =
      ===========================================
     */

    /** -- Place un artefact sur une zone vide
     *
     * @param artefact un artefact
     **/
    public void setArtefact(Artefact artefact) {
        if (this.artefact() == Artefact.Vide) {
            this.artefact = artefact;
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            if (this.artefact == Artefact.Air) {
                Icon icon = new ImageIcon("src/Images/air.png");
                JLabel air = new JLabel();
                air.setIcon(icon);
                this.add(air);
            } else if (this.artefact == Artefact.Eau) {
                Icon icon = new ImageIcon("src/Images/eau.png");
                JLabel eau = new JLabel();
                eau.setIcon(icon);
                this.add(eau);
            } else if (this.artefact == Artefact.Feu) {
                Icon icon = new ImageIcon("src/Images/feu.png");
                JLabel feu = new JLabel();
                feu.setIcon(icon);
                this.add(feu);
            } else if (this.artefact == Artefact.Terre) {
                Icon icon = new ImageIcon("src/Images/terre.png");
                JLabel terre = new JLabel();
                terre.setIcon(icon);
                this.add(terre);
            }
        }
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

    /** -- Selectionne la zone
     */
    public void select(){this.isSelected = true;}

    /** -- La case n'est plus selectionne
     */
    public void quit(){this.isSelected = false;}
}
