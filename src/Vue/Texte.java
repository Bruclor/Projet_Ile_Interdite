package Vue;

import javax.swing.*;
import java.awt.*;

public class Texte extends JLabel {

    public Texte(String text, Color color){
        this.setText(text);
        this.setForeground(color);
    }
    public Texte(String text, Color color, Dimension dimension){
        this.setText(text);
        this.setForeground(color);
        this.setPreferredSize(dimension);
    }

    public Texte(String text, Color color, Dimension dimension, int taille){
        this.setText(text);
        this.setForeground(color);
        this.setPreferredSize(dimension);
        this.setFont(new Font("Serif", Font.CENTER_BASELINE, taille));
    }

}
