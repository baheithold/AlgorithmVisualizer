/**
 * 
 */
package sortingAlgorithms;

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
				
				for (int i = 1; i < array.length() - 1; i++) {
					int j = i;
					while (j > 0 && array.getValue(j - 1) > array.getValue(j)) {
						array.swap(j, j - 1);
						j--;
					}
					publish();
					Thread.sleep(100);
				}
				
				return null;
			}

			@Override
			protected void process(List<Void> chunks) {
				array.repaint();
				array.revalidate();
			}
			
			
			
		};
		
		workerThread.execute();
		
	}

}
