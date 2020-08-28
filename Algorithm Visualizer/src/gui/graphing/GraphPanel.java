package gui.graphing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import algorithms.graphing.DepthFirstSearch;
import algorithms.graphing.Edge;
import algorithms.graphing.Vertex;
import gui.VisualizationPanel;

/**
 * @author Brett Heithold
 *
 */
public class GraphPanel extends VisualizationPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	
	// Constants
	private final int MINIMUM_VERTEX_SEPARATION = 10;
	
	// Dialog Message Constants
	private final String WEIGHT_DIALOG_MESSAGE = "Enter an edge weight (Integers only)";

	// Control Panel
	private GraphControlPanel controlPanel;
	
	// Algorithm
	private DepthFirstSearch graphAlgorithm;
	
	// Start and End Vertices
	private Vertex startVertex;
	private Vertex endVertex;
	
	// Vertices and Edges
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	
	// Vertices for use when creating an edge
	private Vertex uVertex;
	private Vertex vVertex;
	
	// Dragging
	private Vertex vertexToMove;
	private Point mouseMovePoint;
	
	// ID
	private static int nextID;
	
	public GraphPanel(String algorithmName) {
		super(algorithmName);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		// get graphing control panel and add to south border
		controlPanel = new GraphControlPanel(this);
		this.add(controlPanel, BorderLayout.SOUTH);
		
		vertices = new ArrayList<Vertex>();
		nextID = 0;
		edges = new ArrayList<Edge>();
		
		startVertex = null;
		endVertex = null;
		
		uVertex = null;
		vVertex = null;
		
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
	
	private Edge containedByEdge(double x, double y) {
		if (!inBounds(x, y)) return null;
		
		// search for edge
		for (Edge edge : edges) {
			if (edge.containsPoint(x, y)) return edge;
		}
		
		// edge DNE at coordinates (x, y), return null
		return null;
	}
	
	private void addVertex(double xPos, double yPos) {
		Vertex v = new Vertex(xPos, yPos, nextID);
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
			nextID++;
			System.out.println("New Vertex: x = " + v.xCentered() + ", y = " + v.yCentered());
		}
	}
	
	private void removeVertex(double xPos, double yPos) {
		Vertex v = containedByVertex(xPos, yPos);
		if (v != null) {
			
			// if v is start or end, set start/end to null
			if (v.isStart()) startVertex = null;
			else if (v.isEnd()) endVertex = null;
			
			// remove edges connected to v
			Iterator<Edge> iter = edges.iterator();
			while (iter.hasNext()) {
				Edge e = iter.next();
				if (e.hasVertexAsEndpoint(v)) {
					System.out.print("Removed edge: ");
					e.print();
					System.out.println();
					iter.remove();
				}
			}
			
			// remove v
			System.out.print("Removed Vertex: ");
			v.print();
			System.out.println();
			vertices.remove(v);
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
		nextID = 0;
		repaint();
	}
	
	public void clearEdges() {
		edges.clear();
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
		return distanceBetweenOrigins <= u.radius() * 2;
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
	
	public void resetEdgeVerticesUV() {
		if (uVertex != null) {
			uVertex.setSelected(false);
			uVertex = null;
		}
		if (vVertex != null) {
			vVertex.setSelected(false);
			vVertex = null;
		}
		repaint();
	}
	
	private int getEdgeWeightFromUser() {
		String userStr = JOptionPane.showInputDialog(null, WEIGHT_DIALOG_MESSAGE, new Random().nextInt(vertices.size() * 2));
		if (userStr == null || userStr.isEmpty()) {
			// user canceled
			// MIN VALUE is used because future algorithms may need to make use of MAX VALUE
			return Integer.MIN_VALUE;
		}
		else return Integer.parseInt(userStr);
	}
	
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Edge edge : edges) {
			edge.draw(g);
		}
		for (Vertex vertex : vertices) {
			vertex.draw(g);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// if algorithm is running, ignore
		if (graphAlgorithm.isRunning()) {
			return;
		}
		
		// get converted mouse position
		MouseEvent convertedE = SwingUtilities.convertMouseEvent(getParent(), e, this);
		double convertedX = convertedE.getPoint().getX();
		double convertedY = convertedE.getPoint().getY();
		System.out.println("Mouse Clicked: " + convertedX + ", " + convertedY);
		
		switch (controlPanel.whichVertexEdgeRadioSelected()) {
			case "vertex":
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
				break;
			case "edge":
				// if left-click, add a new edge between vertices u and v
				if (e.getButton() == MouseEvent.BUTTON1) {
					Vertex v = containedByVertex(convertedX, convertedY);
					// if v is not null, user must be trying to create another edge
					if (v != null) {
						v.setSelected(true);
						if (uVertex == null) uVertex = v;
						else {
							// second vertex is chosen, reset uv selection
							vVertex = v;
							uVertex.setSelected(false);
							vVertex.setSelected(false);
							
							// create new edge from u to v
							Edge newEdge = new Edge(uVertex, vVertex);
							
							// if graph is weighted, get weight from user
							if (controlPanel.isWeighted()) {
								newEdge.setWeighted(true);
								newEdge.setSelected();
								edges.add(newEdge);
								repaint();
								int userEnteredWeight = getEdgeWeightFromUser();
								if (userEnteredWeight > Integer.MIN_VALUE) {
									// the user entered a valid weight
									// set the new edge's weight and deselect edge
									newEdge.setWeight(userEnteredWeight);
									newEdge.setDefault();
								}
								else {
									// user canceled the new edge during weight dialog
									edges.remove(newEdge);
								}
							}
							// otherwise, just add new edge to edges list
							else {
								edges.add(newEdge);
							}
												
							uVertex = null;
							vVertex = null;
						}
					}
					// if edge is NOT null, user must be trying to edit an edge
					else {
						if (controlPanel.isWeighted()) {
							Edge edge = containedByEdge(convertedX, convertedY);
							if (edge != null) {
								edge.setSelected();
								repaint();
								int userEnteredWeight = getEdgeWeightFromUser();
								if (userEnteredWeight > Integer.MIN_VALUE) {
									edge.setWeight(userEnteredWeight);
								}
								else edge.setWeight(0);
								edge.setDefault();
								repaint();
							}
						}
					}
				}
				// if right-click, delete appropriate edge
				else if (e.getButton() == MouseEvent.BUTTON3) {
					Iterator<Edge> iter = edges.iterator();
					while (iter.hasNext()) {
						Edge edge = iter.next();
						if (containedByVertex(convertedX, convertedY) == null) {
							if (edge.containsPoint(convertedX, convertedY)) {
								iter.remove();
								break;
							}
						}
					}
				}
				break;
			default:
				break;
		}
				
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// if algorithm is running, ignore
		if (graphAlgorithm.isRunning()) {
			return;
		}
		
		if (e.getButton() == MouseEvent.BUTTON3) return;
		
		// get converted mouse position
		MouseEvent convertedE = SwingUtilities.convertMouseEvent(getParent(), e, this);
		double convertedX = convertedE.getPoint().getX();
		double convertedY = convertedE.getPoint().getY();
		vertexToMove = containedByVertex(convertedX, convertedY);
		if (vertexToMove == null) return;
		mouseMovePoint = e.getPoint();
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// if algorithm is running, ignore
		if (graphAlgorithm.isRunning()) {
			return;
		}
		
		// check if dragging a vertex
		if (vertexToMove != null) {
			vertexToMove.setSelected(false);
			vertexToMove = null;
			repaint();
			return;
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (vertexToMove == null) return;
		// get converted mouse position
		double dx = e.getX() - mouseMovePoint.getX();
		double dy = e.getY() - mouseMovePoint.getY();
		vertexToMove.move(dx, dy);
		// if vertex is moved out of bounds, move it back
		if (!inBounds(vertexToMove)) {
			vertexToMove.move(-dx, -dy);
		}
		vertexToMove.setSelected(true);
		mouseMovePoint = e.getPoint();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
}
