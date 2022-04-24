package Vue;

import Modeles.*;

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

    private Panneau  parametres;                                      //Le panneau des parametres
    private Panneau  actions;                                         //Le panneau d'actions (commandes du jeu)
    private Panneau  informations;                                    //Le panneau d'informations/inventaire
    private Panneau  cartesPiochee = new Panneau(Color.BLUE.darker().darker(), new Dimension(1200, 100));
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
        this.setSize(new Dimension(1200, 700));     //Dimension de la fenetre
        this.setLocationRelativeTo(null);                       //Fenetre centrée sur l'écran
        this.setResizable(false);                               //Fenetre non redimensionnable
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);           //Fermer la fenetre termine le processus
        this.initBouton();                                      //Initialisation des boutons
        this.afficheMenu(2, 2, false, 1);  //Affichage de départ sur le menu
        this.setVisible(true);                                  //Rendre la fenetre visible
    }

    /*
      ===========================================
      =                 GETTER                  =
      ===========================================
     */


    /** -- Acces a un bouton
     *
     * @param k indice
     *
     * @return le bouton position k
     **/
    public Bouton bouton(int k){return this.boutons.get(k);}


    /** -- Ajoute un bouton
     *
     * @param b un nouveau bouton
     **/
    public void addBouton(Bouton b){this.boutons.add(b);}

    /** -- Retourne le vecteur des boutons
     *
     * @return le vecteur de boutons
     **/
    public Vector<Bouton> boutons(){return this.boutons;}

    /*
      ===========================================
      =                 SETTER                  =
      ===========================================
     */

    /** -- Initialise tous les boutons
     **/
    public void initBouton(){
        this.addBouton(new Bouton("Nouvelle Partie", Color.GREEN, new Dimension(200,40)));
        this.addBouton(new Bouton("Arreter la partie", Color.RED, new Dimension(200, 30)));
        this.addBouton(new Bouton("Chercher et fin du tour", Color.WHITE,  new Dimension(200, 30)));
        this.addBouton(new Bouton("Recuperer un artefact", Color.WHITE,  new Dimension(200, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/haut.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/bas.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/droite.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/gauche.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/assecheHaut.png"), Color.GREEN.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/assecheBas.png"), Color.GREEN.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/assecheDroite.png"), Color.GREEN.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/assecheGauche.png"), Color.GREEN.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/assecheCentre.png"), Color.GREEN.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/droite.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/gauche.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/droite.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/gauche.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/droite.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton("Utiliser un helico", Color.YELLOW, new Dimension(200, 30)));
        this.addBouton(new Bouton("Utiliser un sac de sable", Color.YELLOW, new Dimension(200, 30)));
        this.addBouton(new Bouton("Annuler", Color.RED, new Dimension(200, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/haut.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/bas.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/droite.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/gauche.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton("Assecher", Color.WHITE, new Dimension(200, 30)));
        this.addBouton(new Bouton("Voyager", Color.WHITE, new Dimension(200, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/gauche.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/droite.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton("Donner une cle", Color.WHITE, new Dimension(200, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/droite.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton(new ImageIcon("src/Images/droite.png"), Color.WHITE.darker().darker(),  new Dimension(30, 30)));
        this.addBouton(new Bouton("Echanger", Color.WHITE, new Dimension(200, 30)));
        this.addBouton(new Bouton("Annuler", Color.RED, new Dimension(200, 30)));
    }

    /** -- Affiche le panneau du menu sur la fenetre
     *
     * @param nbJoueurs   nombre de joueurs parametre
     * @param nbArtefacts  nombre d'artefacts parametre
     * @param actionsSpe  utilisation des actions speciales parametre
     * @param nbClesEchange nombre de cles necessaires pour obtenir un artefact parametre
     */
    public void afficheMenu(int nbJoueurs, int nbArtefacts, boolean actionsSpe, int nbClesEchange){
        JPanel affichageGlobal = new JPanel();
        affichageGlobal.setLayout(new BorderLayout());

        Panneau titre = new Panneau(Color.BLUE.darker(), new Dimension(1200, 100));
        titre.add(new Texte(" L'ile Interdite ", Color.WHITE, 50));
        affichageGlobal.add(titre, BorderLayout.NORTH);

        actualiseParametres(nbJoueurs, nbArtefacts, actionsSpe, nbClesEchange);
        affichageGlobal.add(parametres, BorderLayout.SOUTH);

        Panneau lance = new Panneau(Color.BLUE.darker());
        lance.add(this.boutons.get(0));
        affichageGlobal.add(lance);

        this.setContentPane(affichageGlobal);

    }

    /** -- Affiche le panneau de jeu sur la fenetre
     *
     * @param ile l'ile de jeu
     **/
    public void afficheJeu(Ile ile){

        //Initialisation d'un nouveau panel
        JPanel affichageGlobal = new JPanel();
        affichageGlobal.setLayout(new BorderLayout());

        //Mise à jour de l'ile
        this.jeu = ile;

        //Construction du panel
        affichageGlobal.add(this.actions, BorderLayout.WEST);
        affichageGlobal.add(this.informations, BorderLayout.EAST);
        affichageGlobal.add(this.cartesPiochee, BorderLayout.SOUTH);
        affichageGlobal.add(this.jeu);

        //Mise en place sur la fenetre
        this.setContentPane(affichageGlobal);
    }

    /** -- Actualise le panneau des parametres
     *
     * @param nbJoueurs   nombre de joueurs parametre
     * @param nbArtefacts  nombre d'artefacts parametre
     * @param actionsSpe  utilisation des actions speciales parametre
     * @param nbClesEchange nombre de cles necessaires pour obtenir un artefact parametre
     */
    public void actualiseParametres(int nbJoueurs, int nbArtefacts, boolean actionsSpe, int nbClesEchange){
        Panneau newParam = new Panneau(Color.WHITE.darker().darker(), new Dimension(1200, 500));
        newParam.add(new Texte("Parametres :", Color.WHITE, new Dimension(800, 40), 30));

        Panneau changeJoueur = new Panneau(Color.WHITE.darker(), new Dimension(600, 40));
        changeJoueur.add(new Texte("Nombre de Joueurs", Color.WHITE));
        changeJoueur.add(this.bouton(14));
        changeJoueur.add(new Texte(""+nbJoueurs, Color.WHITE, new Dimension(30, 30), 15));
        changeJoueur.add(this.bouton(13));

        Panneau changeArtefact = new Panneau(Color.WHITE.darker(), new Dimension(600, 40));
        changeArtefact.add(new Texte("Nombre d'artefacts", Color.WHITE));
        changeArtefact.add(this.bouton(16));
        changeArtefact.add(new Texte(""+nbArtefacts, Color.WHITE, new Dimension(30, 30), 15));
        changeArtefact.add(this.bouton(15));

        Panneau utiliserActionsSpe = new Panneau(Color.WHITE.darker(), new Dimension(600, 40));
        utiliserActionsSpe.add(new Texte("Actions Speciales", Color.WHITE));
        utiliserActionsSpe.add(this.bouton(17));
        if (actionsSpe) {
            utiliserActionsSpe.add(new Texte("Utiliser", Color.WHITE, new Dimension(150, 30), 15));
        } else {
            utiliserActionsSpe.add(new Texte("Ne pas utiliser", Color.WHITE, new Dimension(150, 30), 15));
        }

        Panneau nbCleEchange= new Panneau(Color.WHITE.darker(), new Dimension(600, 40));
        nbCleEchange.add(new Texte("Nombre de cles requises pour obtenir un artefact", Color.WHITE));
        nbCleEchange.add(this.bouton(27));
        nbCleEchange.add(new Texte(""+nbClesEchange, Color.WHITE, new Dimension(30, 30), 15));
        nbCleEchange.add(this.bouton(28));

        newParam.add(changeJoueur);
        newParam.add(changeArtefact);
        newParam.add(utiliserActionsSpe);
        newParam.add(nbCleEchange);

        this.parametres = newParam;

    }

    /** -- Initialisation des actions
     **/
    public void actualiseActions(boolean actionsSpe){

        Panneau newActions = new Panneau(Color.BLUE, new Dimension(300, 600));


        newActions.add(this.boutons.get(1));
        newActions.add(this.boutons.get(2));

        newActions.add(this.boutons.get(3));
        newActions.add(boutons.get(29));

        newActions.add(new Texte(" Se deplacer :", Color.WHITE, new Dimension(300, 30), 20));
        JPanel deplacements = new JPanel(new BorderLayout());
        deplacements.add(this.boutons.get(4), BorderLayout.NORTH);
        deplacements.add(this.boutons.get(7), BorderLayout.WEST);
        deplacements.add(this.boutons.get(6), BorderLayout.EAST);
        deplacements.add(this.boutons.get(5), BorderLayout.SOUTH);
        newActions.add(deplacements);

        newActions.add(new Texte(" Assecher une zone :", Color.WHITE, new Dimension(300, 30), 20));
        JPanel assecher = new JPanel(new BorderLayout());
        assecher.add(this.boutons.get(8), BorderLayout.NORTH);
        assecher.add(this.boutons.get(11), BorderLayout.WEST);
        assecher.add(this.boutons.get(10), BorderLayout.EAST);
        assecher.add(this.boutons.get(9), BorderLayout.SOUTH);
        assecher.add(this.boutons.get(12));
        newActions.add(assecher);

        if (actionsSpe) {
            newActions.add(new Texte(" Actions speciales :", Color.WHITE, new Dimension(300, 30), 20));
            newActions.add(boutons.get(18));
            newActions.add(boutons.get(19));

        }
        this.actions = newActions;

    }



    /** -- Actualise l'inventaire de la fenetre
     *
     * @param ile l'ile de jeu
     **/
    public void actualiseInventaire(Ile ile) {

        //Initialisation d'un nouveau panneau
        Panneau newInventaire = new Panneau (Color.BLUE, new Dimension(300, 600));

        //Affichage des artefacts recupérés par l'équipe
        Dictionary<Artefact, Boolean> artefacts = ile.getArtefactsRecuperes();
        newInventaire.add(new Texte(" Artefacts recupérés : ", Color.WHITE, new Dimension(300, 25), 20));
        if (artefacts.get(Artefact.Air)){newInventaire.add(new JLabel(new ImageIcon("src/Images/air.png")));}
        if (artefacts.get(Artefact.Eau)){newInventaire.add(new JLabel(new ImageIcon("src/Images/eau.png")));}
        if (artefacts.get(Artefact.Feu)){newInventaire.add(new JLabel(new ImageIcon("src/Images/feu.png")));}
        if (artefacts.get(Artefact.Terre)){newInventaire.add(new JLabel(new ImageIcon("src/Images/terre.png")));}

        //Affichage de joueur en jeu
        newInventaire.add(new Texte (" Tour du joueur : ", Color.WHITE, new Dimension(300, 25), 20));
        newInventaire.add(new Texte (ile.joueurEnJeu().nom(), ile.joueurEnJeu().couleur(), new Dimension(50, 25), 20));

        //Affichage du nombre d'actions restantes
        newInventaire.add(new Texte (" Actions restantes : "+ile.joueurEnJeu().getNbActions(), Color.WHITE, new Dimension(300, 25), 20));

        //Affichage des clés récupérées par chaque joueur
        for (int k=0; k<ile.getNbJoueurs(); k++) {
            newInventaire.add(new Texte(" Inventaire du joueur " + ile.getJoueur(k).nom() + " :", ile.getJoueur(k).couleur(), new Dimension(300, 25), 12));
            newInventaire.add(new Texte("Air : " + ile.getJoueur(k).nbObjets(Objet.CleAir), Color.WHITE, new Dimension(120, 15)));
            newInventaire.add(new Texte("Eau : " + ile.getJoueur(k).nbObjets(Objet.CleEau), Color.WHITE, new Dimension(120, 15)));
            newInventaire.add(new Texte("Feu : " + ile.getJoueur(k).nbObjets(Objet.CleFeu), Color.WHITE, new Dimension(120, 15)));
            newInventaire.add(new Texte("Terre : " + ile.getJoueur(k).nbObjets(Objet.CleTerre), Color.WHITE, new Dimension(120, 15)));
            if (ile.utiliseActionsSpe()) {
                newInventaire.add(new Texte("Helicos : " + ile.getJoueur(k).nbObjets(Objet.Helicoptere), Color.ORANGE.darker(), new Dimension(120, 15)));
                newInventaire.add(new Texte("Sable : " + ile.getJoueur(k).nbObjets(Objet.SacDeSable), Color.ORANGE.darker(), new Dimension(120, 15)));
            }

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
        Panneau  defaite = new Panneau ( Color.BLUE, new Dimension(300, 600));

        //Affichage de la defaite/raison de la defaite et ajout du bouton arreter
        Texte GameOver  = new Texte("GAME OVER", Color.RED);
        GameOver.setFont(new Font("Serif", Font.BOLD, 30));
        Texte Raison = new Texte(texte, Color.WHITE);
        Raison.setFont(new Font("Serif", Font.BOLD, 15));
        defaite.add(GameOver);
        defaite.add(Raison);
        defaite.add(this.bouton(1));

        //Mise à jour du panneau actions
        this.actions = defaite;
    }

    /** -- Construit le panneau de victoire
     **/
    public void Victory(){

        //Initialisation d'un nouveau panneau
        Panneau  reussite = new Panneau (Color.BLUE, new Dimension(300, 600));

        //Affichage de la victoire et ajout du bouton arreter
        Texte victoire = new Texte("VICTOIRE !", Color.GREEN);
        victoire.setFont(new Font("Serif", Font.BOLD, 30));
        reussite.add(victoire);
        reussite.add(this.bouton(1));

        //Mise à jour du panneau reussite
        this.actions = reussite;
    }

    /** -- Construit le panneau de selection d'une zone pour les actions speciales
     *
     * @param utilise l'objet d'action speciale utilise
     */
    public void SelectZone(Objet utilise) {

        Panneau selection = new Panneau(Color.BLUE, new Dimension(300, 600));

        selection.add(this.boutons.get(20));

        if (utilise == Objet.SacDeSable){
            selection.add(this.boutons.get(25));
        } else {
            selection.add(this.boutons.get(26));
        }

        selection.add(new Texte(" Selectionner :", Color.WHITE, new Dimension(300, 30), 20));
        JPanel deplacements = new JPanel(new BorderLayout());
        deplacements.add(this.boutons.get(21), BorderLayout.NORTH);
        deplacements.add(this.boutons.get(24), BorderLayout.WEST);
        deplacements.add(this.boutons.get(23), BorderLayout.EAST);
        deplacements.add(this.boutons.get(22), BorderLayout.SOUTH);
        selection.add(deplacements);


        this.actions = selection;

    }

    /** -- Affiche le contenue de la carte piochee par le joueur
     *
     * @param objet
     * @param joueur
     */
    public void actualiseCartePiochee(Objet objet, Joueur joueur){

        Panneau CartePiochee = new Panneau(Color.BLUE.darker().darker(), new Dimension(1200, 100));

        String texte = "";
        switch (joueur.id()){
            case 0 : texte += "J1"; break;
            case 1:  texte += "J2"; break;
            case 2:  texte += "J3"; break;
            case 3:  texte += "J4"; break;
        }

        switch (objet){
            case CleAir : texte += " obtient une clé Air"; break;
            case CleEau : texte += " obtient une clé Eau"; break;
            case CleFeu : texte += " obtient une clé Feu"; break;
            case CleTerre : texte += " obtient une clé Terre"; break;
            case Helicoptere : texte += " peut prendre un helicoptere"; break;
            case SacDeSable : texte += " obtient un sac de sable"; break;
            case Rien : texte += " n'a rien obtenu"; break;
            case MonteeDesEaux : texte = " Attention ! Evenement de montee des eaux. Le paquet de cartes zone est remélangé"; break;
        }

        CartePiochee.add(new Texte(texte, Color.WHITE, 20));
        this.cartesPiochee = CartePiochee;

    }

    /** -- Supprime l'information d'objet pioche
     */
    public void videCartePiochee(){
        this.cartesPiochee = new Panneau(Color.BLUE.darker().darker(), new Dimension(1200, 100));
    }

    public void choisirEchange(Joueur j, Objet objet){
        Panneau echange = new Panneau(Color.BLUE, new Dimension(300, 600));
        echange.add(this.boutons.get(33));

        echange.add(new Texte ("Donner une clé à :", Color.WHITE, new Dimension(300, 30), 20));

        String tJoueur = "J"+(j.id()+1);
        String tObjet = "";
        switch (objet){
            case CleAir : tObjet = "Cle Air"; break;
            case CleEau : tObjet = "Cle Eau"; break;
            case CleFeu : tObjet = "Cle Feu"; break;
            case CleTerre : tObjet = "Cle Terre"; break;
        }

        Panneau selectJoueur = new Panneau(Color.BLUE, new Dimension(300, 50));
        selectJoueur.add(this.boutons.get(30));
        selectJoueur.add(new Texte(tJoueur, j.couleur()));

        Panneau selectCle = new Panneau(Color.BLUE, new Dimension(300, 50));
        selectCle.add(this.boutons.get(31));
        selectCle.add(new Texte(tObjet, Color.WHITE));

        echange.add(selectJoueur);
        echange.add(selectCle);

        echange.add(this.boutons.get(32));

        this.actions = echange;

    }
}
