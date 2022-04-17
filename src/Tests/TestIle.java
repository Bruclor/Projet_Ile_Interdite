package Tests;
import static org.junit.Assert.*;

import Modeles.*;
import org.junit.Test;

import java.awt.*;
import java.util.Optional;
import java.util.Vector;

public class TestIle {
    @Test
    public void testConstIle(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA);
        assertEquals(nbJ,i.getNbJoueurs());
        assertEquals(nbA,i.getNbArtefacts());
        assertFalse(i.GameOver());
        assertFalse(i.IsWin());
        assertEquals(i.getGrille().length,i.getTaille());
        assertEquals(i.getGrille()[0].length,i.getTaille());
        assertEquals(i.zone(0,0).etat(), Etat.Submergee);
        assertEquals(i.zone(0,1).etat(), Etat.Submergee);
        assertEquals(i.zone(1,1).etat(), Etat.Normale);
    }

    @Test
    public void testZone(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA);
        int x = 2; int y = 3;
        Coord c = new Coord(x,y);
        assertEquals(i.zone(x,y),i.zone(c));
    }

    @Test
    public void testZoneDispo(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA);
        Vector<Zone> zd = i.getZonesDispo();
        for(int k = 0; k < zd.size();k++)
            assertEquals(zd.get(k).toString(),"VN");
    }

    @Test
    public void testInitJoueurs(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA);
        i.InitJoueurs();
        assertEquals(i.getNbJoueurs(),i.getJoueurs().length);
        assertEquals(i.getJoueur(0).id(),0);
        assertEquals(i.getJoueur(0).nom(),"J1");
        assertEquals(i.getJoueur(0).couleur(), Color.RED);
        assertEquals(i.getJoueur(0).getNbActions(),3);
        assertEquals(i.getJoueur(1).id(),1);
        assertEquals(i.getJoueur(1).nom(),"J2");
        assertEquals(i.getJoueur(1).couleur(), Color.GREEN);
        assertEquals(i.getJoueur(1).getNbActions(),0);
        assertEquals(i.joueurEnJeu().id(),0);
    }

    @Test
    public void testInitArtefact() {
        int nbJ = 2;
        int nbA = 2;
        Ile i = new Ile(nbJ, nbA);
        i.InitArtefacts();
        assertFalse(i.getArtefactsRecuperes().get(Artefact.Air));
        assertFalse(i.getArtefactsRecuperes().get(Artefact.Feu));
        assertFalse(i.getArtefactsRecuperes().get(Artefact.Eau));
        assertFalse(i.getArtefactsRecuperes().get(Artefact.Terre));
    }
}
