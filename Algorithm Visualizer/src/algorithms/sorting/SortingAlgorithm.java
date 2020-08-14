package algorithms.sorting;

import algorithms.Algorithm;
import gui.sorting.SortingPanel;

/**
 * @author Brett Heithold
 *
 */
public abstract class SortingAlgorithm extends Algorithm {
	
	public SortingAlgorithm(SortingPanel panel) {
		super(panel);
		this.panel = panel;
	}
	
}
