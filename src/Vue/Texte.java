package Vue;

import javax.swing.*;
import java.awt.*;


/* =============================================
 * =                                           =
 * =              CLASS TEXTE                  =
 * =                                           =
 * =============================================
 */
public class Texte extends JLabel {

    /*
      ===========================================
      =              CONSTRUCTEURS              =
      ===========================================
     */

    /** -- Construit un texte avec dimension adaptee
     *
     * @param text le texte a afficher
     * @param color la couleur du texte
     */
    public Texte(String text, Color color){
        this.setText(text);
        this.setForeground(color);
    }

    /** -- Construit un texte avec dimension personnalisee
     *
     * @param text le texte a afficher
     * @param color la couleur du texte
     * @param dimension dimension de la zone de texte
     */
    public Texte(String text, Color color, Dimension dimension){
        this.setText(text);
        this.setForeground(color);
        this.setPreferredSize(dimension);
    }

    /** -- Construit un texte plus complexe avec :
     *
     * @param text le texte a afficher
     * @param color la couleur du texte
     * @param dimension dimension de la zone de texte
     * @param taille taille du texte
     */
    public Texte(String text, Color color, Dimension dimension, int taille){
        this.setText(text);
        this.setForeground(color);
        this.setPreferredSize(dimension);
        this.setFont(new Font("Serif", Font.CENTER_BASELINE, taille));
    }

}
