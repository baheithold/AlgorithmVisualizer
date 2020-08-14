package algorithms.sorting;

import java.awt.Color;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gui.sorting.SortingPanel;

/**
 * @author Brett Heithold
 *
 */
public class QuickSort extends SortingAlgorithm implements Runnable {
	private SwingWorker<Void, Void> workerThread;
	
	public QuickSort(SortingPanel array) {
		super(array);
	}
	
	@Override
	public void run() {
		
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				sort(0, ((SortingPanel) panel).length());
				publish();
				// Verify that the array was sorted correctly
				if (verifySortedCorrectly()) {
					System.out.println("QuickSort: Success!");
					((SortingPanel) panel).setAllColors(Color.green);
				}
				else {
					System.out.println("QuickSort: Failed!");
					((SortingPanel) panel).setAllColors(Color.red);
				}
				publish();
				return null;
			}

			@Override
			protected void process(List<Void> chunks) {
				panel.repaint();
			}
			
			private void sort(int low, int high) {
				if (low < high) {
					int pivotIndex = partition(low, high);
					publish();
					try {
						Thread.sleep(panel.getCurrentDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sort(low, pivotIndex);
					publish();
					try {
						Thread.sleep(panel.getCurrentDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sort(pivotIndex + 1, high);
					publish();
					try {
						Thread.sleep(panel.getCurrentDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			private int partition(int low, int high) {
				int pivotValue = ((SortingPanel) panel).getValue(low, false);
				int leftwall = low;
				
				((SortingPanel) panel).setColor(leftwall, Color.blue);
				publish();
				
				for (int i = low + 1; i < high; i++) {
					((SortingPanel) panel).setColor(i, Color.red);
					publish();
					((SortingPanel) panel).incrementComparisons();
					if (((SortingPanel) panel).getValue(i, false) < pivotValue) {
						leftwall++;
						((SortingPanel) panel).swap(i, leftwall);
						publish();
						try {
							Thread.sleep(panel.getCurrentDelay());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					((SortingPanel) panel).setColor(i, Color.lightGray);
					publish();
				}
				((SortingPanel) panel).swap(low, leftwall);
				((SortingPanel) panel).setColor(leftwall, Color.green);
				publish();
				try {
					Thread.sleep(panel.getCurrentDelay());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return leftwall;
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
			
		};
		
		workerThread.execute();
		
	}

	@Override
	public void runAlgorithm() {
		setRunning(true);
		SwingUtilities.invokeLater(this);
	}

	@Override
	public void killAlgorithm() {
		setRunning(false);
		workerThread.cancel(true);
	}
	
}
