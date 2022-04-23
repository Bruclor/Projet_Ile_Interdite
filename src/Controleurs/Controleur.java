package Controleurs;

import Modeles.Coord;
import Modeles.Etat;
import Modeles.Ile;
import Modeles.Objet;
import Vue.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controleur implements ActionListener {


    private Fenetre fenetre;                //Une fenetre d'affichage (Vue)
    private Ile ile;                        //Une ile (Modele)

    /** -- Constructeur : initialise une ile et une fenetre
     */
    public Controleur(){
        this.ile = new Ile( 2, 2, false, 1);
        this.fenetre = new Fenetre();
        this.initActionListener();
    }

    /** -- Ajoute un ActionListener aux boutons
     */
    public void initActionListener(){
        for (int k=0; k<fenetre.boutons().size(); k++){
            fenetre.bouton(k).addActionListener(this);
        }
    }

    /** -- Gere les évènements
     *
     * @param e un evenement enclenché par l'utilisateur
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source =  e.getSource();
        if (fenetre.bouton(0) == source) {                                  //Nouvelle partie
            this.ile = new Ile(ile.getNbJoueurs(), ile.getNbArtefacts(), ile.utiliseActionsSpe(), ile.getNbClesEchanges());   //Modification données (Modele)
            this.ile.setBackground(Color.BLUE.darker().darker());
            this.ile.setPreferredSize(new Dimension(600, 600));
            this.ile.InitJoueurs();
            this.ile.InitArtefacts();
            this.ile.InitZoneIG();
            this.fenetre.actualiseActions(this.ile.utiliseActionsSpe());
            this.fenetre.videCartePiochee();
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(1) == source) {                                 //Arreter la partie
            this.fenetre.afficheMenu(this.ile.getNbJoueurs(), this.ile.getNbArtefacts(), this.ile.utiliseActionsSpe(), this.ile.getNbClesEchanges());
        } else if (fenetre.bouton(2) == source) {                                //Cherche Clé/Fin de tour
            Objet objet = this.ile.recupereObjet();                      //cherche une clé
            this.fenetre.actualiseCartePiochee(objet, this.ile.joueurEnJeu());
            this.ile.finDeTour();                       //fin du tour
            this.fenetre.actualiseInventaire(this.ile); //actualisation inventaire
            if (ile.GameOver()){this.fenetre.GameOver(this.ile.infoGameOver());} //defaite après inondation ?

            this.fenetre.afficheJeu(this.ile);           //actualisation fenetre
        } else if (fenetre.bouton(3) == source) {                               //Recuperer artefact
            this.ile.artefactRecupere();                //recuperation artefact
            this.fenetre.actualiseInventaire(this.ile); //actualisation inventaire
            this.fenetre.afficheJeu(this.ile);           //actualisation fenetre
        } else if (fenetre.bouton(4) == source){                                //Se deplacer vers le haut
            this.ile.deplace(new Coord(-1, 0));   //deplacement
            this.fenetre.actualiseInventaire(this.ile); //actualisation inventaire
            if (ile.IsWin()){this.fenetre.Victory();}   //victoire ?
            this.fenetre.afficheJeu(this.ile);           //actualisation fenetre
        } else if (fenetre.bouton(5) == source){                                //Se deplacer vers le bas
            this.ile.deplace(new Coord(1, 0));     //idem
            this.fenetre.actualiseInventaire(this.ile);
            if (ile.IsWin()){this.fenetre.Victory();}
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(6)==source){                                  //Se deplacer vers la droite
            this.ile.deplace(new Coord(0, 1));     //idem
            this.fenetre.actualiseInventaire(this.ile);
            if (ile.IsWin()){this.fenetre.Victory();}
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(7)==source){                                  //Se deplacer vers la gauche
            this.ile.deplace(new Coord(0, -1));    //idem
            this.fenetre.actualiseInventaire(this.ile);
            if (ile.IsWin()){this.fenetre.Victory();}
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(8) == source){                                //Assecher en haut
            this.ile.asseche(new Coord(-1, 0));
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(9) == source){                                 //Assecher en bas
            this.ile.asseche(new Coord(1, 0));
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(10)==source){                                   //Assecher a droite
            this.ile.asseche(new Coord(0, 1));
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(11)==source){                                   //Assecher a gauche
            this.ile.asseche(new Coord(0, -1));
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(12)==source){                                   //Assecher sur place
            this.ile.asseche(new Coord(0, 0));
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(13)==source){
            this.ile.addJoueur();
            this.fenetre.afficheMenu(ile.getNbJoueurs(), ile.getNbArtefacts(), this.ile.utiliseActionsSpe(), this.ile.getNbClesEchanges());
        } else if (fenetre.bouton(14)==source){
            this.ile.removeJoueur();
            this.fenetre.afficheMenu(ile.getNbJoueurs(), ile.getNbArtefacts(), this.ile.utiliseActionsSpe(), this.ile.getNbClesEchanges());
        } else if (fenetre.bouton(15)==source){
            this.ile.addArtefact();
            this.fenetre.afficheMenu(ile.getNbJoueurs(), ile.getNbArtefacts(), this.ile.utiliseActionsSpe(), this.ile.getNbClesEchanges());
        } else if (fenetre.bouton(16)==source){
            this.ile.removeArtefact();
            this.fenetre.afficheMenu(ile.getNbJoueurs(), ile.getNbArtefacts(), this.ile.utiliseActionsSpe(), this.ile.getNbClesEchanges());
        } else if (fenetre.bouton(17)==source){
            this.ile.changeUtiliseActionsSpe();
            this.fenetre.afficheMenu(ile.getNbJoueurs(), ile.getNbArtefacts(), this.ile.utiliseActionsSpe(), this.ile.getNbClesEchanges());
        } else if (fenetre.bouton(18)==source && this.ile.joueurEnJeu().nbObjets(Objet.Helicoptere) > 0){
            this.ile.initSelect();
            this.fenetre.SelectZone(Objet.Helicoptere);
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(19) == source && this.ile.joueurEnJeu().nbObjets(Objet.SacDeSable) > 0){
            this.ile.initSelect();
            this.fenetre.SelectZone(Objet.SacDeSable);
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(20) == source){
            this.ile.validate();
            this.fenetre.actualiseActions(this.ile.utiliseActionsSpe());
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(21) == source){
            this.ile.select(new Coord(-1, 0));
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(22) == source){
            this.ile.select(new Coord(1, 0));
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(23)==source){
            this.ile.select(new Coord(0, 1));
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(24)==source){
            this.ile.select(new Coord(0, -1));
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(25)==source && this.ile.zone(this.ile.selected()).etat() == Etat.Inondee){
            this.ile.sacDeSable(this.ile.selected());
            this.ile.validate();
            this.fenetre.actualiseActions(this.ile.utiliseActionsSpe());
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(26)==source && this.ile.zone(this.ile.selected()).etat() != Etat.Submergee){
            this.ile.helicoptere(this.ile.selected());
            this.ile.validate();
            this.fenetre.actualiseActions(this.ile.utiliseActionsSpe());
            this.fenetre.actualiseInventaire(this.ile);
            if (ile.IsWin()){this.fenetre.Victory();}
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(27)==source){
            this.ile.removeNbEchange();
            this.fenetre.afficheMenu(ile.getNbJoueurs(), ile.getNbArtefacts(), this.ile.utiliseActionsSpe(), this.ile.getNbClesEchanges());
        } else if (fenetre.bouton(28)==source){
            this.ile.addNbEchange();
            this.fenetre.afficheMenu(ile.getNbJoueurs(), ile.getNbArtefacts(), this.ile.utiliseActionsSpe(), this.ile.getNbClesEchanges());
        } else if (fenetre.bouton(29)==source && this.ile.idJoueursSurMaCase().size() > 0 && this.ile.joueurEnJeu().clePossede().size() > 0){
            this.ile.setIdJoueurEchange(this.ile.idJoueursSurMaCase().get(0));
            this.ile.setObjetEchange(this.ile.joueurEnJeu().clePossede().get(0));
            this.fenetre.choisirEchange(this.ile.getJoueur(this.ile.idJoueurEchange()), this.ile.objetEchange());
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(30)==source){
            this.ile.nextJoueurEchange();
            this.fenetre.choisirEchange(this.ile.getJoueur(this.ile.idJoueurEchange()), this.ile.objetEchange());
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(31)==source){
            this.ile.nextObjetEchange();
            this.fenetre.choisirEchange(this.ile.getJoueur(this.ile.idJoueurEchange()), this.ile.objetEchange());
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(32)==source){
            this.ile.donneCle();
            this.fenetre.actualiseActions(this.ile.utiliseActionsSpe());
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.afficheJeu(this.ile);
        } else if (fenetre.bouton(33)==source){
            this.fenetre.actualiseActions(this.ile.utiliseActionsSpe());
            this.fenetre.afficheJeu(this.ile);
        }
        this.fenetre.setVisible(true);                                               //Afficher
    }

    /** --FONCTION MAIN() : Lance l'application
     */
    public static void main(String[] args){
        Controleur control = new Controleur();
    }

}
