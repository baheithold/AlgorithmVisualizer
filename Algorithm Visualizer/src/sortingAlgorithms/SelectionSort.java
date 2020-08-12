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
				for (int j = 0; j < panel.length() - 1; j++) {
					int iMin = j;
					panel.setColor(iMin, Color.red);
					publish();
					Thread.sleep(panel.getCurrentDelay());
					for (int i = j + 1; i < panel.length(); i++) {
						panel.setColor(i, Color.blue);
						publish();
						Thread.sleep(panel.getCurrentDelay());
						panel.incrementComparisons();
						if (panel.getValue(i, false) < panel.getValue(iMin, false)) {
							panel.setColor(iMin, Color.lightGray);
							panel.setColor(i, Color.red);
							publish();
							iMin = i;
						}
						else {
							panel.setColor(i, Color.lightGray);
							publish();
						}
					}
					if (iMin != j) {
						panel.swap(j, iMin);
						panel.setColor(iMin, Color.lightGray);
						panel.setColor(j, Color.green);
						publish();
					}
					else {
						panel.setColor(iMin, Color.green);
						publish();
					}
				}
				
				// Verify that the array was sorted correctly
				panel.setAllColors(Color.green);
				publish();
				if (verifySortedCorrectly()) {
					System.out.println("SelectionSort: Success!");
					panel.setAllColors(Color.green);
				}
				else {
					System.out.println("SelectionSort: Failed!");
					panel.setAllColors(Color.red);
				}
				publish();
				
				return null;
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
			
			@Override
			protected void process(List<Void> chunks) {
				panel.repaint();
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
