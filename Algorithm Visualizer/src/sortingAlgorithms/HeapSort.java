package sortingAlgorithms;

import java.awt.Color;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gui.SortingArrayPanel;

/**
 * @author Brett Heithold
 *
 */
public class HeapSort extends SortingAlgorithm implements Runnable {
	private SortingArrayPanel array;
	
	public HeapSort(SortingArrayPanel array) {
		this.array = array;
	}

	@Override
	public void run() {
		SwingWorker<Void, Void> workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				// Perform Heap Sort
				sort();
				publish();
				
				// Verify that the array was sorted correctly
				array.setAllColors(Color.green);
				publish();
				if (verifySortedCorrectly()) {
					System.out.println("HeapSort: Success!");
					array.setAllColors(Color.green);
				}
				else {
					System.out.println("HeapSort: Failed!");
					array.setAllColors(Color.red);
				}
				publish();
				
				return null;
			}

			@Override
			protected void process(List<Void> chunks) {
				array.repaint();
			}
			
			private void sort() {
				int heapSize = array.length();
				BuildMaxHeap(heapSize);
				// Swap each extreme value with last value,
				// then call Heapify
				for (int i = heapSize - 1; i > 0; i--) {
					array.swap(0, i);
					array.setColor(i, Color.green);
					publish();
					Heapify(i, 0);
				}
			}
			
			private void BuildMaxHeap(int heapSize) {
				for (int i = heapSize / 2 - 1; i >= 0; i--) {
					Heapify(heapSize, i);
				}
			}
			
			private void Heapify(int heapSize, int i) {		
				int leftIndex = 2 * i + 1;
				int rightIndex = 2 * i + 2;
				int maxIndex = i;
				
				if (leftIndex < heapSize && array.getValue(leftIndex) > array.getValue(maxIndex)) {
					maxIndex = leftIndex;
				}
				array.incrementComparisons();
				
				if (rightIndex < heapSize && array.getValue(rightIndex) > array.getValue(maxIndex)) {
					maxIndex = rightIndex;
				}
				array.incrementComparisons();
				
				if (maxIndex != i) {
					array.setColor(maxIndex, Color.red);
					publish();
					array.swap(i, maxIndex);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					array.setColor(maxIndex, Color.lightGray);
					publish();
					Heapify(heapSize, maxIndex);
				}
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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

	@Override
	public void runSort() {
		SwingUtilities.invokeLater(this);
	}
	
}
