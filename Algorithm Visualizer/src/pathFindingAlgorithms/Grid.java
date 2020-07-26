package pathFindingAlgorithms;

import gui.PathFindingPanel;

/**
 * @author Brett Heithold
 *
 */
public class Grid {
	private PathFindingPanel panel;
	private int numGridRows;
	private int numGridCols;

	public Grid(PathFindingPanel panel, int rows, int cols) {
		this.panel = panel;
		this.numGridRows = rows;
		this.numGridCols = cols;
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
	
}
