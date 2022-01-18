package pong;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Main
{
	// All variables are static becuase the main class is not being instantiated
	private static JFrame gameFrame;
	private static GamePanel gamePanel;
	private static Color backgroundColour = Color.black;
	private static int width = 1000;
	private static int height = 600;
	private static Text gameTitle, gameEndStatement;
	private static Button startButton, settingsButton, customizationButton, playAgainButton, mainMenuButton;
	private static int rightPaddleChange, leftPaddleChange = 0;
	private static int paddleChangePerFrame = 5;
	
	public static void setupFrame()
	{
		gameFrame = new JFrame("Pong");
		gameFrame.setSize(width, height+32); // Set size of frame
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When exit button is pressed, game exits
		gameFrame.setResizable(false); // Prevent frame from being resized
		gameFrame.setVisible(true); // Make frame visible
		gameFrame.setLayout(null); // Don't use built in layout manager
		gameFrame.getContentPane().setBackground(backgroundColour); // Set background color
	}
	
	// Render the main menu
	public static void renderMainMenu()
	{
		// Clear all items from screen
		clearAll();
		
		// Create game title text and add to frame
		gameTitle = new Text(
			"Pong", 
			Color.white,
			50
		);
		gameFrame.add(gameTitle);
		
		// Set coordinates and dimensions of game title
		gameTitle.setBounds(
			gameFrame.getWidth()/2 - gameFrame.getWidth()/4, 
			0, 
			gameFrame.getWidth()/2,
			gameFrame.getHeight()/4
		);
		
		// Create start game button
		startButton = new Button(
			"Start Game",
			Color.black,
			Color.white,
			40
		);
		gameFrame.add(startButton);
		
		// Set coordinates and dimensions of start game button
		startButton.setBounds(
			gameFrame.getWidth()/2 - gameFrame.getWidth()/6, 
			gameFrame.getHeight()/4, 
			gameFrame.getWidth()/3,
			gameFrame.getHeight()/6
		);
		// Add method to be called when start button is clicked
		startButton.addActionListener(e -> renderGame());
		
		
		// Create settings game button
		settingsButton = new Button(
			"Settings",
			Color.black,
			Color.white,
			40
		);
		gameFrame.add(settingsButton);
		
		// Set coordinates and dimensions of settings button
		settingsButton.setBounds(
			gameFrame.getWidth()/2 - gameFrame.getWidth()/6, 
			2*gameFrame.getHeight()/4, 
			gameFrame.getWidth()/3,
			gameFrame.getHeight()/6
		);
		// Add method to be called when settings button is clicked
		settingsButton.addActionListener(e -> renderSettingsMenu());
		
		
		// Create customization button
		customizationButton = new Button(
			"Customization",
			Color.black,
			Color.white,
			40
		);
		gameFrame.add(customizationButton);
		
		// Set coordinates and dimensions of customization button
		customizationButton.setBounds(
			gameFrame.getWidth()/2 - gameFrame.getWidth()/6, 
			3*gameFrame.getHeight()/4, 
			gameFrame.getWidth()/3,
			gameFrame.getHeight()/6
		);
		// Add method to be called when customization button is clicked
		customizationButton.addActionListener(e -> renderCustomizationMenu());
	}
	
	public static void renderGame()
	{
		// Clear screen
		clearAll();
		// Create game panel object and add to game frame
		gamePanel = new GamePanel();
		gameFrame.add(gamePanel);
		
		// Set boundaries of gamepanel object
		gamePanel.setBounds(0, 0, gameFrame.getWidth(), gameFrame.getHeight());
		
		// Set focus to panel so keyboard input can be used
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
	}
	
	public static void renderSettingsMenu()
	{
		
	}
	
	public static void renderCustomizationMenu()
	{
		
	}
	
	public static void clearAll()
	{
		// Clear screen and fill with background color
		gameFrame.getContentPane().removeAll();
		gameFrame.repaint();
	}
	
	public static void renderPlayAgain(String statement)
	{
		// Create statement to display at end of game and add to frame
		gameEndStatement = new Text(
				statement,
				Color.white,
				50
		);
		gameFrame.add(gameEndStatement);
		
		// Set coordinates and dimensions of the end of game statement
		gameEndStatement.setBounds(
			0, 
			0, 
			gameFrame.getWidth(),
			gameFrame.getHeight()/4
		);
		
		// Create play again button and add to frame
		playAgainButton = new Button(
			"Play Again",
			Color.black,
			Color.white,
			40
		);
		gameFrame.add(playAgainButton);
		
		// Set coordinates and dimensions of play again button
		playAgainButton.setBounds(
			gameFrame.getWidth()/2 - gameFrame.getWidth()/6, 
			gameFrame.getHeight()/4, 
			gameFrame.getWidth()/3,
			gameFrame.getHeight()/6
		);
		// Add method to be called when play again button is clicked
		playAgainButton.addActionListener(e -> renderGame());
		
		// Create return to main menu button and add to frame
		mainMenuButton = new Button(
			"Main Menu",
			Color.black,
			Color.white,
			40
		);
		gameFrame.add(mainMenuButton);
		
		// Set coordinates and dimensions of main menu button
		mainMenuButton.setBounds(
			gameFrame.getWidth()/2 - gameFrame.getWidth()/6, 
			2*gameFrame.getHeight()/4, 
			gameFrame.getWidth()/3,
			gameFrame.getHeight()/6
		);
		
		// Add method to be called when main menu button is clicked
		mainMenuButton.addActionListener(e -> renderMainMenu());
	}

	public static void main(String[] args) 
	{
		setupFrame();
		renderMainMenu();
	}
}