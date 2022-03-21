enum Artefact {Eau, Terre, Feu, Air, Heliport, Vide}

enum Etat {Normale, Inondee, Submergee}

public class Zone {

    /** Attributs **/
    /*Une zone a des coordonnées x et y (abscisse et ordonnée)
    * et peut etre un heliport ou avoir un artéfact ou etre vide*/
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
    public int x(){return this.x;}
    public int y(){return this.y;}
    public Etat etat(){return this.etat;}
    public Artefact artefact(){return this.artefact;}

    public String toString(){
        String res = "";
        if (this.artefact == Artefact.Eau) res += "E";
        else if (this.artefact == Artefact.Terre) res += "T";
        else if (this.artefact == Artefact.Feu) res += "F";
        else if (this.artefact == Artefact.Air) res += "A";
        else if (this.artefact == Artefact.Heliport) res += "H";
        else res += "V";
        if (this.etat == Etat.Normale) res += "N";
        else if (this.etat == Etat.Inondee) res += "I";
        else res += "S";
        return res;
    }


}
