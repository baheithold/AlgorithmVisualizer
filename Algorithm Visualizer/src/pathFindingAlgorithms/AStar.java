package pathFindingAlgorithms;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gui.PathFindingPanel;

/**
 * @author Brett Heithold
 *
 */
public class AStar implements Runnable {
	private PathFindingPanel panel;
	private boolean isRunning;
	SwingWorker<Void, Void> workerThread;

	public AStar(PathFindingPanel panel) {
		this.panel = panel;
		this.isRunning = false;
	}
	
	@Override
	public void run() {
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				return null;
			}
			
		};
		
		workerThread.execute();
		
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
