package Modeles;

import java.util.Random;

/**
 * Coordonnée dans un plan
 **/
public class Coord {

    /*******************/
    /**   Attributs   **/
    /*******************/

    private int x;
    private int y;

    /*******************/
    /** Constructeur  **/
    /*******************/

    /**
     * Constructeur
     *
     * @param x Coordonnée abscisse
     * @param y Coordonnée ordonnée
     **/
    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Constructeur aléatoire
     *
     * @param sup Coordonnée max pour x et y
     **/
    public Coord(int sup){
        Random random = new Random();
        this.x = random.nextInt(sup);
        this.y = random.nextInt(sup);
    }

    /*******************/
    /**    Getter     **/
    /*******************/

    /**
     * Getter x
     *
     * @return Coordonnée x
     **/
    public int x(){return this.x;}

    /**
     * Getter x
     *
     * @return Coordonnée x
     **/
    public int y(){return this.y;}

    public void set_x(int x){this.x = x;}
    public void set_y(int y){this.y = y;}

    public boolean equals(Coord other){
        return (this.x()==other.x() && this.y()==other.y();
    }
}
