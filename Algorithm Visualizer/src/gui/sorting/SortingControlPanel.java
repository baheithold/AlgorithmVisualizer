package gui.sorting;

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
public class SortingControlPanel extends JPanel implements ActionListener, ChangeListener {
	private static final long serialVersionUID = 1L;

	private SortingPanel panel;
	
	// JLabels
	private JLabel sortingSpeedJLabel;
	private JLabel numItemsJLabel;
	
	// JSliders
	private JSlider delayJSlider;
	private JSlider numItemsJSlider;
	
	// JButtons
	private JButton runJButton;
	private JButton randomizeJButton;
	
	public SortingControlPanel(SortingPanel panel) {
		this.panel = panel;
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		initializeJLabels();
		initializeJSliders();
		initializeJButtons();
		constructControlPanel();
	}
	
	private void initializeJLabels() {
		sortingSpeedJLabel = new JLabel("Delay (ms)");
		numItemsJLabel = new JLabel("Number of Items");
	}
	
	private void initializeJSliders() {
		// speedJSlider
		delayJSlider = new JSlider(panel.MIN_DELAY, panel.MAX_DELAY, panel.DEFAULT_DELAY);
		delayJSlider.setToolTipText("Select amount of delay between algorithm steps");
		delayJSlider.setMajorTickSpacing(panel.DELAY_MAJOR_TICK_SPACING);
		delayJSlider.setMinorTickSpacing(panel.DELAY_MINOR_TICK_SPACING);
		delayJSlider.setPaintTicks(true);
		delayJSlider.setPaintLabels(true);
		delayJSlider.addChangeListener(this);
		
		// numItemsJSlider
		numItemsJSlider = new JSlider(panel.MIN_NUM_ITEMS, panel.MAX_NUM_ITEMS, panel.DEFAULT_NUM_ITEMS);
		numItemsJSlider.setToolTipText("Select number of items to sort");
		numItemsJSlider.setMajorTickSpacing(10);
		numItemsJSlider.setMinorTickSpacing(1);
		numItemsJSlider.setPaintTicks(true);
		numItemsJSlider.setPaintLabels(true);
		numItemsJSlider.addChangeListener(this);
	}
	
	private void initializeJButtons() {
		runJButton = new JButton("Run");
		runJButton.addActionListener(this);
		randomizeJButton = new JButton("Randomize");
		randomizeJButton.addActionListener(this);
	}
	
	private void constructControlPanel() {
		this.add(Box.createHorizontalStrut(10));
		this.add(sortingSpeedJLabel);
		this.add(delayJSlider);
		this.add(Box.createHorizontalStrut(10));
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(Box.createHorizontalStrut(10));
		this.add(numItemsJLabel);
		this.add(numItemsJSlider);
		this.add(Box.createHorizontalStrut(10));
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(Box.createHorizontalStrut(10));
		this.add(runJButton);
		this.add(Box.createHorizontalStrut(10));
		this.add(randomizeJButton);
		this.add(Box.createHorizontalStrut(10));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == runJButton) {
			System.out.println("Button Pressed: Sort");
			if (!panel.sortAlgorithm.isRunning()) {
				panel.sortAlgorithm.runAlgorithm();
			}
		}
		else if (e.getSource() == randomizeJButton) {
			System.out.println("Button Pressed: Randomize");
			if (panel.sortAlgorithm.isRunning()) {
				panel.sortAlgorithm.killAlgorithm();
			}
			panel.randomizeArray();
			panel.resetColors();
			panel.repaint();
		}
		panel.resetStatistics();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// adjust values for sliders
		if (e.getSource() == numItemsJSlider) {
			System.out.println("Items Slider Changed: " + numItemsJSlider.getValue());
			// check if a sort is currently running and kill it
			if (panel.sortAlgorithm.isRunning()) {
				panel.sortAlgorithm.killAlgorithm();
			}
			panel.setCurrentNumItems(numItemsJSlider.getValue());
		}
		else if (e.getSource() == delayJSlider) {
			System.out.println("Delay Slider Changed: " + delayJSlider.getValue());
			panel.setCurrentStatsControlPanelDelay(delayJSlider.getValue());
		}
	}
	
}
