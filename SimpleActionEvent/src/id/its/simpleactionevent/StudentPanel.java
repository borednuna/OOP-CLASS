package id.its.simpleactionevent;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StudentPanel extends JPanel implements ActionListener {
	public StudentPanel(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
		
		JButton button = new JButton("Click to show student identity");
		this.add(button);
		
		button.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "Hanun Shaka P - 5025211051");
	}
}