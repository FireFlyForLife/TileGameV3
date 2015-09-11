package ui;

import javax.swing.JFrame;

import logic.GamePanel;

public class Setup extends JFrame {
	private GamePanel gamePanel;
	
	public Setup(String title, int width, int height){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(title);
		setSize(width, height);
		
		gamePanel = new GamePanel(Setup.this);
		
		MainMenuBar menuBar = new MainMenuBar(gamePanel, this);
		
		this.setJMenuBar(menuBar);
		add(gamePanel);
	}
	
	public static void main(String[] args) {
		Setup frame = new Setup("A Tile Game: Remake", 500, 500);
		frame.setVisible(true);
	}

}
