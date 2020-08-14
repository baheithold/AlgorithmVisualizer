package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import graphAlgorithms.DepthFirstSearch;

/**
 * @author Brett Heithold
 *
 */
public class GraphPanel extends VisualizationPanel implements MouseListener {
	private static final long serialVersionUID = 1L;

	// Control Panel
	private GraphControlPanel controlPanel;
	
	// Algorithm
	private DepthFirstSearch graphAlgorithm;
	
	public GraphPanel(String algorithmName) {
		super(algorithmName);
		controlPanel = new GraphControlPanel(this);
		this.add(controlPanel, BorderLayout.SOUTH);
		this.addMouseListener(this);
		
		// select what algorithm to use
		switch (algorithmName) {
			case "bfs":
				break;
			case "dfs":
				graphAlgorithm = new DepthFirstSearch(this);
				break;
			case "dijkstra":
				break;
			case "prim":
				break;
			case "kruskal":
				break;
			case "bellman-ford":
				break;
			default:
				break;
		}
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
		if (graphAlgorithm.isRunning()) {
			return;
		}
		
		// get mouse position and display
		double xPos = Math.floor(e.getPoint().getX());
		double yPos = Math.floor(e.getPoint().getY());
		System.out.println("Mouse Released: " + xPos + ", " + yPos);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
}
