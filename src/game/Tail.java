package game;

import java.awt.Color;
import java.awt.Graphics;

import main.Panel;

public class Tail {

    private int x, y;

    public Tail(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(204, 0, 0));
        g.fill3DRect(x * Panel.DEFAULT_TILE_SIZE, y * Panel.DEFAULT_TILE_SIZE, Panel.DEFAULT_TILE_SIZE, Panel.DEFAULT_TILE_SIZE, true);
    }

    public void update(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
