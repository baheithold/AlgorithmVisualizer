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
	
	private int[][] distances;
	private GridNode[][] prev;
	
	public Dijkstra(PathFindingPanel panel) {
		super(panel);
		grid = panel.getGrid();
		distances = new int[grid.getNumGridCols()][grid.getNumGridRows()];
		initializeDistancesMatrixToInfinity();
		prev = new GridNode[grid.getNumGridCols()][grid.getNumGridRows()];
		initializePreviousMatrixToNull();
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
				
				/***** Dijkstra *****/
				
				
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

	private void initializeDistancesMatrixToInfinity() {
		for (int i = 0; i < grid.getNumGridCols(); i++) {
			for (int j = 0; j < grid.getNumGridRows(); j++) {
				distances[i][j] = (int) Double.POSITIVE_INFINITY;
			}
		}
	}
	
	private void initializePreviousMatrixToNull() {
		for (int i = 0; i < grid.getNumGridCols(); i++) {
			for (int j = 0; j < grid.getNumGridRows(); j++) {
				prev[i][j] = null;
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
