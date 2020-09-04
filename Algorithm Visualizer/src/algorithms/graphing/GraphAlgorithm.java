package algorithms.graphing;

import algorithms.Algorithm;
import gui.graphing.GraphPanel;

/**
 * @author Brett Heithold
 *
 */
public abstract class GraphAlgorithm extends Algorithm{
	protected GraphPanel panel;
	
	public GraphAlgorithm(GraphPanel panel) {
		super(panel);
		this.panel = panel;
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

}
