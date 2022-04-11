package Vue;

import Modeles.Ile;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Fenetre extends JFrame {

    //Les boutons
    private Vector<Bouton> boutons = new Vector<Bouton>();

    //Panneaux
    private Commandes menu = new Commandes("menu", Color.BLUE);
    private Commandes parametres = new Commandes("parametres", Color.BLUE);
    private Commandes actions = new Commandes("actions", Color.BLUE);
    private Commandes nombres = new Commandes("nombres", Color.BLUE);

    //Grille de jeu
    private Grille jeu = new Grille(6, 6);

    //Initialisation de la fenetre
    public Fenetre(){
        this.setTitle("L'ile Interdite");
        this.setSize(new Dimension(800, 600));
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
    public Grille Jeu(){return this.jeu;}

    //Methode d'actualisation du jeu
    public void actualise(Ile ile){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.jeu = ile;
        panel.add(jeu, BorderLayout.WEST);
        panel.add(actions);
        this.setContentPane(panel);
    }

}
