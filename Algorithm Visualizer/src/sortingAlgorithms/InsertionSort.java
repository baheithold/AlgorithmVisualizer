package sortingAlgorithms;

import java.awt.Color;
import java.util.List;

import javax.swing.SwingWorker;

import gui.SortingArrayPanel;

/**
 * @author Brett Heithold
 *
 */
public class InsertionSort implements Runnable {

	private SortingArrayPanel array;
	
	public InsertionSort(SortingArrayPanel array) {
		this.array = array;
	}
	
	@Override
	public void run() {
		
		SwingWorker<Void, Void> workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				for (int i = 1; i < array.length(); i++) {
					int j = i;
					while (j > 0 && array.getValue(j - 1) > array.getValue(j)) {
						array.swap(j, j - 1);
						j--;
					}
					publish();
					Thread.sleep(100);
				}
				
				array.setAllColors(Color.green);
				publish();
				if (verifySortedCorrectly()) {
					System.out.println("InsertionSort: Success!");
				}
				else {
					System.out.println("InsertionSort: Failed!");
				}
				array.setAllColors(Color.green);
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
