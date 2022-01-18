package pong;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

// Class to represent a button, inherits from JButton class
public class Button extends JButton{
	public Button(String text, Color fontColor, Color backgroundColor, int fontSize)
	{
		this.setText(text); // Set text of button
		this.setForeground(fontColor); // Set font color
		this.setBackground(backgroundColor); // Set background color
		this.setFont(new Font("Blippo", Font.BOLD, fontSize)); // Set font size
	}
}
