package gui;

/**
 * @author Brett Heithold
 *
 */
public class GraphPanel extends VisualizationPanel {
	private static final long serialVersionUID = 1L;
	private String algorithmName;

	public GraphPanel(String algorithmName) {
		this.algorithmName = algorithmName;
		
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
