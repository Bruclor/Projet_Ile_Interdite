package Vue;

import javax.swing.*;
import java.awt.*;

public class Panneau extends JPanel {

    private String nom;

    public Panneau(String nom, Color fontColor){
        this.setBackground(fontColor);
        this.setLayout(new FlowLayout());
    }

    public void addBouton(Bouton b){
        this.add(b);
    }
}
