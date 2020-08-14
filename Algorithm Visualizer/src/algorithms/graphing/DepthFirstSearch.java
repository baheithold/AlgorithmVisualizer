package algorithms.graphing;

import algorithms.Algorithm;
import gui.graphing.GraphPanel;

/**
 * @author Brett Heithold
 *
 */
public class DepthFirstSearch extends Algorithm {

	public DepthFirstSearch(GraphPanel panel) {
		super(panel);
	}

	@Override
	public void runAlgorithm() {
		setRunning(true);
	}

	@Override
	public void killAlgorithm() {
		setRunning(false);
	}

}
