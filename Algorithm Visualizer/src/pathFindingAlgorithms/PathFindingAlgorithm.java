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
	
	protected void resetParents() {
		for (int i = 0; i < grid.getNumGridCols(); i++) {
			for (int j = 0; j < grid.getNumGridRows(); j++) {
				grid.getNode(i, j).setParent(null);
			}
		}
	}
	
	private void constructPath() {
		GridNode curr = grid.getEndNode();
		while (curr != null) {
			System.out.println(curr.getX() + ", " + curr.getY());
			pathStack.add(curr);
			curr = curr.getParent();
		}
	}
	
	protected void tracePath() {
		System.out.println("TRACE HERE!!!");
		constructPath();
		panel.resetDefaultColors();
		GridNode curr = pathStack.pop();
		while (pathStack.size() > 0) {
			System.out.println("TRACE WHILE HERE!!!!");
			// if current node is not start/end node, set node as a path node
			if (!curr.isStart() && !curr.isEnd()) {
				curr.setPath();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				panel.resetPathColorsToDefault();
			}
			panel.repaint();
			curr = pathStack.pop();
		}
	}
	
	protected boolean verifyStartEndNodesExist() {
		if (!grid.hasStartNode()) {
			System.out.println("Missing Start Node!");
			return false;
		}
		else if (!grid.hasEndNode()) {
			System.out.println("Missing End Node!");
			return false;
		}
		return true;
	}
	
	protected GridNode generateNeighbor(int x, int y) {
		if (!grid.inBounds(x, y)) return null;
		GridNode result = grid.getNode(x, y);
		if (result.isObstacle()) return null;
		return result;
	}
	
	public abstract void runPathFindingAlgorithm();
	public abstract void killPathFindingAlgorithm();
	
}
