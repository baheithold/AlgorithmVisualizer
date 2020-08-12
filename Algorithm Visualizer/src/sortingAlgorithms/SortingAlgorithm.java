package sortingAlgorithms;

import gui.SortingPanel;

/**
 * @author Brett Heithold
 *
 */
public abstract class SortingAlgorithm {
	protected SortingPanel panel;
	private boolean isRunning;
	
	public SortingAlgorithm() {
		isRunning = false;
	}
	
	public SortingAlgorithm(SortingPanel array) {
		isRunning = false;
		this.panel = array;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void setRunning(boolean run) {
		isRunning = run;
	}
	
	public int getCurrentDelay() {
		return panel.getCurrentDelay();
	}
	
	public void setCurrentDelay(int n) {
		panel.setCurrentDelay(n);
	}
	
	public abstract void runSort();
	public abstract void killSort();
	
}
