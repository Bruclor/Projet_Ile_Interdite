package Modeles;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

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
  private Dictionary<Artefact, Integer> cles = new Hashtable<Artefact, Integer>(4);       //Nombre de clés

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

    this.cles.put(Artefact.Air, 0);
    this.cles.put(Artefact.Feu, 0);
    this.cles.put(Artefact.Eau, 0);
    this.cles.put(Artefact.Terre, 0);

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
   * @param artefact artefact dont on veut le nombre de cle
   * @return la quantité de cle
   **/
  public int nbCles(Artefact artefact){return this.cles.get(artefact);}


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
   * @param art cle de l'artefact a retirer
   **/
  public void perdCle(Artefact art){this.cles.put(art,this.cles.get(art)-1);}

  /**  -- le joueur gagne une clé d'artefact
   *
   * @param art cle de l'artefact a ajouter
   **/
  public void gagneCle(Artefact art){this.cles.put(art,this.cles.get(art)+1);}


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
  public void recupereArtefact(Artefact artefact){
      this.perdCle(artefact);
  }

  /** -- Le joueur effectue une action
   **/
  public void effectueAction(){ this.nbActions--;}

  /** -- Modifie le nombre d'actions du joueur
   *
   * @param i le nombre d'actions
   */
  public void setNbActions(int i) {
    this.nbActions = i;
  }

}
  
