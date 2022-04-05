package Modeles;

import Controleur.*;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Une joueur avec un id, un nom et des coordonnées
 * Un joueur peut etre en
 **/
public class Joueur extends Action{

  /*******************/
  /**   Attributs   **/
  /*******************/

  /* identifiant et nom du joueur */
  private int id;
  private String nom;
  private Coord coord;
  
  /* Statut */
  private boolean enJeu;
  private int action;
  
  /* Nombre de clés associées à chaque élément */
  Dictionary<Artefact, Integer> cles = new Hashtable<Artefact, Integer>(4);
  
  /* nombre d'artefacts associés à chaque élément */
  Dictionary<Artefact, Integer> artefacts = new Hashtable<Artefact, Integer>(4);

  /*******************/
  /** Constructeur  **/
  /*******************/

  /** Constructeur
   *
   * @param id identifiant
   * @param nom nom
   * @param coord Etat Normal, Inondee ou Submergee
   **/
  public Joueur(int id, String nom, Coord coord){
    this.coord = coord;
    this.enJeu = true;
    this.id = id;
    this.nom = nom;

    this.cles.put(Artefact.Air, 0);
    this.cles.put(Artefact.Feu, 0);
    this.cles.put(Artefact.Eau, 0);
    this.cles.put(Artefact.Terre, 0);

    this.artefacts.put(Artefact.Air, 0);
    this.artefacts.put(Artefact.Feu, 0);
    this.artefacts.put(Artefact.Eau, 0);
    this.artefacts.put(Artefact.Terre, 0);
  }

  /*******************/
  /**    Getter     **/
  /*******************/

  /**
   * Getter id
   *
   * @return identifiant
   **/
  public int id(){return this.id;}

  /**
   * Getter nom
   *
   * @return nom
   **/
  public String nom(){return this.nom;}

  /**
   * Getter coord
   *
   * @return Coordonnée
   **/
  public Coord coord(){return this.coord;}

  /**
   * Getter vivant = True / mort = False
   *
   * @return Coordonnée x
   **/
  public boolean enJeu() {return this.enJeu;}

  /**
   * Getter x
   *
   * @return Coordonnée x
   **/
  public int x(){return this.coord.x();}

  /**
   * Getter y
   *
   * @return Coordonnée y
   **/
  public int y(){return this.coord.y();}

  /**
   * Renvoie le nombre d'artefact pour un artefact dans l'inventaire
   *
   * @param artefact artefact dont on veut le nombre
   * @return la quantité d'artefact
   **/
  public int nbArtefacts(Artefact artefact){return this.artefacts.get(artefact);}

  /**
   * Renvoie le nombre de cle pour une cle dans l'inventaire
   *
   * @param artefact artefact dont on veut le nombre de cle
   * @return la quantité de cle
   **/
  public int nbCles(Artefact artefact){return this.cles.get(artefact);}

  /**
   * Dit si le joueur a un certain artefact dans son inventaire
   *
   * @param artefact artefact dont on veut savoir si on a un
   * @return vrai ou faux
   **/
  public boolean possedeArtefact(Artefact artefact){return this.artefacts.get(artefact) > 0;}

  /*******************/
  /**    Méthode    **/
  /*******************/

  /**
   * Setter nom
   *
   * @param nom nom du joueur
   **/
  public void setNom(String nom){this.nom = nom;}

  /**
   * Setter id
   *
   * @param id nom du joueur
   **/
  public void setId(int id){this.id = id;}

  /**
   * Retire une cle de l'inventaire
   *
   * @param art cle de l'artefact a retirer
   **/
  public void retireCle(Artefact art){this.cles.put(art,this.cles.get(art)-1);}

  /**
   * Ajoute une cle de l'inventaire
   *
   * @param art cle de l'artefact a ajouter
   **/
  public void ajouteCle(Artefact art){this.cles.put(art,this.cles.get(art)+1);}

  /**
   * Retire un artefact de l'inventaire
   *
   * @param art artefact a retirer
   **/
  public void retireArtefact(Artefact art){this.artefacts.put(art,this.artefacts.get(art)-1);}

  /**
   * Ajoute un artefact de l'inventaire
   *
   * @param art artefact a ajouter
   **/
  public void ajouteArtefact(Artefact art){this.artefacts.put(art,this.artefacts.get(art)+1);}

  /**
   * Deplace le joueur
   *
   * @param coord destination
   **/
  public void deplace(Coord coord){
    this.coord.set_x(coord.x());
    this.coord.set_y(coord.y());
  }

  /**
   * Echange une cle avec un artefact
   *
   * @param artefact artefact a echanger
   **/
  public void getArtefact(Artefact artefact){
    if (this.nbCles(artefact)>0){
      this.cles.put(artefact, this.cles.get(artefact)-1);
      this.artefacts.put(artefact, this.artefacts.get(artefact)+1);
    }
  }


  /**
   * Elimine le joueur
   **/
  public void estElimine(){
    this.enJeu = false;
  }





}
  
