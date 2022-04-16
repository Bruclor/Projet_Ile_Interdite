package Vue;

import javax.swing.*;
import java.awt.*;

/* =============================================
 * =                                           =
 * =              CLASS BOUTON                 =
 * =                                           =
 * =============================================
 */
public class Bouton extends JButton {

    /*
      ===========================================
      =             CONSTRUCTEURS               =
      ===========================================
     */

    /** -- Construit un bouton avec du texte
     * @param texte texte sur le bouton
     * @param fontColor coulueur de fond du bouton
     * @param dimension dimension du bouton
     *
     **/
    public Bouton(String texte, Color fontColor, Dimension dimension){
        this.setText(texte);
        this.setBackground(fontColor);
        this.setPreferredSize(dimension);
    }

    /** -- Construit un bouton avec une image
     * @param icon image sur le bouton
     * @param fontColor coulueur de fond du bouton
     * @param dimension dimension du bouton
     *
     **/
    public Bouton(ImageIcon icon, Color fontColor, Dimension dimension){
        this.setIcon(icon);
        this.setBackground(fontColor);
        this.setPreferredSize(dimension);
    }

}
