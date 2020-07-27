package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pathFindingAlgorithms.AStar;
import pathFindingAlgorithms.Grid;

/**
 * @author Brett Heithold
 *
 */
public class PathFindingPanel extends VisualizationPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private String algorithmName;
	private Grid grid;
	
	// GridNode Colors
	private Color DEFAULT_COLOR = Color.lightGray;
	private Color START_COLOR = Color.green;
	private Color END_COLOR = Color.red;
	private Color OBSTACLE_COLOR = Color.darkGray;
	
	// Control Panel
	private PathFindingControlPanel controlPanel;
	public AStar algorithmAStar;

	public PathFindingPanel(String algorithmName) {
		this.setLayout(new BorderLayout());
		this.algorithmName = algorithmName;
		this.grid = new Grid(this, (this.windowHeight / 20) - 6, (this.windowWidth / 20) - 1);
		initializeControlPanel();
		algorithmAStar = new AStar(this);
		this.addMouseListener(this);
	}
	
	private void initializeControlPanel() {
		controlPanel = new PathFindingControlPanel(this);
		this.add(controlPanel, BorderLayout.SOUTH);
	}
	
	public void randomizeGrid() {
		resetGrid();
		this.grid.randomizeGrid();
		repaint();
	}
	
	public void resetGrid() {
		for (int i = 0; i < grid.getNumGridCols(); i++) {
			for (int j = 0; j < grid.getNumGridRows(); j++) {
				grid.setNodeColor(i, j, DEFAULT_COLOR);
			}
		}
		repaint();
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
		if (x >= 0 && x < grid.getNumGridCols()) {
			if (y >= 0 && y < grid.getNumGridRows()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// get mouse position in grid
		int xPos = (int) Math.floor(e.getPoint().getX() / 20);
		int yPos = (int) Math.floor(e.getPoint().getY() / 20);
		System.out.println("Mouse Released: " + xPos + ", " + yPos + " | Mouse in grid: " + mouseInGrid(xPos,  yPos));
		if (mouseInGrid(xPos, yPos)) {
			switch (controlPanel.whichRadioSelected()) {
				case "start":
					if (grid.getNodeColor(xPos, yPos) == START_COLOR) {
						grid.setStartNode(null);
						grid.setNodeColor(xPos, yPos, DEFAULT_COLOR);
					}
					else {
						if (grid.hasStartNode()) {
							grid.setNodeColor(grid.getStartNode().getXLocation(), grid.getStartNode().getYLocation(), DEFAULT_COLOR);
						}
						grid.setStartNode(grid.getNode(xPos, yPos));
						grid.setNodeColor(xPos, yPos, START_COLOR);
					}
					break;
				case "end":
					if (grid.getNodeColor(xPos, yPos) == END_COLOR) {
						grid.setEndNode(null);
						grid.setNodeColor(xPos, yPos, DEFAULT_COLOR);
					}
					else {
						if (grid.hasEndNode()) {
							grid.setNodeColor(grid.getEndNode().getXLocation(), grid.getEndNode().getYLocation(), DEFAULT_COLOR);
						}
						grid.setEndNode(grid.getNode(xPos, yPos));
						grid.setNodeColor(xPos, yPos, END_COLOR);
					}
					break;
				case "obstacle":
					if (grid.getNodeColor(xPos, yPos) == OBSTACLE_COLOR) grid.setNodeColor(xPos, yPos, DEFAULT_COLOR);
					else grid.setNodeColor(xPos, yPos, OBSTACLE_COLOR);
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
