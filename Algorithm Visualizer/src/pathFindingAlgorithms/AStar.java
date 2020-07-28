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
public class AStar extends PathFindingAlgorithm implements Runnable {
	SwingWorker<Void, Void> workerThread;

	// Open/Closed
	private ArrayList<GridNode> openList;
	private boolean[][] closedList;
	
	public AStar(PathFindingPanel panel) {
		super(panel);
		this.openList = new ArrayList<GridNode>();
		this.closedList = new boolean[grid.getNumGridCols()][grid.getNumGridRows()];
	}
	
	@Override
	public void run() {
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				// if start or end node is missing, return
				if (!grid.hasStartNode() || !grid.hasEndNode()) return null;
				
				// Add start node to open list
				openList.add(grid.getStartNode());
				
				// Neighbors List
				ArrayList<GridNode> neighbors = new ArrayList<GridNode>();
				
				// while the open list is not empty
				while (!openList.isEmpty()) {
					GridNode qNode = findLowestFCost();
					openList.remove(qNode);
					
					/***** Generate successors *****/
					
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
					// For each neighbor (clear neighbors after done)
					for (GridNode neighbor : neighbors) {
						System.out.println("HELLO NEIGHBOR");
						neighbor.setParent(qNode);
						if (neighbor.isEnd()) {
							// end node found!
							System.out.println("End Node Found!");
							setPathFound(true);
							break;
						}
						else if (!closedList[qNode.getX()][qNode.getY()] && !neighbor.isObstacle()) {
							openList.add(neighbor);
							int gNew = neighbor.gCost() + 1;
							int hNew = neighbor.calculateHCost(grid.getEndNode());
							int fNew = gNew + hNew;
							int fOld = neighbor.calculateFCost(grid.getEndNode());
							if (fOld > fNew) {
								neighbor.setGCost(gNew);
							}
						}
					}
					neighbors.clear();
					closedList[qNode.getX()][qNode.getY()] = true;
				}
				
				if (isPathFound()) {
					tracePath();
				}
				publish();
				return null;
			}

			@Override
			protected void process(List<Void> chunks) {
				panel.repaint();
			}
						
		};
		
		workerThread.execute();
		
	}
	
	private GridNode findLowestFCost() {
		GridNode nodeWithLowestFCost = null;
		for (GridNode gridNode : openList) {
			if (nodeWithLowestFCost == null) nodeWithLowestFCost = gridNode;
			else {
				if (gridNode.calculateFCost(grid.getEndNode()) < nodeWithLowestFCost.calculateFCost(grid.getEndNode())) {
					nodeWithLowestFCost = gridNode;
				}
			}
		}
		return nodeWithLowestFCost;
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
