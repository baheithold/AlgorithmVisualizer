package pathFindingAlgorithms;

import java.awt.Color;

import gui.PathFindingPanel;

/**
 * @author Brett Heithold
 *
 */
public class Grid {
	private PathFindingPanel panel;
	private int numGridRows;
	private int numGridCols;
	private GridNode[][] grid;
	private GridNode startNode;
	private GridNode endNode;

	public Grid(PathFindingPanel panel, int rows, int cols) {
		this.panel = panel;
		this.numGridRows = rows;
		this.numGridCols = cols;
		this.grid = new GridNode[numGridCols][numGridRows];
		startNode = null;
		endNode = null;
		// initialize grid matrix
		for (int i = 0; i < numGridCols; i++) {
			for (int j = 0; j < numGridRows; j++) {
				grid[i][j] = new GridNode(i, j);
			}
		}
	}
	
	public int getNumGridRows() {
		return this.numGridRows;
	}
	
	public void setNumGridRows(int n) {
		this.numGridRows = n;
	}
	
	public int getNumGridCols() {
		return this.numGridCols;
	}
	
	public void setNumGridCols(int n) {
		this.numGridCols = n;
	}
	
	public int numGridNodes() {
		return this.numGridRows * this.numGridCols;
	}
	
	public GridNode getNode(int x, int y) {
		return this.grid[x][y];
	}
	
	public boolean hasStartNode() {
		return this.startNode != null;
	}
	
	public GridNode getStartNode() {
		return this.startNode;
	}
	
	public void setStartNode(GridNode node) {
		this.startNode = node;
	}
	
	public boolean hasEndNode() {
		return this.endNode != null;
	}
	
	public GridNode getEndNode() {
		return this.endNode;
	}
	
	public void setEndNode(GridNode node) {
		this.endNode = node;
	}
	
	public Color getNodeColor(int x, int y) {
		return this.grid[x][y].getColor();
	}
	
	public void setNodeColor(int x, int y, Color color) {
		this.grid[x][y].setColor(color);
	}
	
}
