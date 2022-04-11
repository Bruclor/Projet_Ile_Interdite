package Modeles;

import Vue.*;
import Controleurs.*;

import java.util.Random;
import java.util.Vector;

/**
 * Une ile avec une grille d'une certaine taille
 * Une ile posssede un ou plusieurs joueurs
 **/
import static Controleurs.ChangeParametre.*;

public class Ile extends Grille {

    /*******************/
    /**   Attributs   **/
    /*******************/

    private int taille;
    private Zone[][] grille;
    private Joueur[] joueurs;
    private int nbArtefacts;
    private int nbJoueurs;
    private int tour;
    private Joueur JoueurEnJeu;


    private ChangeParametre param = ChangeParametre.Joueurs;

    /*******************/
    /** Constructeur  **/
    /*******************/

    /**
     * Constructeur
     *
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
        this.tour = 0;
    }

    /**
     * Initialise les joueurs de l'ile
     *
     **/
    public void InitJoueurs() {
        this.joueurs = new Joueur[nbJoueurs];
        Random random = new Random();
        Vector<Zone> dispo = this.getZonesDispo();
        int alea;
        for (int k = 0; k < nbJoueurs; k++) {
            alea = random.nextInt(dispo.size());
            this.joueurs[k] = new Joueur(k, "J" + k, dispo.get(alea).coord());
            dispo.get(alea).addJoueur(k);
            dispo.remove(alea);
        }
    }


    /**
     * Initialise les artefacts de l'ile
     *
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

    }

    public void InitZoneIG(){
        for (int x=0; x<this.taille; x++){
            for (int y=0; y<this.taille; y++){
                this.add(this.zone(x, y));
            }
        }

    }

    /*******************/
    /**    Getter     **/
    /*******************/

    /**
     * Getter grille
     *
     * @return la grille de l'ile
     **/
    public Zone[][] grille() {
        return this.grille;
    }

    /**
     * Getter taille
     *
     * @return la taille de l'ile
     **/
    public int taille() {
        return this.taille;
    }

    public int getNbArtefacts(){return this.nbArtefacts;}
    public void setNbArtefacts(int n) {
        if (n > 0 && n < 5) {
            nbArtefacts = n;
        }
    }

    public int getNbJoueurs(){return this.nbJoueurs;}

    /**
     * Getter grille
     *
     * @return tableau des joueurs
     **/
    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /*******************/
    /**    Méthode    **/
    /*******************/

    /**
     *  renvoie la zone de coord x y dans la grille
     *
     * @param x abscisse
     * @param y ordonnée
     *
     * @return zone de coord x y
     **/
    public Zone zone(int x, int y) {
        return grille[x][y];
    }

    /**
     * renvoie le vecteur des zones autour d'une zone
     *
     * @param zone la zone dont on veut connaitre les voisins
     *
     * @return vecteur des zones autour de la zone
     **/
    public Vector<Coord> adjacents(Zone zone) {
        Vector<Coord> adj = new Vector<Coord>();
        if (zone.x() < this.taille - 1) {
            adj.add(new Coord(zone.x() + 1, zone.y()));
        }
        if (zone.x() > 0) {
            adj.add(new Coord(zone.x() - 1, zone.y()));
        }
        if (zone.y() < this.taille - 1) {
            adj.add(new Coord(zone.x(), zone.y() + 1));
        }
        if (zone.y() > 0) {
            adj.add(new Coord(zone.x() + 1, zone.y()));
        }
        return adj;
    }

    /**
     * Un joueur se déplace vers une zone
     *
     * @param j identifiant du joueur
     * @param dest destination
     **/
    public void deplace(Joueur j, Zone dest) {
        Vector<Coord> adjacents = this.adjacents(this.zone(j.x(), j.y()));
        if (adjacents.contains(dest) && dest.etat() != Etat.Submergee) {
            j.deplace(new Coord(dest.x(), dest.y()));
        }
    }

    /**
     * Un joueur asseche un zone
     *
     * @param j identifiant du joueur
     * @param zone zone a assecher
     **/
    public void asseche(Joueur j, Zone zone) {
        Vector<Coord> adjacents = this.adjacents(this.zone(j.x(), j.y()));
        if ((adjacents.contains(zone) || (j.x() == zone.x() && j.y() == zone.y())) && zone.etat() == Etat.Inondee) {
            zone.asseche();
        }
    }

    /**
     * Un joueur cherche une clé
     *
     * @param j identifiant du joueur
     **/
    public void chercheCle(Joueur j) {
        Random random = new Random();
        int r = random.nextInt(6);
        if (r == 0) j.ajouteCle(Artefact.Air);
        if (r == 1) j.ajouteCle(Artefact.Eau);
        if (r == 2) j.ajouteCle(Artefact.Feu);
        if (r == 3) j.ajouteCle(Artefact.Terre);
        if (r == 4 || r == 5) grille[j.x()][j.y()].inonde();
    }

    /**
     * revoie un vecteur des zones disponibles
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

    /**
     * Inonde trois cases differentes et termine le tour
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

        JoueurEnJeu = joueurs[(JoueurEnJeu.id()+1)%joueurs.length];
    }
    /**
    public void actualiseArtefactEnPossession(){
        for (int k=0; k<joueurs.length; k++){
            if (joueurs[k].possedeArtefact(Artefact.Air)){this.artefactsRecuperes[0] = true; break;}
        }
        for (int k=0; k<joueurs.length; k++){
            if (joueurs[k].possedeArtefact(Artefact.Eau)){this.artefactsRecuperes[1] = true; break;}
        }
        for (int k=0; k<joueurs.length; k++){
            if (joueurs[k].possedeArtefact(Artefact.Feu)){this.artefactsRecuperes[2] = true; break;}
        }
        for (int k=0; k<joueurs.length; k++){
            if (joueurs[k].possedeArtefact(Artefact.Terre)){this.artefactsRecuperes[3] = true; break;}
        }
    }
     **/

    /**
     * Indique le type de parametrage effectué
     *
     * @param p le type de parametrage
     */
    public void parametrage(ChangeParametre p){this.param = p;}

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