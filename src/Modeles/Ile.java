package Modeles;

import Vue.Grille;

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

    private static int taille = 6;                                                             //Taille de la grille
    private Zone[][] grille;                                                                   //Grille de jeu
    private Joueur[] joueurs;                                                                  //Liste des joueurs
    private int nbArtefacts;                                                                   //Nombre d'artefacts
    private int nbJoueurs;                                                                     //Nombre de joueurs
    private int idJoueurEnJeu;                                                                 //id du joueur en jeu
    private Dictionary<Artefact, Integer> artefactsAccessibles = new Hashtable(4);  //nb artefacts disponibles
    private Dictionary<Artefact, Boolean> artefactsRecuperes = new Hashtable(4);    //artefacts de l'équipe
    private ChangeParametre param = ChangeParametre.Joueurs;                                   //Le parametre modifié
    private boolean gameOver;                                                                  //Defaite de la partie
    private String infoGameOver;                                                               //Raison de la defaite
    private boolean isWin;                                                                     //Victoire de la partie

    /*
      ===========================================
      =              CONSTRUCTEUR               =
      ===========================================
     */

    /** -- Construit l'ile du jeu
     * @param nbJoueurs nombre de joueurs
     * @param nbArtefacts nombre d'artefacts de chaque type
     **/
    public Ile(int nbJoueurs, int nbArtefacts) {
        super(taille, taille);
        this.nbJoueurs = nbJoueurs;
        this.nbArtefacts = nbArtefacts;
        this.taille = taille;
        this.grille = new Zone[this.taille][this.taille];
        this.gameOver = false;
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

    /** -- Retourne la liste des joueurs
     *
     * @return joueur
     **/
    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /** -- Retourne la grille
     *
     * @return grille
     **/
    public Zone[][] getGrille() {
        return grille;
    }

    /** -- Retourne la taille de l'ile
     *
     * @return taille
     **/
    public int getTaille() {
        return taille;
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
    /** -- Retourne la zone de coord (x, y) dans la grille
     *
     * @param coord coordonnées
     *
     * @return zone de coord (x, y)
     **/
    public Zone zone(Coord coord){
        return grille[coord.x()][coord.y()];
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


    /** --Indique quels artefacts sont recuperes
     *
     * @return Dict<Artefact, booleen>
     **/
    public Dictionary<Artefact, Boolean> getArtefactsRecuperes(){return this.artefactsRecuperes;}

    /** -- Indique le type de parametrage effectué
     *
     * @param p le type de parametrage
     *
     * @return le idctionnaire des artefacts recuperes
     */
    public void parametrage(ChangeParametre p){this.param = p;}


    /** -- Indique si l'équipe a perdu
     *
     * @return booleen vrai si la partie est perdue
     **/
    public boolean GameOver(){return this.gameOver;}

    /** -- Retourne le message de fin de partie (defaite)
     *
     * @return string message de defaite
     */
    public String infoGameOver() {return this.infoGameOver;}

    /** -- Indique si l'équipe a gagné
     *
     * @return booleen vrai si la partie est gagnée
     */
    public boolean IsWin(){return this.isWin;}

    /*
      ===========================================
      =                 SETTER                  =
      ===========================================
     */

    /** -- Methode initialisation des joueurs de l'ile
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
            alea = random.nextInt(dispo.size());
            dispo.get(alea).setArtefact(Artefact.Feu);
            dispo.remove(alea);
            alea = random.nextInt(dispo.size());
            dispo.get(alea).setArtefact(Artefact.Terre);
            dispo.remove(alea);
            alea = random.nextInt(dispo.size());
            dispo.get(alea).setArtefact(Artefact.Eau);
            dispo.remove(alea);
        }
        this.artefactsRecuperes.put(Artefact.Air, false);
        this.artefactsRecuperes.put(Artefact.Eau, false);
        this.artefactsRecuperes.put(Artefact.Feu, false);
        this.artefactsRecuperes.put(Artefact.Terre, false);
        this.artefactsAccessibles.put(Artefact.Air, this.nbArtefacts);
        this.artefactsAccessibles.put(Artefact.Eau, this.nbArtefacts);
        this.artefactsAccessibles.put(Artefact.Feu, this.nbArtefacts);
        this.artefactsAccessibles.put(Artefact.Terre, this.nbArtefacts);

    }

    /** -- Methode initialisation des zones graphiques de l'ile
     **/
    public void InitZoneIG(){
        for (int x=0; x<this.taille; x++){
            for (int y=0; y<this.taille; y++){
                this.ajouteElement(this.zone(x, y));
            }
        }

    }

    /** -- Deplace le joueur en jeu vers une zone de destination
     *
     * @param vectDirection le vecteur direction de déplacement
     **/
    public void deplace(Coord vectDirection) {
        Joueur j = this.joueurEnJeu();
        Coord dest = new Coord(j.x() + vectDirection.x(), j.y()+ vectDirection.y());
        Vector<Coord> adjacents = j.coord().adjacents(0, this.taille);
        for (int k = 0; k<adjacents.size(); k++){
            if (adjacents.get(k).equals(dest)){
                if (zone(dest).etat() != Etat.Submergee && j.getNbActions() > 0) {
                    zone(j.x(), j.y()).removeJoueur(j);
                    j.deplace(dest);
                    zone(dest).addJoueur(j);
                    j.effectueAction();
                    this.checkWin();
                    break;
                }
            }
        }

    }

    /** -- Un joueur asseche une zone
     *
     * @param vectDirection vecteurDirection de la zone a assecher
     **/
    public void asseche(Coord vectDirection) {
        Joueur j = this.joueurEnJeu();
        Coord dest = new Coord(j.x() + vectDirection.x(), j.y()+ vectDirection.y());
        Vector<Coord> adjacents = j.coord().adjacents(0, this.taille);
        adjacents.add(j.coord());
        for (int k = 0; k<adjacents.size(); k++) {
            if (adjacents.get(k).equals(dest)) {
                Zone zone = zone(dest);
                if (zone.etat() == Etat.Inondee && j.getNbActions() > 0) {
                    zone.asseche();
                    j.effectueAction();
                    break;
                }
            }
        }
    }

    /** -- Un joueur cherche une clé
     **/
    public void chercheCle() {
        Joueur j  = this.joueurEnJeu();
        Random random = new Random();
        int r = random.nextInt(5);
        if (r == 0) j.gagneCle(Artefact.Air);
        if (r == 1) j.gagneCle(Artefact.Eau);
        if (r == 2) j.gagneCle(Artefact.Feu);
        if (r == 3) j.gagneCle(Artefact.Terre);
    }

    /** -- Un joueur se deplace sur une case adjacente disponible
     **/
    private void seRattrape(Joueur j) {
        Coord pos = j.coord();
        Vector<Coord> adjacents = pos.adjacents(0, this.taille);
        for (int k=0; k<adjacents.size(); k++) {
            if (!(zone(adjacents.get(k)).etat() == Etat.Submergee)) {
                zone(pos).removeJoueur(j);
                j.deplace(adjacents.get(k));
                zone(adjacents.get(k)).addJoueur(j);
                this.checkWin();
                return;
            }
        }
        zone(j.coord()).removeJoueur(j);
        this.setGameOver("Un joueur s'est noyé !");
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
            Zone zoneAlea = dispo.get(alea);
            zoneAlea.inonde();
            Artefact a = zoneAlea.artefact();
            if (zoneAlea.etat() == Etat.Submergee && a != Artefact.Vide) {
                if (a == Artefact.Heliport) {
                    this.setGameOver("L'heliport a été submergé !");
                } else {
                    int nbArtDispo = this.artefactsAccessibles.get(a);
                    this.artefactsAccessibles.put(a, nbArtDispo-1);
                    if ((!this.getArtefactsRecuperes().get(zoneAlea.artefact())) && this.artefactsAccessibles.get(a)==0) {
                        this.setGameOver("Un artefact a été perdu !");
                    }
                }
            }
            dispo.remove(alea);
        }
        for (int k=0; k<joueurs.length; k++) {
            Joueur j = joueurs[k];
            if (zone(j.coord()).etat() == Etat.Submergee) {
                this.seRattrape(j);
            }
        }
        this.joueurSuivant();

    }

    /** -- Passage au joueur suivant
     **/
    public void joueurSuivant() {
        int nb = this.nbJoueurs;
        if (this.idJoueurEnJeu == nb-1){
            this.idJoueurEnJeu = 0;
        } else {
            this.idJoueurEnJeu++;
        }
        this.joueurEnJeu().setNbActions(3);

    }

    /** -- Rend la partie perdue
     * @param texte la raison de la défaite
     *
     **/
    public void setGameOver(String texte){
        this.gameOver = true;
        this.infoGameOver = texte;
    }

    /** -- Vérifie les conditions de victoire de la partie
     **/
    public void checkWin(){
        Dictionary<Artefact, Boolean> artefacts = this.getArtefactsRecuperes();
        if ( artefacts.get(Artefact.Air) && artefacts.get(Artefact.Feu) && artefacts.get(Artefact.Eau) && artefacts.get(Artefact.Terre)){
            for (int j=0; j<this.getNbJoueurs(); j++){
                Joueur joueur = this.getJoueur(j);
                if (zone(joueur.x(), joueur.y()).artefact() != Artefact.Heliport) {
                    this.isWin = false;
                    return;
                }
            }
            this.isWin = true;
        } else {
            this.isWin = false;
        }
    }

    /** -- Le joueur en jeu recupere l'artefact pour l'équipe
     **/
    public void artefactRecupere(){
        Joueur j = this.joueurEnJeu();
        Artefact artZone = this.zone(j.coord()).artefact();
        if (artZone != Artefact.Heliport && artZone != Artefact.Vide){
            if (j.nbCles(artZone) > 0 && j.getNbActions()>0){
                j.recupereArtefact(artZone);
                this.artefactsRecuperes.put(artZone, true);
                j.effectueAction();
            }
        }
    }

    /** -- Modifie la valeur du parametre concerné
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
