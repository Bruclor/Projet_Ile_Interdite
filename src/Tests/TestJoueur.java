package Tests;
import static org.junit.Assert.*;

import Modeles.Artefact;
import Modeles.Objet;
import org.junit.Test;

import Modeles.Coord;
import Modeles.Joueur;

import java.awt.*;
import java.util.Vector;

public class TestJoueur {
    @Test
    public void testConstJoueur(){
        int id = 1;
        String nom = "Toto";
        int x = 2; int y = 3;
        Coord c = new Coord (x,y);
        Color col = Color.BLUE;
        Joueur j = new Joueur(id,nom,c,col);
        assertEquals(id,j.id());
        assertEquals(nom,j.nom());
        assertEquals(x,j.x());
        assertEquals(y,j.y());
        assertEquals(col,j.couleur());
        assertEquals(0,j.nbObjets(Objet.CleFeu));
        assertEquals(0,j.nbObjets(Objet.CleAir));
        assertEquals(0,j.nbObjets(Objet.CleEau));
        assertEquals(0,j.nbObjets(Objet.CleTerre));
        assertEquals(0,j.nbObjets(Objet.Helicoptere));
        assertEquals(0,j.nbObjets(Objet.SacDeSable));
    }

    @Test
    public void testSetter(){
        int x = 2; int y = 3;
        Coord c = new Coord (x,y);
        Joueur j = new Joueur(1,"Toto",c,Color.BLUE);
        j.setId(4);
        j.setNom("Titi");
        assertEquals(4,j.id());
        assertEquals("Titi",j.nom());
    }

    @Test
    public void testPerdGagneCle(){
        int x = 2; int y = 3;
        Coord c = new Coord (x,y);
        Joueur j = new Joueur(1,"Toto",c,Color.BLUE);
        j.perd(Objet.CleFeu,1);
        assertEquals(0,j.nbObjets(Objet.CleFeu));
        j.gagne(Objet.CleFeu);
        j.gagne(Objet.CleFeu);
        assertEquals(2,j.nbObjets(Objet.CleFeu));
        j.perd(Objet.CleFeu,1);
        assertEquals(1,j.nbObjets(Objet.CleFeu));
    }

    @Test
    public void testDeplace(){
        int x1 = 2; int y1 = 3;
        Coord c1 = new Coord (x1,y1);
        Joueur j = new Joueur(1,"Toto",c1,Color.BLUE);
        int x2 = 1; int y2 = 4;
        Coord c2 = new Coord (x2,y2);
        j.deplace(c2);
        assertEquals(1,j.x());
        assertEquals(4,j.y());
    }

    @Test
    public  void testAction(){
        int x = 2; int y = 3;
        Coord c = new Coord (x,y);
        Joueur j = new Joueur(1,"Toto",c,Color.BLUE);
        j.setNbActions(1);
        assertEquals(1,j.getNbActions());
        j.effectueAction();
        assertEquals(0,j.getNbActions());
        j.effectueAction();
        assertEquals(0,j.getNbActions());
        j.setNbActions(-2);
        assertEquals(0,j.getNbActions());
    }

    @Test
    public void TestClePossede(){
        int x = 2; int y = 3;
        Coord c = new Coord (x,y);
        Joueur j = new Joueur(1,"Toto",c,Color.BLUE);
        j.gagne(Objet.CleFeu);
        j.gagne(Objet.CleAir);
        j.gagne(Objet.CleAir);
        Vector<Objet> v = j.clePossede();
        assertEquals(v.get(0),Objet.CleAir);
        assertEquals(v.get(1),Objet.CleFeu);
        assertEquals(v.size(),2);
    }
}
