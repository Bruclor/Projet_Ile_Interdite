enum Artefact {Eau, Terre, Feu, Air, Heliport, Vide}

enum Etat {Normale, Inondee, Submergee}

public class Zone extends Coord{

    /** Attributs **/
    /*Une zone a des coordonnées x et y (abscisse et ordonnée)
    * et peut etre un heliport ou avoir un artéfact ou etre vide*/
    private Etat etat;
    private Artefact artefact;

    /** Constructeurs
     * @param x
     * @param y
     * @param etat
     * @param vide**/
    public Zone(int x, int y, Etat etat, Artefact vide){
        super(x, y);
        this.etat = etat;
        this.artefact = artefact;
    }

    /** Methodes **/
    public int x(){return this.x();}
    public int y(){return this.y();}
    public Coord coord(){return this.coord();}
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

    public void inonde(){
        if (this.etat == Etat.Normale) this.etat = Etat.Inondee;
        else if (this.etat == Etat.Inondee) this.etat = Etat.Submergee;
    }

}
