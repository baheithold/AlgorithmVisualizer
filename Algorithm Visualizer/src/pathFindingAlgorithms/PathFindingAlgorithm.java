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
	protected Grid grid;
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
		startNode = null;
		endNode = null;
		this.panel = panel;
		grid = panel.getGrid();
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
