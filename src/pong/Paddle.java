package pong;

import java.awt.Color;

public class Paddle
{
	private int width; // Width of paddle
	private int height; // Height of paddle
	private int[] coordinates; // Coordinates of paddle in the form (x, y)
	private Color colour; // Colour of the paddle in the form (Red, Green, Blue)
	
	public Paddle(int width, int height, int[] coordinates, Color colour) 
	{
		this.width = width;
		this.height = height;
		this.coordinates = coordinates;
		this.colour = colour;
	}
	
	// Move paddle (- change is up, + change is down)
	public void movePaddle(int change)
	{
		// Change y-coordinate of paddle
		coordinates[1] += change;
	}
	
	// Getter method for width
	public int getWidth() 
	{
		return width;
	}
	
	// Setter method for width
	public void setWidth(int width) 
	{
		this.width = width;
	}
	
	// Getter method for height
	public int getHeight() 
	{
		return height;
	}
	
	// Setter method for height
	public void setHeight(int height) 
	{
		this.height = height;
	}
	
	// Getter method for coordinates
	public int[] getCoordinates() 
	{
		return coordinates;
	}
	
	// Setter method for coordinates
	public void setCoordinates(int[] coordinates) {
		this.coordinates = coordinates;
	}
	
	// Getter method for colour
	public Color getColour() {
		return colour;
	}
	
	// Setter method for colour
	public void setColour(Color colour) {
		this.colour = colour;
	}
}
