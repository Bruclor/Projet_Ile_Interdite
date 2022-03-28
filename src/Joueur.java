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



}
  
