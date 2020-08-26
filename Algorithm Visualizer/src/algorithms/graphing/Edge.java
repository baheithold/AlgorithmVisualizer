package algorithms.graphing;

import java.awt.BasicStroke;
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
	private final Color SELECTED_COLOR = Color.magenta;
	
	// Edge Constants
	private final int DEFAULT_STROKE = 3;
	private final int SELECTED_STROKE = 5;

	private Vertex u;
	private Vertex v;
	private double weight;
	
	private Color color;
	private int stroke;
	
	public Edge(Vertex u, Vertex v) {
		this.u = u;
		this.v = v;
		this.weight = 0.0;
		color = DEFAULT_COLOR;
		stroke = DEFAULT_STROKE;
	}
	
	public Edge(Vertex u, Vertex v, double w) {
		this.u = u;
		this.v = v;
		this.weight = w;
		color = DEFAULT_COLOR;
		stroke = DEFAULT_STROKE;
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
	
	public void setDefault() {
		color = DEFAULT_COLOR;
		stroke = DEFAULT_STROKE;
	}
	
	public void setSelected() {
		color = SELECTED_COLOR;
		stroke = SELECTED_STROKE;
	}

	private double distanceBetweenVertices(Vertex u, Vertex v) {
		return Math.sqrt(Math.pow(v.yCentered() - u.yCentered(), 2) + Math.pow(v.xCentered() - u.xCentered(), 2));
	}
	
	public boolean containsPoint(double x, double y) {
		Vertex p = new Vertex(x, y, -1);
		return distanceBetweenVertices(u, p) + distanceBetweenVertices(v, p) == distanceBetweenVertices(u, v);
	}
	
	public boolean hasVertexAsEndpoint(Vertex z) {
		return z.getID() == u.getID() || z.getID() == v.getID();
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		// draw line from u to v
		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawLine((int)u.xCentered() + (int)u.radius(), (int)u.yCentered() + (int)u.radius(), (int)v.xCentered() + (int)u.radius(), (int)v.yCentered() + (int)u.radius());
		
		// reset stroke to 0
		g2d.setStroke(new BasicStroke(0));
	}
	
}
