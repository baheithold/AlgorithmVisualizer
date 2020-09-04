package algorithms.graphing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

/**
 * @author Brett Heithold
 *
 */
public class Edge {
	
	// Edge Constants
	private final Color DEFAULT_COLOR = Color.darkGray;
	private final Color SELECTED_COLOR = Color.magenta;
	
	// Edge Constants
	private final int DEFAULT_EDGE_STROKE = 3;
	private final int HIGHLIGHTED_STROKE = 4;
	private final int EDGE_TO_WEIGHT_STROKE = 1;

	private Vertex u;
	private Vertex v;
	private Weight weight;
	private boolean isWeighted;
	private boolean isDirected;
	
	private Color color;
	private int stroke;
	
	public Edge(Vertex u, Vertex v) {
		this.u = u;
		this.v = v;
		// calculate x, y coordinate for weight
		int x = (int) ((u.xCentered() + v.xCentered()) / 2);
		int y = (int) ((u.yCentered() + v.yCentered()) / 2);
		this.weight = new Weight(this, 0, x, y);
		isWeighted = false;
		isDirected = false;
		color = DEFAULT_COLOR;
		stroke = DEFAULT_EDGE_STROKE;
	}
	
	public Edge(Vertex u, Vertex v, int w) {
		this.u = u;
		this.v = v;
		// calculate x, y coordinate for weight
		int x = (int) ((u.xCentered() + v.xCentered()) / 2);
		int y = (int) ((u.yCentered() + v.yCentered()) / 2);
		this.weight = new Weight(this, w, x, y);
		isWeighted = true;
		isDirected = false;
		color = DEFAULT_COLOR;
		stroke = DEFAULT_EDGE_STROKE;
	}
	
	public Vertex getU() {
		return u;
	}
	
	public Vertex getV() {
		return v;
	}
	
	public Weight getWeightObject() {
		return weight;
	}
	
	public int getWeightValue() {
		return weight.getValue();
	}
	
	public void setWeight(int w) {
		weight.setValue(w);
	}
	
	public boolean isWeighted() {
		return isWeighted;
	}
	
	public void setWeighted(boolean b) {
		isWeighted = b;
	}
	
	public boolean isDirected() {
		return isDirected;
	}
	
	public void setDirected(boolean b) {
		isDirected = b;
	}
	
	public void setDefault() {
		color = DEFAULT_COLOR;
		stroke = DEFAULT_EDGE_STROKE;
		weight.setDefault();
		u.setDefault();
		u.setSelected(false);
		v.setDefault();
		v.setSelected(false);
	}
	
	public void setSelected() {
		color = SELECTED_COLOR;
		stroke = HIGHLIGHTED_STROKE;
		weight.setSelected();
		u.setSelected(true);
		v.setSelected(true);
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
	
	public void makeEdgeLineTransparent() {
		color = new Color(0, 0, 0, 0.0f);
	}
	
	public void makeEdgeLineOpaque() {
		setDefault();
	}
	
	private void drawArrow(Graphics g, double x, double y) {
		// arrowhead transform
		AffineTransform tx = new AffineTransform();
		tx.setToIdentity();
		
		Polygon arrowhead = new Polygon();
		arrowhead.addPoint(0, 7);
		arrowhead.addPoint(-7, -7);
		arrowhead.addPoint(7, -7);
		
		// calculate angle
		double angle = Math.atan2(v.yCentered() - u.yCentered(), v.xCentered() - u.xCentered());
		tx.translate(x, y);
		tx.rotate((angle - Math.PI / 2d));
		
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setTransform(tx);
		g2d.fill(arrowhead);
		g2d.dispose();
	}
	
	public void drawLineToWeight(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		// calculate edge midpoint
		double midpointX = (v.xCentered() + u.xCentered() + u.radius()) / 2;
		double midpointY = (v.yCentered() + u.yCentered() + v.radius()) / 2;
		
		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(EDGE_TO_WEIGHT_STROKE));
		g2d.drawLine((int) midpointX, (int) midpointY, (int) weight.xPosition(), (int) weight.yPosition());

		// reset stroke to 0
		g2d.setStroke(new BasicStroke(0));
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		// get vertex edge components for U
		double theta = Math.atan2(v.yCentered() - u.yCentered(), v.xCentered() - u.xCentered());
		double uxComponent = u.xCentered() + u.radius() + ((u.radius() + 5) * Math.cos(theta));
		double uyComponent = u.yCentered() + u.radius() + ((u.radius() + 5) * Math.sin(theta));
		
		// get vertex edge components for V
		double vxComponent = v.xCentered() + v.radius() - ((v.radius() + 5) * Math.cos(theta));
		double vyComponent = v.yCentered() + v.radius() - ((v.radius() + 5) * Math.sin(theta));
		
		// draw line from u to v
		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawLine((int)uxComponent, (int)uyComponent, (int)vxComponent, (int)vyComponent);
		
		// if graph is directed, draw arrowhead
		if (isDirected) {
			drawArrow(g, vxComponent, vyComponent);
		}
		
		// reset stroke to 0
		g2d.setStroke(new BasicStroke(0));
		
		// if edge is weighted, draw weight
		if (isWeighted) {
			weight.draw(g);
			if (weight.isSelected()) {
				drawLineToWeight(g);
			}
		}
	}
}
