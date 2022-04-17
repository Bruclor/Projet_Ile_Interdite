package Tests;
import static org.junit.Assert.*;
import org.junit.Test;

import Modeles.Coord;

import java.util.Vector;

public class TestCoord {
    @Test
    public void testConstCoord(){
        int x = 2;
        int y = 3;
        Coord c = new Coord(x,y);
        assertEquals(x,c.x());
        assertEquals(y,c.y());
    }

    @Test
    public void testSetterXY(){
        int x = 2;
        int y = 3;
        Coord c = new Coord(x,y);
        c.set_x(1);
        c.set_y(4);
        assertEquals(1,c.x());
        assertEquals(4,c.y());
    }

    @Test
    public void testEqual(){
        int x = 2;
        int y = 3;
        Coord c1 = new Coord(x,y);
        Coord c2 = new Coord(x,y);
        assertTrue(c1.equals(c2));
    }

    @Test
    public void testAdjacent(){
        Coord c1 = new Coord(0,0);
        Vector<Coord> adj1 = c1.adjacents(0,6);
        Coord c2 = new Coord(1,0);
        Coord c3 = new Coord(0,1);
        assertTrue(adj1.get(0).equals(c2));
        assertTrue(adj1.get(1).equals(c3));

        Coord c4 = new Coord(1,1);
        Vector<Coord> adj2 = c4.adjacents(0,6);
        Coord c5 = new Coord(2,1);
        Coord c6 = new Coord(1,2);
        assertTrue(adj2.get(0).equals(c5));
        assertTrue(adj2.get(1).equals(c3));
        assertTrue(adj2.get(2).equals(c6));
        assertTrue(adj2.get(3).equals(c2));
    }
}
