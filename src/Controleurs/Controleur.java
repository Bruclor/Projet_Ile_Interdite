package Controleurs;

import Modeles.ChangeParametre;
import Modeles.Coord;
import Modeles.Ile;
import Vue.Fenetre;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controleur implements ActionListener {


    private Fenetre fenetre;                //Une fenetre d'affichage (Vue)
    private Ile ile;                        //Une ile (Modele)

    /** -- Constructeur : initialise une ile et une fenetre
     */
    public Controleur(){
        this.ile = new Ile( 2, 2);
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
        if (fenetre.bouton(0).equals(source)) {                                  //Nouvelle partie
            this.ile = new Ile(ile.getNbJoueurs(), ile.getNbArtefacts());   //Modification données (Modele)
            this.ile.setBackground(Color.BLUE.darker().darker());
            this.ile.setPreferredSize(new Dimension(600, 600));
            this.ile.InitJoueurs();
            this.ile.InitArtefacts();
            this.ile.InitZoneIG();
            this.fenetre.initActions();
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.actualise(this.ile);
        } else if (fenetre.bouton(1) == source) {                                 //Choix des parametres
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(2) == source) {                                 //Retour au menu
            this.fenetre.setContentPane(fenetre.Menu());
        } else if (fenetre.bouton(3) == source) {                                 //Nombre de Joueurs
            this.ile.parametrage(ChangeParametre.Joueurs);
            this.fenetre.setContentPane(fenetre.Nombres());
        } else if (fenetre.bouton(4) == source) {                                 //Nombre d'artefacts
            this.ile.parametrage(ChangeParametre.Artefacts);
            this.fenetre.setContentPane(fenetre.Nombres());
        } else if (fenetre.bouton(5) == source) {                                 //select 1
            this.ile.operateParam(1);
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(6) == source) {                                 //select 2
            this.ile.operateParam(2);
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(7) == source) {                                 //select 3
            this.ile.operateParam(3);
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(8) == source) {                                 //select 4
            this.ile.operateParam(4);
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(9) == source) {                                 //Arreter la partie
            this.fenetre.setContentPane(fenetre.Menu());
        } else if (fenetre.bouton(10) == source) {                                //Cherche Clé/Fin de tour
            this.ile.chercheCle();                      //cherche une clé
            this.ile.finDeTour();                       //fin du tour
            this.fenetre.actualiseInventaire(this.ile); //actualisation inventaire
            if (ile.GameOver()){this.fenetre.GameOver(this.ile.infoGameOver());} //defaite après inondation ?
            this.fenetre.actualise(this.ile);           //actualisation fenetre
        } else if (fenetre.bouton(11) == source) {                               //Recuperer artefact
            this.ile.artefactRecupere();                //recuperation artefact
            this.fenetre.actualiseInventaire(this.ile); //actualisation inventaire
            this.fenetre.actualise(this.ile);           //actualisation fenetre
        } else if (fenetre.bouton(12) == source){                                //Se deplacer vers le haut
            this.ile.deplace(new Coord(-1, 0));   //deplacement
            this.fenetre.actualiseInventaire(this.ile); //actualisation inventaire
            if (ile.IsWin()){this.fenetre.Victory();}   //victoire ?
            this.fenetre.actualise(this.ile);           //actualisation fenetre
        } else if (fenetre.bouton(13) == source){                                //Se deplacer vers le bas
            this.ile.deplace(new Coord(1, 0));     //idem
            this.fenetre.actualiseInventaire(this.ile);
            if (ile.IsWin()){this.fenetre.Victory();}
            this.fenetre.actualise(this.ile);
        } else if (fenetre.bouton(14)==source){                                  //Se deplacer vers la droite
            this.ile.deplace(new Coord(0, 1));     //idem
            this.fenetre.actualiseInventaire(this.ile);
            if (ile.IsWin()){this.fenetre.Victory();}
            this.fenetre.actualise(this.ile);
        } else if (fenetre.bouton(15)==source){                                  //Se deplacer vers la gauche
            this.ile.deplace(new Coord(0, -1));    //idem
            this.fenetre.actualiseInventaire(this.ile);
            if (ile.IsWin()){this.fenetre.Victory();}
            this.fenetre.actualise(this.ile);
        } else if (fenetre.bouton(16) == source){                                //Assecher en haut
            this.ile.asseche(new Coord(-1, 0));
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.actualise(this.ile);
        } else if (fenetre.bouton(17) == source){                                 //Assecher en bas
            this.ile.asseche(new Coord(1, 0));
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.actualise(this.ile);
        } else if (fenetre.bouton(18)==source){                                   //Assecher a droite
            this.ile.asseche(new Coord(0, 1));
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.actualise(this.ile);
        } else if (fenetre.bouton(19)==source){                                   //Assecher a gauche
            this.ile.asseche(new Coord(0, -1));
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.actualise(this.ile);
        } else if (fenetre.bouton(20)==source){                                   //Assecher sur place
            this.ile.asseche(new Coord(0, 0));
            this.fenetre.actualiseInventaire(this.ile);
            this.fenetre.actualise(this.ile);
        }
        this.fenetre.setVisible(true);                                               //Afficher
    }

    /** --FONCTION MAIN() : Lance l'application
     */
    public static void main(String[] args){
        Controleur control = new Controleur();
    }

}
