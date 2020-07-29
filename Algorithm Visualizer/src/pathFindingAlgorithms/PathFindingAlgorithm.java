/**
 * 
 */
package pathFindingAlgorithms;

import java.util.Stack;

import gui.PathFindingPanel;

/**
 * @author Brett Heithold
 *
 */
public abstract class PathFindingAlgorithm {
	protected PathFindingPanel panel;
	protected Grid grid;
	private boolean isRunning;
	
	// path
	private Stack<GridNode> pathStack = new Stack<GridNode>();
	
	public PathFindingAlgorithm() {
		isRunning = false;
	}
	
	public PathFindingAlgorithm(PathFindingPanel panel) {
		this.panel = panel;
		grid = panel.getGrid();
		isRunning = false;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void setRunning(boolean run) {
		isRunning = run;
	}
	
	private void constructPath() {
		GridNode curr = grid.getEndNode();
		while (curr != null) {
			pathStack.add(curr);
			curr = curr.getParent();
			System.out.println("HERE!!!");
		}
	}
	
	protected void tracePath() {
		System.out.println("Path found!");
		constructPath();
		GridNode curr = pathStack.pop();
		while (curr != null) {
			curr.setPath();
			curr = pathStack.pop();
		}
		panel.repaint();
	}
	
	public abstract void runPathFindingAlgorithm();
	public abstract void killPathFindingAlgorithm();
	
}
