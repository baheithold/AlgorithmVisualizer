package sortingAlgorithms;

import gui.SortingArrayPanel;

/**
 * @author Brett Heithold
 *
 */
public abstract class SortingAlgorithm {
	protected SortingArrayPanel array;
	private boolean isRunning;
	
	public SortingAlgorithm() {
		isRunning = false;
	}
	
	public SortingAlgorithm(SortingArrayPanel array) {
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