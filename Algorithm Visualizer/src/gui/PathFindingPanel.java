package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pathFindingAlgorithms.Grid;

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

	public PathFindingPanel(String algorithmName) {
		this.setLayout(new BorderLayout());
		this.algorithmName = algorithmName;
		this.grid = new Grid(this, (this.windowHeight / 20) - 6, (this.windowWidth / 20) - 1);
		initializeControlPanel();
		this.addMouseListener(this);
	}
	
	private void initializeControlPanel() {
		controlPanel = new PathFindingControlPanel(this);
		this.add(controlPanel, BorderLayout.SOUTH);
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
					grid.setNodeColor(xPos, yPos, Color.green);
					break;
				case "end":
					grid.setNodeColor(xPos, yPos, Color.red);
					break;
				case "obstacle":
					grid.setNodeColor(xPos, yPos, Color.darkGray);
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
