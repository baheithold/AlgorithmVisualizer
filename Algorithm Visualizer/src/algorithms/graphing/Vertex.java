package algorithms.graphing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * @author Brett Heithold
 *
 */
public class Vertex {
	
	// Vertex Diameter Constant
	private final double DIAMETER = 50.0;
	
	// Color Constants
	private final Color DEFAULT_COLOR = Color.darkGray;
	private final Color START_COLOR = Color.green;
	private final Color END_COLOR = Color.red;
	private final Color SELECTED_COLOR = Color.magenta;
	private final Color HIGHLIGHTED_COLOR = Color.blue;
	
	// Font Constants
	private final int DEFAULT_FONT_SIZE = 15;
	
	// Color
	private Color color;
	
	// Circle
	Ellipse2D.Double circle;
	
	private boolean isSelected;
	private double xPosition, yPosition;
	private int id;
	private boolean isVisited;

	public Vertex(double xPos, double yPos, int id) {
		xPosition = xPos;
		yPosition = yPos;
		color = DEFAULT_COLOR;
		isSelected = false;
		this.id = id;
		isVisited = false;
	}
	
	public void move(double dx, double dy) {
		xPosition += dx;
		yPosition += dy;
	}
	
	public double xCentered() {
		return xPosition - radius();
	}
	
	public double yCentered() {
		return yPosition - radius();
	}
	
	public double radius() {
		return DIAMETER / 2;
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
	
	public boolean isDefault() {
		return color == DEFAULT_COLOR;
	}
	
	public boolean isStart() {
		return color == START_COLOR;
	}
	
	public boolean isEnd() {
		return color == END_COLOR;
	}
	
	public boolean containsCoordinates(double x, double y) {
		return circle.contains(x, y);
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean b) {
		isSelected = b;
	}
	
	public void setHighlighted() {
		color = HIGHLIGHTED_COLOR;
	}
	
	public int getID() {
		return id;
	}
	
	public boolean isVisited() {
		return isVisited;
	}
	
	public void setVisited(boolean b) {
		isVisited = b;
	}
	
	public void print() {
		System.out.print("<" + xCentered() + ", " + yCentered() + " | " + id + ">");
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		circle = new Ellipse2D.Double(xCentered(), yCentered(), DIAMETER, DIAMETER);
		g2d.setColor(color);
		if (isSelected) {
			g2d.setColor(SELECTED_COLOR);
		}
		g2d.fill(circle);
		
		// draw id
		g2d.setColor(Color.white);
		g2d.setFont(new Font("default", Font.BOLD, DEFAULT_FONT_SIZE));
		g2d.drawString(Integer.toString(id), (int)xPosition - (id < 10 ? 3 : 7), (int)yPosition + 5);
	}

}
