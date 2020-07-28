package pathFindingAlgorithms;

import java.awt.Color;

/**
 * @author Brett Heithold
 *
 */
public class GridNode {
	private GridNode parent;
	private int xLocation;
	private int yLocation;
	private int gCost;
	private Color color;
	
	public GridNode(int x, int y) {
		this.parent = null;
		this.xLocation = x;
		this.yLocation = y;
		this.gCost = 0;
		this.color = Color.lightGray;
	}
	
	public GridNode getParent() {
		return this.parent;
	}
	
	public void setParent(GridNode p) {
		this.parent = p;
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
	
	public int calculateFCost(GridNode endNode) {
		return this.gCost + manhattanDistanceTo(endNode);
	}
	
	public int calculateHCost(GridNode endNode) {
		return manhattanDistanceTo(endNode);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	private int manhattanDistanceTo(GridNode endNode) {
		return Math.abs(this.xLocation - endNode.getXLocation()) + Math.abs(this.yLocation - endNode.getYLocation());
	}
	
}
