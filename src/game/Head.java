package game;

import java.awt.Graphics;
import java.awt.Color;

import main.Panel;

public class Head {
    
    public static int MOVEMENT;
    public static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
    
    private int x, y;

    public Head() {
        x = 10; y = 5;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(204, 0, 0));
        g.fill3DRect(x * Panel.DEFAULT_TILE_SIZE, y * Panel.DEFAULT_TILE_SIZE, Panel.DEFAULT_TILE_SIZE, Panel.DEFAULT_TILE_SIZE, true);
    }
    
    public void update() {
        switch(MOVEMENT) {
            case LEFT: x -= 1;
            break;
            case RIGHT: x += 1;
            break;
            case UP: y -= 1;
            break;
            case DOWN: y += 1;
            break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
