package Vue;

import Modeles.Artefact;
import Modeles.Ile;
import Modeles.Joueur;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Vector;

public class Fenetre extends JFrame {

    //Les boutons
    private Vector<Bouton> boutons = new Vector<Bouton>();

    //Panneaux
    private Panneau  menu;
    private Panneau  parametres;
    private Panneau  actions;
    private Panneau  nombres;
    private Panneau  informations;

    //Grille de jeu
    private Grille jeu = new Grille(6, 6);

    //Initialisation de la fenetre
    public Fenetre(){
        this.setTitle("L'ile Interdite");
        this.setSize(new Dimension(1000, 600));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initBouton();
        this.initCommandes();
        this.setContentPane(this.menu);
        this.setVisible(true);
    }

    //Initalisation des Boutons
    public void initBouton(){
        this.addBouton(new Bouton("Nouvelle Partie", Color.WHITE));
        this.addBouton(new Bouton("Parametres", Color.WHITE));
        this.addBouton(new Bouton("Retour", Color.RED));
        this.addBouton(new Bouton("Nombre de Joueurs", Color.WHITE));
        this.addBouton(new Bouton("Nombre d'artefacts", Color.WHITE));
        this.addBouton(new Bouton("1", Color.WHITE));
        this.addBouton(new Bouton("2", Color.WHITE));
        this.addBouton(new Bouton("3", Color.WHITE));
        this.addBouton(new Bouton("4", Color.WHITE));
        this.addBouton(new Bouton("Arreter la partie", Color.RED, new Dimension(175, 20)));
        this.addBouton(new Bouton("Fin de Tour", Color.WHITE,  new Dimension(175, 20)));
        this.addBouton(new Bouton("Chercher une clé", Color.WHITE,  new Dimension(175, 20)));

        this.addBouton(new Bouton(" haut ", Color.WHITE,  new Dimension(175, 20)));
        this.addBouton(new Bouton(" bas ", Color.WHITE,  new Dimension(175, 20)));
        this.addBouton(new Bouton(" droite ", Color.WHITE,  new Dimension(175, 20)));
        this.addBouton(new Bouton(" gauche ", Color.WHITE,  new Dimension(175, 20)));

    }

    //Methode d'ajout d'un bouton
    public void addBouton(Bouton b){this.boutons.add(b);}


    //Initalisation des panneaux de commande
    public void initCommandes(){
        this.initMenu();
        this.initParametres();
        this.initNombres();
        this.initActions();
    }

    //Acces aux boutons
    public Vector<Bouton> boutons() {return this.boutons;}

    //Acces à un bouton
    public Bouton bouton(int k){return this.boutons.get(k);}

    //Acces aux panneaux
    public Panneau  Menu(){return this.menu;}
    public Panneau  Parametres(){return this.parametres;}
    public Panneau  Nombres(){return this.nombres;}
    public Panneau  Actions(){return this.actions;}
    public Panneau  Informations(){return this.informations;}
    public Grille Jeu(){return this.jeu;}

    //Methode d'actualisation du jeu
    public void actualise(Ile ile){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        this.actions.setPreferredSize(new Dimension(200, 600));
        this.jeu = ile;
        this.informations.setPreferredSize(new Dimension(200, 600));

        panel.add(this.actions, BorderLayout.WEST);
        panel.add(this.informations, BorderLayout.EAST);
        panel.add(this.jeu);

        this.setContentPane(panel);
    }

    public void initParametres(){
        this.parametres = new Panneau("Parametres", Color.BLUE);
        this.parametres.add(this.boutons.get(2));
        this.parametres.add(this.boutons.get(3));
        this.parametres.add(this.boutons.get(4));
    }

    public void initMenu(){
        this.menu = new Panneau("Menu", Color.BLUE);
        this.menu.add(this.boutons.get(0));
        this.menu.add(this.boutons.get(1));
    }

