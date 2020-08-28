package algorithms.graphing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author Brett Heithold
 *
 */
public class Edge {
	
	// Edge Constants
	private final Color DEFAULT_COLOR = Color.darkGray;
	private final Color SELECTED_COLOR = Color.magenta;
	
	// Edge Weight Constants
	private final Color DEFAULT_TEXT_COLOR = Color.darkGray;
	private final int DEFAULT_FONT_SIZE = 15;
	
	// Edge Constants
	private final int DEFAULT_STROKE = 3;

	private Vertex u;
	private Vertex v;
	private int weight;
	private boolean isWeighted;
	
	private Color color;
	private int stroke;
	
	
	public Edge(Vertex u, Vertex v) {
		this.u = u;
		this.v = v;
		this.weight = 0;
		isWeighted = false;
		color = DEFAULT_COLOR;
		stroke = DEFAULT_STROKE;
	}
	
	public Edge(Vertex u, Vertex v, int w) {
		this.u = u;
		this.v = v;
		this.weight = w;
		isWeighted = true;
		color = DEFAULT_COLOR;
		stroke = DEFAULT_STROKE;
	}
	
	public Vertex getU() {
		return u;
	}
	
	public Vertex getV() {
		return v;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int w) {
		weight = w;
	}
	
	public boolean isWeighted() {
		return isWeighted;
	}
	
	public void setWeighted(boolean b) {
		isWeighted = b;
		weight = 0;
	}
	
	public void setDefault() {
		color = DEFAULT_COLOR;
		stroke = DEFAULT_STROKE;
	}
	
	public void setSelected() {
		color = SELECTED_COLOR;
	}

	private double distanceBetweenVertices(Vertex u, Vertex v) {
		return Math.sqrt(Math.pow(v.yCentered() - u.yCentered(), 2) + Math.pow(v.xCentered() - u.xCentered(), 2));
	}
	
	public boolean containsPoint(double x, double y) {
		Vertex p = new Vertex(x, y, -1);
		return distanceBetweenVertices(u, p) + distanceBetweenVertices(v, p) - distanceBetweenVertices(u, v) < 0.2;
	}
	
	public boolean hasVertexAsEndpoint(Vertex z) {
		return z.getID() == u.getID() || z.getID() == v.getID();
	}
	
	public void print() {
		System.out.print("[");
		u.print();
		System.out.print(", ");
		v.print();
		System.out.print(", " + weight + "]");
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		// draw line from u to v
		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawLine((int)u.xCentered() + (int)u.radius(), (int)u.yCentered() + (int)u.radius(), (int)v.xCentered() + (int)u.radius(), (int)v.yCentered() + (int)u.radius());
		
		// reset stroke to 0
		g2d.setStroke(new BasicStroke(0));
		
		// if edge is weighted, draw weight
		if (isWeighted) {
			int xPosition = (int) ((u.xCentered() + v.xCentered()) / 2);
			int yPosition = (int) ((u.yCentered() + v.yCentered()) / 2);
			g2d.setColor(DEFAULT_TEXT_COLOR);
			g2d.setFont(new Font("default", Font.BOLD, DEFAULT_FONT_SIZE));
			g2d.drawString(Integer.toString(weight), xPosition, yPosition);
		}
	}
	
}
