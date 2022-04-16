package Vue;
import javax.swing.*;
import java.awt.*;


/* =============================================
 * =                                           =
 * =              CLASS GRILLE                 =
 * =                                           =
 * =============================================
 */
public class Grille extends JPanel {

    /*
      ===========================================
      =              CONSTRUCTEUR               =
      ===========================================
     */
    /** -- Constructeur, prend en paramètres les dimensions de la grille.
     *
     * @param hauteur Nombre de lignes de la grille.
     * @param largeur Nombre de colonnes de la grille.
     */
    public Grille (int hauteur, int largeur) {
        setLayout(new GridLayout(hauteur, largeur, 5, 5));
    }

    /*
      ===========================================
      =                 SETTER                  =
      ===========================================
     */

    /** -- Ajouter un élément dans la grille.
     *
     * @param element Élément à ajouter.
     */
    public void ajouteElement (JComponent element) {
        this.add(element);
    }

}
