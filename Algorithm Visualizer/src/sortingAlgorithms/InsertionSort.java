package sortingAlgorithms;

import java.awt.Color;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gui.SortingPanel;

/**
 * @author Brett Heithold
 *
 */
public class InsertionSort extends SortingAlgorithm implements Runnable {
	private SwingWorker<Void, Void> workerThread;
	
	public InsertionSort(SortingPanel array) {
		super(array);
	}
	
	@Override
	public void run() {
		
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				((SortingPanel) panel).setColor(0, Color.green);
				publish();
				for (int i = 1; i < ((SortingPanel) panel).length(); i++) {
					int j = i;
					while (j > 0 && ((SortingPanel) panel).getValue(j - 1, false) > ((SortingPanel) panel).getValue(j, false)) {
						((SortingPanel) panel).incrementComparisons();
						((SortingPanel) panel).setColor(j, Color.red);
						publish();
						Thread.sleep(panel.getCurrentDelay());
						((SortingPanel) panel).swap(j, j - 1);
						((SortingPanel) panel).setColor(j, Color.green);
						publish();
						Thread.sleep(panel.getCurrentDelay());
						j--;
					}
					Thread.sleep(panel.getCurrentDelay());
				}
				
				// Verify that the array was sorted correctly
				((SortingPanel) panel).setAllColors(Color.green);
				publish();
				if (verifySortedCorrectly()) {
					System.out.println("InsertionSort: Success!");
					((SortingPanel) panel).setAllColors(Color.green);
				}
				else {
					System.out.println("InsertionSort: Failed!");
					((SortingPanel) panel).setAllColors(Color.red);
				}
				publish();
				
				return null;
			}

			private Boolean verifySortedCorrectly() {
				for (int i = 1; i < ((SortingPanel) panel).length(); i++) {
					if (((SortingPanel) panel).getValue(i - 1, true) <= ((SortingPanel) panel).getValue(i, true)) {
						((SortingPanel) panel).setColor(i - 1, Color.blue);
						publish();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					else return false;
				}
				if (((SortingPanel) panel).getValue(((SortingPanel) panel).length() - 1, true) > ((SortingPanel) panel).getValue(((SortingPanel) panel).length() - 2, true)) {
					((SortingPanel) panel).setColor(((SortingPanel) panel).length() - 1, Color.blue);
					publish();
				}
				return true;
			}
			
			@Override
			protected void process(List<Void> chunks) {
				panel.repaint();
			}
			
		};
		
		workerThread.execute();
		
	}

	@Override
	public void runSort() {
		setRunning(true);
		SwingUtilities.invokeLater(this);
	}

	@Override
	public void killSort() {
		setRunning(false);
		workerThread.cancel(true);
	}
	
}
