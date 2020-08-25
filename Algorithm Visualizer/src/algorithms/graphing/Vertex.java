package algorithms.graphing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * @author Brett Heithold
 *
 */
public class Vertex {
	
	// Color Constants
	private final Color DEFAULT_COLOR = Color.darkGray;
	private final Color START_COLOR = Color.green;
	private final Color END_COLOR = Color.red;
	private final Color SELECTED_COLOR = Color.blue;
	
	// Color
	private Color color;
	
	private double diameter;
	private double xPosition, yPosition;

	public Vertex(double xPos, double yPos, double diameter) {
		this.diameter = diameter;
		xPosition = xPos;
		yPosition = yPos;
		color = DEFAULT_COLOR;
	}
	
	public double xCentered() {
		return xPosition - (diameter / 2);
	}
	
	public double yCentered() {
		return yPosition - (diameter / 2);
	}
	
	public double radius() {
		return diameter / 2;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setDefault() {
		color = DEFAULT_COLOR;
	}
	
	public void setStart() {
		color = START_COLOR;
	}
	
	public void setEnd() {
		color = END_COLOR;
	}
	
	public void setSelected() {
		color = SELECTED_COLOR;
	}
	
	public boolean isDefault() {
		return color == DEFAULT_COLOR;
	}
	
	public boolean isStart() {
		return color == START_COLOR;
	}
	
	public boolean isEnd() {
		return color == END_COLOR;
	}
	
	public boolean isSelected() {
		return color == SELECTED_COLOR;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Ellipse2D.Double circle = new Ellipse2D.Double(xCentered(), yCentered(), diameter, diameter);
		g2d.setColor(color);
		g2d.fill(circle);
	}

}
