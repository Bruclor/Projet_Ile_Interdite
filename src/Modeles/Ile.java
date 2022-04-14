package Modeles;

import Vue.*;
import Controleurs.*;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

/* =============================================
 * =                                           =
 * =              CLASSE ILE                   =
 * =                                           =
 * =============================================
 */

public class Ile extends Grille {


    /*
      ===========================================
      =                ATTRIBUTS                =
      ===========================================
     */

    private int taille;                                                                        //Taille de la grille
    private Zone[][] grille;                                                                   //Grille de jeu
    private Joueur[] joueurs;                                                                  //Liste des joueurs
    private int nbArtefacts;                                                                   //Nombre d'artefacts
    private int nbJoueurs;                                                                     //Nombre de joueurs
    private int idJoueurEnJeu;                                                                 //id du joueur en jeu
    private Dictionary<Artefact, Boolean> artefactsRecuperes = new Hashtable(4);    //Artefacts de l'équipe
    private ChangeParametre param = ChangeParametre.Joueurs;                                   //Le parametre modifié

    /*
      ===========================================
      =              CONSTRUCTEUR               =
      ===========================================
     */

    /** -- Construit l'ile du jeu
     * @param taille taille de l'ile
     * @param nbJoueurs nombre de joueurs
     * @param nbArtefacts nombre d'artefacts de chaque type
     **/
    public Ile(int taille, int nbJoueurs, int nbArtefacts) {
        super(taille, taille);
        this.nbJoueurs = nbJoueurs;
        this.nbArtefacts = nbArtefacts;
        this.taille = taille;
        this.grille = new Zone[this.taille][this.taille];
        for (int x = 0; x < this.taille; x++) {
            for (int y = 0; y < this.taille; y++) {
                if (x == 0 || x == this.taille - 1) {
                    if (y != 0 && y != 1 && y != this.taille - 2 && y != this.taille - 1) {
                        grille[x][y] = new Zone(x, y, Etat.Normale, Artefact.Vide);
                        this.add(grille[x][y]);
                    } else {
                        grille[x][y] = new Zone(x, y, Etat.Submergee, Artefact.Vide);
                        this.add(grille[x][y]);
                    }
                } else if (x == 1 || x == this.taille - 2) {
                    if (y != 0 && y != this.taille - 1) grille[x][y] = new Zone(x, y, Etat.Normale, Artefact.Vide);
                    else {
                        grille[x][y] = new Zone(x, y, Etat.Submergee, Artefact.Vide);
                        this.add(grille[x][y]);
                    }
                } else {
                    grille[x][y] = new Zone(x, y, Etat.Normale, Artefact.Vide);
                    this.add(grille[x][y]);
                }
            }
        }

    }

    /*
      ===========================================
      =                GETTER                   =
      ===========================================
     */


    /** --Retourne la taille de la grille
     *
     * @return la taille de l'ile
     **/
    public int taille() {
        return this.taille;
    }


    /** --Retourne la grille du jeu
     *
     * @return la grille de l'ile
     **/
    public Zone[][] grille() {
        return this.grille;
    }


    /** --Retourne le nombre de joueurs
     *
     * @return le nombre d'artefacts
     */
    public int getNbJoueurs(){return this.nbJoueurs;}


    /** --Retourne le nombre d'artefacts de chaque type
     *
     * @return le nombre d'artefacts
     */
    public int getNbArtefacts(){return this.nbArtefacts;}


