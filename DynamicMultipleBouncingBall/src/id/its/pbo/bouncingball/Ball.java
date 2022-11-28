package id.its.pbo.bouncingball;

import java.awt.*;
import java.util.Random;

public class Ball {
	float x, y;
	float speedX, speedY;
	float radius;
	private Color color;
	Random rand;
	float angleInDegree;
	String str;
	
	public Ball(int width, int height, String str) {
		Random rand = new Random();
		float speed = 2;
		this.radius = 40;
		this.angleInDegree = rand.nextInt(360);

		this.x = rand.nextInt((int) (width - radius * 2 - 20)) + radius + 10;
		this.y = rand.nextInt((int) (height - radius * 2 - 20)) + radius + 10;

		this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = (float)(-speed *(float)Math.sin(Math.toRadians(angleInDegree)));

		this.color = Color.DARK_GRAY;
		this.str = str;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
	}

	public void drawStr(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString(str, (int)x, (int)y);
	}
	
	public void collide(BallArea box) {
		float ballMinX = box.minX + radius;
		float ballMinY = box.minY + radius;
		float ballMaxX = box.maxX - radius;
		float ballMaxY = box.maxY - radius;

		if (x <= ballMinX) {
			speedX = -speedX;
			x = ballMinX;
		} else if (x >= ballMaxX) {
			speedX = -speedX;
			x = ballMaxX;
		}
		
		if (y <= ballMinY) {
			speedY = -speedY;
			y = ballMinX;
		} else if (y >= ballMaxY) {
			speedY = -speedY;
			y = ballMaxY;
		}

		x += speedX;
		y += speedY;
	}

	public void collideBalls(Ball otherBall) {
		// operate with radius so we get the rim of the ball
		float otherMinX = otherBall.x - otherBall.radius;
		float otherMaxX = otherBall.x + otherBall.radius;
		float otherMinY = otherBall.y - otherBall.radius;
		float otherMaxY = otherBall.y + otherBall.radius;

		// check if the other balls rim is touching the balls rim, for further understanding please draw it yourself:) Goodluck
		if (x + radius >= otherMinX && x - radius <= otherMaxX && y + radius >= otherMinY && y - radius <= otherMaxY) {
			speedX = -speedX;
			speedY = -speedY;

			otherBall.speedX = -otherBall.speedX;
			otherBall.speedY = -otherBall.speedY;
		}

		x += speedX;
		y += speedY;

		otherBall.x += otherBall.speedX;
		otherBall.y += otherBall.speedY;
	}
}