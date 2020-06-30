package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.SwingUtilities;

import sortingAlgorithms.BubbleSort;
import sortingAlgorithms.InsertionSort;
import sortingAlgorithms.MergeSort;
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
	
	public SortingArrayPanel(String sortName) {
		array = new int[(windowWidth / BAR_WIDTH) - 2];
		colors = new Color[windowWidth / BAR_WIDTH];
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
			default:
				break;
		}
	}
	
	private void randomizeArray() {
		Random rand = new Random();
		for (int i = 0; i < array.length; i++) {
			// generate an integer [1..windowHeight]
			int n = rand.nextInt(windowHeight - 100) + 70;
			array[i] = n;
		}
	}
	
	public int length() {
		return array.length;
	}
	
	public int getValue(int index) {
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
	
	private void resetColors() {
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
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		super.paintComponent(g);
		for (int i = 0; i < array.length; i++) {
			int height = array[i];
			int xBegin = i + (BAR_WIDTH - 1) * i;
			int yBegin = windowHeight - height;
			graphics.setColor(colors[i]);
			graphics.fillRect(xBegin, yBegin, BAR_WIDTH, height);
		}
	}
	
}
