package gui.graphing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import algorithms.graphing.DepthFirstSearch;
import algorithms.graphing.Vertex;
import gui.VisualizationPanel;

/**
 * @author Brett Heithold
 *
 */
public class GraphPanel extends VisualizationPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	// Constants
	private final int MINIMUM_VERTEX_SEPARATION = 10;

	// Control Panel
	private GraphControlPanel controlPanel;
	
	// Algorithm
	private DepthFirstSearch graphAlgorithm;
	
	// Start and End Vertices
	private Vertex startVertex;
	private Vertex endVertex;
	
	// Vertices
	private ArrayList<Vertex> vertices;
	
	public GraphPanel(String algorithmName) {
		super(algorithmName);
		this.addMouseListener(this);
		controlPanel = new GraphControlPanel(this);
		this.add(controlPanel, BorderLayout.SOUTH);
		vertices = new ArrayList<Vertex>();
		startVertex = null;
		endVertex = null;
		
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
	
	private Vertex containedByVertex(double x, double y) {
		// coordinates are out of bounds, return null
		if (!inBounds(x, y)) return null;
		
		// search for vertex
		for (Vertex v : vertices) {
			if (v.containsCoordinates(x, y)) return v;
		}
		
		// vertex DNE at coordinates (x, y), return null
		return null;
	}
	
	private void addVertex(double xPos, double yPos) {
		Vertex v = new Vertex(xPos, yPos, controlPanel.DIAMETER);
		if (inBounds(v) && !overlapExists(v)) {
			// set correct vertex type
			switch (controlPanel.whichVertexTypeRadioSelected()) {
				case "default":
					v.setDefault();
					break;
				case "start":
					v.setStart();
					if (startVertex != null) startVertex.setDefault();
					startVertex = v;
					break;
				case "end":
					v.setEnd();
					if (endVertex != null) endVertex.setDefault();
					endVertex = v;
					break;
				default:
					System.out.println("addVertex: Unknown vertex type");
					break;
			}
			
			// add vertex to vertices list
			vertices.add(v);
			System.out.println("New Vertex: x = " + v.xCentered() + ", y = " + v.yCentered());
		}
	}
	
	private void removeVertex(double xPos, double yPos) {
		Vertex v = containedByVertex(xPos - this.controlPanel.DIAMETER / 2, yPos - this.controlPanel.DIAMETER / 2);
		if (v != null) {
			if (v.isStart()) startVertex = null;
			else if (v.isEnd()) endVertex = null;
			vertices.remove(v);
			System.out.println("Removed Vertex centered at: x = " + v.xCentered() + " y = " + v.yCentered());
		}
		else {
			System.out.println("Vertex DNE at coordinates: " + xPos + ", " + yPos);
		}
	}
	
	private void updateVertex(Vertex v) {
		switch (controlPanel.whichVertexTypeRadioSelected()) {
			case "default":
				if (v.isStart()) {
					startVertex = null;
					v.setDefault();
				}
				else if (v.isEnd()) {
					endVertex = null;
					v.setDefault();
				}
				break;
			case "start":
				if (v.isStart()) {
					startVertex = null;
					v.setDefault();
				}
				else if (v.isEnd()) {
					endVertex = null;
					startVertex.setDefault();
					v.setStart();
					startVertex = v;
				}
				else {
					if (startVertex != null) {
						startVertex.setDefault();
					}
					v.setStart();
					startVertex = v;
				}
				break;
			case "end":
				if (v.isStart()) {
					startVertex = null;
					endVertex.setDefault();
					v.setEnd();
					endVertex = v;
				}
				else if (v.isEnd()) {
					endVertex = null;
					v.setDefault();
				}
				else {
					if (endVertex != null) {
						endVertex.setDefault();
					}
					v.setEnd();
					endVertex = v;
				}
				break;
			default:
				break;
		}
	}
	
	public void clearVertices() {
		vertices.clear();
		repaint();
	}
	
	public boolean inBounds(Vertex v) {
		boolean xInBounds = false;
		boolean yInBounds = false;
		if (v.xCentered() >= 0 && v.xCentered() + 65 <= WINDOW_WIDTH) {
			xInBounds = true;
		}
		if (v.yCentered() + v.radius() * 2 + this.controlPanel.getHeight() + 65 <= WINDOW_HEIGHT && v.yCentered() >= 0) {
			yInBounds = true;
		}
		return xInBounds && yInBounds;
	}
	
	public boolean inBounds(double xPos, double yPos) {
		boolean xInBounds = false;
		boolean yInBounds = false;
		if (xPos <= WINDOW_WIDTH && xPos >= 0) {
			xInBounds = true;
		}
		if (yPos <= WINDOW_HEIGHT && yPos >= 0) {
			yInBounds = true;
		}
		return xInBounds && yInBounds;
	}
	
	private boolean verticesOverlap(Vertex u, Vertex v) {
		double ux = u.xCentered();
		double uy = u.yCentered();
		double vx = v.xCentered();
		double vy = v.yCentered();
		double distanceBetweenOrigins = Math.sqrt(Math.pow(vx - ux, 2) + Math.pow(vy - uy, 2)) - MINIMUM_VERTEX_SEPARATION;
		return distanceBetweenOrigins <= controlPanel.DIAMETER;
	}
	
	private boolean overlapExists(Vertex u) {
		for (Vertex v : vertices) {
			if (verticesOverlap(u, v)) {
				System.out.println("Overlap exists!");
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Vertex vertex : vertices) {
			vertex.draw(g);
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
		
		// get converted mouse position
		MouseEvent convertedE = SwingUtilities.convertMouseEvent(getParent(), e, this);
		double convertedX = convertedE.getPoint().getX();
		double convertedY = convertedE.getPoint().getY();
		System.out.println("Mouse Released: " + convertedX + ", " + convertedY);
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			// Left mouse button click
			Vertex v = containedByVertex(convertedX, convertedY);
			if (v != null) {
				// update vertex v
				updateVertex(v);
			}
			else addVertex(convertedX, convertedY);
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			// Right mouse button click
			removeVertex(convertedX, convertedY);
		}
		
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
}
