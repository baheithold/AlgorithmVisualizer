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
	private SwingWorker<Void, Void> workerThread;
	
	public BubbleSort(SortingPanel array) {
		super(array);
	}
	
	public void run() {
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				boolean isSorted = false;
				int lastUnsortedIndex = panel.length() - 1;
				while (!isSorted) {
					isSorted = true;
					for (int i = 0; i < lastUnsortedIndex; i++) {
						panel.setColor(i, Color.blue);
						panel.setColor(i + 1, Color.red);
						publish();
						Thread.sleep(panel.getCurrentDelay());
						if (panel.getValue(i, false) > panel.getValue(i + 1, false)) {
							panel.swap(i, i + 1);
							isSorted = false;
						}
						panel.incrementComparisons();
						panel.setColor(i, Color.lightGray);
						publish();
					}
					panel.setColor(lastUnsortedIndex, Color.green);
					publish();
					lastUnsortedIndex--;					
					try {
						Thread.sleep(panel.getCurrentDelay());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				// Verify that the array was sorted correctly
				panel.setAllColors(Color.green);
				publish();
				if (verifySortedCorrectly()) {
					System.out.println("BubbleSort: Success!");
					panel.setAllColors(Color.green);
				}
				else {
					System.out.println("BubbleSort: Failed!");
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
