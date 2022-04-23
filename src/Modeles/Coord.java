package Modeles;

import java.util.Random;
import java.util.Vector;

/* =============================================
 * =                                           =
 * =              CLASSE COORD                 =
 * =                                           =
 * =============================================
 */
public class Coord {

    /*
      ===========================================
      =                ATTRIBUTS                =
      ===========================================
     */

    private int x;           //X
    private int y;           //Y

    /*
      ===========================================
      =              CONSTRUCTEUR               =
      ===========================================
     */

    /** -- Constructeur
     *
     * @param x Coordonnée abscisse
     * @param y Coordonnée ordonnée
     **/
    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }

    /** -- Constructeur aléatoire
     *
     * @param sup Coordonnée max pour x et y
     **/
    public Coord(int sup){
        Random random = new Random();
        this.x = random.nextInt(sup);
        this.y = random.nextInt(sup);
    }

    /*
      ===========================================
      =                GETTER                   =
      ===========================================
     */

    /** -- Coordonnée x
     *
     * @return coord x
     **/
    public int x(){return this.x;}

    /** -- Coordonnée y
     *
     * @return coord y
     **/
    public int y(){return this.y;}

    /** -- Indique si une coordonnée est égale a une autre
     *
     * @param other
     *
     * @return booleen vrai si this et other sont egaux
     */
    public boolean equals(Coord other){
        return (this.x()==other.x() && this.y()==other.y());
    }

    /** -- Retourne le vecteur des coordonnées adjacentes
     *
     * @param inf la borne inférieur (incluse)
     * @param sup la borne supérieur (non incluse)
     *
     * @return vecteur des zones autour de la zone
     **/
    public Vector<Coord> adjacents(int inf, int sup) {
        Vector<Coord> adj = new Vector<Coord>();
        if (this.x() < sup - 1) {
            adj.add(new Coord(this.x() + 1, this.y()));
        }
        if (this.x() > inf) {
            adj.add(new Coord(this.x() - 1, this.y()));
        }
        if (this.y() < sup - 1) {
            adj.add(new Coord(this.x(), this.y() + 1));
        }
        if (this.y() > inf) {
            adj.add(new Coord(this.x(), this.y() - 1));
        }
        return adj;
    }

    /*
      ===========================================
      =                SETTER                   =
      ===========================================
     */

    /** -- Change la coordonnée x
     *
     * @param x nouvelle coord
     */
    public void set_x(int x){this.x = x;}

    /** -- Change la coordonnée y
     *
     * @param y nouvelle coord
     */
    public void set_y(int y){this.y = y;}

}
