package gui.graphing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
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
	private final int NUM_ITEMS_MAJOR_TICK = 25;
	private final int NUM_ITEMS_MINOR_TICK = 5;
	public final int DIAMETER = 50;
	
	// Graph Panel
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
	
	private void initializeButtons() {
		runJButton = new JButton("Run");
		runJButton.addActionListener(this);
		randomizeJButton = new JButton("Randomize");
		randomizeJButton.addActionListener(this);
		resetJButton = new JButton("Reset");
		resetJButton.addActionListener(this);
	}
	
	private void constructControlPanel() {
		// add border around control panel
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
		
		// Delay Slider
		add(constructDelayPanel());

		// Separator
		add(new JSeparator(SwingConstants.VERTICAL));
		
		// Number of Items Slider
		add(constructNumberOfItemsPanel());
		
		// Separator
		add(new JSeparator(SwingConstants.VERTICAL));
		
		// Randomize Button
		this.add(Box.createHorizontalStrut(10));
		add(constructButtonsPanel());
		this.add(Box.createHorizontalStrut(10));
	}
	
	private JPanel constructDelayPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(delayJLabel);
		panel.add(Box.createVerticalStrut(5));
		panel.add(delayJSlider);
		this.add(panel);
		return panel;
	}
	
	private JPanel constructNumberOfItemsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(numItemsJLabel);
		panel.add(Box.createVerticalStrut(5));
		panel.add(numItemsJSlider);
		this.add(panel);
		return panel;
	}
	
	private JPanel constructButtonsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBorder(new EmptyBorder(0, 10, 0, 10));
		
		// Run Button
		panel.add(runJButton);
		panel.add(Box.createHorizontalStrut(10));
		
		// Randomize Button
		panel.add(randomizeJButton);
		panel.add(Box.createHorizontalStrut(10));
				
		// Reset Button
		panel.add(resetJButton);
		
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
			panel.clearVertices();
		}
		else if (e.getSource() == resetJButton) {
			System.out.println("Button Pressed: " + "Reset");
			panel.clearVertices();
		}
	}

}
