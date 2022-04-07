package Controleurs;

import Modeles.Ile;
import Vue.Fenetre;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controleur implements ActionListener {

    private Fenetre fenetre;
    private Ile ile;

    public Controleur(){
        this.ile = new Ile(6, 1, 1);
        this.fenetre = new Fenetre();
        this.initActionListener();
    }

    public void initActionListener(){
        for (int k=0; k<fenetre.boutons().size(); k++){
            fenetre.bouton(k).addActionListener(this);
        }
    }

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
        } else if (fenetre.bouton(1).equals(source)) { //Parametres
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(2).equals(source)) { //Retour
            this.fenetre.setContentPane(fenetre.Menu());
        } else if (fenetre.bouton(3).equals(source)) { //Nombre de Joueurs
            this.ile.parametrage(ChangeParametre.Joueurs);
            this.fenetre.setContentPane(fenetre.Nombres());
        } else if (fenetre.bouton(4).equals(source)) { //Nombre d'artefacts
            this.ile.parametrage(ChangeParametre.Artefacts);
            this.fenetre.setContentPane(fenetre.Nombres());
        } else if (fenetre.bouton(5).equals(source)) { //1
            this.ile.operateParam(1);
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(6).equals(source)) { //2
            this.ile.operateParam(2);
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(7).equals(source)) { //3
            this.ile.operateParam(3);
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(8).equals(source)) { //4
            this.ile.operateParam(4);
            this.fenetre.setContentPane(fenetre.Parametres());
        } else if (fenetre.bouton(9).equals(source)) { //Arreter la partie
            this.fenetre.setContentPane(fenetre.Menu());
        } else if (fenetre.bouton(10).equals(source)) { //Fin de tour
            this.ile.finDeTour();
            this.fenetre.actualise(this.ile);
        }
        this.fenetre.setVisible(true);

    }


    public static void main(String[] args){
        Controleur control = new Controleur();   //Controleur
    }
}
