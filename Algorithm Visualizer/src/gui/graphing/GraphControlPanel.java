package gui.graphing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Brett Heithold
 *
 */
public class GraphControlPanel extends JPanel implements ActionListener, ChangeListener {
	private static final long serialVersionUID = 1L;
	
	// Number of Items slider Constants
	private final int MIN_NUM_ITEMS = 0;
	private final int MAX_NUM_ITEMS = 50;
	private final int DEFAULT_NUM_ITEMS = 10;
	private final int NUM_ITEMS_MAJOR_TICK = 10;
	private final int NUM_ITEMS_MINOR_TICK = 5;
	
	// Graph Panel
	private GraphPanel panel;
	
	// JLabels
	private JLabel delayJLabel, numItemsJLabel, vertexEdgeJLabel, vertexTypeJLabel;
	
	// JSliders
	private JSlider delayJSlider, numItemsJSlider;
	
	// VertexEdge Radio
	private ButtonGroup vertexEdgeRadioGroup;
	private JRadioButton vertexRadioButton;
	private JRadioButton edgeRadioButton;
	
	// Vertex Type Radio
	private ButtonGroup vertexTypeRadioGroup;
	private JRadioButton defaultRadioButton;
	private JRadioButton startRadioButton;
	private JRadioButton endRadioButton;
	
	// JButtons
	private JButton runJButton, resetJButton;

