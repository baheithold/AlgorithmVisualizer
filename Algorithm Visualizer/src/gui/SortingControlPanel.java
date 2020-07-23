package gui;

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
		speedJSlider = new JSlider(array.MIN_DELAY, array.MAX_DELAY);
		speedJSlider.setValue(array.DEFAULT_DELAY);
		speedJSlider.addChangeListener(this);
		numItemsJSlider = new JSlider(array.MIN_NUM_ITEMS, array.MAX_NUM_ITEMS);
		numItemsJSlider.setValue(array.DEFAULT_NUM_ITEMS);
		numItemsJSlider.addChangeListener(this);
	}
	
	private void initializeJButtons() {
		sortJButton = new JButton("Sort");
		sortJButton.addActionListener(this);
		randomizeJButton = new JButton("Randomize");
		randomizeJButton.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == randomizeJButton) {
			System.out.println("Button Pressed: Randomize");
		}
		else if (e.getSource() == sortJButton) {
			System.out.println("Button Pressed: Sort");
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == numItemsJSlider) {
			System.out.println("Items Slider Changed: " + numItemsJSlider.getValue());
			array.setCurrentNumItems(numItemsJSlider.getValue());
		}
		else if (e.getSource() == speedJSlider) {
			System.out.println("Speed Slider Changed: " + speedJSlider.getValue());
			array.setCurrentDelay(speedJSlider.getValue());
		}
	}
	
}
