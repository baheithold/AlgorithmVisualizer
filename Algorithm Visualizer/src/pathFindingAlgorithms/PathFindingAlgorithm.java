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
	protected GridNode startNode;
	protected GridNode endNode;
	
	public PathFindingAlgorithm() {
		isRunning = false;
		startNode = null;
		endNode = null;
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
	
	public GridNode getStartNode() {
		return startNode;
	}
	
	public void setStartNode(GridNode start) {
		startNode = start;
	}
	
	public GridNode getEndNode() {
		return endNode;
	}
	
	public void setEndNode(GridNode end) {
		endNode = end;
	}
	
	public abstract void runPathFindingAlgorithm();
	public abstract void killPathFindingAlgorithm();
	
}
