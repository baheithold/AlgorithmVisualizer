package pathFindingAlgorithms;

import java.awt.Color;
import java.util.Random;

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
	
	public Boolean inBounds(int x, int y) {
		return x >= 0 && x < this.numGridCols && y >= 0 && y < this.numGridRows;
	}
	
	public void randomizeGrid() {
		int numberOfObstacles = generateRandomNumber(0, (this.numGridRows * this.numGridCols) / 2);
		while (numberOfObstacles > 0) {
			int x = generateRandomNumber(0, 39);
			int y = generateRandomNumber(0, 24);
			while (this.getNodeColor(x, y) != Color.lightGray) {
				x = generateRandomNumber(0, 39);
				y = generateRandomNumber(0, 24);
			}
			this.setNodeColor(x, y, Color.darkGray);
			numberOfObstacles--;
		}
		// pick random start node
		int xStart = generateRandomNumber(0, 39);
		int yStart = generateRandomNumber(0, 24);
		this.setNodeColor(xStart, yStart, Color.green);
		this.setStartNode(this.grid[xStart][yStart]);
		// pick random end node
		int xEnd = generateRandomNumber(0, 39);
		int yEnd = generateRandomNumber(0, 24);
		// make sure start and end nodes are not the same
		while (xStart == xEnd && yStart == yEnd) {
			xEnd = generateRandomNumber(0, 39);
			yEnd = generateRandomNumber(0, 24);
		}
		// set end node
		this.setEndNode(this.grid[xEnd][yEnd]);
		this.setNodeColor(xEnd, yEnd, Color.red);
	}
	
	private int generateRandomNumber(int low, int high) {
		Random rand = new Random();
		return rand.nextInt(high - low) + low;
	}
	
}
