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

	public Grid(PathFindingPanel panel, int rows, int cols) {
		this.panel = panel;
		this.numGridRows = rows;
		this.numGridCols = cols;
		this.grid = new GridNode[numGridCols][numGridRows];
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
	
	public Color getNodeColor(int x, int y) {
		return this.grid[x][y].getColor();
	}
	
	public void setNodeColor(int x, int y, Color color) {
		this.grid[x][y].setColor(color);
	}
	
}
