package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener
{
	private int width, height;
	private Timer timer;
	private Color backgroundColor;
	private Pong gameInstance = new Pong(); ////////// Add in fields that can be customized;
	private int paddleWidth = 20;// = gameInstance.getPaddleWidth();
	private int paddleHeight = 125;// = gameInstance.getPaddleHeight();
	private int ballRadius = 20;
	
	private int leftPaddleChange = 0;
	private int rightPaddleChange = 0;
	private int paddleChangePerFrame = 5;
	
	// Coordinates of ball and paddles
	private int[] leftPaddleCoordinates = gameInstance.getLeftPaddleCoordinates();
	private int[] rightPaddleCoordinates = gameInstance.getRightPaddleCoordinates();
	private int[] ballCoordinates = gameInstance.getBallCoordinates();
	
	// Variables to track game progress
	private int leftPlayerScore;
	private int rightPlayerScore;
	private int targetScore = 5;
	private boolean gameOver = false;	
	
	// Action listener variables
	private Action upAction = new UpAction();
	private Action downAction = new DownAction();
	private Action wAction = new WAction();
	private Action sAction = new SAction();
	
	
	public GamePanel()
	{
		this.width = 1000;
		this.height = 600;
		this.backgroundColor = Color.black;
		this.setBackground(backgroundColor);
		
		int millisecondsPerFrame = (int)((double)1/60*1000);
		timer = new Timer(millisecondsPerFrame, this);
		timer.start();
		
		// Set up action listeners
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
		this.getActionMap().put("upAction", upAction);
		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
		this.getActionMap().put("downAction", downAction);
		this.getInputMap().put(KeyStroke.getKeyStroke("W"), "sAction");
		this.getActionMap().put("wAction", sAction);
		this.getInputMap().put(KeyStroke.getKeyStroke("S"), "wAction");
		this.getActionMap().put("sAction", wAction);
	}
	
	// Method to draw items to screen
	public void paint(Graphics graphics)
	{	
		super.paint(graphics);
		// Make objects to stores 2D graphics by casting graphics parameter to Graphics2D object
		Graphics2D graphics2D = (Graphics2D)graphics;

		
		// Set color to draw
		graphics2D.setPaint(Color.white);
		
		
		// Draw left paddle
		graphics2D.fillRect(leftPaddleCoordinates[0], leftPaddleCoordinates[1], paddleWidth, paddleHeight);
		
		// Draw right paddle
		graphics2D.fillRect(rightPaddleCoordinates[0], rightPaddleCoordinates[1], paddleWidth, paddleHeight);
		
		
		// Draw ball
		graphics2D.fillOval(ballCoordinates[0], ballCoordinates[1], ballRadius, ballRadius);
		
		// Draw center line
		int y = 0;
		while (y<height)
		{
			graphics2D.fillRect(width/2-5, y, 10, 20);
			y += 30;
		}
		
		// Draw scores
		graphics2D.setFont(new Font("Blippo", Font.BOLD, 50));
		graphics2D.drawString(String.valueOf(leftPlayerScore), width/4, 50);
		graphics2D.drawString(String.valueOf(rightPlayerScore), 3*width/4, 50);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!gameOver)
		{
			// Step gameInstance to next game frame
			gameInstance.stepGame(leftPaddleChange, rightPaddleChange);;
			
			// Get coordinates for paddles
			leftPaddleCoordinates = gameInstance.getLeftPaddleCoordinates();
			rightPaddleCoordinates = gameInstance.getRightPaddleCoordinates();
			
			// Get coordinates for ball
			ballCoordinates = gameInstance.getBallCoordinates();
			
			// Get scores
			leftPlayerScore = gameInstance.getLeftPlayerScore();
			rightPlayerScore = gameInstance.getRightPlayerScore();
			
			// If one of the players reached the target score, end the game
			if (leftPlayerScore == targetScore || rightPlayerScore == targetScore)
			{
				gameOver = true;
			}

			this.repaint();
		}
		else
		{
			timer.stop();
			endGame();
		}
	}
	
	public void endGame()
	{
		// Remove all items from screen
		Main.clearAll();
		
		// Create statement to display when game ends
		String statement;
		if (leftPlayerScore > rightPlayerScore)
		{
			statement = "The left player won " + leftPlayerScore + " - " + rightPlayerScore;
		}
		else
		{
			statement = "The right player won " + rightPlayerScore + " - " + leftPlayerScore;
		}
		Main.renderPlayAgain(statement);
	}
	
	// Action classes
	public class UpAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			rightPaddleChange = -paddleChangePerFrame;
		}
	}
	
	public class DownAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			rightPaddleChange = paddleChangePerFrame;
		}
	}
	
	public class WAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			leftPaddleChange = -paddleChangePerFrame;
		}
	}
	
	public class SAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			leftPaddleChange = paddleChangePerFrame;
		}
	}	
}
