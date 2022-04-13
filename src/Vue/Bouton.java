package Vue;

import javax.swing.*;
import java.awt.*;

public class Bouton extends JButton {

    public Bouton(String nom, Color fontColor){
        this.setText(nom);
        this.setBackground(fontColor);
    }

    public Bouton(String nom, Color fontColor, Dimension dimension){
        this.setText(nom);
        this.setBackground(fontColor);
        this.setPreferredSize(dimension);
    }

    public void setColor(Color color){this.setBackground(color);}

}