	public GraphControlPanel(GraphPanel panel) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.panel = panel;
		initializeLabels();
		initializeSliders();
		initializeButtons();
		initializeRadioButtons();
		constructControlPanel();
	}

	private void initializeLabels() {
		delayJLabel = new JLabel("Delay (ms)");
		numItemsJLabel = new JLabel("Number of Items");
		vertexEdgeJLabel = new JLabel("Vertex or Edge?");
		vertexTypeJLabel = new JLabel("Vertex Type");
	}
	
	private void initializeSliders() {
		// delay slider
		delayJSlider = new JSlider(panel.MIN_DELAY, panel.MAX_DELAY, panel.DEFAULT_DELAY);
		delayJSlider.setMajorTickSpacing(panel.DELAY_MAJOR_TICK_SPACING);
		delayJSlider.setMinorTickSpacing(panel.DELAY_MINOR_TICK_SPACING);
		delayJSlider.setPaintTicks(true);
		delayJSlider.setPaintLabels(true);
		delayJSlider.addChangeListener(this);
		
		// number of items slider
		numItemsJSlider = new JSlider(MIN_NUM_ITEMS, MAX_NUM_ITEMS, DEFAULT_NUM_ITEMS);
		numItemsJSlider.setMajorTickSpacing(NUM_ITEMS_MAJOR_TICK);
		numItemsJSlider.setMinorTickSpacing(NUM_ITEMS_MINOR_TICK);
		numItemsJSlider.setPaintTicks(true);
		numItemsJSlider.setPaintLabels(true);
		numItemsJSlider.addChangeListener(this);
	}
	
	private void initializeRadioButtons() {
		// VertexEdge Radio
		vertexEdgeRadioGroup = new ButtonGroup();
		vertexRadioButton = new JRadioButton("Vertex");
		vertexRadioButton.setToolTipText("Vertex or Edge?");
		vertexRadioButton.setSelected(true);
		vertexRadioButton.addActionListener(this);
		edgeRadioButton = new JRadioButton("Edge");
		edgeRadioButton.setToolTipText("Vertex or Edge");
		edgeRadioButton.addActionListener(this);
		
		// Vertex Type Radio
		vertexTypeRadioGroup = new ButtonGroup();
		defaultRadioButton = new JRadioButton("Default");
		defaultRadioButton .setToolTipText("Select Vertex Type");
		defaultRadioButton.setSelected(true);
		defaultRadioButton.addActionListener(this);
		startRadioButton = new JRadioButton("Start");
		startRadioButton.setToolTipText("Select Vertex type");
		startRadioButton.addActionListener(this);
		endRadioButton = new JRadioButton("End");
		endRadioButton.setToolTipText("Select Vertex type");
		endRadioButton.addActionListener(this);
	}
	
	private void initializeButtons() {
		runJButton = new JButton("Run");
		runJButton.addActionListener(this);
		resetJButton = new JButton("Reset");
		resetJButton.addActionListener(this);
	}
	
	private void constructControlPanel() {
		// add border around control panel
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
		
		// Delay Slider Panel
		add(constructDelayPanel());

		// Separator
		add(new JSeparator(SwingConstants.VERTICAL));
		
		// Number of Items Panel
		add(constructNumberOfItemsPanel());
		
		// Separator
		add(new JSeparator(SwingConstants.VERTICAL));
		
		add(constructVertexEdgePanel());
		
		// Separator
		add(new JSeparator(SwingConstants.VERTICAL));
		
		// Vertex Type Panel
		add(constructVertexTypePanel());
		
		// Separator
		add(new JSeparator(SwingConstants.VERTICAL));
		
		// Button Panel
		this.add(Box.createHorizontalStrut(10));
		add(constructButtonsPanel());
		this.add(Box.createHorizontalStrut(10));
		
	}
	
	private JPanel constructDelayPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(delayJLabel);
		panel.add(Box.createVerticalStrut(5));
		panel.add(delayJSlider);
		return panel;
	}
	
	private JPanel constructNumberOfItemsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(numItemsJLabel);
		panel.add(Box.createVerticalStrut(5));
		panel.add(numItemsJSlider);
		return panel;
	}
	
	private JPanel constructVertexEdgeRadioPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		vertexEdgeRadioGroup.add(vertexRadioButton);
		vertexEdgeRadioGroup.add(edgeRadioButton);
		panel.add(vertexRadioButton);
		panel.add(edgeRadioButton);
		return panel;
	}
	
	private JPanel constructVertexEdgePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(vertexEdgeJLabel);
		panel.add(constructVertexEdgeRadioPanel());
		return panel;
	}
	
	private JPanel constructVertexTypeRadioPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		vertexTypeRadioGroup.add(defaultRadioButton);
		vertexTypeRadioGroup.add(startRadioButton);
		vertexTypeRadioGroup.add(endRadioButton);
		panel.add(defaultRadioButton);
		panel.add(startRadioButton);
		panel.add(endRadioButton);
		return panel;
	}
	
	private JPanel constructVertexTypePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(vertexTypeJLabel);
		panel.add(constructVertexTypeRadioPanel());
		return panel;
	}
	
	private JPanel constructButtonsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		// Run Button
		panel.add(runJButton);
		panel.add(Box.createHorizontalStrut(10));
				
		// Reset Button
		panel.add(resetJButton);
		
		return panel;
	}
	
	public String whichVertexTypeRadioSelected() {
		if (startRadioButton.isSelected()) return "start";
		else if (endRadioButton.isSelected()) return "end";
		else if (defaultRadioButton.isSelected()) return "default";
		else return "unknown";
	}
	
	public String whichVertexEdgeRadioSelected() {
		if (vertexRadioButton.isSelected()) return "vertex";
		else if (edgeRadioButton.isSelected()) return "edge";
		else return "unknown";
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == delayJSlider) {
			System.out.println("Delay Slider Changed: " + delayJSlider.getValue());
		}
		else if (e.getSource() == numItemsJSlider) {
			System.out.println("Items Slider Changed: " + numItemsJSlider.getValue());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == runJButton) {
			System.out.println("Button Pressed: " + "Run");
		}
		else if (e.getSource() == resetJButton) {
			System.out.println("Button Pressed: " + "Reset");
			panel.clearVertices();
			panel.clearEdges();
		}
		else if (e.getSource() == vertexRadioButton) {
			panel.resetEdgeVerticesUV();
			System.out.println("Vertex Radio Button Selected");
		}
		else if (e.getSource() == edgeRadioButton) {
			System.out.println("Edge Radio Button Selected");
		}
		else if (e.getSource() == defaultRadioButton) {
			System.out.println("Vertex Type Radio Button Selected: Default");
		}
		else if (e.getSource() == startRadioButton) {
			System.out.println("Vertex Type Radio Button Selected: Start");
		}
		else if (e.getSource() == endRadioButton) {
			System.out.println("Vertex Type Radio Button Selected: End");
		}
	}

}
