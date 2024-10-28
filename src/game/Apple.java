package game;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import main.Panel;

public class Apple {

    private Snake snake;
    private Random r;
    private int x, y;

    public Apple(Snake snake) {
        this.snake = snake;
        r = new Random();
        spawnApple();
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fill3DRect(x * Panel.DEFAULT_TILE_SIZE, y * Panel.DEFAULT_TILE_SIZE, Panel.DEFAULT_TILE_SIZE, Panel.DEFAULT_TILE_SIZE, true);
    }

    public void spawnApple() {
        x = r.nextInt(Panel.TILES_IN_WIDTH);
        y = r.nextInt(Panel.TILES_IN_HEIGHT);

        if(snake.head.getX() == x && snake.head.getY()== y) {
            spawnApple();
        }

        for(Tail t : snake.tails) {
            if(t.getX() == x && t.getY() == y) {
                spawnApple();
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
