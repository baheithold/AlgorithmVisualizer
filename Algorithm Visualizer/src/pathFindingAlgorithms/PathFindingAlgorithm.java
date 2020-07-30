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
	
	private void resetDefaultColors() {
		for (int i = 0; i < grid.getNumGridCols(); i++) {
			for (int j = 0; j < grid.getNumGridRows(); j++) {
				GridNode node = grid.getNode(i, j);
				if (!node.isDefault() && !node.isStart() && !node.isEnd() && !node.isObstacle()) {
					node.setDefault();
				}
			}
		}
		panel.repaint();
	}
	
	private void constructPath() {
		GridNode curr = grid.getEndNode();
		while (curr != null) {
			pathStack.add(curr);
			curr = curr.getParent();
		}
	}
	
	protected void tracePath() {
		constructPath();
		resetDefaultColors();
		GridNode curr = pathStack.pop();
		while (pathStack.size() > 0) {
			// if current node is not start/end node, set node as a path node
			if (!curr.isStart() && !curr.isEnd()) {
				curr.setPath();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			panel.repaint();
			curr = pathStack.pop();
		}
	}
	
	public abstract void runPathFindingAlgorithm();
	public abstract void killPathFindingAlgorithm();
	
}
