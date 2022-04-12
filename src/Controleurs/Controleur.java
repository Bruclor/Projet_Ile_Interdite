package Controleurs;

import Modeles.Ile;
import Vue.Fenetre;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controleur implements ActionListener {

    //Controle sur une fenetre et un modele
    private Fenetre fenetre;
    private Ile ile;

    //Construction
    public Controleur(){
        this.ile = new Ile(6, 1, 1);
        this.fenetre = new Fenetre();
        this.initActionListener();
    }

    //Boutons fonctionnels
    public void initActionListener(){
        for (int k=0; k<fenetre.boutons().size(); k++){
            fenetre.bouton(k).addActionListener(this);
        }
    }

    //gestion des évènements
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source =  e.getSource();
        if (fenetre.bouton(0).equals(source)) { //Nouvelle partie
            this.ile = new Ile(6, ile.getNbJoueurs(), ile.getNbArtefacts());  //Modification données (Modele)
            this.ile.setBackground(Color.BLUE.darker().darker());
            this.ile.setPreferredSize(new Dimension(600, 600));
            this.ile.InitJoueurs();
            this.ile.InitArtefacts();
            this.ile.InitZoneIG();
            this.fenetre.actualise(this.ile);
        } else if (fenetre.bouton(1) == source) { //Parametres
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(2) == source) { //Retour
            this.fenetre.setContentPane(fenetre.Menu());
        } else if (fenetre.bouton(3) == source) { //Nombre de Joueurs
            this.ile.parametrage(ChangeParametre.Joueurs);
            this.fenetre.setContentPane(fenetre.Nombres());
        } else if (fenetre.bouton(4) == source) { //Nombre d'artefacts
            this.ile.parametrage(ChangeParametre.Artefacts);
            this.fenetre.setContentPane(fenetre.Nombres());
        } else if (fenetre.bouton(5) == source) { //1
            this.ile.operateParam(1);
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(6) == source) { //2
            this.ile.operateParam(2);
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(7) == source) { //3
            this.ile.operateParam(3);
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(8) == source) { //4
            this.ile.operateParam(4);
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(9) == source) { //Arreter la partie
            this.fenetre.setContentPane(fenetre.Menu());
        } else if (fenetre.bouton(10) == source) { //Fin de tour
            this.ile.finDeTour();
            this.fenetre.actualise(this.ile);
        } //else if (fenetre.bouton(11).equals(source)){ //Chercher une clé
            //this.ile.chercheCle(JoueurEnJeu);

        this.fenetre.setVisible(true);

    }

    //Fonction main
    public static void main(String[] args){
        Controleur control = new Controleur();   //Controleur
    }
}
