package gui.sorting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import gui.VisualizerWindow;

/**
 * @author Brett Heithold
 *
 */
public class SortingMenu extends JMenu implements ActionListener {
	static final long serialVersionUID = 1L;
	
	// Window
	private VisualizerWindow window;
	
	// Sorting Menu Items
	private JMenuItem bubbleSortJMenuItem, insertionSortJMenuItem, selectionSortJMenuItem, mergeSortJMenuItem, quickSortJMenuItem, heapSortJMenuItem, bucketSortJMenuItem, countingSortJMenuItem, radixSortJMenuItem;
	
	public SortingMenu(VisualizerWindow window) {
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
		bucketSortJMenuItem = new JMenuItem("Bucket Sort (Coming Soon!)");
		bucketSortJMenuItem.addActionListener(this);
		add(bucketSortJMenuItem);
		countingSortJMenuItem = new JMenuItem("Counting Sort (Coming Soon!)");
		countingSortJMenuItem.addActionListener(this);
		add(countingSortJMenuItem);
		radixSortJMenuItem = new JMenuItem("Radix Sort (Coming Soon!)");
		radixSortJMenuItem.addActionListener(this);
		add(radixSortJMenuItem);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// set which sorting algorithm was selected
		String sortName;
		if (e.getSource() == bubbleSortJMenuItem) {
			sortName = "bubbleSort";
			window.setTitle("Bubble Sort");
		}
		else if (e.getSource() == insertionSortJMenuItem) {
			sortName = "insertionSort";
			window.setTitle("Insertion Sort");
		}
		else if (e.getSource() == selectionSortJMenuItem) {
			sortName = "selectionSort";
			window.setTitle("Selection Sort");
		}
		else if (e.getSource() == mergeSortJMenuItem) {
			sortName = "mergeSort";
			window.setTitle("Merge Sort");
		}
		else if (e.getSource() == quickSortJMenuItem) {
			sortName = "quickSort";
			window.setTitle("Quick Sort");
		}
		else if (e.getSource() == heapSortJMenuItem) {
			sortName = "heapSort";
			window.setTitle("Heap Sort");
		}
		else if (e.getSource() == bucketSortJMenuItem) {
			sortName = "bucketSort";
		}
		else if (e.getSource() == countingSortJMenuItem) {
			sortName = "countingSort";
		}
		else if (e.getSource() == radixSortJMenuItem) {
			sortName = "radixSort";
		}
		else sortName = "unknown algorithm";
		
		// if user clicks on an algorithm that is "Coming Soon" then notify user and stay on current screen
		switch (sortName) {
			case "unknown algorithm":
				JOptionPane.showMessageDialog(window, "How did you get here? Be you hacker?");
				break;
			case "bucketSort":
			case "countingSort":
			case "radixSort":
				JOptionPane.showMessageDialog(window, "Coming Soon!");
				break;
			default:
				//instantiate sortingArrayPanel
				window.getContentPane().removeAll();
				window.getContentPane().add(new SortingPanel(sortName, window.getCurrentWindowWidth(), window.getCurrentWindowHeight()));
				window.revalidate();
				break;
		}
	}
	
}
