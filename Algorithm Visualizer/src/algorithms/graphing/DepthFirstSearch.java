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
	SwingWorker<Void, Void> workerThread;
	
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;

	Stack<Vertex> s;
	
	public DepthFirstSearch(GraphPanel panel) {
		super(panel);
		this.vertices = panel.getVertices();
		this.edges = panel.getEdges();
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
				
				// push start node to stack
				s.push(panel.getStartVertex());
				while (!s.isEmpty()) {
					Vertex u = s.pop();
					// if u vertex has not yet been visited, push unvisited neighbors of u onto stack
					if (!u.isVisited()) {
						u.setVisited(true);
						pushUnvisitedNeighborsToStack(u);
					}
				}
				
				System.out.println("DFS Finished!");
				highlightUsedEdges();
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
	
	private void setAllVerticesUnvisited() {
		for (Vertex vertex : vertices) {
			vertex.setVisited(false);
		}
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
	
	private void highlightUsedEdges() {
		for (Edge edge : edges) {
			if (edge.getU().isVisited() && edge.getV().isVisited()) {
				edge.setHighlighted();
			}
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