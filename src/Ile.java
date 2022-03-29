import javax.swing.*;
import java.util.Random;
import java.util.Vector;

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


    public void InitJoueurs(int nb, String[] noms){
        this.joueurs = new Joueur[nb];
        Random random = new Random();
        Vector<Zone> dispo = this.getZonesDispo();
        int alea;
        if (nb == noms.length) {
            for (int k = 0; k < nb; k++) {
                alea = random.nextInt(dispo.size());
                this.joueurs[k] = new Joueur(k, noms[k], dispo.get(alea).coord());
                dispo.remove(alea);
            }
        }
        else {
            for (int k = 0; k < nb; k++) {
                alea = random.nextInt(dispo.size());
                this.joueurs[k] = new Joueur(k, "J" + k, dispo.get(alea).coord());
                dispo.remove(alea);
            }
        }
    }

    public void InitArtefacts(){
        Random random = new Random();
        Vector<Zone> dispo = this.getZonesDispo();
        int alea;
        alea = random.nextInt(dispo.size());
        dispo.get(alea).setArtefact(Artefact.Heliport);
        for (int k=0; k<2; k++) {
            alea = random.nextInt(dispo.size());
            dispo.get(alea).setArtefact(Artefact.Air);
            dispo.remove(alea);
        }
        for (int k=0; k<2; k++) {
            alea = random.nextInt(dispo.size());
            dispo.get(alea).setArtefact(Artefact.Feu);
            dispo.remove(alea);
        }
        for (int k=0; k<2; k++) {
            alea = random.nextInt(dispo.size());
            dispo.get(alea).setArtefact(Artefact.Terre);
            dispo.remove(alea);
        }
        for (int k=0; k<2; k++) {
            alea = random.nextInt(dispo.size());
            dispo.get(alea).setArtefact(Artefact.Eau);
            dispo.remove(alea);
        }

    }


    /**
     * Methodes
     */
    public Zone zone(int x, int y){return grille[x][y];}
    public Zone[][] grille() {return this.grille;}
    public int taille() {return this.taille;}
    public Vector<Coord> adjacents(Zone zone){
        Vector<Coord> adj = new Vector<Coord>();
        if (zone.x() < this.taille-1){adj.add(new Coord(zone.x()+1, zone.y()));}
        if (zone.x() > 0){adj.add(new Coord(zone.x()-1, zone.y()));}
        if (zone.y() < this.taille-1){adj.add(new Coord(zone.x(), zone.y()+1));}
        if (zone.y() > 0){adj.add(new Coord(zone.x()+1, zone.y()));}
        return adj;
    }

    public void deplace(Joueur j, Zone dest){
        Vector<Coord> adjacents = this.adjacents(this.zone(j.x(), j.y()));
        if (adjacents.contains(dest) && zone(dest.x(), dest.y()).etat()!=Etat.Submergee){
            j.deplace(dest);
        }
    }

    public void asseche(Joueur j,Zone zone){
        Vector<Coord> adjacents = this.adjacents(this.zone(j.x(), j.y()));
        if ((adjacents.contains(zone) || (j.x() == zone.x() && j.y() == zone.y())) && zone(zone.x(), zone.y()).etat()==Etat.Inondee){
            zone.asseche();
        }
    }

    public Vector<Zone> getZonesDispo(){
        Vector<Zone> dispo = new Vector<Zone>();
        for (int x=0; x<this.taille; x++){
            for (int y=0; y<this.taille; y++){
                if (this.zone(x,y).etat() != Etat.Submergee) {
                    dispo.add(this.zone(x, y));
                }
            }
        }
        return dispo;
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    public void chercheCle(Joueur j){
        Random random = new Random();
        int r = random.nextInt(8);
        if (r == 0) j.ajouteCle(Artefact.Air);
        if (r == 1) j.ajouteCle(Artefact.Eau);
        if (r == 2) j.ajouteCle(Artefact.Feu);
        if (r == 3) j.ajouteCle(Artefact.Terre);
        if (r == 4 || r == 5) grille[j.x()][j.y()].inonde();
    }

    public void finDeTour(){
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

    public static void main (String[] args){
        Ile ile = new Ile();

        String[] noms = new String[2];
        noms[0] = "Lorenzo";
        noms[1] = "Alexandre";

        /*ile.InitJoueurs(2, noms);*/
        ile.InitArtefacts();
        JFrame Ile_Interdite = new ZoneWindow(ile);
        Ile_Interdite.setSize(800,800);
        Ile_Interdite.setVisible(true);
    }
}






/**
 * Positions initiales des coordonnées joueurs aléatoires ?
 * Nombre d'artefacts de chaque type sur l'ile ?
 * Un meme joueur peut-il récuperer plusieurs artefacts ?
 * L'héliport peut-il etre submergé ?
 * Une meme zone peut-etre inondé deux fois de suite ?
 * Actions du joueur.
 */