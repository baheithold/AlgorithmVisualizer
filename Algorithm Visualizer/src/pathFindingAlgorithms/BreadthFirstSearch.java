/**
 * 
 */
package pathFindingAlgorithms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gui.PathFindingPanel;

/**
 * @author Brett Heithold
 *
 */
public class BreadthFirstSearch extends PathFindingAlgorithm implements Runnable {
	SwingWorker<Void, Void> workerThread;
	
	// Grid
	private Grid grid;
	
	// Neighbors
	private ArrayList<GridNode> neighbors;
	private GridNode northNeighbor;
	private GridNode southNeighbor;
	private GridNode eastNeighbor;
	private GridNode westNeighbor;
	
	// Discovery Matrix
	private boolean[][] discoveryMatrix;
	
	public BreadthFirstSearch(PathFindingPanel panel) {
		super(panel);
		grid = panel.getGrid();
		discoveryMatrix = new boolean[grid.getNumGridCols()][grid.getNumGridRows()];
		initializeDiscoveryMatrix();
	}
	
	// Queue
	private LinkedList<GridNode> queue;
	
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
				
				/***** Breadth First Search *****/
				queue = new LinkedList<GridNode>();
				neighbors = new ArrayList<GridNode>();
				
				// set start as discovered and add to queue
				setDiscovered(grid.getStartNode());
				queue.addLast(grid.getStartNode());
				
				// while queue is not empty
				GridNode v = null;
				while (!queue.isEmpty()) {
					v = queue.removeFirst();
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
					
					/***** Generate neighbors (North, South, East, and West) of v *****/
					
					// Determine if north neighbor is inbounds and not an obstacle
					// if inbounds and not an obstacle, add to neighbors list
					if (grid.inBounds(v.getX(), v.getY() - 1) && !grid.getNode(v.getX(), v.getY() - 1).isObstacle()) {
						northNeighbor = grid.getNode(v.getX(), v.getY() - 1);
						if (!northNeighbor.isStart() && !northNeighbor.isEnd()) {
							northNeighbor.setColor(Color.pink);
							publish();
						}
						neighbors.add(northNeighbor);
					}
					// Determine if south neighbor is inbounds and not an obstacle
					// if inbounds and not an obstacle, add to neighbors list
					if (grid.inBounds(v.getX(), v.getY() + 1) && !grid.getNode(v.getX(), v.getY() + 1).isObstacle()) {
						southNeighbor = grid.getNode(v.getX(), v.getY() + 1);
						if (!southNeighbor.isStart() && !southNeighbor.isEnd()) {
							southNeighbor.setColor(Color.pink);
							publish();
						}
						neighbors.add(southNeighbor);
					}
					// Determine if east neighbor is inbounds and not an obstacle
					// if inbounds and not an obstacle, add to neighbors list
					if (grid.inBounds(v.getX() + 1, v.getY()) && !grid.getNode(v.getX() + 1, v.getY()).isObstacle()) {
						eastNeighbor = grid.getNode(v.getX() + 1, v.getY());
						if (!eastNeighbor.isStart() && !eastNeighbor.isEnd()) {
							eastNeighbor.setColor(Color.pink);
							publish();
						}
						neighbors.add(eastNeighbor);
					}
					// Determine if west neighbor is inbounds and not an obstacle
					// if inbounds and not an obstacle, add to neighbors list
					if (grid.inBounds(v.getX() - 1, v.getY()) && !grid.getNode(v.getX() - 1, v.getY()).isObstacle()) {
						westNeighbor = grid.getNode(v.getX() - 1, v.getY());
						if (!westNeighbor.isStart() && !westNeighbor.isEnd()) {
							westNeighbor.setColor(Color.pink);
							publish();
						}
						neighbors.add(westNeighbor);
					}
					Thread.sleep(150);
					
					for (GridNode neighbor : neighbors) {
						if (!isDiscovered(neighbor)) {
							setDiscovered(neighbor);
							publish();
							Thread.sleep(10);
							neighbor.setParent(v);
							queue.addLast(neighbor);
						}
						else {
							if (!neighbor.isStart() && !neighbor.isEnd()) {
								neighbor.setColor(Color.cyan);
							}
						}
					}
					neighbors.clear();
					if (!v.isStart() && !v.isEnd()) {
						v.setColor(Color.cyan);
						publish();
					}
				}
				
				System.out.println("Path does not exist!");
				publish();
				clearDiscoveryMatrix();
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
