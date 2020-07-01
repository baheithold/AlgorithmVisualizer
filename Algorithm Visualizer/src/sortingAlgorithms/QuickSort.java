package sortingAlgorithms;

import java.awt.Color;
import java.util.List;

import javax.swing.SwingWorker;

import gui.SortingArrayPanel;

/**
 * @author Brett Heithold
 *
 */
public class QuickSort implements Runnable {
	private SortingArrayPanel array;
	
	public QuickSort(SortingArrayPanel array) {
		this.array = array;
	}
	
	@Override
	public void run() {
		
		SwingWorker<Void, Void> workerThread = new SwingWorker<Void, Void>() {

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
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sort(low, pivotIndex);
					publish();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sort(pivotIndex + 1, high);
					publish();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			private int partition(int low, int high) {
				int pivotValue = array.getValue(low);
				int leftwall = low;
				
				for (int i = low + 1; i < high; i++) {
					if (array.getValue(i) < pivotValue) {
						leftwall++;
						array.swap(i, leftwall);
						publish();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				array.swap(low, leftwall);
				publish();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return leftwall;
			}
			
			private Boolean verifySortedCorrectly() {
				for (int i = 1; i < array.length(); i++) {
					if (array.getValue(i - 1) <= array.getValue(i)) {
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
				if (array.getValue(array.length() - 1) > array.getValue(array.length() - 2)) {
					array.setColor(array.length() - 1, Color.blue);
					publish();
				}
				return true;
			}
			
		};
		
		workerThread.execute();
		
	}

}
