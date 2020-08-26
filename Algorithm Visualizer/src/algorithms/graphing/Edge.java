package algorithms.graphing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author Brett Heithold
 *
 */
public class Edge {
	
	// Color Constants
	private final Color DEFAULT_COLOR = Color.black;

	private Vertex u;
	private Vertex v;
	private double weight;
	
	private Color color;
	
	public Edge(Vertex u, Vertex v) {
		this.u = u;
		this.v = v;
		this.weight = 0.0;
		color = DEFAULT_COLOR;
	}
	
	public Edge(Vertex u, Vertex v, double w) {
		this.u = u;
		this.v = v;
		this.weight = w;
		color = DEFAULT_COLOR;
	}
	
	public Vertex getU() {
		return u;
	}
	
	public Vertex getV() {
		return v;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double w) {
		weight = w;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.drawLine((int)u.xCentered(), (int)u.yCentered(), (int)v.xCentered(), (int)v.yCentered());
	}
	
}
