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
	private final Color PATH_COLOR = Color.blue;
	
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
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public int calculateFCost(GridNode endNode) {
		return this.gCost + manhattanDistanceTo(endNode);
	}
	
	public int calculateHCost(GridNode endNode) {
		return manhattanDistanceTo(endNode);
	}
	
	public boolean isDefault() {
		return this.color == DEFAULT_COLOR;
	}
	
	public void setDefault() {
		this.color = DEFAULT_COLOR;
	}
	
	public boolean isStart() {
		return this.color == START_COLOR;
	}
	
	public void setStart() {
		this.color = START_COLOR;
	}
	
	public boolean isEnd() {
		return this.color == END_COLOR;
	}
	
	public void setEnd() {
		this.color = END_COLOR;
	}
	
	public boolean isObstacle() {
		return this.color == OBSTACLE_COLOR;
	}
	
	public void setObstacle() {
		this.color = OBSTACLE_COLOR;
	}
	
	public boolean isPath() {
		return this.color == PATH_COLOR;
	}
	
	public void setPath() {
		this.color = PATH_COLOR;
	}
	
	public int manhattanDistanceTo(GridNode endNode) {
		return Math.abs(this.x - endNode.getX()) + Math.abs(this.y - endNode.getY());
	}
	
}
