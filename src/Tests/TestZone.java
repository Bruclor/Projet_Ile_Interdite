package Tests;
import static org.junit.Assert.*;

import Modeles.Artefact;
import Modeles.Etat;
import org.junit.Test;

import Modeles.Zone;
import Modeles.Joueur;

import java.awt.*;

public class TestZone {
    @Test
    public void testConstZone(){
        int x = 2;
        int y = 3;
        Etat e = Etat.Inondee;
        Artefact a = Artefact.Feu;
        Zone z = new Zone(x,y,e,a);
        assertEquals(x,z.x());
        assertEquals(y,z.y());
        assertEquals(e,z.etat());
        assertEquals(a,z.artefact());
        assertEquals(0,z.getJoueurs().size());
    }

    @Test
    public void testToString(){
        int x = 2;
        int y = 3;
        Etat e = Etat.Inondee;
        Artefact a = Artefact.Feu;
        Zone z = new Zone(x,y,e,a);
        assertEquals("FI",z.toString());
    }

    @Test
    public void testSetArtefact(){
        int x = 2;
        int y = 3;
        Etat e = Etat.Inondee;
        Artefact a = Artefact.Feu;
        Zone z = new Zone(x,y,e,a);
        z.setArtefact(Artefact.Heliport);
        assertEquals(Artefact.Feu,z.artefact());
    }

    @Test
    public void testInonde(){
        int x = 2;
        int y = 3;
        Artefact a = Artefact.Feu;
        Zone zn = new Zone(x,y,Etat.Normale,a);
        Zone zi = new Zone(x,y,Etat.Inondee,a);
        Zone zs = new Zone(x,y,Etat.Submergee,a);
        zn.inonde();
        zi.inonde();
        zs.inonde();
        assertEquals(Etat.Inondee,zn.etat());
        assertEquals(Etat.Submergee,zi.etat());
        assertEquals(Etat.Submergee,zs.etat());
    }

    @Test
    public void testAsseche(){
        int x = 2;
        int y = 3;
        Artefact a = Artefact.Feu;
        Zone zn = new Zone(x,y,Etat.Normale,a);
        Zone zi = new Zone(x,y,Etat.Inondee,a);
        Zone zs = new Zone(x,y,Etat.Submergee,a);
        zn.asseche();
        zi.asseche();
        zs.asseche();
        assertEquals(Etat.Normale,zn.etat());
        assertEquals(Etat.Normale,zi.etat());
        assertEquals(Etat.Submergee,zs.etat());
    }

    @Test
    public void testAddRemove(){
        int x = 2;
        int y = 3;
        Etat e = Etat.Inondee;
        Artefact a = Artefact.Feu;
        Zone z = new Zone(x,y,e,a);
        Joueur j3 = new Joueur(3,"joueur1",z.coord(), Color.BLUE);
        Joueur j5 = new Joueur(5,"joueur2",z.coord(), Color.RED);
        z.addJoueur(j3);
        z.addJoueur(j5);
        assertEquals(z.getJoueurs().get(0).id(),3);
        assertEquals(z.getJoueurs().get(1).id(),5);
        assertEquals(2,z.getJoueurs().size());
        z.removeJoueur(j3);
        assertEquals(z.getJoueurs().get(0).id(),5);
        assertEquals(1,z.getJoueurs().size());
    }
}
