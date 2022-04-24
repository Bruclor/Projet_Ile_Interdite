package Modeles;

import Vue.Grille;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

import static Modeles.Objet.*;

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

    //Plateau
    private static int taille = 6;                                                             //Taille de la grille
    private Zone[][] grille;                                                                   //Grille de jeu
    private Joueur[] joueurs;                                                                  //Liste des joueurs

    //Parametres
    private int nbArtefacts;                                                                   //Nombre d'artefacts
    private int nbJoueurs;                                                                     //Nombre de joueurs
    private boolean utiliserActionsSpeciales;
    private int nbClesEchanges;

    //Paquets de carte
    private Paquet<Objet> piocheObjets;                                                        //La pioche des objets
    private Paquet<Coord> piocheCase;                                                          //La pioche des zones

    //Statut
    private int idJoueurEnJeu;                                                                 //id du joueur en jeu
    private Dictionary<Artefact, Integer> artefactsAccessibles = new Hashtable(4);  //nb artefacts disponibles
    private Dictionary<Artefact, Boolean> artefactsRecuperes = new Hashtable(4);    //artefacts de l'équipe
    private boolean gameOver;                                                                  //Defaite de la partie
    private String infoGameOver;                                                               //Raison de la defaite
    private boolean isWin;                                                                     //Victoire de la partie
    private Coord coordSelect;                                                                 //coordonnée selectionnee
    private int idJoueurEchange;                                                               //id du joueur de l'echange
    private Objet cleEchange;                                                                  //objet de l'echange

    /*
      ===========================================
      =              CONSTRUCTEUR               =
      ===========================================
     */

    /** -- Construit l'ile du jeu
     * @param nbJoueurs nombre de joueurs
     * @param nbArtefacts nombre d'artefacts de chaque type
     * @param UAS accord pour les actions spéciales
     * @param nbClesEchanges nombre de clé pour échanger artefact
     **/
    public Ile(int nbJoueurs, int nbArtefacts, boolean UAS, int nbClesEchanges) {
        super(taille, taille);
        this.nbJoueurs = nbJoueurs;
        this.nbArtefacts = nbArtefacts;
        this.utiliserActionsSpeciales = UAS;
        this.nbClesEchanges = nbClesEchanges;
        this.grille = new Zone[this.taille][this.taille];
        this.createPiocheObjets();
        this.createPiocheCase();
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

    /** --Retourne la grille
     *
     * @return la grille
     */
    public Zone[][] getGrille() {return this.grille;}

    /** --Retourne la liste des joueurs
     *
     * @return la liste des joueurs
     */
    public Joueur[] getJoueurs() {return joueurs;}

    /** -- option "utilisation des actions speciales"
     *
     * @return booleen vrai si option activee
     */
    public boolean utiliseActionsSpe(){return this.utiliserActionsSpeciales;}

    /** -- nombre de cless à utiliser pour obtenir un artefact
     *
     * @return le nombre d'artefacts à utiliser
     */
    public int getNbClesEchanges(){return this.nbClesEchanges;}

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

    /** -- Indique la coordonnée de selection (pour selectionner une zone)
     *
     * @return coordonnée de selection
     */
    public Coord selected(){return this.coordSelect;}

    public Vector<Integer> idJoueursSurMaCase(){
        Joueur j = this.joueurEnJeu();
        Coord pos = j.coord();
        Vector<Integer> idJSMC = new Vector<Integer>(0);
        for(int k=0; k<this.joueurs.length; k++){
            if (this.getJoueur(k).coord().equals(pos) && k!=j.id()){
                idJSMC.add(k);
            }
        }
        return  idJSMC;
    }

    public int idJoueurEchange(){return this.idJoueurEchange;}

    public Objet objetEchange(){return this.cleEchange;}

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

    /** -- Methode d'initialisation du paquet de cartes pour les cases
     */
    public void createPiocheCase(){
        this.piocheCase = new Paquet<Coord>();
        for (int x=0; x<this.taille; x++){
            for (int y=0; y<this.taille; y++){
                this.piocheCase.addCarte(new Coord(x,y));
            }
        }
        this.piocheCase.melangePaquet();
    }

    /** -- Methode d'initialisation du paquet de cartes pour les objets
     */
    public void createPiocheObjets() {
        this.piocheObjets = new Paquet<Objet>();
                                                             //Dans les regles du jeu :
        for (int k = 0; k < 5; k++) {
            this.piocheObjets.addCarte(CleAir);        //5 cartes artefacts de chaque type
            this.piocheObjets.addCarte(CleFeu);
            this.piocheObjets.addCarte(Objet.CleTerre);
            this.piocheObjets.addCarte(CleEau);
        }
        if (this.utiliserActionsSpeciales) {
            this.piocheObjets.addCarte(Objet.Helicoptere);    //3 cartes helicoptere
            this.piocheObjets.addCarte(Objet.Helicoptere);
            this.piocheObjets.addCarte(Objet.Helicoptere);
            this.piocheObjets.addCarte(Objet.SacDeSable);     //2 cartes sac de sable
            this.piocheObjets.addCarte(Objet.SacDeSable);
            this.piocheObjets.addCarte(Objet.MonteeDesEaux);  //3 cartes montee des eaux
            this.piocheObjets.addCarte(Objet.MonteeDesEaux);
            this.piocheObjets.addCarte(Objet.MonteeDesEaux);
        } else {
            for (int k = 0; k < 8; k++) {
                this.piocheObjets.addCarte(Objet.Rien);
            }
        }
        this.piocheObjets.melangePaquet();
    }

    /** -- Ajoute un joueur
     */
    public void addJoueur(){if (this.nbJoueurs < 4) this.nbJoueurs++;}

    /** -- Enleve un joueur
     */
    public void removeJoueur(){if (this.nbJoueurs > 1) this.nbJoueurs--;}

    /** -- Ajoute un set complet d'artefact
     */
    public void addArtefact(){if (this.nbArtefacts < 4) this.nbArtefacts++;}

    /** -- Enleve un set complet d'artefact
     */
    public void removeArtefact(){if (this.nbArtefacts > 1) this.nbArtefacts--;}

    /** -- Change l'option d'utilisation des actions speciales
     */
    public void changeUtiliseActionsSpe(){this.utiliserActionsSpeciales = !this.utiliserActionsSpeciales;}

    /** -- Incremente le nombre de cles nécessaires à l'échange
     */
    public void addNbEchange(){if (this.nbClesEchanges< 4) this.nbClesEchanges++;}

    /** -- Decrement le nombre de cles necessaires à l'échange
     */
    public void removeNbEchange(){if (this.nbClesEchanges > 1) this.nbClesEchanges--;}

    /** -- Ajoute un artefact a l'inventaire
     *
     * @param art Artefact a ajouter
     **/
    public void ajouteArtefact(Artefact art) {
        this.artefactsRecuperes.put(art, true);
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

    /** -- Un joueur asseche une zone
     *
     * @param selection vecteurDirection de la zone a assecher
     **/
    public void sacDeSable(Coord selection) {
        Zone zone = zone(selection);
        Joueur j = joueurEnJeu();
        if (zone.etat() == Etat.Inondee) {
            zone.asseche();
            j.perd(Objet.SacDeSable, 1);
        }
    }

    /** -- Un joueur asseche une zone
     *
     * @param selection vecteurDirection de la zone a assecher
     **/
    public void helicoptere(Coord selection) {
        Zone zone = zone(selection);
        Joueur j = joueurEnJeu();
        if (zone.etat() != Etat.Submergee) {
            zone(j.x(), j.y()).removeJoueur(j);
            j.deplace(selection);
            j.perd(Objet.Helicoptere, 1);
            zone(selection).addJoueur(j);
            this.checkWin();
        }
    }

    /** -- Un joueur cherche une clé
     **/
    public Objet recupereObjet() {
        Joueur j  = this.joueurEnJeu();
        Objet obj = this.piocheObjets.piocheUneCarte();
        if (obj == Objet.MonteeDesEaux){this.piocheCase.melangePaquet();}
        else if (obj != Objet.Rien){
            j.gagne(obj);
        }
        return obj;
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
        Zone zonePiochee;
        for (int nb=0; nb<3; nb++){
            zonePiochee = zone(this.piocheCase.piocheUneCarte());
            while (zonePiochee.etat() == Etat.Submergee){
                piocheCase.removeCartePiochee();
                zonePiochee = zone(this.piocheCase.piocheUneCarte());
            }
            zonePiochee.inonde();
            Artefact a = zonePiochee.artefact();
            if (zonePiochee.etat() == Etat.Submergee && a != Artefact.Vide) {
                if (a == Artefact.Heliport) {
                    this.setGameOver("L'heliport a été submergé !");
                } else {
                    int nbArtDispo = this.artefactsAccessibles.get(a);
                    this.artefactsAccessibles.put(a, nbArtDispo-1);
                    if ((!this.getArtefactsRecuperes().get(zonePiochee.artefact())) && this.artefactsAccessibles.get(a)==0) {
                        this.setGameOver("Un artefact a été perdu !");
                    }
                }
            }
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
            Objet utilise = Objet.Rien;
            switch (artZone){
                case Air : utilise = Objet.CleAir; break;
                case Eau : utilise = Objet.CleEau; break;
                case Feu : utilise = Objet.CleFeu; break;
                case Terre : utilise = Objet.CleTerre; break;
            }
            if (j.nbObjets(utilise) >= this.getNbClesEchanges() && j.getNbActions()>0){
                j.recupereArtefact(artZone, this.getNbClesEchanges());
                this.artefactsRecuperes.put(artZone, true);
                j.effectueAction();
            }
        }
    }

    /** -- Procedure d'initialisation de la selection
     */
    public void initSelect(){
        this.coordSelect = this.joueurEnJeu().coord();
        zone(this.coordSelect).select();

    }

    /** -- Le joueur en jeu donne une clé a un joueur
     *
     * @param idJoueur
     * @param objet
     */
    public void donneCleAuJoueur(int idJoueur, Objet objet){
        Joueur j = this.joueurEnJeu();
        j.perd(objet, 1);
        this.getJoueur(idJoueur).gagne(objet);
    }

    /** -- Deplacement de la selection
     *
     * @param vectDirection vecteur direction du deplacement
     */
    public void select(Coord vectDirection){
        int x = this.coordSelect.x()+vectDirection.x();
        int y = this.coordSelect.y()+vectDirection.y();
        if (x >= 0  && x<this.taille && y >= 0 && y<this.taille){
            zone(this.coordSelect).quit();
            this.coordSelect = new Coord(x, y);
            zone(x,y).select();
        }

    }

    /** -- procedure de fin de la selection
     */
    public void validate(){
        zone(this.coordSelect).quit();
    }

    /** -- Modifie l'identifiant du joueur de l'echange de clé
     *
     * @param i un identifiant de joueur
     */
    public void setIdJoueurEchange(int i){this.idJoueurEchange = i;}

    /** -- recherche le prochain joueur possible de l'echange
     */
    public void nextJoueurEchange(){
        Vector<Integer> JSMC = this.idJoueursSurMaCase();
        for (int k=0; k<JSMC.size(); k++){
            if (JSMC.get(k) == this.idJoueurEchange){
                if (k == JSMC.size()-1){this.idJoueurEchange = JSMC.get(0);}
                else {this.idJoueurEchange = JSMC.get(k+1);}
                break;
            }
        }
    }

    /** -- Modifie l'objet de l'echange de clé
     *
     * @param o un objet
     */
    public void setObjetEchange(Objet o){this.cleEchange = o;}

    /** -- recherche le prochain objet possible de l'échange
     */
    public void nextObjetEchange(){
        Vector<Objet> MO = this.joueurEnJeu().clePossede();
        for (int k=0; k<MO.size(); k++){
            if (MO.get(k) == this.objetEchange()){
                if (k == MO.size()-1){this.cleEchange = MO.get(0);}
                else {this.cleEchange = MO.get(k+1);}
                break;
            }
        }
    }
    /** -- Le joueur en jeu donne une clé de l'objet echange au joueur de l'echange,
     */
    public void donneCle(){
        this.joueurEnJeu().perd(this.objetEchange(),1);
        this.joueurEnJeu().effectueAction();
        this.getJoueur(this.idJoueurEchange).gagne(this.objetEchange());
    }

}
