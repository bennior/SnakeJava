package main;

import javax.swing.JFrame;

public class Frame extends JFrame{
		
	public Frame(Panel panel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		pack();
		setResizable(false);
		setTitle("Snake");
		setVisible(true);
		setLocationRelativeTo(null);	
	}
}