/**
 * 
 */
package pathFindingAlgorithms;

import gui.PathFindingPanel;

/**
 * @author Brett Heithold
 *
 */
public abstract class PathFindingAlgorithm {
	protected PathFindingPanel panel;
	private boolean isRunning;
	
	public PathFindingAlgorithm() {
		isRunning = false;
	}
	
	public PathFindingAlgorithm(PathFindingPanel panel) {
		isRunning = false;
		this.panel = panel;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void setRunning(boolean run) {
		isRunning = run;
	}
	
	public abstract void runPathFindingAlgorithm();
	public abstract void killPathFindingAlgorithm();
	
}
