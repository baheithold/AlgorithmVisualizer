package sortingAlgorithms;

import gui.SortingArrayPanel;

/**
 * @author Brett Heithold
 *
 */
public abstract class SortingAlgorithm {
	protected SortingArrayPanel array;
	
	public SortingAlgorithm() {
	}
	
	public SortingAlgorithm(SortingArrayPanel array) {
		this.array = array;
	}
	
	public int getCurrentDelay() {
		return array.getCurrentDelay();
	}
	
	public void setCurrentDelay(int n) {
		array.setCurrentDelay(n);
	}
	
	public abstract void runSort();
	
}
