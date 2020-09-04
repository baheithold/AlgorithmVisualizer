package algorithms.graphing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * @author Brett Heithold
 *
 */
public class Weight extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final Color DEFAULT_COLOR = Color.darkGray;
	private final Color HIGHLIGHTED_COLOR = Color.magenta;
	private final int DEFAULT_FONT_SIZE = 15;
	
	private Edge parentEdge;
	private int value;
	private double xPosition;
	private double yPosition;
	private Color color;

	public Weight(Edge parent, int value, double xPosition, double yPosition) {
		this.parentEdge = parent;
		this.value = value;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public Edge getParentEdge() {
		return this.parentEdge;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public double xPosition() {
		return xPosition;
	}
	
	public double yPosition() {
		return yPosition;
	}
	
	public void setDefault() {
		color = DEFAULT_COLOR;
	}
	
	public boolean isSelected() {
		return color == HIGHLIGHTED_COLOR;
	}
	
	public void setSelected() {
		color = HIGHLIGHTED_COLOR;
	}
	
	public void move(double dx, double dy) {
		xPosition += dx;
		yPosition += dy;
	}
	
	public void print() {
		System.out.print(value);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.setFont(new Font("default", Font.BOLD, DEFAULT_FONT_SIZE));
		g2d.drawString(Integer.toString(value), (int)xPosition, (int)yPosition);
	}

}
