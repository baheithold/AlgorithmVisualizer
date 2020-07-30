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
public class BreadthFirstSearch extends PathFindingAlgorithm implements Runnable {
	SwingWorker<Void, Void> workerThread;
	
	public BreadthFirstSearch(PathFindingPanel panel) {
		super(panel);
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
