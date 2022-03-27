import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// j'ai pris ca sur internet
public class ZoneWindow extends JFrame {

    public ZoneWindow(Ile ile) {

        super("Ile Interdite");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        addWindowListener(l);

        //ImageIcon img = new ImageIcon("tips.gif");
        JButton bouton = new JButton("Fin de tour");
        bouton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ile.finDeTour();
                System.out.println("ok");
                JPanel panneau = new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.setColor(Color.BLACK);
                        for (int x=0; x<ile.getTaille(); x++){
                            for (int y=0; y< ile.getTaille(); y++) {
                                if (ile.getGrille()[x][y].etat() == Etat.Normale)g.setColor(Color.ORANGE.brighter());
                                else if (ile.getGrille()[x][y].etat() == Etat.Inondee)g.setColor(Color.GRAY);
                                g.fillRect(105+x*100, 105+y*100, 90, 90);
                                g.setColor(Color.BLACK);
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
                g.setColor(Color.BLACK);
                for (int x=0; x<ile.getTaille(); x++){
                    for (int y=0; y< ile.getTaille(); y++) {
                        if (ile.getGrille()[x][y].etat() == Etat.Normale)g.setColor(Color.ORANGE.brighter());
                        else if (ile.getGrille()[x][y].etat() == Etat.Inondee)g.setColor(Color.GRAY);
                        g.fillRect(105+x*100, 105+y*100, 90, 90);
                        g.setColor(Color.BLACK);
                    }
                }

            }
        };


        panneau.add(bouton);
        setContentPane(panneau);
        setSize(200, 100);
        setVisible(true);
    }
}