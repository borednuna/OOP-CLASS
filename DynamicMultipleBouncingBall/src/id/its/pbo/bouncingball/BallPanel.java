package id.its.pbo.bouncingball;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class BallPanel extends JPanel implements KeyListener {
	private static final int REFRESH_RATE = 60;
	private BallArea box;
	private int areaWidth;
	private int areaHeight;
	ArrayList<Ball> ballArray = new ArrayList<>();
	
	public BallPanel(int width, int height) {
		this.addKeyListener(this);
		this.setFocusable(true);

		this.areaWidth = width;
		this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		
		box = new BallArea(0, 0, width, height, Color.BLACK, Color.WHITE);
		ballArray.add(new Ball(width, height, "A")); // add ball A to the ball array
		
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
					// Nested iteration to check all the ball to all other ball
					for (Ball ball : ballArray) {
						ball.collide(box);
						for (Ball ball2 : ballArray) {
							// if ball & ball2 has the same pointer address, continue iteration
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
		// draw each ball using iteration
		for  (Ball ball : ballArray) {
			ball.draw(g);
			ball.drawStr(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// add ball to array everytime a key is pressed
		ballArray.add(new Ball(areaWidth, areaHeight, String.valueOf(e.getKeyChar())));
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}