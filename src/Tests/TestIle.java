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

    @Test
    public void testDeplace() {
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ, nbA);
        i.InitJoueurs();
        i.InitArtefacts();
        int x = 1;
        int y = 1;
        Coord c =new Coord(x,y);
        i.getJoueur(0).deplace(c);
        assertEquals(i.getJoueur(0).x(),x);
        assertEquals(i.getJoueur(0).y(),y);
        Coord v1 = new Coord(-1,0);
        Coord v2 = new Coord(-10,-10);
        Coord v3 = new Coord(1,0);
        i.deplace(v1);
        assertEquals(i.getJoueur(0).x(),x);
        assertEquals(i.getJoueur(0).y(),y);
        i.deplace(v2);
        assertEquals(i.getJoueur(0).x(),x);
        assertEquals(i.getJoueur(0).y(),y);
        i.deplace(v3);
        assertEquals(i.getJoueur(0).x(),x+1);
        assertEquals(i.getJoueur(0).y(),y);
        assertEquals(i.getJoueur(0).getNbActions(),2);
    }

    @Test
    public void testAsseche(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ, nbA);
        i.InitJoueurs();
        i.InitArtefacts();
        int x = 1;
        int y = 1;
        Coord c =new Coord(x,y);
        i.getJoueur(0).deplace(c);
        i.zone(2,1).inonde();
        Coord v1 = new Coord(-1,0);
        Coord v2 = new Coord(0,1);
        Coord v3 = new Coord(1,0);
        i.asseche(v1);
        assertEquals(i.zone(0,1).etat(),Etat.Submergee);
        i.asseche(v2);
        assertEquals(i.zone(1,2).etat(),Etat.Normale);
        i.asseche(v3);
        assertEquals(i.zone(2,1).etat(),Etat.Normale);
        assertEquals(i.getJoueur(0).getNbActions(),2);
    }

    @Test
    public void testJoueurSuivant(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ, nbA);
        i.InitJoueurs();
        i.InitArtefacts();
        i.joueurSuivant();
        assertEquals(i.joueurEnJeu().id(),1);
        assertEquals(i.getJoueur(1).getNbActions(),3);
        i.joueurSuivant();
        assertEquals(i.joueurEnJeu().id(),0);
    }

    @Test
    public void testGameOver(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ, nbA);
        i.setGameOver("Test");
        assertTrue(i.GameOver());
        assertEquals(i.infoGameOver(),"Test");
    }

    @Test
    public void testAjouteArtefact(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ, nbA);
        i.ajouteArtefact(Artefact.Feu);
        i.ajouteArtefact(Artefact.Air);
        i.ajouteArtefact(Artefact.Terre);
        i.ajouteArtefact(Artefact.Eau);
        assertTrue(i.getArtefactsRecuperes().get(Artefact.Air));
        assertTrue(i.getArtefactsRecuperes().get(Artefact.Feu));
        assertTrue(i.getArtefactsRecuperes().get(Artefact.Terre));
        assertTrue(i.getArtefactsRecuperes().get(Artefact.Eau));
    }

    @Test
    public void testCheckWin(){
        int nbJ = 2;
        int nbA = 2;
        Ile i = new Ile(nbJ, nbA);
        i.InitJoueurs();
        i.InitArtefacts();

        i.checkWin();
        assertFalse(i.IsWin());

        i.ajouteArtefact(Artefact.Feu);
        i.ajouteArtefact(Artefact.Air);
        i.ajouteArtefact(Artefact.Terre);
        i.ajouteArtefact(Artefact.Eau);
        i.checkWin();
        assertFalse(i.IsWin());

        Vector<Zone> zd = i.getZonesDispo();
        int x = zd.get(0).x();
        int y = zd.get(0).x();
        i.zone(x,y).setArtefact(Artefact.Heliport);
        i.getJoueur(0).deplace(new Coord(x,y));
        i.getJoueur(1).deplace(new Coord(x,y));
        i.checkWin();
        assertTrue(i.IsWin());
    }

    @Test
    public void testArtefactRecupere(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ, nbA);
        i.InitJoueurs();
        i.InitArtefacts();
        Vector<Zone> zd = i.getZonesDispo();
        int x = zd.get(0).x();
        int y = zd.get(0).x();
        i.zone(x,y).setArtefact(Artefact.Feu);
        i.getJoueur(0).deplace(new Coord(x,y));
        i.getJoueur(0).gagneCle(Artefact.Feu);
        i.artefactRecupere();
        assertTrue(i.getArtefactsRecuperes().get(Artefact.Feu));
        assertEquals(i.getJoueur(0).getNbActions(),2);
    }
}



