package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * @author Brett Heithold
 *
 */
public class SortingMenu extends JMenu implements ActionListener {
	static final long serialVersionUID = 1L;
	
	// Window
	private JFrame window;
	
	// Sorting Menu Items
	private JMenuItem bubbleSortJMenuItem, insertionSortJMenuItem, selectionSortJMenuItem, algorithm3Item, algorithm4Item, algorithm5Item;
	
	public SortingMenu(JFrame window) {
		this.window = window;
		setText("Sorting");
		initializeSortingMenuItems();
	}
	
	private void initializeSortingMenuItems() {
		// Comparison Based Sorting Algorithms
		add(new JSeparator(SwingConstants.HORIZONTAL));
		JLabel comparisonLabel = new JLabel("Comparison Based");
		add(comparisonLabel);
		add(new JSeparator(SwingConstants.HORIZONTAL));
		bubbleSortJMenuItem = new JMenuItem("Bubble Sort");
		bubbleSortJMenuItem.addActionListener(this);
		add(bubbleSortJMenuItem);
		insertionSortJMenuItem = new JMenuItem("Insertion Sort");
		insertionSortJMenuItem.addActionListener(this);
		add(insertionSortJMenuItem);
		selectionSortJMenuItem = new JMenuItem("Selection Sort");
		selectionSortJMenuItem.addActionListener(this);
		add(selectionSortJMenuItem);
		// Non-comparison Based Sorting Algorithms
		add(new JSeparator(SwingConstants.HORIZONTAL));
		JLabel noncomparisonLabel = new JLabel("Non-comparison Based");
		add(noncomparisonLabel);
		add(new JSeparator(SwingConstants.HORIZONTAL));
		algorithm3Item = new JMenuItem("Algorithm3");
		algorithm3Item.addActionListener(this);
		add(algorithm3Item);
		algorithm4Item = new JMenuItem("Algorithm4");
		algorithm4Item.addActionListener(this);
		add(algorithm4Item);
		algorithm5Item = new JMenuItem("Algorithm5");
		algorithm5Item.addActionListener(this);
		add(algorithm5Item);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// set which sorting algorithm was selected
		String sortName;
		if (e.getSource() == bubbleSortJMenuItem) sortName = "bubbleSort";
		else if (e.getSource() == insertionSortJMenuItem) sortName = "insertionSort";
		else if (e.getSource() == selectionSortJMenuItem) sortName = "selectionSort";
		else sortName = "unknown algorithm";
		
		// instantiate sortingArrayPanel
		window.getContentPane().removeAll();
		window.getContentPane().add(new SortingArrayPanel(sortName));
		window.revalidate();
	}
	
}
