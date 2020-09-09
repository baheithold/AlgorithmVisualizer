package algorithms.graphing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gui.graphing.GraphPanel;

/**
 * @author Brett Heithold
 *
 */
public class BreadthFirstSearch extends GraphAlgorithm implements Runnable {
	private SwingWorker<Void, Void> workerThread;
	
	public BreadthFirstSearch(GraphPanel panel) {
		super(panel);
	}

	@Override
	public void run() {
		workerThread = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				//if start or end node is missing, return
				if (!verifyStartEndVerticesExist(true, false)) {
					setRunning(false);
					return null;
				}
				
				// Queue
				Queue<Vertex> q = new LinkedList<Vertex>();
				
				// set start vertex as visited
				panel.getStartVertex().setVisited(true);
				q.add(panel.getStartVertex());
				while (!q.isEmpty()) {
					Vertex v = q.remove();
					v.setSelected(true);
					publish();
					Thread.sleep(panel.getCurrentDelay());
					for (Vertex vertex : getNeighbors(v)) {
						vertex.setHighlighted();
						publish();
						Thread.sleep(panel.getCurrentDelay());
						if (!vertex.isVisited()) {
							vertex.setVisited(true);
							vertex.setParent(v);
							q.add(vertex);
						}
					}
					v.setSelected(false);
					v.setHighlighted();
					publish();
					Thread.sleep(panel.getCurrentDelay());
				}
				
				System.out.println("BFS Finished!");
				publish();
				setRunning(false);
				return null;
			}

			@Override
			protected void process(List<Void> chunks) {
				panel.repaint();
			}
					
		};
		
		workerThread.execute();
		
	}
	
	private ArrayList<Vertex> getNeighbors(Vertex u) {
		ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
		for (Edge edge : edges) {
			if (edge.getU().getID() == u.getID() || edge.getV().getID() == u.getID()) {
				if (edge.getU().getID() == u.getID()) {
					neighbors.add(edge.getV());
				}
				else if (edge.getV().getID() == u.getID()) {
					neighbors.add(edge.getU());
				}
			}
		}
		return neighbors;
	}

	@Override
	public void runAlgorithm() {
		setRunning(true);
		SwingUtilities.invokeLater(this);
	}

	@Override
	public void killAlgorithm() {
		setRunning(false);
		workerThread.cancel(true);
	}

}
