package pong;
import java.awt.Color;
import java.lang.Math;
import java.util.Random;

public class Ball 
{
	private int[] coordinates; // (x, y) coordinates of the ball
	private Color colour; // Colour of the ball in the form (Red, Green, Blue)
	private double speed; // Speed of the ball (Number of pixels it moves per frame)
	private double direction; // Direction of the ball in standard position in radians
	private int radius; // Radius of the ball in pixels
	private int resetSpeed = 6; // Speed to set the ball to when it is reset
	
	public Ball(int[] coordinates, Color colour, int speed, double direction, int radius)
	{
		this.coordinates = coordinates;
		this.colour = colour;
		this.speed = speed; 
		this.direction = direction;
		this.radius = radius;
	}
	
	// Reset ball to center position
	public void reset(int[] newCoordinates)
	{
		coordinates = newCoordinates;
		Random randomGenerator = new Random();
		direction = (randomGenerator.nextDouble()+0.5) * Math.PI/4; // Get random angle
		// If random number is even, reverse direction
		if (randomGenerator.nextInt() % 2 == 0)
		{
			direction = -direction;
		}
		
		// If random number is even, add pi to direction
		if (randomGenerator.nextInt() % 2 == 0)
		{
			direction = Math.PI + direction;
		}
		
		// Set the speed of the ball to the reset speed
		speed = resetSpeed;
	}
	
	// Bounce ball off a wall
	public void wallBounce()
	{
		direction = -direction; // Bounce off wall
		speed *= 1.1; // Increase ball speed
	}
	
	// Bounce ball of a paddle 
	public void paddleBounce()
	{
		direction = Math.PI - direction; // Bounce off paddle
		speed *= 1.1; // Increase ball speed
	}
	
	// Move ball to next position
	public void moveBall()
	{
		// Change position of ball according to x and y components of the speed
		coordinates[0] += (int)(speed * Math.cos(direction)); // Change x coordinate
		coordinates[1] += (int)(speed * Math.sin(direction)); // Change y coordinate
		
		// Set direction equal to direction % (2*PI) so direction is always between 0 and 2*PI
		direction %= (2 * Math.PI);
		if (direction < 0)
		{
			direction = 2*Math.PI + direction; // Convert direction to position angle
		}
	}
	
	// Getter method for coordinates
	public int[] getCoordinates() {
		return coordinates;
	}
	
	
	// Getter method for colour
	public Color getColour() {
		return colour;
	}

	// Getter method for direction
	public double getDirection() {
		return direction;
	}
}
