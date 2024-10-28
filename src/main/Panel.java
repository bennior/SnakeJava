package main;

import javax.swing.JPanel;

import game.Snake;
import game.Head;
import inputs.KeyInputs;
import game.Apple;
import game.CollisionHandler;

import java.awt.Graphics;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.awt.font.FontRenderContext;


public class Panel extends JPanel{
	
	public static final int DEFAULT_TILE_SIZE = 30;
	public static final int GAME_WIDTH = 600;
	public static final int GAME_HEIGHT = 600;
	public static final int TILES_IN_WIDTH = GAME_WIDTH / DEFAULT_TILE_SIZE;
	public static final int TILES_IN_HEIGHT = GAME_HEIGHT / DEFAULT_TILE_SIZE;

	public static final int START_SCREEN = 0;
    public static final int GAME_OVER = 1;
    public static final int PLAYING = 2;
	public static final int RESET = 3;
    public static int CURRENT_GAME_STATE = START_SCREEN;

	private KeyInputs inputs;
	private Snake snake;
	private Apple apple;
	private CollisionHandler handler;
	private Font gameFont;

	private int fontOffset = 1;
	private int index = 0;
	private String start = "PRESS ANY KEY TO START!", over = "GAME OVER!", score = "SCORE: ";
	private float strSize = 25f, ovrSize = 40f;

	public static int SCORE = 0;
	
	public Panel() {
		setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		setBackground(Color.BLACK);
		setOpaque(true);
		init();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		switch(CURRENT_GAME_STATE) {
			case START_SCREEN:
				g.setColor(Color.BLUE);
				g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

				//DRAW TEXT
				g.setColor(Color.WHITE);
				g.setFont(gameFont.deriveFont(Font.PLAIN, strSize));
				g.drawString(start, getFontX(start, strSize),(int) (GAME_HEIGHT - strSize) / 2);
				break;
			case RESET:
				break;
			case GAME_OVER:
				g.setColor(Color.BLUE);
				g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

				//DRAW TEXT
				g.setColor(Color.WHITE);
				g.setFont(gameFont.deriveFont(Font.PLAIN, ovrSize));
				g.drawString(over, getFontX(over, ovrSize),(int) (GAME_HEIGHT - ovrSize) / 2);

				g.setFont(gameFont.deriveFont(Font.PLAIN, 20f));
				g.drawString(score + Integer.toString(SCORE), getFontX(score + Integer.toString(SCORE), 20f),(int) (GAME_HEIGHT + 35) / 2);
				break; 
			case PLAYING:
				snake.draw(g);
				apple.draw(g);

				//DRAW TEXT
				g.setColor(Color.WHITE);
				g.setFont(gameFont.deriveFont(Font.PLAIN, 15f));
				g.drawString(score + Integer.toString(SCORE), 5, 20);
				break;
		}

	}
	
	
	public void update() {
		switch(CURRENT_GAME_STATE) {
			case START_SCREEN: cycleFont(); break;
			case RESET:
				reset();
				CURRENT_GAME_STATE = GAME_OVER;
			case GAME_OVER: cycleFont(); break;
			case PLAYING:
				snake.update();
				handler.update();
			break;
		}
	}
	
	private void init() {
		Head.MOVEMENT = Head.DOWN;
		inputs = new KeyInputs();
		addKeyListener(inputs);
		createFont();
		snake = new Snake();
		apple = new Apple(snake);
		handler = new CollisionHandler(snake, apple);
	}

	private void reset() {
		Head.MOVEMENT = Head.DOWN;
		snake = new Snake();
		apple = new Apple(snake);
		handler = new CollisionHandler(snake, apple);
	}

	private void createFont() {
		try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream("src/res/prstartk.ttf"));
			gameFont = Font.createFont(Font.TRUETYPE_FONT, stream);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void cycleFont() {
		if(index < GameLoop.UPS_SET) {
			index++;
		}else {
			index = 0;
			fontOffset *= -1;
			strSize += fontOffset; ovrSize += fontOffset;
		}
	}
	
	private int getFontX(String text, float size) {
		AffineTransform af = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(af, true, true);     
		
		return (int)(GAME_WIDTH - gameFont.deriveFont(Font.PLAIN, size).getStringBounds(text, frc).getWidth()) / 2;
	}
}
