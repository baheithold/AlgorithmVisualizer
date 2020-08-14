package algorithms;

import gui.VisualizationPanel;

/**
 * @author Brett Heithold
 *
 */
public abstract class Algorithm {
	protected VisualizationPanel panel;
	private boolean isRunning;

	public Algorithm() {
		isRunning = false;
	}
	
	public Algorithm(VisualizationPanel panel) {
		this.panel = panel;
		isRunning = false;
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
	
	public abstract void runAlgorithm();
	public abstract void killAlgorithm();
	
}
