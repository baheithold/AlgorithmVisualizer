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
	
	private double diameter;
	private double xPosition, yPosition;

	public Vertex(double xPos, double yPos, double diameter) {
		this.diameter = diameter;
		xPosition = xPos;
		yPosition = yPos;
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
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Ellipse2D.Double circle = new Ellipse2D.Double(xCentered(), yCentered(), diameter, diameter);
		g2d.setColor(Color.black);
		g2d.fill(circle);
	}

}
