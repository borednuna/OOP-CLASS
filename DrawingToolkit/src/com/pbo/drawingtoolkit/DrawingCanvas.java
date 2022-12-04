package com.pbo.drawingtoolkit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawingCanvas extends JPanel {
	private ArrayList<DrawingObject> myShapes;
	private int currentShapeType;
	private DrawingObject currentShapeObject;
	private Color currentShapeColor;
	JLabel statusLabel;
	
	public DrawingCanvas(JLabel statusLabel){
		myShapes = new ArrayList<DrawingObject>();
		currentShapeType=0;
		currentShapeObject=null;
		currentShapeColor=Color.BLACK;
		this.statusLabel = statusLabel;
		setLayout(null);
		setBackground( Color.WHITE );
		add( statusLabel, BorderLayout.SOUTH );
		MouseHandler handler = new MouseHandler();
		addMouseListener( handler );
		addMouseMotionListener( handler );
	}
	
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		for ( int counter= myShapes.size()-1; counter>=0; counter-- )
		myShapes.get(counter).draw(g);
		if (currentShapeObject!=null)
		currentShapeObject.draw(g);
	}
	
	public void setCurrentShapeType(int type)
	{
		currentShapeType=type;
	}
	
	public void clearDrawing()
	{
		myShapes.clear();
		repaint();
	}
	
	private class MouseHandler extends MouseAdapter
	{
		public void mousePressed( MouseEvent event )
		{
			switch (currentShapeType)
			{
				case 0:
				currentShapeObject= new Line( event.getX(), event.getY(),
				event.getX(), event.getY(),currentShapeColor);
				break;
				
				case 1:
				currentShapeObject= new Circle( event.getX(), event.getY(),
				event.getX(), event.getY(), currentShapeColor, true);
				break;
				
				case 2:
				currentShapeObject = new Rectangle( event.getX(), event.getY(),
				event.getX(), event.getY(), currentShapeColor, true);
				break;
				
				case 3:
				for (DrawingObject obj : myShapes) {
					if (obj.checkCursorInside(event.getX(), event.getY())) {
						currentShapeObject = obj;
						break;
					}
				}
				currentShapeObject.setColor(Color.RED);
				repaint();
				break;
			}
		}
		
		public void mouseReleased( MouseEvent event )
		{
			switch (currentShapeType) {
			case 0:
			case 1:
			case 2:
				currentShapeObject.setX2(event.getX());
				currentShapeObject.setY2(event.getY());
				myShapes.add(currentShapeObject);
				System.out.println(myShapes.toArray());
				currentShapeObject=null;
				repaint();
				break;
			case 3:
				currentShapeObject = null;
				break;
			}
		}
		
		public void mouseMoved( MouseEvent event )
		{
			statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d",event.getX(),event.getY()));
		}
		
		public void mouseDragged( MouseEvent event )
		{
			switch(currentShapeType) {
			case 0:
			case 1:
			case 2:
				currentShapeObject.setX2(event.getX());
				currentShapeObject.setY2(event.getY());
				statusLabel.setText(String.format("Dragged Mouse Coordinates X: %d Y: %d",event.getX(),event.getY()));
				repaint();
				break;
			case 3:
				for (DrawingObject obj : myShapes) {
					if (obj.checkCursorInside(event.getX(), event.getY())) {
						int width = currentShapeObject.getX2() - currentShapeObject.getX1();
						int height = currentShapeObject.getY2() - currentShapeObject.getY1();
						currentShapeObject.setX1(event.getX() + width / 2);
						currentShapeObject.setY1(event.getY() + height / 2);
						currentShapeObject.setX2(event.getX() - width / 2);
						currentShapeObject.setY2(event.getY() - height / 2);
					}
				}
				statusLabel.setText(String.format("Dragged Mouse Coordinates X: %d Y: %d",event.getX(),event.getY()));
				repaint();
				break;
			}
		}
	}
}
