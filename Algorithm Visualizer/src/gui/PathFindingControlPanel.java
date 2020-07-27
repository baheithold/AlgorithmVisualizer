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
	private JLabel delayJLabel;
	
	// JSliders
	private JSlider delayJSlider;
	
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
		delayJLabel = new JLabel("Delay (ms)");
	}
	
	private void initializeSliders() {
		delayJSlider = new JSlider(MIN_DELAY, MAX_DELAY, DEFAULT_DELAY);
		delayJSlider.setMajorTickSpacing(500);
		delayJSlider.setMinorTickSpacing(100);
		delayJSlider.setPaintTicks(true);
		delayJSlider.setPaintLabels(true);
		delayJSlider.addChangeListener(this);
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
		startRadioButton.setSelected(true);
		startRadioButton.addActionListener(this);
		endRadioButton = new JRadioButton("End");
		endRadioButton.addActionListener(this);
		obstacleRadioButton = new JRadioButton("Obstacle");
		obstacleRadioButton.addActionListener(this);
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

	public String whichRadioSelected() {
		if (startRadioButton.isSelected()) return "start";
		else if (endRadioButton.isSelected()) return "end";
		else if (obstacleRadioButton.isSelected()) return "obstacle";
		else return "unknown";
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == runJButton) {
			System.out.println("Button Pressed: Run");
		}
		else if (e.getSource() == stepJButton) {
			System.out.println("Button Pressed: Step");
		}
		else if (e.getSource() == resetJButton) {
			System.out.println("Button Pressed: Reset");
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
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == delayJSlider) {
			System.out.println("Delay Slider Changed: " + delayJSlider.getValue());
		}
	}
	
}
