package com.pbo.drawingtoolkit;

import java.awt.Dimension;

public class DrawingToolkit
{
	public static void main( String args[] )
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				DrawingFrame canvasGUI = new DrawingFrame();
				canvasGUI.setSize(new Dimension(1200, 800));
				canvasGUI.setVisible(true);
			}
		});
	}
}