package game;

import main.Panel;

public class CollisionHandler {
    
    private Snake snake;
    private Apple apple;
    
    public CollisionHandler(Snake snake, Apple apple) {
        this.snake = snake;
        this.apple = apple;
    }

    public void update() {
       //Check for Snake collision
       for(Tail t : snake.tails) {
        if(snake.head.getX() == t.getX() && snake.head.getY()== t.getY()) {
            Panel.CURRENT_GAME_STATE = Panel.RESET;
        }
       } 

       //Check for Wall
       if(snake.head.getX() < 0 || snake.head.getX() >= Panel.TILES_IN_WIDTH || snake.head.getY() < 0 || snake.head.getY() >= Panel.TILES_IN_HEIGHT) {
           Panel.CURRENT_GAME_STATE = Panel.RESET;
        }
        
        //Check for Apple collision
        if(snake.head.getX()== apple.getX() && snake.head.getY()== apple.getY()) {
             apple.spawnApple();
             snake.addTail(apple.getX(), apple.getY());
             Panel.SCORE++;
        }
    }
}
