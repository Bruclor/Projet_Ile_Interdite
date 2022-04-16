package Vue;

import Modeles.Artefact;
import Modeles.Ile;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Vector;

/* =============================================
 * =                                           =
 * =             CLASS FENETRE                 =
 * =                                           =
 * =============================================
 */
public class Fenetre extends JFrame {

     /*
      ===========================================
      =               ATTRIBUTS                 =
      ===========================================
     */

    private Vector<Bouton> boutons = new Vector<Bouton>();            //Un vecteur pour contenir les boutons
    private Panneau  menu;                                            //Le panneau du menu
    private Panneau  parametres;                                      //Le panneau des parametres
    private Panneau  actions;                                         //Le panneau d'actions (commandes du jeu)
    private Panneau  nombres;                                         //Le panneau pour saisir une valeur
    private Panneau  informations;                                    //Le panneau d'informations/inventaire
    private Grille jeu = new Grille(6, 6);              //La grille de jeu

    /*
      ===========================================
      =              CONSTRUCTEUR               =
      ===========================================
     */
    /** -- Construit une fenetre
     **/
    public Fenetre(){
        this.setTitle("L'ile Interdite");                       //Titre de la fenetre
        this.setSize(new Dimension(1000, 600));     //Dimension de la fenetre
        this.setLocationRelativeTo(null);                       //Fenetre centrée sur l'écran
        this.setResizable(false);                               //Fenetre non redimensionnable
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);           //Fermer la fenetre termine le processus
        this.initBouton();                                      //Initialisation des boutons
        this.initCommandes();                                   //Initialisation des panneaux
        this.setContentPane(this.menu);                         //Affichage de départ sur le menu
        this.setVisible(true);                                  //Rendre la fenetre visible
    }

    /*
      ===========================================
      =                 GETTER                  =
      ===========================================
     */

    /** -- Retourne les boutons
     *
     * @return le vecteur de boutons
     **/
    public Vector<Bouton> boutons() {return this.boutons;}

    /** -- Acces a un bouton
     *
     * @param k indice
     *
     * @return le bouton position k
     **/
    public Bouton bouton(int k){return this.boutons.get(k);}

    /** -- Acces au menu
     *
     * @return le panneau du menu
     */
    public Panneau  Menu(){return this.menu;}

    /** -- Acces aux parametres
     *
     * @return le panneau des parametres
     */
    public Panneau  Parametres(){return this.parametres;}

    /** -- Acces aux valeurs
     *
     * @return le panneau des valeurs
     */
    public Panneau  Nombres(){return this.nombres;}

    /** -- Acces aux valeurs
     *
     * @return le panneau des valeurs
     */
    public Panneau  Actions(){return this.actions;}

    /** -- Acces aux valeurs
     *
     * @return le panneau des actions
     */
    public Panneau  Informations(){return this.informations;}

    /** -- Acces aux valeurs
     *
     * @return le panneau des informations/inventaire
     */
    public Grille Jeu(){return this.jeu;}

    /** -- Acces à la grille de jeu
     *
     * @return la grille de jeu
     **/
    public void addBouton(Bouton b){this.boutons.add(b);}

    /*
      ===========================================
      =                 SETTER                  =
      ===========================================
     */

    /** -- Initialise tous les boutons
     **/
    public void initBouton(){
        this.addBouton(new Bouton("Nouvelle Partie", Color.WHITE, new Dimension(150,40)));
        this.addBouton(new Bouton("Parametres", Color.WHITE, new Dimension(150,40)));
        this.addBouton(new Bouton("Retour", Color.RED, new Dimension(150,40)));
        this.addBouton(new Bouton("Nombre de Joueurs", Color.WHITE, new Dimension(150,40)));
        this.addBouton(new Bouton("Nombre d'artefacts", Color.WHITE, new Dimension(150,40)));
        this.addBouton(new Bouton("1", Color.WHITE, new Dimension(50,20)));
        this.addBouton(new Bouton("2", Color.WHITE, new Dimension(50,20)));
        this.addBouton(new Bouton("3", Color.WHITE, new Dimension(50,20)));
        this.addBouton(new Bouton("4", Color.WHITE, new Dimension(50,20)));
        this.addBouton(new Bouton("Arreter la partie", Color.RED, new Dimension(175, 20)));
        this.addBouton(new Bouton("Chercher et fin du tour", Color.WHITE,  new Dimension(175, 20)));
        this.addBouton(new Bouton("Recuperer un artefact", Color.WHITE,  new Dimension(175, 20)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/haut.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/bas.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/droite.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/gauche.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/assecheHaut.png"), Color.GREEN.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/assecheBas.png"), Color.GREEN.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/assecheDroite.png"), Color.GREEN.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/assecheGauche.png"), Color.GREEN.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/assecheCentre.png"), Color.GREEN.darker().darker(),  new Dimension(30, 30)));
    }

    /** -- Initialise les panneaux
     **/
    public void initCommandes(){
        this.initMenu();
        this.initParametres();
        this.initNombres();
        this.initActions();
    }

    /** -- Initialisation des parametres
     **/
    public void initParametres(){
        this.parametres = new Panneau(Color.BLUE, new Dimension(200, 600));
        this.parametres.add(this.boutons.get(2));
        this.parametres.add(this.boutons.get(3));
        this.parametres.add(this.boutons.get(4));
    }

    /** -- Initialisation du menu
     **/
    public void initMenu(){
        this.menu = new Panneau(Color.BLUE);
        this.menu.add(this.boutons.get(0));
        this.menu.add(this.boutons.get(1));
    }

    /** -- Initialisation des actions
     **/
    public void initActions(){
        this.actions = new Panneau(Color.BLUE, new Dimension(200, 600));
        this.actions.add(this.boutons.get(9));
        this.actions.add(this.boutons.get(10));

        this.actions.add(this.boutons.get(11));

        this.actions.add(new Texte(" Se deplacer :", Color.WHITE, new Dimension(200, 20), 20));
        JPanel deplacements = new JPanel(new BorderLayout());
        deplacements.add(this.boutons.get(12), BorderLayout.NORTH);
        deplacements.add(this.boutons.get(15), BorderLayout.WEST);
        deplacements.add(this.boutons.get(14), BorderLayout.EAST);
        deplacements.add(this.boutons.get(13), BorderLayout.SOUTH);
        this.actions.add(deplacements);

        this.actions.add(new Texte(" Assecher une zone :", Color.WHITE, new Dimension(200, 20), 20));
        JPanel assecher = new JPanel(new BorderLayout());
        assecher.add(this.boutons.get(16), BorderLayout.NORTH);
        assecher.add(this.boutons.get(19), BorderLayout.WEST);
        assecher.add(this.boutons.get(18), BorderLayout.EAST);
        assecher.add(this.boutons.get(17), BorderLayout.SOUTH);
        assecher.add(this.boutons.get(20));
        this.actions.add(assecher);

    }

    /** -- Initialisation des nombres
     **/
    public void initNombres(){
        this.nombres = new Panneau(Color.BLUE);
        this.nombres.add(this.boutons.get(5));
        this.nombres.add(this.boutons.get(6));
        this.nombres.add(this.boutons.get(7));
        this.nombres.add(this.boutons.get(8));
    }

    /** -- Actualise l'ile de la fenetre
     *
     * @param ile l'ile de jeu
     **/
    public void actualise(Ile ile){

        //Initialisation d'un nouveau panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //Mise à jour de l'ile
        this.jeu = ile;

        //Construction du panel
        panel.add(this.actions, BorderLayout.WEST);
        panel.add(this.informations, BorderLayout.EAST);
        panel.add(this.jeu);

        //Mise en place sur la fenetre
        this.setContentPane(panel);
    }

    /** -- Actualise l'inventaire de la fenetre
     *
     * @param ile l'ile de jeu
     **/
    public void actualiseInventaire(Ile ile) {

        //Initialisation d'un nouveau panneau
        Panneau newInventaire = new Panneau (Color.BLUE, new Dimension(200, 600));

        //Affichage des artefacts recupérés par l'équipe
        Dictionary<Artefact, Boolean> artefacts = ile.getArtefactsRecuperes();
        newInventaire.add(new Texte(" Artefacts recupérés : ", Color.WHITE, new Dimension(199, 20), 20));
        if (artefacts.get(Artefact.Air)){newInventaire.add(new JLabel(new ImageIcon("src/Images/air.png")));}
        if (artefacts.get(Artefact.Eau)){newInventaire.add(new JLabel(new ImageIcon("src/Images/eau.png")));}
        if (artefacts.get(Artefact.Feu)){newInventaire.add(new JLabel(new ImageIcon("src/Images/feu.png")));}
        if (artefacts.get(Artefact.Terre)){newInventaire.add(new JLabel(new ImageIcon("src/Images/terre.png")));}

        //Affichage de joueur en jeu
        newInventaire.add(new Texte (" Tour du joueur : ", Color.WHITE, new Dimension(199, 20), 20));
        newInventaire.add(new Texte (ile.joueurEnJeu().nom(), ile.joueurEnJeu().couleur(), new Dimension(30, 20), 20));

        //Affichage du nombre d'actions restantes
        newInventaire.add(new Texte (" Actions restantes : "+ile.joueurEnJeu().getNbActions(), Color.WHITE, new Dimension(199, 20), 20));

        //Affichage des clés récupérées par chaque joueur
        for (int k=0; k<ile.getNbJoueurs(); k++) {
            newInventaire.add(new Texte(" Clés du joueur " + ile.getJoueur(k).nom() + " :", ile.getJoueur(k).couleur(), new Dimension(199, 20), 15));
            newInventaire.add(new Texte("Air : " + ile.getJoueur(k).nbCles(Artefact.Air), Color.WHITE, new Dimension(199, 10)));
            newInventaire.add(new Texte("Eau : " + ile.getJoueur(k).nbCles(Artefact.Eau), Color.WHITE, new Dimension(199, 10)));
            newInventaire.add(new Texte("Feu : " + ile.getJoueur(k).nbCles(Artefact.Feu), Color.WHITE, new Dimension(199, 10)));
            newInventaire.add(new Texte("Terre : " + ile.getJoueur(k).nbCles(Artefact.Terre), Color.WHITE, new Dimension(199, 10)));

        }

        //Mise à jour de l'inventaire
        this.informations = newInventaire;

    }

    /** -- Construit le panneau de defaite
     *
     * @param texte la raison de la defaite
     **/
    public void GameOver(String texte){

        //Initialisation d'un nouveau panneau
        Panneau  defaite = new Panneau ( Color.BLUE, new Dimension(200, 600));

        //Affichage de la defaite/raison de la defaite et ajout du bouton arreter
        Texte GameOver  = new Texte("GAME OVER", Color.RED);
        GameOver.setFont(new Font("Serif", Font.BOLD, 30));
        Texte Raison = new Texte(texte, Color.WHITE);
        Raison.setFont(new Font("Serif", Font.BOLD, 15));
        defaite.add(GameOver);
        defaite.add(Raison);
        defaite.add(this.bouton(9));

        //Mise à jour du panneau actions
        this.actions = defaite;
    }

    /** -- Construit le panneau de victoire
     **/
    public void Victory(){

        //Initialisation d'un nouveau panneau
        Panneau  reussite = new Panneau (Color.BLUE, new Dimension(200, 600));

        //Affichage de la victoire et ajout du bouton arreter
        Texte victoire = new Texte("VICTOIRE !", Color.GREEN);
        victoire.setFont(new Font("Serif", Font.BOLD, 30));
        reussite.add(victoire);
        reussite.add(this.bouton(9));

        //Mise à jour du panneau reussite
        this.actions = reussite;
    }
}
