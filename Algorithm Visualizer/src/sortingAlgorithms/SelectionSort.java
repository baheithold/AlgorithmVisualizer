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
				for (int j = 0; j < ((SortingPanel) panel).length() - 1; j++) {
					int iMin = j;
					((SortingPanel) panel).setColor(iMin, Color.red);
					publish();
					Thread.sleep(panel.getCurrentDelay());
					for (int i = j + 1; i < ((SortingPanel) panel).length(); i++) {
						((SortingPanel) panel).setColor(i, Color.blue);
						publish();
						Thread.sleep(panel.getCurrentDelay());
						((SortingPanel) panel).incrementComparisons();
						if (((SortingPanel) panel).getValue(i, false) < ((SortingPanel) panel).getValue(iMin, false)) {
							((SortingPanel) panel).setColor(iMin, Color.lightGray);
							((SortingPanel) panel).setColor(i, Color.red);
							publish();
							iMin = i;
						}
						else {
							((SortingPanel) panel).setColor(i, Color.lightGray);
							publish();
						}
					}
					if (iMin != j) {
						((SortingPanel) panel).swap(j, iMin);
						((SortingPanel) panel).setColor(iMin, Color.lightGray);
						((SortingPanel) panel).setColor(j, Color.green);
						publish();
					}
					else {
						((SortingPanel) panel).setColor(iMin, Color.green);
						publish();
					}
				}
				
				// Verify that the array was sorted correctly
				((SortingPanel) panel).setAllColors(Color.green);
				publish();
				if (verifySortedCorrectly()) {
					System.out.println("SelectionSort: Success!");
					((SortingPanel) panel).setAllColors(Color.green);
				}
				else {
					System.out.println("SelectionSort: Failed!");
					((SortingPanel) panel).setAllColors(Color.red);
				}
				publish();
				
				return null;
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
