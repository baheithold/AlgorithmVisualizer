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
public class AStar implements Runnable {
	private PathFindingPanel panel;
	private Grid grid;
	private boolean isRunning;
	SwingWorker<Void, Void> workerThread;

	// Open/Closed
	private TreeSet<GridNode> openList;
	private HashMap<GridNode, Boolean> closedList;
	
	// Start and End nodes
	private GridNode startNode;
	private GridNode endNode;
	
	public AStar(PathFindingPanel panel) {
		this.panel = panel;
		this.grid = panel.getGrid();
		this.isRunning = false;
		this.openList = new TreeSet<GridNode>();
		this.closedList = new HashMap<GridNode, Boolean>();
		startNode = panel.getGrid().getStartNode();
		endNode = panel.getGrid().getEndNode();
	}
	
	@Override
	public void run() {
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				// Add start node to open list
				openList.add(panel.getGrid().getStartNode());
				
				// while the open list is not empty
				while (!openList.isEmpty()) {
					GridNode qNode = findLowestFCost();
					openList.remove(qNode);
					
					/***** Generate successors *****/
					ArrayList<GridNode> neighbors = new ArrayList<GridNode>();
					
					// North Neighbor
					if (grid.inBounds(qNode.getXLocation(), qNode.getYLocation() - 1)) {
						GridNode neighbor = grid.getNode(qNode.getXLocation(), qNode.getYLocation() - 1);
						neighbors.add(neighbor);
					}
					// South Neighbor
					if (grid.inBounds(qNode.getXLocation(), qNode.getYLocation() + 1)) {
						GridNode neighbor = grid.getNode(qNode.getXLocation(), qNode.getYLocation() + 1);
						neighbors.add(neighbor);
					}
					// East Neighbor
					if (grid.inBounds(qNode.getXLocation() + 1, qNode.getYLocation())) {
						GridNode neighbor = grid.getNode(qNode.getXLocation() + 1, qNode.getYLocation());
						neighbors.add(neighbor);
					}
					// West Neighbor
					if (grid.inBounds(qNode.getXLocation() - 1, qNode.getYLocation())) {
						GridNode neighbor = grid.getNode(qNode.getXLocation() - 1, qNode.getYLocation());
						neighbors.add(neighbor);
					}
					// NorthEast Neighbor
					if (grid.inBounds(qNode.getXLocation() + 1, qNode.getYLocation() - 1)) {
						GridNode neighbor = grid.getNode(qNode.getXLocation() + 1, qNode.getYLocation() - 1);
						neighbors.add(neighbor);
					}
					// NorthWest Neighbor
					if (grid.inBounds(qNode.getXLocation() - 1, qNode.getYLocation() - 1)) {
						GridNode neighbor = grid.getNode(qNode.getXLocation() - 1, qNode.getYLocation() - 1);
						neighbors.add(neighbor);
					}
					// SouthEast Neighbor
					if (grid.inBounds(qNode.getXLocation() + 1, qNode.getYLocation() + 1)) {
						GridNode neighbor = grid.getNode(qNode.getXLocation() + 1, qNode.getYLocation() + 1);
						neighbors.add(neighbor);
					}
					// SouthWest Neighbor
					if (grid.inBounds(qNode.getXLocation() - 1, qNode.getYLocation() + 1)) {
						GridNode neighbor = grid.getNode(qNode.getXLocation() - 1, qNode.getYLocation() + 1);
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
		return node.getXLocation() == endNode.getXLocation() && node.getYLocation() == endNode.getYLocation();
	}
	
	public void runAStar() {
		this.isRunning = true;
		SwingUtilities.invokeLater(this);
	}
	
	public void killAStar() {
		this.isRunning = false;
		workerThread.cancel(true);
	}

}
