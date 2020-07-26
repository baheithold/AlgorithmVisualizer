package gui;

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

	public PathFindingPanel(String algorithmName) {
		this.algorithmName = algorithmName;
		this.grid = new Grid(this, this.windowWidth / 20, this.windowHeight / 20);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		for (int i = 0; i <= this.windowWidth; i += 20) {
			for (int j = 0; j <= this.windowHeight - 60; j += 20) {
				g.drawRect(i, j, 20, 20);
			}
		}
	}
	
}
