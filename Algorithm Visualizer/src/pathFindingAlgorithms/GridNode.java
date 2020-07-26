package pathFindingAlgorithms;

import java.awt.Color;

/**
 * @author Brett Heithold
 *
 */
public class GridNode {
	private final int NODE_SIZE = 10;
	private int xLocation;
	private int yLocation;
	private int gCost;
	private int hCost;
	private int fCost;
	private Color color;
	
	public GridNode(int x, int y) {
		this.xLocation = x;
		this.yLocation = y;
		this.color = Color.lightGray;
	}
	
	public int getNodeSize() {
		return NODE_SIZE;
	}
	
	public int getXLocation() {
		return this.xLocation;
	}
	
	public int getYLocation() {
		return this.yLocation;
	}
	
	public int gCost() {
		return this.gCost;
	}
	
	public void setGCost(int g) {
		this.gCost = g;
	}
	
	public int hCost() {
		return this.hCost;
	}
	
	public void setHCost(int h) {
		this.hCost = h;
	}
	
	public int fCost() {
		return this.fCost;
	}
	
	public void setFCost(int f) {
		this.fCost = f;
	}
	
	public int calculateFCost() {
		return this.gCost + this.hCost;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
}
