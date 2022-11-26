package id.its.pbo.bouncingball;

import java.awt.*;
import java.util.ArrayList;

public class Ball {
	float x, y;
	float speedX, speedY;
	float radius;
	private int colorPicker;
	private Color color;
	private ArrayList<Color> ballColors = new ArrayList<Color>();
	
	public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color) {
		this.x = x;
		this.y = y;
		this.speedX = (float)(speed *
		Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = (float)(-speed *
		(float)Math.sin(Math.toRadians(angleInDegree)));
		this.radius = radius;
		this.color = color;

		this.ballColors.add(Color.WHITE);
		this.ballColors.add(Color.CYAN);
		this.ballColors.add(Color.GRAY);
		this.ballColors.add(Color.GREEN);
		this.ballColors.add(Color.MAGENTA);
		this.ballColors.add(Color.YELLOW);

		this.colorPicker = 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 *
		radius), (int)(2 * radius));
	}
	
	public void collide(BallArea box) {
		float ballMinX = box.minX + radius;
		float ballMinY = box.minY + radius;
		float ballMaxX = box.maxX - radius;
		float ballMaxY = box.maxY - radius;
		
		x += speedX;
		y += speedY;
		
		if (x < ballMinX) {
			speedX = -speedX;
			x = ballMinX;
			
			this.color = ballColors.get(colorPicker);
			colorPicker = (colorPicker + 1) % 6;
		} else if (x > ballMaxX) {
			speedX = -speedX;
			x = ballMaxX;
			
			this.color = ballColors.get(colorPicker);
			colorPicker = (colorPicker + 1) % 6;
		}
		
		if (y < ballMinY) {
			speedY = -speedY;
			y = ballMinY;
			
			this.color = ballColors.get(colorPicker);
			colorPicker = (colorPicker + 1) % 6;
		} else if (y > ballMaxY) {
			speedY = -speedY;
			y = ballMaxY;
			
			this.color = ballColors.get(colorPicker);
			colorPicker = (colorPicker + 1) % 6;
		}
	}
}