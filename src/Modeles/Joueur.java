package Modeles;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Vector;

/* =============================================
 * =                                           =
 * =            CLASSE JOUEUR                  =
 * =                                           =
 * =============================================
 */

public class Joueur {

  /*
    ===========================================
    =                ATTRIBUTS                =
    ===========================================
   */

  private int id;                                                                    //Identifiant
  private String nom;                                                                //Nom
  private Color couleur;                                                             //Couleur
  private Coord coord;                                                               //Coordonnées
  private int nbActions;                                                             //Actions restantes
  private Dictionary<Objet, Integer> inventaire = new Hashtable<Objet, Integer>(6);       //Nombre d'objets

  /*
    ===========================================
    =              CONSTRUCTEUR               =
    ===========================================
   */

  /** -- Construit un joueur du jeu
   *
   * @param id identifiant
   * @param nom nom
   * @param coord position initiale
   * @param couleur la couleur de représentation
   **/
  public Joueur(int id, String nom, Coord coord, Color couleur){


    this.coord = coord;
    this.id = id;
    this.nom = nom;
    this.couleur = couleur;

    this.inventaire.put(Objet.CleAir, 0);
    this.inventaire.put(Objet.CleEau, 0);
    this.inventaire.put(Objet.CleFeu, 0);
    this.inventaire.put(Objet.CleTerre, 0);
    this.inventaire.put(Objet.Helicoptere, 0);
    this.inventaire.put(Objet.SacDeSable, 0);

  }

  /*
    ===========================================
    =                GETTER                   =
    ===========================================
   */

  /** -- Identifiant du joueur
   *
   * @return identifiant
   **/
  public int id(){return this.id;}

  /** -- Nom du joueur
   *
   * @return nom
   **/
  public String nom(){return this.nom;}

  /** -- Coordonnées du joueur
   *
   * @return Coordonnée
   **/
  public Coord coord(){return this.coord;}


  /** -- Coordonnée x du joueur
   *
   * @return Coordonnée x
   **/
  public int x(){return this.coord.x();}

  /** -- Coordonnée y du joueur
   *
   * @return Coordonnée y
   **/
  public int y(){return this.coord.y();}

  /** -- Nombre de clés d'un type récupéré
   *
   * @param objet artefact dont on veut le nombre de cle
   * @return la quantité de cle
   **/
  public int nbObjets(Objet objet){return this.inventaire.get(objet);}


  /** -- Retourne le nombre d'actions restantes pour le joueur
   *
   * @return entier nombre d'actions restantes
   **/
  public int getNbActions(){return this.nbActions;}

  /** -- Retourne la couleur du joueur
   *
   * @return couleur du joueur
   **/
  public Color couleur(){return this.couleur;}

  /** -- Retourne la liste des objets dont le joueur possede au moins une clé
   *
   * @return vecteur d'objets de clé
   */
  public Vector<Objet> clePossede() {
    Vector<Objet> mesObjets = new Vector<Objet>(0);
    if (this.inventaire.get(Objet.CleAir) > 0) {
      mesObjets.add(Objet.CleAir);
    }
    if (this.inventaire.get(Objet.CleEau) > 0) {
      mesObjets.add(Objet.CleEau);
    }
    if (this.inventaire.get(Objet.CleFeu) > 0) {
      mesObjets.add(Objet.CleFeu);
    }
    if (this.inventaire.get(Objet.CleTerre) > 0) {
      mesObjets.add(Objet.CleTerre);
    }
    return mesObjets;
  }

  /*
    ===========================================
    =                SETTER                   =
    ===========================================
   */

  /** -- Modifie le nom du joueur
   *
   * @param nom nom du joueur
   **/
  public void setNom(String nom){this.nom = nom;}

  /** -- Modifie l'identifiant du joueur
   *
   * @param id nom du joueur
   **/
  public void setId(int id){this.id = id;}

  /** -- Le joueur tilise une clé d'artefact
   *
   * @param objet un objet
   **/
  public void perd(Objet objet, int nb){
      this.inventaire.put(objet,this.inventaire.get(objet)-nb);
  }

  /**  -- le joueur gagne une clé d'artefact
   *
   * @param objet un objet
   **/
  public void gagne(Objet objet){
    this.inventaire.put(objet,this.inventaire.get(objet)+1);
  }


  /** -- Deplace le joueur
   *
   * @param coord destination
   **/
  public void deplace(Coord coord){
    this.coord.set_x(coord.x());
    this.coord.set_y(coord.y());
  }

  /** -- Echange une cle avec un artefact
   *
   * @param artefact artefact a echanger
   **/
  public void recupereArtefact(Artefact artefact, int nbClesUtilises){
    switch (artefact){
      case Air : this.perd(Objet.CleAir, nbClesUtilises); break;
      case Eau : this.perd(Objet.CleEau, nbClesUtilises); break;
      case Feu : this.perd(Objet.CleFeu, nbClesUtilises); break;
      case Terre : this.perd(Objet.CleTerre, nbClesUtilises); break;
    }
  }

  /** -- Le joueur effectue une action
   **/
  public void effectueAction(){ if (nbActions > 0) nbActions--;}

  /** -- Modifie le nombre d'actions du joueur
   *
   * @param i le nombre d'actions
   */
  public void setNbActions(int i) {
    if (i > 0)
      this.nbActions = i;
  }


}
  
