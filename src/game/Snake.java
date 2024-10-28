package game;

import java.util.ArrayList;
import java.awt.Graphics;

public class Snake {

    public Head head;
    public ArrayList<Tail> tails;

    public Snake() {
        head = new Head();
        tails = new ArrayList<Tail>(400);        tails.add(new Tail(head.getX(), head.getY() - 1));
        tails.add(new Tail(head.getX(), head.getY() - 2));
        tails.add(new Tail(head.getX(), head.getY() - 3));
    }

    public void draw(Graphics g) {
        head.draw(g);
        for(int i = 0; i < tails.size(); i++) {
            Tail t = tails.get(i);
            t.draw(g);
        }
    }

    public void update() {
        for(int i = tails.size() - 1; i >= 1; i--) {
            Tail prev = tails.get(i - 1);
            tails.get(i).update(prev.getX(), prev.getY());
        }
        tails.get(0).update(head.getX(), head.getY());
        head.update();
    }

    public void addTail(int appleX, int appleY) {
            tails.add(new Tail(appleX, appleY));
    }

}
