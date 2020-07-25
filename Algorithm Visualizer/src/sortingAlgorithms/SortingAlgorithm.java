package sortingAlgorithms;

import gui.SortingPanel;

/**
 * @author Brett Heithold
 *
 */
public abstract class SortingAlgorithm {
	protected SortingPanel array;
	private boolean isRunning;
	
	public SortingAlgorithm() {
		isRunning = false;
	}
	
	public SortingAlgorithm(SortingPanel array) {
		this.array = array;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void setRunning(boolean run) {
		isRunning = run;
	}
	
	public int getCurrentDelay() {
		return array.getCurrentDelay();
	}
	
	public void setCurrentDelay(int n) {
		array.setCurrentDelay(n);
	}
	
	public abstract void runSort();
	public abstract void killSort();
	
}
