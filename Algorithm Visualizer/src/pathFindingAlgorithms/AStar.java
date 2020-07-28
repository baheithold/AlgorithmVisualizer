package pathFindingAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gui.PathFindingPanel;

/**
 * @author Brett Heithold
 *
 */
public class AStar extends PathFindingAlgorithm implements Runnable {
	SwingWorker<Void, Void> workerThread;

	// Open/Closed
	private TreeSet<GridNode> openList;
	private HashMap<GridNode, Boolean> closedList;
	
	public AStar(PathFindingPanel panel) {
		super(panel);
		this.openList = new TreeSet<GridNode>();
		this.closedList = new HashMap<GridNode, Boolean>();
		startNode = grid.getStartNode();
		endNode = grid.getEndNode();
	}
	
	@Override
	public void run() {
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				// Add start node to open list
				openList.add(startNode);
				
				// while the open list is not empty
				while (!openList.isEmpty()) {
					GridNode qNode = findLowestFCost();
					openList.remove(qNode);
					
					/***** Generate successors *****/
					ArrayList<GridNode> neighbors = new ArrayList<GridNode>();
					
					// North Neighbor
					if (grid.inBounds(qNode.getX(), qNode.getY() - 1)) {
						GridNode neighbor = grid.getNode(qNode.getX(), qNode.getY() - 1);
						neighbors.add(neighbor);
					}
					// South Neighbor
					if (grid.inBounds(qNode.getX(), qNode.getY() + 1)) {
						GridNode neighbor = grid.getNode(qNode.getX(), qNode.getY() + 1);
						neighbors.add(neighbor);
					}
					// East Neighbor
					if (grid.inBounds(qNode.getX() + 1, qNode.getY())) {
						GridNode neighbor = grid.getNode(qNode.getX() + 1, qNode.getY());
						neighbors.add(neighbor);
					}
					// West Neighbor
					if (grid.inBounds(qNode.getX() - 1, qNode.getY())) {
						GridNode neighbor = grid.getNode(qNode.getX() - 1, qNode.getY());
						neighbors.add(neighbor);
					}
					// NorthEast Neighbor
					if (grid.inBounds(qNode.getX() + 1, qNode.getY() - 1)) {
						GridNode neighbor = grid.getNode(qNode.getX() + 1, qNode.getY() - 1);
						neighbors.add(neighbor);
					}
					// NorthWest Neighbor
					if (grid.inBounds(qNode.getX() - 1, qNode.getY() - 1)) {
						GridNode neighbor = grid.getNode(qNode.getX() - 1, qNode.getY() - 1);
						neighbors.add(neighbor);
					}
					// SouthEast Neighbor
					if (grid.inBounds(qNode.getX() + 1, qNode.getY() + 1)) {
						GridNode neighbor = grid.getNode(qNode.getX() + 1, qNode.getY() + 1);
						neighbors.add(neighbor);
					}
					// SouthWest Neighbor
					if (grid.inBounds(qNode.getX() - 1, qNode.getY() + 1)) {
						GridNode neighbor = grid.getNode(qNode.getX() - 1, qNode.getY() + 1);
						neighbors.add(neighbor);
					}
					
					// For each neighbor
					for (GridNode neighbor : neighbors) {
						neighbor.setParent(qNode);
						if (isEndNode(neighbor)) {
							// end node found!
							System.out.println("End Node Found!");
							break;
						}
					}
					
				}
				
				return null;
			}
			
		};
		
		workerThread.execute();
		
	}
	
	private GridNode findLowestFCost() {
		GridNode nodeWithLowestFCost = null;
		for (GridNode gridNode : openList) {
			if (nodeWithLowestFCost == null) nodeWithLowestFCost = gridNode;
			else {
				if (gridNode.calculateFCost(endNode) < nodeWithLowestFCost.calculateFCost(endNode)) {
					nodeWithLowestFCost = gridNode;
				}
			}
		}
		return nodeWithLowestFCost;
	}
	
	private Boolean isEndNode(GridNode node) {
		return node.getX() == endNode.getX() && node.getY() == endNode.getY();
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
