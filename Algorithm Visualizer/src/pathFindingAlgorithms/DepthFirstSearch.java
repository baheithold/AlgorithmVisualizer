package pathFindingAlgorithms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gui.PathFindingPanel;

/**
 * @author Brett Heithold
 *
 */
public class DepthFirstSearch extends PathFindingAlgorithm implements Runnable {
	private SwingWorker<Void, Void> workerThread;
	
	// Grid
	private Grid grid;
	
	// Neighbors
	private ArrayList<GridNode> neighbors;
	private GridNode neighbor;
	
	// Discovery Matrix
	private boolean[][] discoveryMatrix;
	
	// stack
	private Stack<GridNode> stack;
	
	public DepthFirstSearch(PathFindingPanel panel) {
		super(panel);
		grid = panel.getGrid();
		neighbors = new ArrayList<GridNode>();
		discoveryMatrix = new boolean[grid.getNumGridCols()][grid.getNumGridRows()];
		stack = new Stack<GridNode>();
	}
	
	@Override
	public void run() {
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				// if start or end node is missing, return
				if (!verifyStartEndNodesExist()) {
					setRunning(false);
					return null;
				}
				
				// reset neighbors list and neighbor node
				resetNeighbors();
				
				// initialize discovery matrix
				initializeDiscoveryMatrix();
				
				/***** Iterative version of DFS *****/
				
				// push start node onto stack
				stack.push(grid.getStartNode());
				
				// while the stack is not empty
				GridNode v = null;
				while (!stack.isEmpty()) {
					v = stack.pop();
					
					if (!v.isStart() && !v.isEnd()) {
						v.setColor(Color.magenta);
						publish();
					}
					if (v.isEnd()) {
						System.out.println("Path found!");
						tracePath();
						clearDiscoveryMatrix();
						setRunning(false);
						return null;
					}
					
					if (!isDiscovered(v)) {
						setDiscovered(v);
						
						/***** Generate neighbors (North, South, East, and West) of v *****/
						
						// Determine if north neighbor is inbounds and not an obstacle
						// if inbounds and not an obstacle, add to neighbors list
						neighbor = generateNeighbor(v.getX(), v.getY() - 1);
						if (neighbor != null) neighbors.add(neighbor);
						// Determine if south neighbor is inbounds and not an obstacle
						// if inbounds and not an obstacle, add to neighbors list
						neighbor = generateNeighbor(v.getX(), v.getY() + 1);
						if (neighbor != null) neighbors.add(neighbor);
						// Determine if east neighbor is inbounds and not an obstacle
						// if inbounds and not an obstacle, add to neighbors list
						neighbor = generateNeighbor(v.getX() + 1, v.getY());
						if (neighbor != null) neighbors.add(neighbor);
						// Determine if west neighbor is inbounds and not an obstacle
						// if inbounds and not an obstacle, add to neighbors list
						neighbor = generateNeighbor(v.getX() - 1, v.getY());
						if (neighbor != null) neighbors.add(neighbor);
						Thread.sleep(10);
						
						// for each neighbor
						for (GridNode neighbor : neighbors) {
							if (!isDiscovered(neighbor)) {
								neighbor.setParent(v);
							}
							stack.push(neighbor);
						}
						neighbors.clear();
					}
					if (!v.isStart() && !v.isEnd()) {
						v.setColor(Color.cyan);
						publish();
					}
					Thread.sleep(10);
				}
				
				System.out.println("Path does not exist!");
				publish();
				resetParents();
				setRunning(false);
				return null;
			}
			
			@Override
			protected void process(List<Void> chunks) {
				panel.repaint();
			}
			
		};
		
		workerThread.execute();
	}

	private void resetNeighbors() {
		neighbors.clear();
		neighbor = null;
	}
	
	private GridNode generateNeighbor(int x, int y) {
		if (!grid.inBounds(x, y)) return null;
		GridNode result = grid.getNode(x, y);
		if (result.isObstacle()) return null;
		// Determine if neighbor is inbounds and not an obstacle
		// if inbounds and not an obstacle, add to neighbors list
		if (!result.isStart() && !result.isEnd()) {
			result.setColor(Color.pink);
		}
		return result;
	}
	
	private void initializeDiscoveryMatrix() {
		clearDiscoveryMatrix();
	}
	
	private void clearDiscoveryMatrix() {
		for (int i = 0; i < grid.getNumGridCols(); i++) {
			for (int j = 0; j < grid.getNumGridRows(); j++) {
				discoveryMatrix[i][j] = false;
			}
		}
	}
	
	private void setDiscovered(GridNode node) {
		int x = node.getX();
		int y = node.getY();
		discoveryMatrix[x][y] = true;
		if (!node.isStart() && !node.isEnd()) {
			node.setColor(Color.cyan);
		}
	}
	
	private boolean isDiscovered(GridNode node) {
		int x = node.getX();
		int y = node.getY();
		return discoveryMatrix[x][y];
	}
	
	@Override
	public void runPathFindingAlgorithm() {
		setRunning(true);
		SwingUtilities.invokeLater(this);
	}

	@Override
	public void killPathFindingAlgorithm() {
		setRunning(false);
		workerThread.cancel(true);
	}

}
