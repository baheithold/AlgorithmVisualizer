package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;

import pathFindingAlgorithms.Grid;

/**
 * @author Brett Heithold
 *
 */
public class PathFindingPanel extends VisualizationPanel {
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
	}
	
	private void initializeControlPanel() {
		controlPanel = new PathFindingControlPanel(this);
		this.add(controlPanel, BorderLayout.SOUTH);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		for (int i = 1; i < this.windowWidth - 20; i += 20) {
			for (int j = 1; j < this.windowHeight - 120; j += 20) {
				g.drawRect(i, j, 20, 20);
			}
		}
	}
	
}
