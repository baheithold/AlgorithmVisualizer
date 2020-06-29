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
		SwingWorker<Void, Void> workerThread= new SwingWorker<Void, Void>() {

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
						Thread.sleep(10);
						if (array.getValue(i) > array.getValue(i + 1)) {
							array.swap(i, i + 1);
							isSorted = false;
						}
						array.setColor(i, Color.lightGray);
					}
					array.setColor(lastUnsortedIndex, Color.green);
					publish();
					lastUnsortedIndex--;					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
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
