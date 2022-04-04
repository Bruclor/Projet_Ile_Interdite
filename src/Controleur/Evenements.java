package Controleur;

import Modeles.Ile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Evenements implements ActionListener {

    /**====================================
     *          ATTRIBUTS
     * ====================================
     */

    //Les modeles
    private Ile ile = new Ile(6);                          //Ile du jeu
    private Parametres parametres = new Parametres();            //nombre d'artefacts
    private TypeAction type = TypeAction.Menu;   //true si mode "changer nb joueur" false sinon

    //Les boutons de la fenetre
    private JButton nouvellePartieB = new JButton("Nouvelle Partie");
    private JButton parametresB = new JButton("Parametres");
    private JButton retourB = new JButton("< Retour");
    private JButton nombreJoueursB = new JButton("Nombre de joueurs");
    private JButton nombreArtefactsB = new JButton("Nombre d'artefacts");
    private JButton b1B = new JButton("1");
    private JButton b2B = new JButton("2");
    private JButton b3B = new JButton("3");
    private JButton b4B = new JButton("4");
    private JButton arretB = new JButton("Arreter la partie");
    private JButton finDeTourB = new JButton("Fin de tour");



    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==parametresB) {this.type = TypeAction.Parametres;}
        else if (e.getSource()==retourB){this.type = TypeAction.Menu;}
        else if (e.getSource()==nombreArtefactsB){this.type = TypeAction.ChangeNbArtefacts;}
        else if (e.getSource() == nombreJoueursB){this.type = TypeAction.ChangeNbJoueurs;}
        else if (e.getSource()==b1B){this.type = TypeAction.Parametres;}
        else if (e.getSource()==b2B){this.type = TypeAction.Parametres;}
        else if (e.getSource()==b3B){this.type = TypeAction.Parametres;}
        else if (e.getSource()==b4B){this.type = TypeAction.Parametres;}
        else if (e.getSource()==nouvellePartieB){this.type = TypeAction.Jeu;}
        else if (e.getSource()==arretB){this.type = TypeAction.Menu;}
        else if (e.getSource()==finDeTourB){ile.finDeTour();}
    }
}
