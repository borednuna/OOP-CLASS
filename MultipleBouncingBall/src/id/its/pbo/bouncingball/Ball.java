package id.its.pbo.bouncingball;

import java.awt.*;

public class Ball {
	float x, y;
	float speedX, speedY;
	float radius;
	private Color color;
	
	public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color) {
		this.x = x;
		this.y = y;
		this.speedX = (float)(speed *
		Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = (float)(-speed *
		(float)Math.sin(Math.toRadians(angleInDegree)));
		this.radius = radius;
		this.color = color;
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
		float otherMinX = otherBall.x - otherBall.radius;
		float otherMaxX = otherBall.x + otherBall.radius;
		float otherMinY = otherBall.y - otherBall.radius;
		float otherMaxY = otherBall.y + otherBall.radius;

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
