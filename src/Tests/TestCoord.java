package Tests;
import static org.junit.Assert.*;
import org.junit.Test;

import Modeles.Coord;

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
        Coord c = new Coord(x,y);
        Coord d = new Coord(x,y);
        assertTrue(c.equals(d));
    }
}
