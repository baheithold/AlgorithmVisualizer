/**
 * 
 */
package pathFindingAlgorithms;

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
	private GridNode[][] prev;
	private Boolean[][] shortestPathSet;
	
	public Dijkstra(PathFindingPanel panel) {
		super(panel);
		grid = panel.getGrid();
		distances = new Integer[grid.getNumGridCols()][grid.getNumGridRows()];
		prev = new GridNode[grid.getNumGridCols()][grid.getNumGridRows()];
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
				initializeMatrixTo(prev, null);
				initializeMatrixTo(shortestPathSet, false);
				
				/*************** Dijkstra ***************/
		
				// Set start distance to 0
				distances[grid.getStartNode().getX()][grid.getStartNode().getY()] = 0;
				
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
