package pathFindingAlgorithms;

import java.awt.Color;
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
	
	// Neighbors used in both manhattan and diagonal heuristics
	private ArrayList<GridNode> neighbors;
	private GridNode northNeighbor;
	private GridNode southNeighbor;
	private GridNode eastNeighbor;
	private GridNode westNeighbor;
	// Neighbors used in only diagonal heuristic
	private GridNode northeastNeighbor;
	private GridNode northwestNeighbor;
	private GridNode southeastNeighbor;
	private GridNode southwestNeighbor;
	
	// Heuristic
	private String heuristic;
	
	public AStar(PathFindingPanel panel) {
		super(panel);
		this.openList = new ArrayList<GridNode>();
		this.closedList = new boolean[grid.getNumGridCols()][grid.getNumGridRows()];
		this.neighbors = new ArrayList<GridNode>();
		heuristic = null;
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
				
				// get heuristic
				heuristic = panel.getPathFindingControlPanel().whichHeuristicRadioSelected();
				if (heuristic == "manhattan") {
					panel.getGrid().setManhattanHeuristicAStar();
				}
				else if (heuristic == "diagonal") {
					panel.getGrid().setDiagonalHeuristicAStar();
				}
				
				// clear open/closed lists
				openList.clear();
				clearClosedList();
				
				// add start node to open list
				openList.add(grid.getStartNode());
				
				GridNode qNode = null;
				while (!openList.isEmpty()) {
					
					// clear neighbors
					neighbors.clear();
					
					// Find node with lowest f cost
					qNode = findLowestOpenFCost();
					
					// Path does not exist
					if (qNode == null) {
						System.out.println("Path does not exist!");
						setRunning(false);
						return null;
					}
					
					// if qNode is the target (end) node, path found
					if (qNode.isEnd()) {
						System.out.println("Path found!");
						tracePath();
						setRunning(false);
						return null;
					}
					
					/***** Calculate neighbors used in both heuristics *****/
					
					// Determine if north neighbor is inbounds and not an obstacle
					// if inbounds and not an obstacle, add to neighbors list
					if (grid.inBounds(qNode.getX(), qNode.getY() - 1) && !grid.getNode(qNode.getX(), qNode.getY() - 1).isObstacle()) {
						northNeighbor = grid.getNode(qNode.getX(), qNode.getY() - 1);
						neighbors.add(northNeighbor);
					}
					// Determine if south neighbor is inbounds and not an obstacle
					// if inbounds and not an obstacle, add to neighbors list
					if (grid.inBounds(qNode.getX(), qNode.getY() + 1) && !grid.getNode(qNode.getX(), qNode.getY() + 1).isObstacle()) {
						southNeighbor = grid.getNode(qNode.getX(), qNode.getY() + 1);
						neighbors.add(southNeighbor);
					}
					// Determine if east neighbor is inbounds and not an obstacle
					// if inbounds and not an obstacle, add to neighbors list
					if (grid.inBounds(qNode.getX() + 1, qNode.getY()) && !grid.getNode(qNode.getX() + 1, qNode.getY()).isObstacle()) {
						eastNeighbor = grid.getNode(qNode.getX() + 1, qNode.getY());
						neighbors.add(eastNeighbor);
					}
					// Determine if west neighbor is inbounds and not an obstacle
					// if inbounds and not an obstacle, add to neighbors list
					if (grid.inBounds(qNode.getX() - 1, qNode.getY()) && !grid.getNode(qNode.getX() - 1, qNode.getY()).isObstacle()) {
						westNeighbor = grid.getNode(qNode.getX() - 1, qNode.getY());
						neighbors.add(westNeighbor);
					}
					
					/***** Calculate neighbors used in only diagonal heuristic *****/
					if (heuristic == "diagonal") {
						// Determine if northeast neighbor is inbounds and not an obstacle
						// if inbounds and not an obstacle, add to neighbors list
						if (grid.inBounds(qNode.getX() + 1, qNode.getY() - 1) && !grid.getNode(qNode.getX() + 1, qNode.getY() - 1).isObstacle()) {
							northeastNeighbor = grid.getNode(qNode.getX() + 1, qNode.getY() - 1);
							neighbors.add(northeastNeighbor);
						}
						if (grid.inBounds(qNode.getX() - 1, qNode.getY() - 1) && !grid.getNode(qNode.getX() - 1, qNode.getY() - 1).isObstacle()) {
							northwestNeighbor = grid.getNode(qNode.getX() - 1, qNode.getY() - 1);
							neighbors.add(northwestNeighbor);
						}
						if (grid.inBounds(qNode.getX() + 1, qNode.getY() + 1) && !grid.getNode(qNode.getX() + 1, qNode.getY() + 1).isObstacle()) {
							southeastNeighbor = grid.getNode(qNode.getX() + 1, qNode.getY() + 1);
							neighbors.add(southeastNeighbor);
						}
						if (grid.inBounds(qNode.getX() - 1, qNode.getY() + 1) && !grid.getNode(qNode.getX() - 1, qNode.getY() + 1).isObstacle()) {
							southwestNeighbor = grid.getNode(qNode.getX() - 1, qNode.getY() + 1);
							neighbors.add(southwestNeighbor);
						}
					}
					
					// for all neighbors of qNode
					for (GridNode neighbor : neighbors) {
						// if neighbor is not in either open or closed lists
						Thread.sleep(10);
						publish();
						if (!openList.contains(neighbor) && closedList[neighbor.getX()][neighbor.getY()] == false) {
							neighbor.setParent(qNode);
							neighbor.setGCost(qNode.gCost() + 1);
							if (!neighbor.isStart() && !neighbor.isEnd()) {
								neighbor.setColor(Color.magenta);
								publish();
								Thread.sleep(10);
							}
							openList.add(neighbor);
						}
						else {
							if (neighbor.gCost() > qNode.gCost() + 1) {
								neighbor.setGCost(qNode.gCost() + 1);
								neighbor.setParent(qNode);
								// if neighbor is in closed list
								if (closedList[neighbor.getX()][neighbor.getY()] == true) {
									closedList[neighbor.getX()][neighbor.getY()] = false;
									if (!neighbor.isStart() && !neighbor.isEnd()) {
										neighbor.setColor(Color.magenta);
										publish();
										Thread.sleep(10);
									}
									openList.add(neighbor);
								}
							}
						}
					}
					
					// all of qNode's neighbors has been processed,
					// remove qNode from openList and add it to the closedList
					openList.remove(qNode);
					if (!qNode.isStart() && !qNode.isEnd()) {
						qNode.setColor(Color.cyan);
						publish();
						Thread.sleep(10);
					}
					closedList[qNode.getX()][qNode.getY()] = true;
				}
				
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
	
	private GridNode findLowestOpenFCost() {
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
	
	private void clearClosedList() {
		for (int i = 0; i < closedList.length; i++) {
			for (int j = 0; j < closedList[i].length; j++) {
				closedList[i][j] = false;
			}
		}
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
