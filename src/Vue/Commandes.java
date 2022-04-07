package Vue;

import javax.swing.*;
import java.awt.*;

public class Commandes extends JPanel {

    private String nom;

    public Commandes(String nom, Color fontColor){
        this.setBackground(fontColor);
        this.setLayout(new FlowLayout());
    }

    public void addBouton(Bouton b){
        this.add(b);
    }
}
