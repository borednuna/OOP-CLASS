package id.its.simplekeyboardevent;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import java.util.List;

import javax.swing.JPanel;

public class KeyboardPanel extends JPanel implements KeyListener {
	private List<Key> keys;
	
	public KeyboardPanel(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
		
		this.keys = new ArrayList<Key>();
		
		this.keys.add(new Key(10, 10, 'k'));
		this.keys.add(new Key(120, 10, 'e'));
		this.keys.add(new Key(230, 10, 'l'));
		this.keys.add(new Key(340, 10, 'a'));
		this.keys.add(new Key(450, 10, 's'));
		this.keys.add(new Key(10, 120, 'p'));
		this.keys.add(new Key(120, 120, 'b'));
		this.keys.add(new Key(230, 120, 'o'));
		this.keys.add(new Key(340, 120, 'c'));
		
		this.addKeyListener(this);
		this.setFocusable(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Key key : this.keys) {
			key.render(g);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	//do nothing
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		for (Key k : this.keys) {
			if (k.isSymbolMatch(key)) {
				k.setPressed();
				repaint();
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		for (Key k : this.keys) {
			if (k.isSymbolMatch(key)) {
				k.setReleased();
				repaint();
			}
		}
	}
}