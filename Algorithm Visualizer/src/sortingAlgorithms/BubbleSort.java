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
public class BubbleSort extends SortingAlgorithm implements Runnable {
	public SwingWorker<Void, Void> workerThread;
	
	public BubbleSort(SortingPanel array) {
		super(array);
	}
	
	public void run() {
		workerThread = new SwingWorker<Void, Void>() {

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
						Thread.sleep(array.getCurrentDelay());
						if (array.getValue(i, false) > array.getValue(i + 1, false)) {
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
						Thread.sleep(array.getCurrentDelay());
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
