package id.its.pbo.bouncingball;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class BallPanel extends JPanel {
	private static final int REFRESH_RATE = 60;
	private BallArea box;
	private int areaWidth;
	private int areaHeight;
	private ArrayList<Ball> ballArray = new ArrayList<Ball>();
	
	public BallPanel(int width, int height) {
		this.areaWidth = width;
		this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		
		box = new BallArea(0, 0, width, height, Color.BLACK, Color.WHITE);

		Random rand = new Random();
		int radius = 40;
		int x = rand.nextInt(width - radius * 2 - 20) + radius + 10;
		int y = rand.nextInt(height - radius * 2 - 20) + radius + 10;
		
		int speed = 1;
		int angleInDegree = rand.nextInt(360);
		ballArray.add(new Ball(x, y, radius, speed, angleInDegree, Color.BLUE));

		rand = new Random();
		radius = 40;
		x = rand.nextInt(width - radius * 2 - 20) + radius + 10;
		y = rand.nextInt(height - radius * 2 - 20) + radius + 10;
		
		speed = 1;
		angleInDegree = rand.nextInt(360);
		ballArray.add(new Ball(x, y, radius, speed, angleInDegree, Color.GRAY));

		rand = new Random();
		radius = 40;
		x = rand.nextInt(width - radius * 2 - 20) + radius + 10;
		y = rand.nextInt(height - radius * 2 - 20) + radius + 10;
		
		speed = 1;
		angleInDegree = rand.nextInt(360);
		ballArray.add(new Ball(x, y, radius, speed, angleInDegree, Color.MAGENTA));
		
		//untuk mendapatkan ukuran area latar belakang jika frame diresize
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				Dimension dim = c.getSize();
				areaWidth = dim.width;
				areaHeight = dim.height;
				box.set(0, 0, areaWidth, areaHeight);
			}
		});
		
		startThread();
	}
	
	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					// Iterate to check all ball to all other ball
					for (Ball ball : ballArray) {
						ball.collide(box);
						for (Ball ball2 : ballArray) {
							// Skips if both ball has the same pointer address
							if (ball == ball2) continue;
							ball.collideBalls(ball2);
						}
					}
					repaint();
					try {
						Thread.sleep(1000 / REFRESH_RATE);
					} catch (InterruptedException ex) {}
				}
			}
		};
		
		gameThread.start();
	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		box.draw(g);
		// Draw ball using iteration
		for (Ball ball : ballArray) {
			ball.draw(g);
		}
	}
}