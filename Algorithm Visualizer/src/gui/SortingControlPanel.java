package gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

/**
 * @author Brett Heithold
 *
 */
public class SortingControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private SortingArrayPanel array;
	
	// JLabels
	private JLabel sortingSpeedJLabel;
	private JLabel numItemsJLabel;
	
	// JSliders
	private JSlider speedJSlider;
	private JSlider numItemsJSlider;
	
	// JButtons
	private JButton sortJButton;
	private JButton randomizeJButton;
	
	public SortingControlPanel(SortingArrayPanel array) {
		this.array = array;
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		initializeJLabels();
		initializeJSliders();
		initializeJButtons();
		constructControlPanel();
	}
	
	private void initializeJLabels() {
		sortingSpeedJLabel = new JLabel("Sorting Speed");
		numItemsJLabel = new JLabel("Number of Items");
	}
	
	private void initializeJSliders() {
		speedJSlider = new JSlider(1, 1000);
		numItemsJSlider = new JSlider(5, 100);
	}
	
	private void initializeJButtons() {
		sortJButton = new JButton("Sort");
		randomizeJButton = new JButton("Randomize");
	}
	
	private void constructControlPanel() {
		this.add(Box.createHorizontalStrut(10));
		this.add(sortingSpeedJLabel);
		this.add(speedJSlider);
		this.add(Box.createHorizontalStrut(10));
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(Box.createHorizontalStrut(10));
		this.add(numItemsJLabel);
		this.add(numItemsJSlider);
		this.add(Box.createHorizontalStrut(10));
		this.add(sortJButton);
		this.add(Box.createHorizontalStrut(10));
		this.add(randomizeJButton);
		this.add(Box.createHorizontalStrut(10));
	}
	
}
