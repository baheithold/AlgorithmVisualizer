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
				((SortingPanel) panel).setAllColors(Color.green);
				publish();
				if (verifySortedCorrectly()) {
					System.out.println("HeapSort: Success!");
					((SortingPanel) panel).setAllColors(Color.green);
				}
				else {
					System.out.println("HeapSort: Failed!");
					((SortingPanel) panel).setAllColors(Color.red);
				}
				publish();
				
				return null;
			}

			@Override
			protected void process(List<Void> chunks) {
				panel.repaint();
			}
			
			private void sort() {
				int heapSize = ((SortingPanel) panel).length();
				BuildMaxHeap(heapSize);
				// Swap each extreme value with last value,
				// then call Heapify
				for (int i = heapSize - 1; i > 0; i--) {
					((SortingPanel) panel).swap(0, i);
					((SortingPanel) panel).setColor(i, Color.green);
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
				
				if (leftIndex < heapSize && ((SortingPanel) panel).getValue(leftIndex, false) > ((SortingPanel) panel).getValue(maxIndex, false)) {
					maxIndex = leftIndex;
				}
				((SortingPanel) panel).incrementComparisons();
				
				if (rightIndex < heapSize && ((SortingPanel) panel).getValue(rightIndex, false) > ((SortingPanel) panel).getValue(maxIndex, false)) {
					maxIndex = rightIndex;
				}
				((SortingPanel) panel).incrementComparisons();
				
				if (maxIndex != i) {
					((SortingPanel) panel).setColor(maxIndex, Color.red);
					publish();
					((SortingPanel) panel).swap(i, maxIndex);
					try {
						Thread.sleep(panel.getCurrentDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					((SortingPanel) panel).setColor(maxIndex, Color.lightGray);
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
