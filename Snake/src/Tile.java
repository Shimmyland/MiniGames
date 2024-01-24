import java.util.Random;

public class Tile {
    private int x;
    private int y;

    // constructor
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // setters
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }


    // other methods
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
