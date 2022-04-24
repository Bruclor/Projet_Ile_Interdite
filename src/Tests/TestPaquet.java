package Tests;
import static org.junit.Assert.*;
import org.junit.Test;

import Modeles.Paquet;
import Modeles.Paquet.*;

import java.util.Optional;
import java.util.Vector;

public class TestPaquet {
    @Test
    public void testConstCarte() {
        Paquet<Integer> p = new Paquet<Integer>();
        assertEquals(0, p.getNbCartes());
        assertEquals(null, p.getNextPioche());
        assertEquals(null, p.getNextDefausse());
    }

    @Test
    public void TestAddCarte(){
        Integer e = 5;
        Paquet<Integer> p = new Paquet<Integer>();
        p.addCarte(e);
        assertEquals(1, p.getNbCartes());
        p.piocheUneCarte();
        p.removeCartePiochee();
        assertEquals(0, p.getNbCartes());
    }

    @Test
    public void TestPioche(){
        Integer e = 5;
        Paquet<Integer> p = new Paquet<Integer>();
        p.addCarte(e);
        assertEquals(e,p.piocheUneCarte());
    }

    @Test
    public void TestRemoveCarte(){
        Integer e = 5;
        Paquet<Integer> p = new Paquet<Integer>();
        p.addCarte(e);
        p.piocheUneCarte();
        p.removeCartePiochee();
        assertEquals(0, p.getNbCartes());
        assertEquals(null, p.getNextPioche());
    }

    @Test
    public void TestMelange(){
        Integer e = 5;
        Integer l = 6;
        Paquet<Integer> p = new Paquet<Integer>();
        p.addCarte(e);
        p.addCarte(l);
        p.melangePaquet();
        assertEquals(2, p.getNbCartes());
        assertEquals(e, p.piocheUneCarte());
    }
}
