package sortingAlgorithms;

import java.util.List;

import javax.swing.SwingWorker;

import gui.SortingArrayPanel;

/**
 * @author Brett Heithold
 *
 */
public class MergeSort implements Runnable {
	private SortingArrayPanel array;
	
	public MergeSort(SortingArrayPanel array) {
		this.array = array;
	}
	
	@Override
	public void run() {
		
		SwingWorker<Void, Void> workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				sort(array.getArray(), 0, array.length() - 1);
				for (int i = 0; i < array.length(); i++) {
					System.out.println(array.getValue(i));
				}
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
					if (leftPartition[i] <= rightPartition[j]) {
						arr[k] = leftPartition[i];
						publish();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Comparing " + leftPartition[i] + " and " + rightPartition[j]);
						i++;
					}
					else {
						arr[k] = rightPartition[j];
						publish();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						j++;
					}
					System.out.println("Merged: " + arr[k]);
					k++;
				}
				
				// Either left partition or right partition is now empty, merge the remaining elements
				while (i < nLeft) {
					arr[k] = leftPartition[i];
					System.out.println("Merged: " + arr[k]);
					i++;
					k++;
				}
				while (j < nRight) {
					arr[k] = rightPartition[j];
					System.out.println("Merged: " + arr[k]);
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
			
		};
		
		workerThread.execute();
	}

}
