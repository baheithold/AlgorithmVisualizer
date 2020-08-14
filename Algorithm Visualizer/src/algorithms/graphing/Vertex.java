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
	
	private double DIAMETER = 20;
	
	private double xPosition, yPosition;

	public Vertex(double x, double y) {
		xPosition = x;
		yPosition = y;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Ellipse2D.Double circle = new Ellipse2D.Double(xPosition, yPosition, DIAMETER, DIAMETER);
		g2d.setColor(Color.black);
		g2d.fill(circle);
	}

}
