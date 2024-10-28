package inputs;

import java.awt.event.KeyListener;

import game.Head;
import main.Panel;

import java.awt.event.KeyEvent;

public class KeyInputs implements KeyListener {


    @Override
    public void keyPressed(KeyEvent e) {
        switch(Panel.CURRENT_GAME_STATE) {
            case Panel.START_SCREEN: Panel.CURRENT_GAME_STATE = Panel.PLAYING;
                break;
            case Panel.PLAYING:
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT: if(Head.MOVEMENT != Head.RIGHT) Head.MOVEMENT = Head.LEFT;
                        break;
                    case KeyEvent.VK_RIGHT: if(Head.MOVEMENT != Head.LEFT) Head.MOVEMENT = Head.RIGHT;
                        break;
                    case KeyEvent.VK_DOWN: if(Head.MOVEMENT != Head.UP) Head.MOVEMENT = Head.DOWN;
                        break;
                    case KeyEvent.VK_UP: if(Head.MOVEMENT != Head.DOWN) Head.MOVEMENT = Head.UP;
                        break;
                }
                break;
            case Panel.GAME_OVER: Panel.CURRENT_GAME_STATE = Panel.PLAYING; Panel.SCORE = 0;
                break;
        }
    }

    @Override 
    public void keyReleased(KeyEvent e) {

    }

    @Override 
    public void keyTyped(KeyEvent e) {

    }
}
