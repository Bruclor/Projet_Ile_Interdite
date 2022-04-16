package Vue;

import javax.swing.*;
import java.awt.*;


/* =============================================
 * =                                           =
 * =             CLASS PANNEAU                 =
 * =                                           =
 * =============================================
 */
public class Panneau extends JPanel {

    /*
      ===========================================
      =              CONSTRUCTEURS              =
      ===========================================
     */

    /** -- Construit un panneau avec dimension adaptee
     *
     * @param fontColor la couleur de fond
     */
    public Panneau(Color fontColor){
        this.setBackground(fontColor);
        this.setLayout(new FlowLayout());
    }

    /** -- Construit un panneau avec dimension personnalisee
     *
     * @param fontColor la couleur de fond
     * @param dimension dimension du panneau
     */
    public Panneau(Color fontColor, Dimension dimension){
        this.setBackground(fontColor);
        this.setLayout(new FlowLayout());
        this.setPreferredSize(dimension);
    }

}
