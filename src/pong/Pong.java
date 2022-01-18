package pong;

import java.awt.Color;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Pong 
{	
	// Colour constants
	private final Color BLACK = Color.black; // Array to represent the colour black
	private final Color WHITE = Color.white; // Array to represent the colour black
	
	// Game Setting variables
	private int height = 600; // Height of screen
	private int width = 1000; // Width of screen
	private Color backgroundColour = BLACK; // Background color is black by default
	private Color ballColour = WHITE; // Colour of ball by default
	private Color paddleColour = WHITE; // Colour of paddle by default
	private Color lineColour = WHITE; // Colour of lines by default
	private Color fontColour = WHITE; // Colour of text by default
	private Color buttonColour = WHITE; // Colour of buttons by default
	private Color buttonTextColour = WHITE; // Colour of text in buttons by default
	private int framesPerSecond = 30; // Game speed is 30 frames per second by default
	
	//Paddle variables
	// y position of both paddles starts half the height initially (Middle of screen)
	private int initialPaddleY = height / 2; 
	
	// x position of both paddles is 20 pixels from the edge of the screen
	public int leftPaddleX = 20; 
	public int rightPaddleX = width - 50; 
	
	// Initial coordinates of paddles
	private int[] leftPaddleCoordinates = {leftPaddleX, initialPaddleY};
	private int[] rightPaddleCoordinates = {rightPaddleX, initialPaddleY};
	
	// Width and height of paddles
	private int paddleWidth = 20;
	private int paddleHeight = 125;
	
	private int paddleSpeed = 8; // Number of pixels each paddle moves per frame
	
	// Ball variables
	private int ballSpeed = 10; // Number of pixels ball moves per frame
	private int ballRadius = 20;
	private int[] ballCoordinates = {width/2, height/2};
	
	// Create game variables
	Paddle leftPaddle, rightPaddle;
	Ball ball;
	boolean gameOver;
	int leftPlayerScore, rightPlayerScore;

	public Pong()
	{
		// Boolean to be set to true when the game is over
		gameOver = false;
		
		// Variables to track player scores
		leftPlayerScore = 0;
		rightPlayerScore = 0;
		
		// Left and right paddle objects
		leftPaddle = new Paddle(paddleWidth, paddleHeight, leftPaddleCoordinates, paddleColour);
		rightPaddle = new Paddle(paddleWidth, paddleHeight, rightPaddleCoordinates, paddleColour);
		
		// Create ball
		int[] ballCoordinates = {width/2, height/2};
		ball = new Ball(ballCoordinates, ballColour, ballSpeed, 0.0, ballRadius);
		ball.reset(ballCoordinates);
	}
	
	
	// Run one frame of the game
	public void stepGame(int leftPaddleChange, int rightPaddleChange)
	{
		leftPaddle.movePaddle(leftPaddleChange);
		rightPaddle.movePaddle(rightPaddleChange);
		ball.moveBall();
		
		leftPaddleCoordinates = leftPaddle.getCoordinates();
		rightPaddleCoordinates = rightPaddle.getCoordinates();
		ballCoordinates = ball.getCoordinates();
		
		// Check for ball collisions with walls
		if (ballCoordinates[1] - ballRadius <= 0 || ballCoordinates[1] + ballRadius >= height)
		{
			ball.wallBounce();
		}
		
		// Check for ball collisions with paddles
		// Left paddle
		if (ballCoordinates[0] - ballRadius <= leftPaddleCoordinates[0] && 
			ballCoordinates[0] - ballRadius >= leftPaddleCoordinates[0] - paddleWidth && 
			ballCoordinates[1] + ballRadius >= leftPaddleCoordinates[1] && 
			ballCoordinates[1] - ballRadius <= leftPaddleCoordinates[1] + paddleHeight &&
			ball.getDirection() > Math.PI/2 && ball.getDirection() < 3*Math.PI/2
			)
		{
			ball.paddleBounce();
		}
		
		// Right paddle
		if (ballCoordinates[0] + ballRadius >= rightPaddleCoordinates[0] && 
			ballCoordinates[0] + ballRadius <= rightPaddleCoordinates[0] + paddleWidth && 
			ballCoordinates[1] + ballRadius >= rightPaddleCoordinates[1] && 
			ballCoordinates[1] - ballRadius <= rightPaddleCoordinates[1] + paddleHeight &&
			(
				(ball.getDirection() > 0 && ball.getDirection() < Math.PI/2) || 
				(ball.getDirection() > 3*Math.PI/2 && ball.getDirection() < 2*Math.PI)
			)
		)
		{
			ball.paddleBounce();
		}
		
		// Check if left paddle hit walls
		if (leftPaddleCoordinates[1] < 0)
		{
			leftPaddleCoordinates[1] = 0;
		}
		
		if (leftPaddleCoordinates[1] + paddleHeight > height)
		{
			leftPaddleCoordinates[1] = height - paddleHeight;
		}
		
		// Check if right paddle hit walls
		if (rightPaddleCoordinates[1] < 0)
		{
			rightPaddleCoordinates[1] = 0;
		}
		
		if (rightPaddleCoordinates[1] + paddleHeight > height)
		{
			rightPaddleCoordinates[1] = height - paddleHeight;
		}
		
		// Check if player scored
		if (ballCoordinates[0] < 0)
		{
			rightPlayerScore++;
			int[] newballCoordinates = {width/2, height/2};
			ball.reset(newballCoordinates);
		}
		if (ballCoordinates[0] > width)
		{
			int[] newballCoordinates = {width/2, height/2};
			ball.reset(newballCoordinates);
			leftPlayerScore++;
		}
			
	}

	public int getPaddleWidth() 
	{
		return paddleWidth;
	}

	public int getPaddleHeight() 
	{
		return paddleHeight;
	}

	public int[] getLeftPaddleCoordinates() 
	{
		return leftPaddleCoordinates;
	}
	
	public int[] getRightPaddleCoordinates() 
	{
		return rightPaddleCoordinates;
	}
	
	public int[] getBallCoordinates() 
	{
		return ballCoordinates;
	}
	
	public int getLeftPlayerScore()
	{
		return leftPlayerScore;
	}
	
	public int getRightPlayerScore()
	{
		return rightPlayerScore;
	}
}
