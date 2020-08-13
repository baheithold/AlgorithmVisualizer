package gui;

/**
 * @author Brett Heithold
 *
 */
public class GraphPanel extends VisualizationPanel {
	private static final long serialVersionUID = 1L;

	public GraphPanel(String algorithmName) {
		super(algorithmName);
		
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