    /** -- Retourne la liste des joueurs
     *
     * @return tableau des joueurs
     **/
    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /** -- Retourne le joueur d'identifiant k
     *
     * @return joueur[k]
     **/
    public Joueur getJoueur(int k) {
        if (k >=0 && k<joueurs.length) {
            return joueurs[k];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    /** -- Retourne le joueur en jeu
     *
     * @return joueur en jeu
     **/
    public Joueur joueurEnJeu(){
        return this.joueurs[idJoueurEnJeu];
    }

    /** -- Retourne la zone de coord (x, y) dans la grille
     *
     * @param x abscisse
     * @param y ordonnée
     *
     * @return zone de coord (x, y)
     **/
    public Zone zone(int x, int y) {
        return grille[x][y];
    }

    public Zone zone(Coord coord){
        return grille[coord.x()][coord.y()];
    }

    /** -- Retourne le vecteur des zones adjacentes d'une zone
     *
     * @param coord la zone dont on veut connaitre les voisins
     *
     * @return vecteur des zones autour de la zone
     **/
    public Vector<Coord> adjacents(Coord coord) {
        Vector<Coord> adj = new Vector<Coord>();
        if (coord.x() < this.taille - 1) {
            adj.add(new Coord(coord.x() + 1, coord.y()));
        }
        if (coord.x() > 0) {
            adj.add(new Coord(coord.x() - 1, coord.y()));
        }
        if (coord.y() < this.taille - 1) {
            adj.add(new Coord(coord.x(), coord.y() + 1));
        }
        if (coord.y() > 0) {
            adj.add(new Coord(coord.x(), coord.y() - 1));
        }
        return adj;
    }

    /** -- Retourne un vecteur des zones non submergées
     *
     * @return vecteurs des zones dispos
     **/
    public Vector<Zone> getZonesDispo() {
        Vector<Zone> dispo = new Vector<Zone>();
        for (int x = 0; x < this.taille; x++) {
            for (int y = 0; y < this.taille; y++) {
                if (this.zone(x, y).etat() != Etat.Submergee) {
                    dispo.add(this.zone(x, y));
                }
            }
        }
        return dispo;
    }

    /** -- Indique si l'artefact a a été recupere
     *
     * @param a un artefact
     *
     * @return booleen vrai si l'artefact a été recupere
     **/
    public boolean possedeArtefact(Artefact a){
        return artefactsRecuperes.get(a);
    }

    /** -- Retourne quatre booleen indiquant quels artefacts sont recuperes
     *
     * @return Dict<Artefact, booleen>
     **/
    public Dictionary<Artefact, Boolean> getArtefactsRecuperes(){return this.artefactsRecuperes;}

    /** -- Indique le type de parametrage effectué
     *
     * @param p le type de parametrage
     */
    public void parametrage(ChangeParametre p){this.param = p;}


    /** -- Indique si l'équipe a perdu
     *
     * @return booleen vrai s'il y a une défaite
     **/
    public boolean GameOver(){
        /* Améliorer la fonction en testant :
         * - Si un artefact non recupéré n'est plus accesible -> game over
         * - Si l'héliport est inaccessible -> game over
         */
        for (int k=0; k<this.joueurs.length; k++){
            if (! joueurs[k].estElimine()){
                return false;
            }
        }
        return true;
    }

    /*
      ===========================================
      =                 SETTER                  =
      ===========================================
     */

    /**
     * -- Methode initialisation des joueurs de l'ile
     *
     **/
    public void InitJoueurs() {
        this.joueurs = new Joueur[nbJoueurs];
        Random random = new Random();
        Vector<Zone> dispo = this.getZonesDispo();
        int alea;
        for (int k = 0; k < nbJoueurs; k++) {
            alea = random.nextInt(dispo.size());
            switch (k){
                case 0: this.joueurs[k] = new Joueur(k, "J" + (k+1), dispo.get(alea).coord(), Color.RED); break;
                case 1: this.joueurs[k] = new Joueur(k, "J" + (k+1), dispo.get(alea).coord(), Color.GREEN); break;
                case 2: this.joueurs[k] = new Joueur(k, "J" + (k+1), dispo.get(alea).coord(), Color.BLUE.darker()); break;
                case 3: this.joueurs[k] = new Joueur(k, "J" + (k+1), dispo.get(alea).coord(), Color.ORANGE); break;

            }
            dispo.get(alea).addJoueur(joueurs[k]);
            dispo.remove(alea);
        }
        this.idJoueurEnJeu = 0;
        this.joueurs[idJoueurEnJeu].setNbActions(3);
    }


    /** -- Methode initialisation des artefacts de l'ile
     **/
    public void InitArtefacts() {
        Random random = new Random();
        Vector<Zone> dispo = this.getZonesDispo();
        int alea;
        alea = random.nextInt(dispo.size());
        dispo.get(alea).setArtefact(Artefact.Heliport);
        dispo.remove(alea);
        for (int k = 0; k < nbArtefacts; k++) {
            alea = random.nextInt(dispo.size());
            dispo.get(alea).setArtefact(Artefact.Air);
            dispo.remove(alea);
        }
        for (int k = 0; k < nbArtefacts; k++) {
            alea = random.nextInt(dispo.size());
            dispo.get(alea).setArtefact(Artefact.Feu);
            dispo.remove(alea);
        }
        for (int k = 0; k < nbArtefacts; k++) {
            alea = random.nextInt(dispo.size());
            dispo.get(alea).setArtefact(Artefact.Terre);
            dispo.remove(alea);
        }
        for (int k = 0; k < nbArtefacts; k++) {
            alea = random.nextInt(dispo.size());
            dispo.get(alea).setArtefact(Artefact.Eau);
            dispo.remove(alea);
        }
        this.artefactsRecuperes.put(Artefact.Air, false);
        this.artefactsRecuperes.put(Artefact.Eau, false);
        this.artefactsRecuperes.put(Artefact.Feu, false);
        this.artefactsRecuperes.put(Artefact.Terre, false);

    }

    /** -- Methode initialisation des zones graphiques de l'ile
     **/
    public void InitZoneIG(){
        for (int x=0; x<this.taille; x++){
            for (int y=0; y<this.taille; y++){
                this.add(this.zone(x, y));
            }
        }

    }

    /** -- Modifie le nombre d'artefacts de l'ile
     **/
    public void setNbArtefacts(int n) {
        if (n > 0 && n < 5) {
            nbArtefacts = n;
        }
    }

    /** -- Deplace le joueur en jeu vers une zone de destination
     *
     * @param vectDirection le vecteur direction de déplacement
     **/
    public void deplace(Coord vectDirection) {
        Joueur j = joueurs[idJoueurEnJeu];
        Coord dest = new Coord(j.x() + vectDirection.x(), j.y()+ vectDirection.y());
        Vector<Coord> adjacents = adjacents(j.coord());
        System.out.println("Joueur en ("+ j.x() + ", "+ j.y()+") veut aller en ("+ dest.x() + "," + dest.y() + ")");
        //if (zone(dest).etat() == Etat.Submergee){System.out.println("Mais la zone est submergee !");}
        for (int k = 0; k<adjacents.size(); k++){
            System.out.println(adjacents.get(k).x() + " " + adjacents.get(k).y());
            if (adjacents.get(k).equals(dest)){
                System.out.println("coord trouvee");
                if (zone(dest).etat() != Etat.Submergee && j.getNbActions() > 0) {
                    System.out.println("Un joueur se deplace");
                    zone(j.x(), j.y()).removeJoueur(j);
                    j.deplace(dest);
                    zone(dest).addJoueur(j);
                    j.effectueAction();
                    break;
                }
            }
        }

    }

    /** -- Un joueur asseche une zone
     *
     * @param zone zone a assecher
     **/
    public void asseche(Zone zone) {
        Joueur j = joueurs[idJoueurEnJeu];
        Vector<Coord> adjacents = this.adjacents(new Coord (j.x(), j.y()));
        if ((adjacents.contains(zone) || (j.x() == zone.x() && j.y() == zone.y())) && zone.etat() == Etat.Inondee && j.getNbActions() > 0) {
            zone.asseche();
            j.effectueAction();
        }
    }

    /** -- Un joueur cherche une clé
     **/
    public void chercheCle() {
        Joueur j  = joueurs[idJoueurEnJeu];
        if (j.getNbActions() > 0) {
            Random random = new Random();
            int r = random.nextInt(6);
            if (r == 0) j.gagneCle(Artefact.Air);
            if (r == 1) j.gagneCle(Artefact.Eau);
            if (r == 2) j.gagneCle(Artefact.Feu);
            if (r == 3) j.gagneCle(Artefact.Terre);
            if (r == 4 || r == 5) {
                grille[j.x()][j.y()].inonde();
                if (grille[j.x()][j.y()].etat() == Etat.Submergee){
                    j.elimine();
                    grille[j.x()][j.y()].removeJoueur(j);
                }
            }
            j.effectueAction();
        }
    }

    /** -- Inonde trois cases differentes et termine le tour
     **/
    public void finDeTour() {
        Vector<Zone> dispo = this.getZonesDispo();
        Random random = new Random();
        int alea = -1;
        int nb = 0;
        while (dispo.size() > 0 && nb < 3) {
            nb++;
            alea = random.nextInt(dispo.size());
            dispo.get(alea).inonde();
            dispo.remove(alea);
        }
        for (int k=0; k<joueurs.length; k++) {
            Joueur j = joueurs[k];
            if ((!j.estElimine()) && grille[j.x()][j.y()].etat() == Etat.Submergee) {

                System.out.println(j.nom()+" est elimine");
                j.elimine();
                grille[j.x()][j.y()].removeJoueur(j);
            }
        }
        this.joueurSuivant();

    }

    /** -- Passage au joueur suivant
     **/
    public void joueurSuivant() {
        int nb = this.nbJoueurs;
        int iter = 0;
        do {
            iter++;
            if (this.idJoueurEnJeu == nb-1){
                this.idJoueurEnJeu = 0;
            } else {
                this.idJoueurEnJeu++;
            }
        } while (joueurs[idJoueurEnJeu].estElimine() && iter <= nb);
        joueurs[idJoueurEnJeu].setNbActions(3);

    }

    /** -- L'équipe recupere l'artefact a
     *
     * @param a l'artefact recupere
     **/
    public void artefactRecupere(Artefact a){
        this.artefactsRecuperes.put(a, true);
    }

    /**
     * Modifie la valeur du parametre concerné
     *
     * @param n entier
     */
    public void operateParam(int n){

        switch (this.param){

            case Joueurs :
                this.nbJoueurs = n;
                break;

            case Artefacts :
                this.nbArtefacts = n;
                break;
        }
    }
}
