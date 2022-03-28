import java.util.Random;

public class Coord {

    private int x;
    private int y;

    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coord(int sup){
        Random random = new Random();
        this.x = random.nextInt(sup);
        this.y = random.nextInt(sup);
    }

    public int x(){return this.x;}
    public int y(){return this.y;}

    public void set_x(int x){this.x = x;}
    public void set_y(int y){this.y = y;}

}
