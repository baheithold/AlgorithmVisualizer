package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import sortingAlgorithms.BubbleSort;
import sortingAlgorithms.HeapSort;
import sortingAlgorithms.InsertionSort;
import sortingAlgorithms.MergeSort;
import sortingAlgorithms.QuickSort;
import sortingAlgorithms.SelectionSort;
import sortingAlgorithms.SortingAlgorithm;

/**
 * @author Brett Heithold
 *
 */
public class SortingPanel extends VisualizationPanel {
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
	
	// Control Panel
	public final int DEFAULT_NUM_ITEMS = 98;
	public final int MIN_NUM_ITEMS = 1;
	public final int MAX_NUM_ITEMS = 98;
	private int currentNumItems;
	
	// Sort
	public SortingAlgorithm sortAlgorithm;
	
	public SortingPanel(String sortName) {
		
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
				sortAlgorithm = new BubbleSort(this);
				break;
			case "insertionSort":
				sortAlgorithm = new InsertionSort(this);
				break;
			case "selectionSort":
				sortAlgorithm = new SelectionSort(this);
				break;
			case "mergeSort":
				sortAlgorithm = new MergeSort(this);
				break;
			case "quickSort":
				sortAlgorithm = new QuickSort(this);
				break;
			case "heapSort":
				sortAlgorithm = new HeapSort(this);
				break;
			default:
				break;
		}
	}
	
	public void randomizeArray() {
		Random rand = new Random();
		for (int i = 0; i < array.length; i++) {
			// generate an integer [1..windowHeight]
			int n = rand.nextInt(WINDOW_HEIGHT - 125) + 20;
			array[i] = n;
		}
	}
	
	public int length() {
		return array.length;
	}
	
	public int getValue(int index, boolean isVerifying) {
		if (!isVerifying) numAccesses++;
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
		resetStatistics();
	}
	
	public void resetStatistics() {
		numSwaps = 0;
		numAccesses = 0;
		numComparisons = 0;
		resetStatsPanel();
	}
	
	private void resetStatsPanel() {
		statsPanel.updateNumSwapsJLabel(0);
		statsPanel.updateNumAccessesJLabel(0);
		statsPanel.updateNumComparisonsJLabel(0);
	}
	
	private void initializeControlPanel() {
		controlPanel = new SortingControlPanel(this);
		this.add(controlPanel, BorderLayout.SOUTH);
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
	
	public void setCurrentStatsControlPanelDelay(int delay) {
		setCurrentDelay(delay);
		statsPanel.updateDelayJLabel(getCurrentDelay());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		super.paintComponent(g);
		for (int i = 0; i < array.length; i++) {
			int height = array[i];
			int xBegin = i + (BAR_WIDTH - 1) * i;
			int yBegin = WINDOW_HEIGHT - height - 85;
			graphics.setColor(colors[i]);
			graphics.fillRect(xBegin, yBegin, BAR_WIDTH, height);
		}
	}
	
}
