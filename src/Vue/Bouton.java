package Vue;

import javax.swing.*;
import java.awt.*;

public class Bouton extends JButton {

    public Bouton(String nom, Color fontColor){
        this.setText(nom);
        this.setBackground(fontColor);
    }

    public void setColor(Color color){this.setBackground(color);}

}
