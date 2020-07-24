package gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * @author Brett Heithold
 *
 */
public class SortingStatisticsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private SortingArrayPanel array;
	private JLabel itemsJLabel, numItemsJLabel;
	private JLabel delayJLabel, delayValueJLable;
	private JLabel swapsJLabel, numSwapsJLabel;
	private JLabel accessesJLabel, numAccessesJLabel;
	private JLabel comparisonsJlabel, numComparisonsJLabel;
	
	public SortingStatisticsPanel(SortingArrayPanel array) {
		this.array = array;
		
		// initialize statistic panel items
		initializeStatisticJLabels();
		
		// construct the statistics panel
		constructStatisticsPanel();
	}
	
	private void initializeStatisticJLabels() {
		itemsJLabel = new JLabel("Items: ");
		numItemsJLabel = new JLabel(Integer.toString(array.length()));
		delayJLabel = new JLabel("Delay (ms): ");
		delayValueJLable = new JLabel(Integer.toString(array.getCurrentDelay()));
		swapsJLabel = new JLabel("Swaps: ");
		numSwapsJLabel = new JLabel(Integer.toString(array.getNumSwaps()));
		accessesJLabel = new JLabel("Accesses: ");
		numAccessesJLabel = new JLabel(Integer.toString(array.getNumAccesses()));
		comparisonsJlabel = new JLabel("Comparisons: ");
		numComparisonsJLabel = new JLabel(Integer.toString(array.getNumComparisons()));
	}
	
	private void constructStatisticsPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.add(Box.createHorizontalStrut(10));
		this.add(itemsJLabel);
		this.add(numItemsJLabel);
		this.add(Box.createHorizontalStrut(10));
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(Box.createHorizontalStrut(10));
		this.add(delayJLabel);
		this.add(delayValueJLable);
		this.add(Box.createHorizontalStrut(10));
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(Box.createHorizontalStrut(10));
		this.add(swapsJLabel);
		this.add(numSwapsJLabel);
		this.add(Box.createHorizontalStrut(10));
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(Box.createHorizontalStrut(10));
		this.add(accessesJLabel);
		this.add(numAccessesJLabel);
		this.add(Box.createHorizontalStrut(10));
		this.add(new JSeparator(SwingConstants.VERTICAL));
		this.add(Box.createHorizontalStrut(10));
		this.add(comparisonsJlabel);
		this.add(numComparisonsJLabel);
		this.add(Box.createHorizontalStrut(10));
	}
	
	public void updateNumSwapsJLabel(int n) {
		numSwapsJLabel.setText(Integer.toString(n));
	}
	
	public void updateNumAccessesJLabel(int n) {
		numAccessesJLabel.setText(Integer.toString(n));
	}

	public void updateNumComparisonsJLabel(int n) {
		numComparisonsJLabel.setText(Integer.toString(n));
	}
	
	public void updateNumItemsJLabel(int n) {
		numItemsJLabel.setText(Integer.toString(n));
	}
	
	public void updateDelayJLabel(int n) {
		delayValueJLable.setText(Integer.toString(n));
	}
	
}
