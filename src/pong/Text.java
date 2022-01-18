package pong;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

// Class to represent text fields, inherits from JLabel class
public class Text extends JLabel
{
	public Text(String title, Color textColor, int fontSize)
	{
		// Create JLabel from parameters
		this.setText(title);
		
		// Align text in the center
		this.setHorizontalTextPosition(JLabel.CENTER);
		this.setVerticalTextPosition(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
		this.setForeground(textColor); // Set color of texts
		this.setFont(new Font("Blippo", Font.BOLD, fontSize)); // Set font of text
	}
}
