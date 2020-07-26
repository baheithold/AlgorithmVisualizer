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
	private final int MAX_DELAY = 1000;
	private final int DEFAULT_DELAY = 100;
	private PathFindingPanel panel;

	// JLabels
	private JLabel speedJLabel;
	
	// JSliders
	private JSlider speedJSlider;
	
	// JButtons
	private JButton runJButton;
	private JButton stepJButton;
	private JButton resetJButton;
	
	// JRadioButtons
	private ButtonGroup radioGroup;
	private JRadioButton startRadioButton;
	private JRadioButton endRadioButton;
	private JRadioButton obstacleRadioButton;
	
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
		speedJLabel = new JLabel("Speed");
	}
	
	private void initializeSliders() {
		speedJSlider = new JSlider(MIN_DELAY, MAX_DELAY, DEFAULT_DELAY);
		speedJSlider.setMajorTickSpacing(500);
		speedJSlider.setMinorTickSpacing(100);
		speedJSlider.setPaintTicks(true);
		speedJSlider.setPaintLabels(true);
		speedJSlider.addChangeListener(this);
	}
	
	private void initializeButtons() {
		runJButton = new JButton("Run");
		runJButton.addActionListener(this);
		stepJButton = new JButton("Step");
		stepJButton.addActionListener(this);
		resetJButton = new JButton("Reset");
		resetJButton.addActionListener(this);
	}
	
	private void initializeRadioButtons() {
		radioGroup = new ButtonGroup();
		startRadioButton = new JRadioButton("Start");
		endRadioButton = new JRadioButton("End");
		obstacleRadioButton = new JRadioButton("Obstacle");
	}
	
	private void constructControlPanel() {
		// Speed slider
		this.add(Box.createHorizontalStrut(10));
		this.add(speedJLabel);
		this.add(speedJSlider);
		
		// Separator
		this.add(Box.createHorizontalStrut(10));
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(Box.createHorizontalStrut(10));
		
		// radio buttons
		radioGroup.add(startRadioButton);
		radioGroup.add(endRadioButton);
		radioGroup.add(obstacleRadioButton);
		this.add(startRadioButton);
		this.add(endRadioButton);
		this.add(obstacleRadioButton);
		
		// Separator
		this.add(Box.createHorizontalStrut(10));
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(Box.createHorizontalStrut(10));
		
		// Run Button
		this.add(runJButton);
		this.add(Box.createHorizontalStrut(10));
		
		// Step Button
		this.add(stepJButton);
		this.add(Box.createHorizontalStrut(10));
		
		// Reset Button
		this.add(resetJButton);
		this.add(Box.createHorizontalStrut(10));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
	}
	
}
