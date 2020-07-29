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
				if (!grid.hasStartNode()) {
					System.out.println("Missing Start Node!");
					return null;
				}
				else if (!grid.hasEndNode()) {
					System.out.println("Missing End Node!");
					return null;
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
