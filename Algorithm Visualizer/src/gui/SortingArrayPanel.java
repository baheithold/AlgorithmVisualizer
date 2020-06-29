package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import sortingAlgorithms.BubbleSort;

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
	
	public SortingArrayPanel() {
		array = new int[windowWidth / BAR_WIDTH];
		colors = new Color[windowWidth / BAR_WIDTH];
		randomizeArray();
		resetColors();
		setBackground(Color.DARK_GRAY);
		BubbleSort bs = new BubbleSort(this);
		SwingUtilities.invokeLater(bs);
		resetColors();
		repaint();
	}
	
	private void randomizeArray() {
		Random rand = new Random();
		for (int i = 0; i < array.length; i++) {
			// generate an integer [1..windowHeight]
			int n = rand.nextInt(windowHeight);
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
	
	private void resetColors() {
		for (int i = 0; i < colors.length; i++) {
			colors[i] = Color.LIGHT_GRAY;
		}
	}
	
	public void setColor(int index, Color color) {
		colors[index] = color;
	}
	
	public void swap(int a, int b) {
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
