package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.SwingUtilities;

import sortingAlgorithms.BubbleSort;
import sortingAlgorithms.HeapSort;
import sortingAlgorithms.InsertionSort;
import sortingAlgorithms.MergeSort;
import sortingAlgorithms.QuickSort;
import sortingAlgorithms.SelectionSort;

/**
 * @author Brett Heithold
 *
 */
public class SortingArrayPanel extends VisualizationPanel {
	private static final long serialVersionUID = 1L;
	private static final int BAR_WIDTH = 8;
	
	// Array
	private int[] array;
	private Color[] colors;
	
	// Statistics panel
	private SortingStatisticsPanel statsPanel;
	private int numSwaps;
	private int numAccesses;
	private int numComparisons;
	
	// Control Panel
	private SortingControlPanel controlPanel;
	
	// Control Panel Constants
	public final int DEFAULT_DELAY = 500;
	public final int MIN_DELAY = 1;
	public final int MAX_DELAY = 1000;
	private int currentDelay;
	public final int DEFAULT_NUM_ITEMS = 98;
	public final int MIN_NUM_ITEMS = 1;
	public final int MAX_NUM_ITEMS = 98;
	private int currentNumItems;
	
	public SortingArrayPanel(String sortName) {
		currentDelay = DEFAULT_DELAY;
		currentNumItems = DEFAULT_NUM_ITEMS;
		array = new int[DEFAULT_NUM_ITEMS];
		colors = new Color[DEFAULT_NUM_ITEMS];

		// set layout to BorderLayout
		this.setLayout(new BorderLayout());

		// initialize statistics panel
		initializeStatisticsPanel();
		
		// initialize control panel
		initializeControlPanel();
		
		// Randomize sorting panel and reset colors
		randomizeArray();
		resetColors();
		setBackground(Color.DARK_GRAY);
		
		switch (sortName) {
			case "bubbleSort":
				BubbleSort bs = new BubbleSort(this);
				SwingUtilities.invokeLater(bs);
				break;
			case "insertionSort":
				InsertionSort is = new InsertionSort(this);
				SwingUtilities.invokeLater(is);
				break;
			case "selectionSort":
				SelectionSort ss = new SelectionSort(this);
				SwingUtilities.invokeLater(ss);
				break;
			case "mergeSort":
				MergeSort ms = new MergeSort(this);
				SwingUtilities.invokeLater(ms);
				break;
			case "quickSort":
				QuickSort qs = new QuickSort(this);
				SwingUtilities.invokeLater(qs);
				break;
			case "heapSort":
				HeapSort hs = new HeapSort(this);
				SwingUtilities.invokeLater(hs);
				break;
			default:
				break;
		}
	}
	
	public void randomizeArray() {
		Random rand = new Random();
		for (int i = 0; i < array.length; i++) {
			// generate an integer [1..windowHeight]
			int n = rand.nextInt(windowHeight - 125) + 20;
			array[i] = n;
		}
	}
	
	public int length() {
		return array.length;
	}
	
	public int getValue(int index) {
		numAccesses++;
		statsPanel.updateNumAccessesJLabel(numAccesses);
		return array[index];
	}
	
	public void setValue(int index, int value) {
		array[index] = value;
	}
	
	public int[] getArray() {
		return this.array;
	}
	
	public void setAllColors(Color color) {
		for (int i = 0; i < colors.length; i++) {
			setColor(i, color);
		}
	}
	
	public void resetColors() {
		setAllColors(Color.lightGray);
	}
	
	public void setColor(int index, Color color) {
		colors[index] = color;
	}
	
	public void swap(int a, int b) {
		System.out.println("Swapping indices " + a + " and " + b);
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
		numSwaps++;
		statsPanel.updateNumSwapsJLabel(numSwaps);
	}
	
	public int getNumSwaps() {
		return this.numSwaps;
	}
	
	public int getNumAccesses() {
		return this.numAccesses;
	}
	
	public int getNumComparisons() {
		return this.numComparisons;
	}
	
	public void incrementComparisons() {
		numComparisons++;
		statsPanel.updateNumComparisonsJLabel(numComparisons);
	}
	
	private void initializeStatisticsPanel() {
		statsPanel = new SortingStatisticsPanel(this);
		this.add(statsPanel, BorderLayout.NORTH);
		numSwaps = 0;
		numAccesses = 0;
		numComparisons = 0;
	}
	
	private void initializeControlPanel() {
		controlPanel = new SortingControlPanel(this);
		this.add(controlPanel, BorderLayout.SOUTH);
	}
	
	public int getCurrentDelay() {
		return currentDelay;
	}
	
	public void setCurrentDelay(int delay) {
		currentDelay = delay;
	}
	
	public int getCurrentNumItems() {
		return currentNumItems;
	}
	
	public void setCurrentNumItems(int num) {
		currentNumItems = num;
		array = new int[currentNumItems];
		colors = new Color[currentNumItems];
		randomizeArray();
		resetColors();
		repaint();
		statsPanel.updateNumItemsJLabel(num);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		super.paintComponent(g);
		for (int i = 0; i < array.length; i++) {
			int height = array[i];
			int xBegin = i + (BAR_WIDTH - 1) * i;
			int yBegin = windowHeight - height - 85;
			graphics.setColor(colors[i]);
			graphics.fillRect(xBegin, yBegin, BAR_WIDTH, height);
		}
	}
	
}
