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
	private JMenuItem bubbleSortJMenuItem, insertionSortJMenuItem, selectionSortJMenuItem, mergeSortJMenuItem, quickSortJMenuItem, heapSortJMenuItem, bucketSortJMenuItem, countingSortJMenuItem, radixSortJMenuItem;
	
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
		mergeSortJMenuItem = new JMenuItem("Merge Sort");
		mergeSortJMenuItem.addActionListener(this);
		add(mergeSortJMenuItem);
		quickSortJMenuItem = new JMenuItem("Quick Sort");
		quickSortJMenuItem.addActionListener(this);
		add(quickSortJMenuItem);
		heapSortJMenuItem = new JMenuItem("Heap Sort");
		heapSortJMenuItem.addActionListener(this);
		add(heapSortJMenuItem);
		
		// Non-comparison Based Sorting Algorithms
		add(new JSeparator(SwingConstants.HORIZONTAL));
		JLabel noncomparisonLabel = new JLabel("Non-comparison Based");
		add(noncomparisonLabel);
		add(new JSeparator(SwingConstants.HORIZONTAL));
		bucketSortJMenuItem = new JMenuItem("Bucket Sort");
		bucketSortJMenuItem.addActionListener(this);
		add(bucketSortJMenuItem);
		countingSortJMenuItem = new JMenuItem("Counting Sort");
		countingSortJMenuItem.addActionListener(this);
		add(countingSortJMenuItem);
		radixSortJMenuItem = new JMenuItem("Radix Sort");
		radixSortJMenuItem.addActionListener(this);
		add(radixSortJMenuItem);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// set which sorting algorithm was selected
		String sortName;
		if (e.getSource() == bubbleSortJMenuItem) sortName = "bubbleSort";
		else if (e.getSource() == insertionSortJMenuItem) sortName = "insertionSort";
		else if (e.getSource() == selectionSortJMenuItem) sortName = "selectionSort";
		else if (e.getSource() == mergeSortJMenuItem) sortName = "mergeSort";
		else if (e.getSource() == quickSortJMenuItem) sortName = "quickSort";
		else if (e.getSource() == heapSortJMenuItem) sortName = "heapSort";
		else if (e.getSource() == bucketSortJMenuItem) sortName = "bucketSort";
		else if (e.getSource() == countingSortJMenuItem) sortName = "countingSort";
		else if (e.getSource() == radixSortJMenuItem) sortName = "radixSort";
		else sortName = "unknown algorithm";
		
//		 instantiate sortingArrayPanel
		window.getContentPane().removeAll();
		window.getContentPane().add(new SortingArrayPanel(sortName));
		window.revalidate();
	}
	
}
