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
public class MergeSort extends SortingAlgorithm implements Runnable {
	public SwingWorker<Void, Void> workerThread;
	
	public MergeSort(SortingPanel array) {
		super(array);
	}
	
	@Override
	public void run() {
		
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				sort(array.getArray(), 0, array.length() - 1);
				if (verifySortedCorrectly()) {
					System.out.println("MergeSort: Success!");
					array.setAllColors(Color.green);
				}
				else {
					System.out.println("MergeSort: Failed!");
					array.setAllColors(Color.red);
				}
				publish();
				return null;
			}

			@Override
			protected void process(List<Void> chunks) {
				array.repaint();
			}

			private void merge(int arr[], int l, int m, int r) {
				// Calculate partition sizes
				int nLeft = m - l + 1;
				int nRight = r - m;
				
				// Create temporary arrays for left and right partitions
				int leftPartition[] = new int[nLeft];
				int rightPartition[] = new int [nRight];
				
				// Copy data to partition arrays
				for (int i = 0; i < nLeft; i++) {
					leftPartition[i] = arr[l + i];
				}
				for (int j = 0; j < nRight; j++) {
					rightPartition[j] = arr[m + 1 + j];
				}
				
				// Merge left and right partitions
				int i = 0;
				int j = 0;
				int k = l;
				while (i < nLeft && j < nRight) {
					array.setColor(k, Color.red);
					publish();
					array.incrementComparisons();
					if (leftPartition[i] <= rightPartition[j]) {
						arr[k] = leftPartition[i];
						array.setColor(k, Color.red);
						publish();
						try {
							Thread.sleep(array.getCurrentDelay());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						i++;
					}
					else {
						arr[k] = rightPartition[j];
						array.setColor(k, Color.red);
						publish();
						try {
							Thread.sleep(array.getCurrentDelay());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						j++;
					}
					// paint newly merged element green
					array.setColor(k, Color.green);
					publish();
					k++;
				}
				
				// Either left partition or right partition is now empty, merge the remaining elements
				while (i < nLeft) {
					arr[k] = leftPartition[i];
					array.setColor(k, Color.red);
					publish();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					array.setColor(k, Color.green);
					publish();
					try {
						Thread.sleep(array.getCurrentDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
					k++;
				}
				while (j < nRight) {
					arr[k] = rightPartition[j];
					array.setColor(k, Color.red);
					publish();
					try {
						Thread.sleep(array.getCurrentDelay());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					array.setColor(k, Color.green);
					publish();
					try {
						Thread.sleep(array.getCurrentDelay());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					j++;
					k++;
				}
			}
			
			private void sort(int arr[], int l, int r) {
				if (l < r) {
					// Find the midpoint
					int m = (l + r) / 2;
					
					// Sort left and right partitions
					sort(arr, l, m);
					sort(arr, m + 1, r);
					
					// Merge left and right partitions
					merge(arr, l, m, r);
				}
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