    public void initActions(){
        this.actions = new Panneau("Actions", Color.BLUE);
        this.actions.add(this.boutons.get(9));
        this.actions.add(this.boutons.get(10));
        this.actions.add(this.boutons.get(11));
        this.actions.add(this.boutons.get(12));
        this.actions.add(this.boutons.get(13));
        this.actions.add(this.boutons.get(14));
        this.actions.add(this.boutons.get(15));
    }

    public void initNombres(){
        this.nombres = new Panneau("Nombres", Color.BLUE);
        this.nombres.add(this.boutons.get(5));
        this.nombres.add(this.boutons.get(6));
        this.nombres.add(this.boutons.get(7));
        this.nombres.add(this.boutons.get(8));
    }

    public void actualiseInventaire(Ile ile) {

        Panneau newInventaire = new Panneau ("Inventaire", Color.BLUE);
        Dictionary<Artefact, Boolean> artefacts = ile.getArtefactsRecuperes();

        newInventaire.add(new Texte(" Artefacts recupérés : ", Color.WHITE, new Dimension(199, 50), 20));
        if (artefacts.get(Artefact.Air)){
            newInventaire.add(new Texte("[  Air  ]", Color.WHITE, new Dimension(30,15)));
        } else {
            newInventaire.add(new Texte("[       ]", Color.WHITE, new Dimension(30,15)));
        }
        if (artefacts.get(Artefact.Eau)){
            newInventaire.add(new Texte("[  Eau  ]", Color.WHITE, new Dimension(30,15)));
        } else {
            newInventaire.add(new Texte("[       ]", Color.WHITE, new Dimension(30,15)));
        }
        if (artefacts.get(Artefact.Feu)){
            newInventaire.add(new Texte("[  Feu  ]", Color.WHITE, new Dimension(30,15)));
        } else {
            newInventaire.add(new Texte("[       ]", Color.WHITE, new Dimension(30,15)));
        }
        if (artefacts.get(Artefact.Terre)){
            newInventaire.add(new Texte("[ Terre ]", Color.WHITE, new Dimension(30,15)));
        } else {
            newInventaire.add(new Texte("[       ]", Color.WHITE, new Dimension(30,15)));
        }

        newInventaire.add(new Texte (" Tour du joueur : ", Color.WHITE, new Dimension(160, 30), 20));
        newInventaire.add(new Texte (ile.joueurEnJeu().nom(), ile.joueurEnJeu().couleur(), new Dimension(30, 30), 20));
        newInventaire.add(new Texte (" Actions restantes : "+ile.joueurEnJeu().getNbActions(), Color.WHITE, new Dimension(199, 30), 20));

        for (int k=0; k<ile.getNbJoueurs(); k++) {
            newInventaire.add(new Texte(" Clés du joueur " + ile.getJoueur(k).nom() + " :", ile.getJoueur(k).couleur(), new Dimension(199, 30), 15));
            newInventaire.add(new Texte("Air : " + ile.getJoueur(k).nbCles(Artefact.Air), Color.WHITE, new Dimension(199, 10)));
            newInventaire.add(new Texte("Eau : " + ile.getJoueur(k).nbCles(Artefact.Eau), Color.WHITE, new Dimension(199, 10)));
            newInventaire.add(new Texte("Feu : " + ile.getJoueur(k).nbCles(Artefact.Feu), Color.WHITE, new Dimension(199, 10)));
            newInventaire.add(new Texte("Terre : " + ile.getJoueur(k).nbCles(Artefact.Terre), Color.WHITE, new Dimension(199, 10)));

        }

        this.informations = newInventaire;


    }

    public void GameOver(){
        Panneau  defaite = new Panneau ("Defaite", Color.BLUE);

        Texte GameOver  = new Texte("GAME OVER", Color.RED);
        GameOver.setFont(new Font("Serif", Font.BOLD, 30));

        defaite.add(GameOver);
        defaite.add(this.bouton(9));

        this.actions = defaite;
    }

}
