enum Artefact {Eau, Terre, Feu, Air, Heliport}

enum Etat {Normale, Inondee, Submergee}

public class Zone {

    /** Attributs **/
    private int x;
    private int y;
    private Etat etat;
    private Artefact artefact;

    /** Constructeurs **/
    public Zone(int x, int y, Etat etat, Artefact artefact){
        this.x = x;
        this.y = y;
        this.etat = etat;
        this.artefact = artefact;
    }

    /** Methodes **/

}
