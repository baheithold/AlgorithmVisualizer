package sortingAlgorithms;

import java.util.List;

import javax.swing.SwingWorker;

import gui.SortingArrayPanel;

/**
 * @author Brett Heithold
 *
 */
public class MergeSort implements Runnable {
	private SortingArrayPanel array;
	
	public MergeSort(SortingArrayPanel array) {
		this.array = array;
	}
	
	@Override
	public void run() {
		
		SwingWorker<Void, Void> workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				return null;
			}

			@Override
			protected void process(List<Void> chunks) {
				array.repaint();
			}
			
		};
		
	}

}
