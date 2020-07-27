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
public class SelectionSort extends SortingAlgorithm implements Runnable {
	private SwingWorker<Void, Void> workerThread;
	
	public SelectionSort(SortingPanel array) {
		super(array);
	}
	
	@Override
	public void run() {	
		
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				for (int j = 0; j < array.length() - 1; j++) {
					int iMin = j;
					array.setColor(iMin, Color.red);
					publish();
					Thread.sleep(array.getCurrentDelay());
					for (int i = j + 1; i < array.length(); i++) {
						array.setColor(i, Color.blue);
						publish();
						Thread.sleep(array.getCurrentDelay());
						array.incrementComparisons();
						if (array.getValue(i, false) < array.getValue(iMin, false)) {
							array.setColor(iMin, Color.lightGray);
							array.setColor(i, Color.red);
							publish();
							iMin = i;
						}
						else {
							array.setColor(i, Color.lightGray);
							publish();
						}
					}
					if (iMin != j) {
						array.swap(j, iMin);
						array.setColor(iMin, Color.lightGray);
						array.setColor(j, Color.green);
						publish();
					}
					else {
						array.setColor(iMin, Color.green);
						publish();
					}
				}
				
				// Verify that the array was sorted correctly
				array.setAllColors(Color.green);
				publish();
				if (verifySortedCorrectly()) {
					System.out.println("SelectionSort: Success!");
					array.setAllColors(Color.green);
				}
				else {
					System.out.println("SelectionSort: Failed!");
					array.setAllColors(Color.red);
				}
				publish();
				
				return null;
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
			
			@Override
			protected void process(List<Void> chunks) {
				array.repaint();
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
