package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class PathFindingControlPanel extends JPanel implements ActionListener, ChangeListener {
	private static final long serialVersionUID = 1L;
	private final int MIN_DELAY = 1;
	private final int MAX_DELAY = 200;
	private final int DEFAULT_DELAY = 100;
	private PathFindingPanel panel;

	// JLabels
	private JLabel delayJLabel;
	
	// JSliders
	private JSlider delayJSlider;
	
	// JButtons
	private JButton runJButton;
	private JButton randomizeJButton;
	private JButton resetJButton;
	
	// Node type JRadioButtons
	private ButtonGroup nodeTypeRadioGroup;
	private JRadioButton startRadioButton;
	private JRadioButton endRadioButton;
	private JRadioButton obstacleRadioButton;
	
	// Heuristic JRadioButtons
	private ButtonGroup heuristicRadioGroup;
	private JRadioButton manhattanRadioButton;
	private JRadioButton diagonalRadioButton;
	
	public PathFindingControlPanel(PathFindingPanel panel) {
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
	}
	
	private void initializeSliders() {
		delayJSlider = new JSlider(MIN_DELAY, MAX_DELAY, DEFAULT_DELAY);
		delayJSlider.setToolTipText("Select amount of delay between algorithm steps");
		delayJSlider.setMajorTickSpacing(100);
		delayJSlider.setMinorTickSpacing(10);
		delayJSlider.setPaintTicks(true);
		delayJSlider.setPaintLabels(true);
		delayJSlider.addChangeListener(this);
	}
	
	private void initializeButtons() {
		runJButton = new JButton("Run");
		runJButton.addActionListener(this);
		randomizeJButton = new JButton("Randomize");
		randomizeJButton.addActionListener(this);
		resetJButton = new JButton("Reset");
		resetJButton.addActionListener(this);
	}
	
	private void initializeRadioButtons() {
		// initialize node type radio buttons
		nodeTypeRadioGroup = new ButtonGroup();
		startRadioButton = new JRadioButton("Start");
		startRadioButton.setToolTipText("Select GridNode type");
		startRadioButton.setSelected(true);
		startRadioButton.addActionListener(this);
		endRadioButton = new JRadioButton("End");
		endRadioButton.setToolTipText("Select GridNode type");
		endRadioButton.addActionListener(this);
		obstacleRadioButton = new JRadioButton("Obstacle");
		obstacleRadioButton.setToolTipText("Select GridNode type");
		obstacleRadioButton.addActionListener(this);
		// initialize heuristic radio buttons
		heuristicRadioGroup = new ButtonGroup();
		manhattanRadioButton = new JRadioButton("Manhattan");
		manhattanRadioButton.setToolTipText("Select a heuristic");
		manhattanRadioButton.setSelected(true);
		manhattanRadioButton.addActionListener(this);
		diagonalRadioButton = new JRadioButton("Diagonal");
		diagonalRadioButton.setToolTipText("Select a heuristic");
		diagonalRadioButton.addActionListener(this);
	}
	
	private void constructControlPanel() {
		// Speed slider
		this.add(Box.createHorizontalStrut(10));
		this.add(delayJLabel);
		this.add(delayJSlider);
		
		// Separator
		this.add(Box.createHorizontalStrut(10));
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(Box.createHorizontalStrut(10));
		
		// Node type radio buttons
		nodeTypeRadioGroup.add(startRadioButton);
		nodeTypeRadioGroup.add(endRadioButton);
		nodeTypeRadioGroup.add(obstacleRadioButton);
		this.add(startRadioButton);
		this.add(endRadioButton);
		this.add(obstacleRadioButton);
		
		// Separator
		this.add(Box.createHorizontalStrut(10));
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(Box.createHorizontalStrut(10));
		
		// Heuristic radio buttons
		heuristicRadioGroup.add(manhattanRadioButton);
		heuristicRadioGroup.add(diagonalRadioButton);
		this.add(manhattanRadioButton);
		this.add(diagonalRadioButton);
		
		// Separator
		this.add(Box.createHorizontalStrut(10));
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(Box.createHorizontalStrut(10));
		
		// Run Button
		this.add(runJButton);
		this.add(Box.createHorizontalStrut(10));
		
		// Randomize Button
		this.add(randomizeJButton);
		this.add(Box.createHorizontalStrut(10));
		
		// Reset Button
		this.add(resetJButton);
		this.add(Box.createHorizontalStrut(10));
	}

	public String whichNodeTypeRadioSelected() {
		if (startRadioButton.isSelected()) return "start";
		else if (endRadioButton.isSelected()) return "end";
		else if (obstacleRadioButton.isSelected()) return "obstacle";
		else return "unknown";
	}
	
	public String whichHeuristicRadioSelected() {
		if (manhattanRadioButton.isSelected()) return "manhattan";
		else if (diagonalRadioButton.isSelected()) return "diagonal";
		else return "unknown";
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == runJButton) {
			System.out.println("Button Pressed: Run");
			if (!panel.pathFindingAlgorithm.isRunning()) {
				panel.resetPathColorsToDefault();
				panel.resetDefaultColors();
				panel.pathFindingAlgorithm.runPathFindingAlgorithm();
			}
		}
		else if (e.getSource() == randomizeJButton) {
			System.out.println("Button Pressed: Randomize");
			if (panel.pathFindingAlgorithm.isRunning()) {
				panel.pathFindingAlgorithm.killPathFindingAlgorithm();
			}
			panel.randomizeGrid();
		}
		else if (e.getSource() == resetJButton) {
			System.out.println("Button Pressed: Reset");
			if (panel.pathFindingAlgorithm.isRunning()) {
				panel.pathFindingAlgorithm.killPathFindingAlgorithm();				
			}
			panel.resetGrid();
		}
		else if (e.getSource() == startRadioButton) {
			System.out.println("Radio Button Selected: Start");
		}
		else if (e.getSource() == endRadioButton) {
			System.out.println("Radio Button Selected: End");
		}
		else if (e.getSource() == obstacleRadioButton) {
			System.out.println("Radio Button Selected: Obstacle");
		}
		else if (e.getSource() == manhattanRadioButton) {
			System.out.println("Radio Button Selected: Manhattan");
		}
		else if (e.getSource() == diagonalRadioButton) {
			System.out.println("Radio Button Selected: Diagonal");
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == delayJSlider) {
			System.out.println("Delay Slider Changed: " + delayJSlider.getValue());
		}
	}
	
}
