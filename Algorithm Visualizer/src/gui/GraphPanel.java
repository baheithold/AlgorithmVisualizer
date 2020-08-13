package gui;

import java.awt.BorderLayout;

/**
 * @author Brett Heithold
 *
 */
public class GraphPanel extends VisualizationPanel {
	private static final long serialVersionUID = 1L;

	// Control Panel
	private GraphControlPanel controlPanel;
	
	public GraphPanel(String algorithmName) {
		super(algorithmName);
		controlPanel = new GraphControlPanel(this);
		this.add(controlPanel, BorderLayout.SOUTH);
		
		// select what algorithm to use
		switch (algorithmName) {
			case "dfs":
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
	
}
