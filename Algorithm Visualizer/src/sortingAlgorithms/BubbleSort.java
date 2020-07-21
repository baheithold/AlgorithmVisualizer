package sortingAlgorithms;

import java.awt.Color;
import java.util.List;

import javax.swing.SwingWorker;

import gui.SortingArrayPanel;

/**
 * @author Brett Heithold
 *
 */
public class BubbleSort implements Runnable {
	private SortingArrayPanel array;
	
	public BubbleSort(SortingArrayPanel array) {
		this.array = array;
	}
	
	public void run() {
		SwingWorker<Void, Void> workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				boolean isSorted = false;
				int lastUnsortedIndex = array.length() - 1;
				while (!isSorted) {
					isSorted = true;
					for (int i = 0; i < lastUnsortedIndex; i++) {
						array.setColor(i, Color.blue);
						array.setColor(i + 1, Color.red);
						publish();
						Thread.sleep(1);
						if (array.getValue(i) > array.getValue(i + 1)) {
							array.swap(i, i + 1);
							isSorted = false;
						}
						array.incrementComparisons();
						array.setColor(i, Color.lightGray);
						publish();
					}
					array.setColor(lastUnsortedIndex, Color.green);
					publish();
					lastUnsortedIndex--;					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				// Verify that the array was sorted correctly
				array.setAllColors(Color.green);
				publish();
				if (verifySortedCorrectly()) {
					System.out.println("BubbleSort: Success!");
					array.setAllColors(Color.green);
				}
				else {
					System.out.println("BubbleSort: Failed!");
					array.setAllColors(Color.red);
				}
				publish();
				
				return null;
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
			
			@Override
			protected void process(List<Void> chunks) {
				array.repaint();
			}

		};
		
		workerThread.execute();		
		
	}
	
}
