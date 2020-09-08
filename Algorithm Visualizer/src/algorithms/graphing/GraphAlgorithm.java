package algorithms.graphing;

import java.util.ArrayList;

import algorithms.Algorithm;
import gui.graphing.GraphPanel;

/**
 * @author Brett Heithold
 *
 */
public abstract class GraphAlgorithm extends Algorithm{
	protected GraphPanel panel;
	
	protected ArrayList<Vertex> vertices;
	protected ArrayList<Edge> edges;
	
	public GraphAlgorithm(GraphPanel panel) {
		super(panel);
		this.panel = panel;
		this.vertices = panel.getVertices();
		this.edges = panel.getEdges();
	}

	protected boolean verifyStartEndVerticesExist(boolean needsStartVertex, boolean needsEndVertex) {
		if (needsStartVertex) {
			if (!panel.hasStartVertex()) {
				System.out.println("Missing Start Vertex!");
				return false;
			}
		}
		if (needsEndVertex) {
			if (!panel.hasEndVertex()) {
				System.out.println("Missing End Vertex!");
				return false;
			}
		}
		
		return true;
	}
	
	protected void setAllVerticesUnvisited() {
		for (Vertex vertex : vertices) {
			vertex.setVisited(false);
		}
	}
	
	protected Edge findEdge(Vertex v1, Vertex v2) {
		for (Edge edge : edges) {
			if (edge.getU().getID() == v1.getID() && edge.getV().getID() == v2.getID()) {
				return edge;
			}
		}
		return null;
	}

}
