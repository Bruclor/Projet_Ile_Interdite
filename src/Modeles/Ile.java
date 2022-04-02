package Modeles;

import Vue.*;

import java.util.Random;
import java.util.Vector;

public class Ile extends Grille {

    private int taille;
    private Zone[][] grille;
    private Joueur[] joueurs;
    private int tour;

    /**
     * Constructeurs
     **/
    public Ile(int taille) {
        super(taille, taille);
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


    public void InitJoueurs(int nb, String[] noms) {
        this.joueurs = new Joueur[nb];
        Random random = new Random();
        Vector<Zone> dispo = this.getZonesDispo();
        int alea;
        if (nb == noms.length) {
            for (int k = 0; k < nb; k++) {
                alea = random.nextInt(dispo.size());
                Coord posalea = dispo.get(alea).coord();
                this.grille[posalea.x()][posalea.y()].addJoueur(k);
                this.joueurs[k] = new Joueur(k, noms[k], posalea);
                dispo.get(alea).addJoueur(k);
                dispo.remove(alea);
            }
        } else {
            for (int k = 0; k < nb; k++) {
                alea = random.nextInt(dispo.size());
                this.joueurs[k] = new Joueur(k, "J" + k, dispo.get(alea).coord());
                dispo.remove(alea);
            }
        }
    }

    public void InitArtefacts(int nbArtefacts) {
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

    public Zone zone(int x, int y) {
        return grille[x][y];
    }

    public Zone[][] grille() {
        return this.grille;
    }

    public int taille() {
        return this.taille;
    }

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

    public void deplace(Joueur j, Zone dest) {
        Vector<Coord> adjacents = this.adjacents(this.zone(j.x(), j.y()));
        if (adjacents.contains(dest) && dest.etat() != Etat.Submergee) {
            j.deplace(new Coord(dest.x(), dest.y()));
        }
    }

    public void asseche(Joueur j, Zone zone) {
        Vector<Coord> adjacents = this.adjacents(this.zone(j.x(), j.y()));
        if ((adjacents.contains(zone) || (j.x() == zone.x() && j.y() == zone.y())) && zone.etat() == Etat.Inondee) {
            zone.asseche();
        }
    }

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

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    public void chercheCle(Joueur j) {
        Random random = new Random();
        int r = random.nextInt(6);
        if (r == 0) j.ajouteCle(Artefact.Air);
        if (r == 1) j.ajouteCle(Artefact.Eau);
        if (r == 2) j.ajouteCle(Artefact.Feu);
        if (r == 3) j.ajouteCle(Artefact.Terre);
        if (r == 4 || r == 5) grille[j.x()][j.y()].inonde();
    }

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
    }
}