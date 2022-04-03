package Vue;

import Modeles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// j'ai pris ca sur internet
public class ZoneWindow extends JFrame {

    public ZoneWindow(Ile ile) {

        super("Modeles.Ile Interdite");

        /** Action de fermeture fenetre **/
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        addWindowListener(l);

        //ImageIcon img = new ImageIcon("tips.gif");
        /** Ajout du bouton "Fin de tour" **/
        JButton bouton = new JButton("Fin de tour");

        /** Ajout des boutons de déplacement **/
        Icon haut = new ImageIcon("src/Images/haut.jpg");
        Icon bas = new ImageIcon("src/Images/bas.jpg");
        Icon droite = new ImageIcon("src/Images/droite.jpg");
        Icon gauche = new ImageIcon("src/Images/gauche.jpg");
        JButton btHaut = new JButton(haut);
        JButton btBas = new JButton(bas);
        JButton btDroite = new JButton(droite);
        JButton btGauche = new JButton(gauche);


        bouton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ile.finDeTour();
                JPanel panneau = new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {

                        super.paintComponent(g);
                        g.setColor(Color.BLUE);
                        for (int x=0; x<ile.taille(); x++){
                            for (int y=0; y< ile.taille(); y++) {
                                if (ile.grille()[x][y].etat() == Etat.Normale)g.setColor(Color.ORANGE);
                                else if (ile.grille()[x][y].etat() == Etat.Inondee)g.setColor(Color.CYAN);
                                g.fillRect(105+x*100, 105+y*100, 90, 90);
                                if (ile.grille()[x][y].artefact() == Artefact.Air)g.setColor(Color.GRAY);
                                else if (ile.grille()[x][y].artefact() == Artefact.Eau)g.setColor(new Color(0,150,200));
                                else if (ile.grille()[x][y].artefact() == Artefact.Feu)g.setColor(Color.RED);
                                else if (ile.grille()[x][y].artefact() == Artefact.Terre)g.setColor(Color.orange.darker());
                                g.fillPolygon(new int[]{105 + x * 100, 105 + x * 100 + 45, 105 + x * 100 + 90},
                                              new int[]{105 + y * 100 + 90, 105 + y * 100 + 45, 105 + y * 100 + 90},3);
                                g.setColor(Color.WHITE);
                                /*for (int j = 0; j < ile.getJoueurs().length; j++){
                                    if (ile.getJoueurs()[j].x() == x && ile.getJoueurs()[j].y() == y) {
                                        g.drawString(ile.getJoueurs()[j].nom(),105 + x * 100,105 + x * 100+j*20);
                                    }
                                }*/
                                g.setColor(Color.BLUE);
                            }
                        }

                    }
                };
                panneau.add(bouton);
                setContentPane(panneau);
                setVisible(true);
            }
        });

        JPanel panneau = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                for (int x=0; x<ile.taille(); x++) {
                    for (int y = 0; y < ile.taille(); y++) {
                        if (ile.grille()[x][y].etat() == Etat.Normale) g.setColor(Color.ORANGE);
                        else if (ile.grille()[x][y].etat() == Etat.Inondee) g.setColor(Color.CYAN);
                        g.fillRect(105 + x * 100, 105 + y * 100, 90, 90);
                        if (ile.grille()[x][y].artefact() == Artefact.Air) g.setColor(Color.GRAY);
                        else if (ile.grille()[x][y].artefact() == Artefact.Eau) g.setColor(new Color(0, 150, 200));
                        else if (ile.grille()[x][y].artefact() == Artefact.Feu) g.setColor(Color.RED);
                        else if (ile.grille()[x][y].artefact() == Artefact.Terre) g.setColor(Color.orange.darker());
                        g.fillPolygon(new int[]{105 + x * 100, 105 + x * 100 + 45, 105 + x * 100 + 90},
                                      new int[]{105 + y * 100 + 90, 105 + y * 100 + 45, 105 + y * 100 + 90}, 3);
                        /*g.setColor(Color.WHITE);
                        for (int j = 0; j < ile.getJoueurs().length; j++){
                              if (ile.getJoueurs()[j].x() == x && ile.getJoueurs()[j].y() == y) {
                                    g.drawString(ile.getJoueurs()[j].nom(),105 + x * 100,105 + x * 100+j*20);
                               }
                        }*/
                        g.setColor(Color.BLUE);
                    }
                }
            }
        };

        panneau.setLocation(1250, 350);
        panneau.add(bouton);
        setContentPane(panneau);
        setSize(200, 100);
        setVisible(true);

    }
}
