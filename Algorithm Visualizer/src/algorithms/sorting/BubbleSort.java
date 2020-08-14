package algorithms.sorting;

import java.awt.Color;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gui.sorting.SortingPanel;

/**
 * @author Brett Heithold
 *
 */
public class BubbleSort extends SortingAlgorithm implements Runnable {
	private SwingWorker<Void, Void> workerThread;
	
	public BubbleSort(SortingPanel panel) {
		super(panel);
	}
	
	public void run() {
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				boolean isSorted = false;
				int lastUnsortedIndex = ((SortingPanel) panel).length() - 1;
				while (!isSorted) {
					isSorted = true;
					for (int i = 0; i < lastUnsortedIndex; i++) {
						((SortingPanel) panel).setColor(i, Color.blue);
						((SortingPanel) panel).setColor(i + 1, Color.red);
						publish();
						Thread.sleep(panel.getCurrentDelay());
						if (((SortingPanel) panel).getValue(i, false) > ((SortingPanel) panel).getValue(i + 1, false)) {
							((SortingPanel) panel).swap(i, i + 1);
							isSorted = false;
						}
						((SortingPanel) panel).incrementComparisons();
						((SortingPanel) panel).setColor(i, Color.lightGray);
						publish();
					}
					((SortingPanel) panel).setColor(lastUnsortedIndex, Color.green);
					publish();
					lastUnsortedIndex--;					
					try {
						Thread.sleep(panel.getCurrentDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				// Verify that the array was sorted correctly
				((SortingPanel) panel).setAllColors(Color.green);
				publish();
				if (verifySortedCorrectly()) {
					System.out.println("BubbleSort: Success!");
					((SortingPanel) panel).setAllColors(Color.green);
				}
				else {
					System.out.println("BubbleSort: Failed!");
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
	public void runAlgorithm() {
		setRunning(true);
		SwingUtilities.invokeLater(this);
	}
	
	@Override
	public void killAlgorithm() {
		setRunning(false);
		workerThread.cancel(true);
	}
	
}
