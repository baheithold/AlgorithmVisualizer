package sortingAlgorithms;

import java.awt.Color;
import java.util.List;

import javax.swing.SwingWorker;

import gui.SortingArrayPanel;

/**
 * @author Brett Heithold
 *
 */
public class SelectionSort implements Runnable {
	private SortingArrayPanel array;
	
	public SelectionSort(SortingArrayPanel array) {
		this.array = array;
	}
	
	@Override
	public void run() {	
		
		SwingWorker<Void, Void> workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				for (int j = 0; j < array.length() - 1; j++) {
					int iMin = j;
					array.setColor(iMin, Color.red);
					Thread.sleep(10);
					for (int i = j + 1; i < array.length(); i++) {
						array.setColor(i, Color.blue);
						publish();
						Thread.sleep(10);
						if (array.getValue(i) < array.getValue(iMin)) {
							iMin = i;
						}
					}
					if (iMin != j) {
						array.swap(j, iMin);
						array.setColor(j, Color.green);
						publish();
					}
				}
				
				// Verify that the array was sorted correctly
				array.setAllColors(Color.green);
				if (verifySortedCorrectly()) {
					System.out.println("SelectionSort: Success!");
				}
				else {
					System.out.println("SelectionSort: Failed!");
				}
				array.setAllColors(Color.green);
				
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
