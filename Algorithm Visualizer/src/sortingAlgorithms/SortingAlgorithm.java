package sortingAlgorithms;

import algorithmVisualizer.Algorithm;
import gui.SortingPanel;

/**
 * @author Brett Heithold
 *
 */
public abstract class SortingAlgorithm extends Algorithm {
	
	public SortingAlgorithm() {
		setRunning(false);
	}
	
	public SortingAlgorithm(SortingPanel panel) {
		super(panel);
		this.panel = panel;
	}
	
}
