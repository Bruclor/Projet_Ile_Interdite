import java.util.Dictionary;
import java.util.Hashtable;

public class Joueur {
  
  /* identifiant et nom du joueur */
  private int id;
  private String nom;
  private Coord coord;
  
  /* Statut */
  private boolean enJeu;
  
  /* Nombre de clés associées à chaque élément */
  Dictionary<Artefact, Integer> cles = new Hashtable<Artefact, Integer>(4);
  
  /* Artefact en possession */
  Dictionary<Artefact, Integer> artefacts = new Hashtable<Artefact, Integer>(4);

  public Joueur(int id, String nom, int x, int y){
    this.coord.set_x(x);
    this.coord.set_y(y);
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

  /* Getters */
  public int id(){return this.id;}
  public String nom(){return this.nom;}
  public Coord coord(){return this.coord;}
  public boolean enJeu() {return this.enJeu;}
  public int x(){return this.coord.x();}
  public int y(){return this.coord.y();}
  public int nbArtefacts(Artefact artefact){return this.artefacts.get(artefact);}
  public int nbCles(Artefact artefact){return this.cles.get(artefact);}
  public boolean possedeArtefact(Artefact artefact){return this.artefacts.get(artefact) > 0;}

  /* Setters */

  /* Statut */
  public void setNom(String nom){this.nom = nom;}
  public void setId(int id){this.id = id;}

  /* Deplacements */
  public void haut(){this.coord.set_y(this.coord.y()-1);}
  public void bas(){this.coord.set_y(this.coord.y()+1);}
  public void droite(){this.coord.set_x(this.coord.x()+1);}
  public void gauche(){this.coord.set_y(this.coord.x()-1);}

  /* Inventaire */
  public void getArtefact(Artefact artefact){
    if (this.nbCles(artefact)>0){
      this.cles.put(artefact, this.cles.get(artefact)-1);
      this.artefacts.put(artefact, this.artefacts.get(artefact)+1);
    }
  }

  public void getCle(Artefact artefact){
    this.cles.put(artefact, this.cles.get(artefact)+1);
  }

  public void asseche(Zone zone){
    if (zone.etat() == Etat.Inondee)
  }

  public void estElimine(){
    this.enJeu = false;
  }






}
  
