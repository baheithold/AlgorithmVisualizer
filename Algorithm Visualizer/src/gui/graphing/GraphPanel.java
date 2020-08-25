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

	// Control Panel
	private GraphControlPanel controlPanel;
	
	// Algorithm
	private DepthFirstSearch graphAlgorithm;
	
	// Vertices
	private ArrayList<Vertex> vertices;
	
	public GraphPanel(String algorithmName) {
		super(algorithmName);
		this.addMouseListener(this);
		controlPanel = new GraphControlPanel(this);
		this.add(controlPanel, BorderLayout.SOUTH);
		vertices = new ArrayList<Vertex>();
		
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
			if (x <= v.xCentered() + v.radius() && x >= v.xCentered() - v.radius()) {
				if (y <= v.yCentered() + v.radius() && y >= v.yCentered() - v.radius()) {
					return v;
					
				}
			}
		}
		
		// vertex DNE at coordinates (x, y), return null
		return null;
	}
	
	private void addVertex(double xPos, double yPos) {
		Vertex v = new Vertex(xPos, yPos, controlPanel.getDiameter());
		if (inBounds(v)) {
			vertices.add(v);
			System.out.println("New Vertex: x = " + v.xCentered() + ", y = " + v.yCentered() + ", diameter = " + controlPanel.getDiameter());
		}
	}
	
	private void removeVertex(double xPos, double yPos) {
		Vertex v = containedByVertex(xPos - this.controlPanel.getDiameter() / 2, yPos - this.controlPanel.getDiameter() / 2);
		if (v != null) {
			vertices.remove(v);
			System.out.println("Removed Vertex centered at: x = " + v.xCentered() + " y = " + v.yCentered());
		}
		else {
			System.out.println("Vertex DNE at coordinates: " + xPos + ", " + yPos);
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
			addVertex(convertedX, convertedY);
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
