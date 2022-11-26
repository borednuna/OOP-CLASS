package id.its.simplemouseevent;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MousePanel extends JPanel implements MouseListener, MouseMotionListener {
	private int areaWidth;
	private int areaHeight;
	private String textStatus;
	private String textPosition;
	
	public MousePanel(int width, int height) {
		this.areaWidth = width;
		this.areaHeight = height;
		
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		
		this.textStatus = "Lakukan sesuatu menggunakan mouse...";
		this.textPosition = "Posisi kursor ada di ...";
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setFocusable(true);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(this.textPosition, 0, 10);
		g.drawString(this.textStatus, 0, this.areaHeight / 2);
	}
	
	public void mouseClicked(MouseEvent e) {
		this.textStatus = "Tombol mouse diklik pada posisi X: " + e.getX() +
				" Y: " + e.getY();
		repaint();
	}
	
	public void mousePressed(MouseEvent e) {
		this.textStatus = "Tombol mouse ditekan pada posisi X: " + e.getX() +
				" Y: " + e.getY();
		repaint();
	}
	
	public void mouseReleased(MouseEvent e) {
		this.textStatus = "Tombol mouse dilepas pada posisi X: " + e.getX() +
				" Y: " + e.getY();
		repaint();
	}
	
	public void mouseEntered(MouseEvent e) {
		this.textStatus = "Mouse memasuki area MousePanel";
		repaint();
	}
	
	public void mouseExited(MouseEvent e) {
		this.textStatus = "Mouse meninggalkan area MousePanel";
		repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
		this.textPosition = "Kursor berada di posisi x:" + e.getX() + 
				" y: " + e.getY() + " dragged";
		repaint();
	}
	
	public void mouseMoved(MouseEvent e) {
		this.textPosition = "Kursor berada di posisi x:" + e.getX() + 
				" y: " + e.getY() + " not dragged";
		repaint();
	}
}