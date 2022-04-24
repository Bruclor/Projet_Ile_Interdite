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
        Ile i = new Ile(nbJ,nbA,true,3);
        assertEquals(nbJ,i.getNbJoueurs());
        assertEquals(nbA,i.getNbArtefacts());
        assertFalse(i.GameOver());
        assertFalse(i.IsWin());
        assertEquals(i.getGrille().length,6);
        assertEquals(i.getGrille()[0].length,6);
        assertEquals(i.zone(0,0).etat(), Etat.Submergee);
        assertEquals(i.zone(0,1).etat(), Etat.Submergee);
        assertEquals(i.zone(1,1).etat(), Etat.Normale);
    }

    @Test
    public void testZone(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA,true,3);
        int x = 2; int y = 3;
        Coord c = new Coord(x,y);
        assertEquals(i.zone(x,y),i.zone(c));
    }

    @Test
    public void testZoneDispo(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA,true,3);
        Vector<Zone> zd = i.getZonesDispo();
        for(int k = 0; k < zd.size();k++)
            assertEquals(zd.get(k).toString(),"VN");
    }

    @Test
    public void testInitJoueurs(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA,true,3);
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
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA,true,3);
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
        Ile i = new Ile(nbJ,nbA,true,3);
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
        Ile i = new Ile(nbJ,nbA,true,3);
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
        Ile i = new Ile(nbJ,nbA,true,3);
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
        Ile i = new Ile(nbJ,nbA,true,3);
        i.setGameOver("Test");
        assertTrue(i.GameOver());
        assertEquals(i.infoGameOver(),"Test");
    }

    @Test
    public void testAjouteArtefact(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA,true,3);
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
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA,true,3);
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
        Ile i = new Ile(nbJ,nbA,true,3);
        i.InitJoueurs();
        i.InitArtefacts();
        Vector<Zone> zd = i.getZonesDispo();
        int x = zd.get(0).x();
        int y = zd.get(0).x();
        i.zone(x,y).setArtefact(Artefact.Feu);
        i.getJoueur(0).deplace(new Coord(x,y));
        i.getJoueur(0).gagne(Objet.CleFeu);
        i.getJoueur(0).gagne(Objet.CleFeu);
        i.getJoueur(0).gagne(Objet.CleFeu);
        i.artefactRecupere();
        assertTrue(i.getArtefactsRecuperes().get(Artefact.Feu));
        assertEquals(i.getJoueur(0).getNbActions(),2);
    }

    @Test
    public void recupereObjet(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA,true,3);
        i.InitJoueurs();
        i.InitArtefacts();
        Paquet<Objet> p = new Paquet<Objet>();
        p.addCarte(Objet.CleFeu);
        i.setPiocheObjets(p);
        i.recupereObjet();
        assertEquals(i.getJoueur(0).clePossede().get(0),Objet.CleFeu);
        assertEquals(i.getJoueur(0).clePossede().size(),1);
    }

    @Test
    public void TestSacDeSable(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA,true,3);
        i.InitJoueurs();
        i.InitArtefacts();
        int x1 = 1; int y1 = 1;
        int x3 = 3; int y3 = 3;
        i.getJoueur(0).deplace(new Coord(x1,y1));
        i.zone(x3,x3).inonde();
        i.sacDeSable(new Coord(x3,y3));
        assertEquals(Etat.Inondee,i.zone(x3,x3).etat());
        Paquet<Objet> p = new Paquet<Objet>();
        p.addCarte(Objet.SacDeSable);
        i.setPiocheObjets(p);
        i.recupereObjet();
        i.sacDeSable(new Coord(x3,y3));
        assertEquals(Etat.Normale,i.zone(x3,x3).etat());
    }

    @Test
    public void TestHelicoptere(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA,true,3);
        i.InitJoueurs();
        i.InitArtefacts();
        int x1 = 1; int y1 = 1;
        int x3 = 3; int y3 = 3;
        i.getJoueur(0).deplace(new Coord(x1,y1));
        i.zone(x3,x3).inonde();
        i.helicoptere(new Coord(x3,y3));
        assertEquals(i.getJoueur(0).x(),x1);
        assertEquals(i.getJoueur(0).y(),y1);
        Paquet<Objet> p = new Paquet<Objet>();
        p.addCarte(Objet.Helicoptere);
        i.setPiocheObjets(p);
        i.recupereObjet();
        i.helicoptere(new Coord(x3,y3));
        assertEquals(i.getJoueur(0).x(),x3);
        assertEquals(i.getJoueur(0).y(),y3);
    }

    @Test
    public void TestDonneCle(){
        int nbJ = 2;
        int nbA = 4;
        Ile i = new Ile(nbJ,nbA,true,3);
        i.InitJoueurs();
        i.InitArtefacts();
        int x = 1; int y = 1;
        i.getJoueur(0).deplace(new Coord(x,y));
        i.getJoueur(1).deplace(new Coord(x,y));
        Paquet<Objet> p = new Paquet<Objet>();
        p.addCarte(Objet.CleFeu);
        i.setPiocheObjets(p);
        i.recupereObjet();
        i.setObjetEchange(Objet.CleFeu);
        i.setIdJoueurEchange(1);
        assertEquals(i.getJoueur(0).nbObjets(Objet.CleFeu),1);
        i.donneCle();
        assertEquals(i.getJoueur(0).nbObjets(Objet.CleFeu),0);
        assertEquals(i.getJoueur(0).getNbActions(),2);
        assertEquals(i.getJoueur(1).clePossede().get(0),Objet.CleFeu);
        assertEquals(i.getJoueur(1).clePossede().size(),1);
    }
}



