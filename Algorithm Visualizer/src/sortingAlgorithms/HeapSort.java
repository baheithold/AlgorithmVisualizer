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
public class HeapSort extends SortingAlgorithm implements Runnable {
	private SwingWorker<Void, Void> workerThread;
	
	public HeapSort(SortingPanel array) {
		super(array);
	}

	@Override
	public void run() {
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				// Perform Heap Sort
				sort();
				publish();
				
				// Verify that the array was sorted correctly
				panel.setAllColors(Color.green);
				publish();
				if (verifySortedCorrectly()) {
					System.out.println("HeapSort: Success!");
					panel.setAllColors(Color.green);
				}
				else {
					System.out.println("HeapSort: Failed!");
					panel.setAllColors(Color.red);
				}
				publish();
				
				return null;
			}

			@Override
			protected void process(List<Void> chunks) {
				panel.repaint();
			}
			
			private void sort() {
				int heapSize = panel.length();
				BuildMaxHeap(heapSize);
				// Swap each extreme value with last value,
				// then call Heapify
				for (int i = heapSize - 1; i > 0; i--) {
					panel.swap(0, i);
					panel.setColor(i, Color.green);
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
				
				if (leftIndex < heapSize && panel.getValue(leftIndex, false) > panel.getValue(maxIndex, false)) {
					maxIndex = leftIndex;
				}
				panel.incrementComparisons();
				
				if (rightIndex < heapSize && panel.getValue(rightIndex, false) > panel.getValue(maxIndex, false)) {
					maxIndex = rightIndex;
				}
				panel.incrementComparisons();
				
				if (maxIndex != i) {
					panel.setColor(maxIndex, Color.red);
					publish();
					panel.swap(i, maxIndex);
					try {
						Thread.sleep(panel.getCurrentDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					panel.setColor(maxIndex, Color.lightGray);
					publish();
					Heapify(heapSize, maxIndex);
				}
				
				try {
					Thread.sleep(panel.getCurrentDelay());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			private Boolean verifySortedCorrectly() {
				for (int i = 1; i < panel.length(); i++) {
					if (panel.getValue(i - 1, true) <= panel.getValue(i, true)) {
						panel.setColor(i - 1, Color.blue);
						publish();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					else return false;
				}
				if (panel.getValue(panel.length() - 1, true) > panel.getValue(panel.length() - 2, true)) {
					panel.setColor(panel.length() - 1, Color.blue);
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
