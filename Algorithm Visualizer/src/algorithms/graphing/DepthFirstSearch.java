package algorithms.graphing;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gui.graphing.GraphPanel;

/**
 * @author Brett Heithold
 *
 */
public class DepthFirstSearch extends GraphAlgorithm implements Runnable {
	private SwingWorker<Void, Void> workerThread;

	// Stack
	Stack<Vertex> s;
	
	public DepthFirstSearch(GraphPanel panel) {
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
				
				// create empty stack
				s = new Stack<Vertex>();
				
				// set all vertices to unvisited
				setAllVerticesUnvisited();
				
				// used for highlighting edges
				ArrayList<Vertex> alreadyVisitedQueue = new ArrayList<Vertex>();
				
				// push start node to stack
				s.push(panel.getStartVertex());
				while (!s.empty()) {
					Vertex u = s.pop();
					// if u vertex has not yet been visited, push unvisited neighbors of u onto stack
					if (!u.isVisited()) {
						u.setVisited(true);
						u.setHighlighted();
						// find and highlight appropriate edge
						if (!u.isStart()) {
							Edge edgeToHighlight = null;
							for (Vertex vertex : alreadyVisitedQueue) {
								edgeToHighlight = findEdge(vertex, u);
								if (edgeToHighlight != null) break;
							}
							if (edgeToHighlight == null) {
								for (Vertex vertex : alreadyVisitedQueue) {
									edgeToHighlight = findEdge(u, vertex);
									if (edgeToHighlight != null) break;
								}
							}
							if (edgeToHighlight != null) {
								edgeToHighlight.setHighlighted();
							}
						}
						pushUnvisitedNeighborsToStack(u);
						alreadyVisitedQueue.add(u);
					}
					publish();
					Thread.sleep(panel.getCurrentDelay());
				}
				
				System.out.println("DFS Finished!");
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
	
	private ArrayList<Vertex> getUnvisitedNeighbors(Vertex u) {
		ArrayList<Vertex> unvisitedList = new ArrayList<Vertex>();
		for (Edge edge : edges) {
			if (edge.getU().getID() == u.getID() || edge.getV().getID() == u.getID()) {
				if (!edge.getU().isVisited()) {
					unvisitedList.add(edge.getU());
				}
				else if (!edge.getV().isVisited()) {
					unvisitedList.add(edge.getV());
				}
			}
		}
		return unvisitedList;
	}
	
	private void pushUnvisitedNeighborsToStack(Vertex u) {
		ArrayList<Vertex> neighbors = getUnvisitedNeighbors(u);
		for (Vertex vertex : neighbors) {
			s.push(vertex);
		}
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