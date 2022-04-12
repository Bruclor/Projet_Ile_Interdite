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
    private Commandes menu = new Commandes("menu", Color.BLUE);
    private Commandes parametres = new Commandes("parametres", Color.BLUE);
    private Commandes actions = new Commandes("actions", Color.BLUE);
    private Commandes nombres = new Commandes("nombres", Color.BLUE);
    private Commandes inventaire = new Commandes("inventaire", Color.BLUE);

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
        this.addBouton(new Bouton("Arreter la partie", Color.RED));
        this.addBouton(new Bouton("Fin de Tour", Color.WHITE));
        this.addBouton(new Bouton("Chercher", Color.WHITE));

    }

    //Methode d'ajout d'un bouton
    public void addBouton(Bouton b){this.boutons.add(b);}


    //Initalisation des panneaux de commande
    public void initCommandes(){
        this.menu.add(this.boutons.get(0));
        this.menu.add(this.boutons.get(1));

        this.parametres.add(this.boutons.get(2));
        this.parametres.add(this.boutons.get(3));
        this.parametres.add(this.boutons.get(4));

        this.nombres.add(this.boutons.get(5));
        this.nombres.add(this.boutons.get(6));
        this.nombres.add(this.boutons.get(7));
        this.nombres.add(this.boutons.get(8));

        this.actions.add(this.boutons.get(9));
        this.actions.add(this.boutons.get(10));

        this.informations.add(this.inventaireEquipe);
        this.informations.add(this.joueurEnJeu);
        this.informations.add(this.inventaireJoueur);
        this.informations.add(this.actionsRestantes);
    }

    //Acces aux boutons
    public Vector<Bouton> boutons() {return this.boutons;}

    //Acces à un bouton
    public Bouton bouton(int k){return this.boutons.get(k);}

    //Acces aux panneaux de commande
    public Commandes Menu(){return this.menu;}
    public Commandes Parametres(){return this.parametres;}
    public Commandes Nombres(){return this.nombres;}
    public Commandes Actions(){return this.actions;}
    public Commandes Informations(){return this.informations;}
    public Grille Jeu(){return this.jeu;}



    //Methode d'actualisation du jeu
    public void actualise(Ile ile){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.jeu = ile;
        this.inventaire = this.actualiseInventaire();
        informations.setPreferredSize(new Dimension(200, 600));
        panel.add(informations, BorderLayout.EAST);
        actions.setPreferredSize(new Dimension(200, 600));
        panel.add(actions, BorderLayout.WEST);
        panel.add(jeu);


        this.setContentPane(panel);
    }

    private void actualiseInventaire(Dictionary<Artefact, Boolean> artefacts, Joueur enJeu) {
        Commandes newInventaire = new Commandes("Inventaire", Color.WHITE);
        newInventaire.add(new Texte("Artefacts recupérés : ", Color.WHITE));
        if (artefacts.get(Artefact.Air)){newInventaire.add(new Texte("  Air  ", Color.CYAN));}
        if (artefacts.get(Artefact.Eau)){newInventaire.add(new Texte("  Eau  ", Color.CYAN.darker()));}
        if (artefacts.get(Artefact.Feu)){newInventaire.add(new Texte("  Feu  ", Color.RED));}
        if (artefacts.get(Artefact.Terre)){newInventaire.add(new Texte(" Terre ", Color.ORANGE));}
        newInventaire.add(new Texte("Clés du joueur : ", Color.WHITE));

        newInventaire.add(new Texte("Clés du joueur : ", Color.WHITE));

        private Texte inventaireJoueur = new Texte("Clés du joueur : ", Color.WHITE);
        private Texte joueurEnJeu = new Texte("Tour du joueur : ", Color.WHITE);
        private Texte actionsRestantes = new Texte("Actions restantes : ", Color.WHITE);
    }

}
