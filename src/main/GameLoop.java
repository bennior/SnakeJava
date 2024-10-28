package main;

import java.awt.Toolkit;

public class GameLoop implements Runnable {

	private Panel panel;
	private Frame frame;
	private Thread gameThread;
	public final static int FPS_SET = 10;
	public final static int UPS_SET = 10;
	
	public GameLoop() {
		panel = new Panel();
		frame = new Frame(panel);
		panel.requestFocus();
		startGameLoop();
	}

	public void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	private void render() {
		panel.repaint();
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void update() {
		panel.update();
	}
	
	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			
			if (deltaF >= 1) {
				render();
				// System.out.println("FPS: " + updates);
				deltaF--;
			}
			
			if (deltaU >= 1) {
				update();
				// System.out.println("UPS: " + updates);
				deltaU--;
			}
			
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
			}
		}
	}
    
}
