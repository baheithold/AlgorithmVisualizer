package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pathFindingAlgorithms.AStar;
import pathFindingAlgorithms.Grid;
import pathFindingAlgorithms.GridNode;
import pathFindingAlgorithms.PathFindingAlgorithm;

/**
 * @author Brett Heithold
 *
 */
public class PathFindingPanel extends VisualizationPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private String algorithmName;
	private Grid grid;
	
	// Control Panel
	private PathFindingControlPanel controlPanel;
	
	// Path Finding Algorithm
	public PathFindingAlgorithm pathFindingAlgorithm;

	public PathFindingPanel(String algorithmName) {
		this.setLayout(new BorderLayout());
		this.algorithmName = algorithmName;
		this.grid = new Grid(this, (this.WINDOW_HEIGHT / 20) - 6, (this.WINDOW_WIDTH / 20) - 1);
		initializeControlPanel();
		pathFindingAlgorithm = new AStar(this);
		this.addMouseListener(this);
		
		switch (algorithmName) {
			case "AStar":
				pathFindingAlgorithm = new AStar(this);
				break;
			default:
				break;
		}
	}
	
	public String getAlgorithmName() {
		return this.algorithmName;
	}
	
	public Grid getGrid() {
		return this.grid;
	}
	
	private void initializeControlPanel() {
		controlPanel = new PathFindingControlPanel(this);
		this.add(controlPanel, BorderLayout.SOUTH);
	}
	
	public PathFindingControlPanel getPathFindingControlPanel() {
		return this.controlPanel;
	}
	
	public void randomizeGrid() {
		resetGrid();
		this.grid.randomizeGrid();
		repaint();
	}
	
	public void resetGrid() {
		grid.setStartNode(null);
		grid.setEndNode(null);
		for (int i = 0; i < grid.getNumGridCols(); i++) {
			for (int j = 0; j < grid.getNumGridRows(); j++) {
				grid.getNode(i, j).setDefault();
				grid.getNode(i, j).setGCost(0);
				grid.getNode(i, j).setParent(null);
			}
		}
		repaint();
	}
	
	public void resetDefaultColors() {
		for (int i = 0; i < grid.getNumGridCols(); i++) {
			for (int j = 0; j < grid.getNumGridRows(); j++) {
				GridNode node = grid.getNode(i, j);
				if (!node.isDefault() && !node.isStart() && !node.isEnd() && !node.isObstacle()) {
					node.setDefault();
				}
			}
		}
		this.repaint();
	}
	
	public void resetPathColorsToDefault() {
		for (int i = 0; i < grid.getNumGridCols(); i++) {
			for (int j = 0; j < grid.getNumGridRows(); j++) {
				GridNode node = grid.getNode(i, j);
				if (node.isPath()) {
					node.setDefault();
				}
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < grid.getNumGridCols(); i++) {
			for (int j = 0; j < grid.getNumGridRows(); j++) {
				g.drawRect((i * 20) + 1, (j * 20) + 1, 20, 20);
				g.setColor(grid.getNodeColor(i, j));
				g.fillRect((i * 20) + 1, (j * 20) + 1, 19, 19);
			}
		}
	}

	private boolean mouseInGrid(int x, int y) {
		return grid.inBounds(x, y);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// if algorithm is running, ignore
		if (this.pathFindingAlgorithm.isRunning()) {
			return;
		}
		// Reset path nodes to default
		resetPathColorsToDefault();
		resetDefaultColors();
		// get mouse position in grid
		int xPos = (int) Math.floor(e.getPoint().getX() / 20);
		int yPos = (int) Math.floor(e.getPoint().getY() / 20);
		System.out.println("Mouse Released: " + xPos + ", " + yPos + " | Mouse in grid: " + mouseInGrid(xPos,  yPos));
		if (mouseInGrid(xPos, yPos)) {
			switch (controlPanel.whichNodeTypeRadioSelected()) {
				case "start":
					if (grid.getNode(xPos, yPos).isStart()) {
						grid.setStartNode(null);
						grid.getNode(xPos, yPos).setDefault();
					}
					else if (grid.getNode(xPos, yPos).isEnd()) {
						grid.setEndNode(null);
						grid.getNode(xPos, yPos).setStart();
						grid.setStartNode(grid.getNode(xPos, yPos));
					}
					else {
						if (grid.hasStartNode()) {
							grid.getNode(grid.getStartNode().getX(), grid.getStartNode().getY()).setDefault();
						}
						grid.setStartNode(grid.getNode(xPos, yPos));
						grid.getNode(xPos, yPos).setStart();;
					}
					break;
				case "end":
					if (grid.getNode(xPos, yPos).isEnd()) {
						grid.setEndNode(null);
						grid.getNode(xPos, yPos).setDefault();
					}
					else if (grid.getNode(xPos, yPos).isStart()) {
						grid.setStartNode(null);
						grid.getNode(xPos, yPos).setEnd();
						grid.setEndNode(grid.getNode(xPos, yPos));
					}
					else {
						if (grid.hasEndNode()) {
							grid.getNode(grid.getEndNode().getX(), grid.getEndNode().getY()).setDefault();
						}
						grid.setEndNode(grid.getNode(xPos, yPos));
						grid.getNode(xPos, yPos).setEnd();
					}
					break;
				case "obstacle":
					if (grid.getNode(xPos, yPos).isObstacle()) grid.getNode(xPos, yPos).setDefault();
					else grid.getNode(xPos, yPos).setObstacle();
					break;
				default:
					System.out.println("Unknown radio button selected!");
					break;
			}
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}	
	
}
