package Vue;

import Controleur.*;
import Modeles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame implements Evenements {


    /**====================================
     *          ATTRIBUTS
     * ====================================
     */

    //Les modeles
    private Ile ile;                          //Ile du jeu
    private int nbJoueurs = 1;                //nombre de joueurs
    private int nbArtefacts = 1;              //nombre d'artefacts
    private boolean changeNbJoueurs = true;   //true si mode "changer nb joueur" false sinon

    //Les boutons de la fenetre
    private JButton NouvellePartie = new JButton("Nouvelle Partie");
    private JButton Parametres = new JButton("Parametres");
    private JButton Retour = new JButton("< Retour");
    private JButton NombreJoueurs = new JButton("Nombre de joueurs");
    private JButton NombreArtefacts = new JButton("Nombre d'artefacts");
    private JButton b1 = new JButton("1");
    private JButton b2 = new JButton("2");
    private JButton b3 = new JButton("3");
    private JButton b4 = new JButton("4");
    private JButton Arret = new JButton("Arreter la partie");
    private JButton FinDeTour = new JButton("Fin de tour");

    /**====================================
     *          CONSTRUCTEUR
     * ====================================
     */
    private JPanel menu = new JPanel();
    private JPanel settings = new JPanel();
    private JPanel numbers = new JPanel();
    private JPanel game = new JPanel();
    private JPanel souris = new JPanel();

    //Constructeur de la fenetre
    public Fenetre(){
        this.setTitle("Ile Interdite");                                  //Titre
        this.setSize(900, 600);                             //Taille
        this.setLocationRelativeTo(null);                                //Position centrée
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);          //Fermeture avec croix
        this.initPanelAndButton();
        this.setContentPane(menu);                                       //Selection du panneau initial
        this.setVisible(true);                                           //Affichage
    }

    /**====================================
     *              METHODES
     * ====================================
     */

    /**
     * void : initialisation des panels et des buttons
     *
     **/
    public void initPanelAndButton(){

        //initialisation du menu
        JPanel menu = new JPanel();
        menu.setBackground(Color.BLUE.darker().darker());
        menu.setLayout(new FlowLayout());

        NouvellePartie.addActionListener(this);
        NouvellePartie.setBackground(Color.WHITE);
        menu.add(NouvellePartie);

        Parametres.addActionListener(this);
        Parametres.setBackground(Color.WHITE);
        menu.add(Parametres);

        this.menu = menu;

        JPanel settings = new JPanel();
        settings.setBackground(Color.BLUE.darker().darker());
        settings.setLayout(new FlowLayout());

        Retour.setBackground(Color.RED);
        Retour.setPreferredSize(new Dimension(200, 20));
        Retour.addActionListener(this);
        settings.add(Retour);

        NombreJoueurs.setBackground(Color.WHITE);
        NombreJoueurs.setPreferredSize(new Dimension(200, 20));
        NombreJoueurs.addActionListener(this);
        settings.add(NombreJoueurs);

        NombreArtefacts.setBackground(Color.WHITE);
        NombreArtefacts.setPreferredSize(new Dimension(200, 20));
        NombreArtefacts.addActionListener(this);
        settings.add(NombreArtefacts);

        this.settings = settings;

        JPanel nb = new JPanel();
        nb.setBackground(Color.BLUE.darker().darker());
        nb.setLayout(new FlowLayout());

        b1.addActionListener(this);
        b1.setBackground(Color.WHITE);
        nb.add(b1);
        b2.addActionListener(this);
        b2.setBackground(Color.WHITE);
        nb.add(b2);
        b3.addActionListener(this);
        b3.setBackground(Color.WHITE);
        nb.add(b3);
        b4.addActionListener(this);
        b4.setBackground(Color.WHITE);
        nb.add(b4);

        this.numbers = nb;

        FinDeTour.addActionListener(this);
        FinDeTour.setBackground(Color.WHITE);
        FinDeTour.setPreferredSize(new Dimension(160, 20));

        Arret.addActionListener(this);
        Arret.setBackground(Color.RED);
        Arret.setPreferredSize(new Dimension(160, 20));
    }

    /**
     * @void initGame : Initialisation du jeu (au lancement d'une nouvelle partie)
     *
     */

    public void initGame(){
        JPanel jeu = new JPanel();
        jeu.setLayout(new BorderLayout());
        this.ile = new Ile(6);
        String[] noms = new String[nbJoueurs];
        for (int k=0; k<nbJoueurs; k++){
            noms[k] = "J"+k;
        }
        this.ile.InitJoueurs(nbJoueurs, noms);
        this.ile.InitArtefacts(nbArtefacts);
        this.ile.setBackground(Color.BLUE.darker().darker());
        for (int x=0; x<ile.taille(); x++){
            for (int y=0; y<ile.taille(); y++){
                Zone k = ile.zone(x, y);
                ile.add(k);
            }
        }
        ile.setPreferredSize(new Dimension(600, 600));
        jeu.add(ile, BorderLayout.WEST);
        JPanel commandes = new JPanel();
        commandes.setBackground(Color.BLUE);
        commandes.setLayout(new FlowLayout());
        commandes.add(Arret);
        commandes.add(FinDeTour);
        jeu.add(commandes);
        this.game = jeu;
    }

    /**
     * @void upload game : mise à jour du jeu (après un évènement sur le jeu)
     *
     **/
    public void uploadGame(){
        JPanel upload = new JPanel();
        upload.setLayout(new BorderLayout());
        upload.add(this.ile, BorderLayout.WEST);
        JPanel commandes = new JPanel();
        commandes.setBackground(Color.BLUE);
        commandes.setLayout(new FlowLayout());
        commandes.add(Arret);
        commandes.add(FinDeTour);
        upload.add(commandes);
        this.game = upload;
    }



    /**
     * @void actionPerformed : gestion des evenements
     * @param ActionEvent e : un evenement
     *
     **/
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==Parametres) {this.setContentPane(this.settings);}
        else if (e.getSource()==Retour){this.setContentPane(menu);}
        else if (e.getSource()==NombreArtefacts){this.setContentPane(numbers); this.changeNbJoueurs = false;}
        else if (e.getSource() == NombreJoueurs){this.setContentPane(numbers); this.changeNbJoueurs = true;}
        else if (e.getSource()==b1){this.setContentPane(settings); if (changeNbJoueurs){this.nbJoueurs = 1;} else {this.nbArtefacts = 1;}}
        else if (e.getSource()==b2){this.setContentPane(settings); if (changeNbJoueurs){this.nbJoueurs = 2;} else {this.nbArtefacts = 2;}}
        else if (e.getSource()==b3){this.setContentPane(settings); if (changeNbJoueurs){this.nbJoueurs = 3;} else {this.nbArtefacts = 3;}}
        else if (e.getSource()==b4){this.setContentPane(settings);if (changeNbJoueurs){this.nbJoueurs = 4;} else {this.nbArtefacts = 4;}}
        else if (e.getSource()==NouvellePartie){this.initGame(); this.setContentPane(game);}
        else if (e.getSource()==Arret){this.setContentPane(menu);}
        else if (e.getSource()==FinDeTour){this.ile.finDeTour(); this.uploadGame(); this.setContentPane(game);}
        this.setVisible(true);
    }

    /**
     * @void main : fonction principale
     * @param String args[]
     *
     **/
    public static void main(String[] args){
        Fenetre fenetre = new Fenetre();

        /*while (true) {
            for (int i = 0; i < fenetre.ile.getJoueurs().length; i++) {
                fenetre.ile.getJoueurs().play();

                if (isGameOver()) {
                    initGame();
                }
            }
        }*/
    }
}



