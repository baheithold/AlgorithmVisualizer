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
public class QuickSort extends SortingAlgorithm implements Runnable {
	public SwingWorker<Void, Void> workerThread;
	
	public QuickSort(SortingPanel array) {
		super(array);
	}
	
	@Override
	public void run() {
		
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				sort(0, array.length());
				publish();
				// Verify that the array was sorted correctly
				if (verifySortedCorrectly()) {
					System.out.println("QuickSort: Success!");
					array.setAllColors(Color.green);
				}
				else {
					System.out.println("QuickSort: Failed!");
					array.setAllColors(Color.red);
				}
				publish();
				return null;
			}

			@Override
			protected void process(List<Void> chunks) {
				array.repaint();
			}
			
			private void sort(int low, int high) {
				if (low < high) {
					int pivotIndex = partition(low, high);
					publish();
					try {
						Thread.sleep(array.getCurrentDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sort(low, pivotIndex);
					publish();
					try {
						Thread.sleep(array.getCurrentDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sort(pivotIndex + 1, high);
					publish();
					try {
						Thread.sleep(array.getCurrentDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			private int partition(int low, int high) {
				int pivotValue = array.getValue(low, false);
				int leftwall = low;
				
				array.setColor(leftwall, Color.blue);
				publish();
				
				for (int i = low + 1; i < high; i++) {
					array.setColor(i, Color.red);
					publish();
					array.incrementComparisons();
					if (array.getValue(i, false) < pivotValue) {
						leftwall++;
						array.swap(i, leftwall);
						publish();
						try {
							Thread.sleep(array.getCurrentDelay());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					array.setColor(i, Color.lightGray);
					publish();
				}
				array.swap(low, leftwall);
				array.setColor(leftwall, Color.green);
				publish();
				try {
					Thread.sleep(array.getCurrentDelay());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return leftwall;
			}
			
			private Boolean verifySortedCorrectly() {
				for (int i = 1; i < array.length(); i++) {
					if (array.getValue(i - 1, true) <= array.getValue(i, true)) {
						array.setColor(i - 1, Color.blue);
						publish();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					else return false;
				}
				if (array.getValue(array.length() - 1, true) > array.getValue(array.length() - 2, true)) {
					array.setColor(array.length() - 1, Color.blue);
					publish();
				}
				return true;
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
