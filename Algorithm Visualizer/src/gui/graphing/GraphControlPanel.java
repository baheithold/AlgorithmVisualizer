package gui.graphing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private GraphPanel panel;
	
	// JLabels
	private JLabel delayJLabel, numItemsJLabel;
	
	// JSliders
	private JSlider delayJSlider, numItemsJSlider;
	
	// JButtons
	private JButton runJButton, randomizeJButton, resetJButton;

	public GraphControlPanel(GraphPanel panel) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.panel = panel;
		initializeLabels();
		initializeSliders();
		initializeButtons();
		constructControlPanel();
	}

	private void initializeLabels() {
		delayJLabel = new JLabel("Delay (ms)");
		numItemsJLabel = new JLabel("Number of Items");
	}
	
	private void initializeSliders() {
		delayJSlider = new JSlider(panel.MIN_DELAY, panel.MAX_DELAY, panel.DEFAULT_DELAY);
		delayJSlider.addChangeListener(this);
		numItemsJSlider = new JSlider(0, 10, 5);
		numItemsJSlider.addChangeListener(this);
	}
	
	private void initializeButtons() {
		runJButton = new JButton("Run");
		runJButton.addActionListener(this);
		randomizeJButton = new JButton("Randomize");
		randomizeJButton.addActionListener(this);
		resetJButton = new JButton("Reset");
		resetJButton.addActionListener(this);
	}
	
	private void constructControlPanel() {
		// Delay Slider
		add(constructDelayPanel());

		// Separator
		add(new JSeparator(SwingConstants.VERTICAL));
		
		// Number of Items Slider
		add(constructNumberOfItemsPanel());
		
		// Separator
		add(new JSeparator(SwingConstants.VERTICAL));
		
		// Run Button
		add(runJButton);
		add(Box.createHorizontalStrut(10));
		
		// Randomize Button
		add(constructButtonsPanel());
	}
	
	private JPanel constructDelayPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		delayJLabel.add(Box.createVerticalStrut(5));
		panel.add(delayJLabel);
		delayJSlider.add(Box.createVerticalStrut(5));
		panel.add(delayJSlider);
		this.add(panel);
		return panel;
	}
	
	private JPanel constructNumberOfItemsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		numItemsJLabel.add(Box.createVerticalStrut(5));
		panel.add(numItemsJLabel);
		numItemsJSlider.add(Box.createVerticalStrut(5));
		panel.add(numItemsJSlider);
		this.add(panel);
		return panel;
	}
	
	private JPanel constructButtonsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		// Run Button
		panel.add(runJButton);
		panel.add(Box.createHorizontalStrut(10));
		
		// Randomize Button
		panel.add(randomizeJButton);
		panel.add(Box.createHorizontalStrut(10));
				
		// Reset Button
		panel.add(resetJButton);
		panel.add(Box.createHorizontalStrut(10));
		
		return panel;
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
		else if (e.getSource() == randomizeJButton) {
			System.out.println("Button Pressed: " + "Randomize");
		}
		else if (e.getSource() == resetJButton) {
			System.out.println("Button Pressed: " + "Reset");
		}
	}

}