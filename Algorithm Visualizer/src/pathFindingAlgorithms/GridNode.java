package pathFindingAlgorithms;

import java.awt.Color;

/**
 * @author Brett Heithold
 *
 */
public class GridNode {
	// Color Constants
	private final Color DEFAULT_COLOR = Color.lightGray;
	private final Color START_COLOR = Color.green;
	private final Color END_COLOR = Color.red;
	private final Color OBSTACLE_COLOR = Color.darkGray;
	
	private GridNode parent;
	private int x;
	private int y;
	private int gCost;
	private Color color;
	
	public GridNode(int x, int y) {
		this.parent = null;
		this.x = x;
		this.y = y;
		this.gCost = 0;
		this.color = DEFAULT_COLOR;
	}
	
	public GridNode getParent() {
		return this.parent;
	}
	
	public void setParent(GridNode p) {
		this.parent = p;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
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
	
	public Boolean isDefault() {
		return this.color == DEFAULT_COLOR;
	}
	
	public void setDefault() {
		this.color = DEFAULT_COLOR;
	}
	
	public Boolean isStart() {
		return this.color == START_COLOR;
	}
	
	public void setStart() {
		this.color = START_COLOR;
	}
	
	public Boolean isEnd() {
		return this.color == END_COLOR;
	}
	
	public void setEnd() {
		this.color = END_COLOR;
	}
	
	public Boolean isObstacle() {
		return this.color == OBSTACLE_COLOR;
	}
	
	public void setObstacle() {
		this.color = OBSTACLE_COLOR;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public int manhattanDistanceTo(GridNode endNode) {
		return Math.abs(this.x - endNode.getX()) + Math.abs(this.y - endNode.getY());
	}
	
}
