/**
 * 
 */
package pathFindingAlgorithms;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gui.PathFindingPanel;

/**
 * @author Brett Heithold
 *
 */
public class Dijkstra extends PathFindingAlgorithm implements Runnable {
	private SwingWorker<Void, Void> workerThread;
	
	private Grid grid;
	
	private Integer[][] distances;
	private Boolean[][] shortestPathSet;
	
	public Dijkstra(PathFindingPanel panel) {
		super(panel);
		grid = panel.getGrid();
		distances = new Integer[grid.getNumGridCols()][grid.getNumGridRows()];
		shortestPathSet = new Boolean[grid.getNumGridCols()][grid.getNumGridRows()];
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
				
				// set initialize distances, prev, and shortestPathSet matrices
				initializeMatrixTo(distances, Integer.MAX_VALUE);
				initializeMatrixTo(shortestPathSet, false);
				
				/*************** Dijkstra ***************/
		
				// Set start distance to 0
				distances[grid.getStartNode().getX()][grid.getStartNode().getY()] = 0;
				
				// Find shortest path for all vertices
				GridNode u = null;
				for (int i = 0; i < grid.getNumGridCols(); i++) {
					for (int j = 0; j < grid.getNumGridRows(); j++) {
						// get node with minimum distance and mark it as processed
						u = nodeWithMinDistance();
						if (u.getX() == grid.getEndNode().getX() && u.getY() == grid.getEndNode().getY()) {
							System.out.println("Path found!");
							return null;
						}
						shortestPathSet[u.getX()][u.getY()] = true;
						// update distances of vertices v that are adjacent (North, South, East, and West)
						// of u
						ArrayList<GridNode> neighbors = generateNeighborsList(u);
						for (GridNode neighbor : neighbors) {
							System.out.println("HELLO NEIGHBOR");
							// if neighbor isn't already in the shortest path set, update neighbor's distance from start
							if (!shortestPathSet[neighbor.getX()][neighbor.getY()]) {
								grid.getNode(neighbor.getX(), neighbor.getY()).setParent(u);
								distances[neighbor.getX()][neighbor.getY()] = distances[u.getX()][u.getY()] + 1;
							}
						}
					}
				}
				
				// Path does NOT exist
				System.out.println("Path does not exist!");
				publish();
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
	
	private <T> void initializeMatrixTo(T[][] matrix, T value) {
		for (int i = 0; i < grid.getNumGridCols(); i++) {
			for (int j = 0; j < grid.getNumGridRows(); j++) {
				matrix[i][j] = value;
			}
		}
	}
	
	private GridNode nodeWithMinDistance() {
		int currentMinimum = Integer.MAX_VALUE;
		GridNode currentMinimumNode = null;
		for (int i = 0; i < grid.getNumGridCols(); i++) {
			for (int j = 0; j < grid.getNumGridRows(); j++) {
				if (!shortestPathSet[i][j] && distances[i][j] <= currentMinimum) {
					currentMinimum = distances[i][j];
					currentMinimumNode = grid.getNode(i, j);
				}
			}
		}
		return currentMinimumNode;
	}
	
	private ArrayList<GridNode> generateNeighborsList(GridNode u) {
		ArrayList<GridNode> neighbors = new ArrayList<GridNode>();
		GridNode neighbor = generateNeighbor(u.getX(), u.getY() - 1);
		// if neighbor is inbounds and is not an obstacle node, add neighbor to neighbors list
		if (grid.inBounds(neighbor.getX(), neighbor.getY()) && !neighbor.isObstacle()) {
			neighbors.add(neighbor);
		}
		return neighbors;
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
