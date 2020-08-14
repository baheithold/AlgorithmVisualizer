package gui.graphing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
	
	private void addVertex(double xPos, double yPos) {
		System.out.println("New Vertex: x = " + xPos + ", y = " + yPos + ", diameter = " + controlPanel.getDiameter());
		Vertex v = new Vertex(xPos, yPos, controlPanel.getDiameter());
		vertices.add(v);
	}
	
	public void clearVertices() {
		vertices.clear();
		repaint();
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
		
		// get mouse position and display
		double xPos = Math.floor(e.getPoint().getX());
		double yPos = Math.floor(e.getPoint().getY());
		System.out.println("Mouse Released: " + xPos + ", " + yPos);
		addVertex(xPos, yPos);
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
}
