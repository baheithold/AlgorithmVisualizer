package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

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
	
	public SortingArrayPanel() {
		setBackground(Color.DARK_GRAY);
		array = new int[windowWidth / BAR_WIDTH];
		randomizeArray();
		repaint();
		new BubbleSort(this).Run();
		repaint();
	}
	
	private void randomizeArray() {
		Random rand = new Random();
		for (int i = 0; i < array.length; i++) {
			// generate an integer [1..windowHeight]
			int n = rand.nextInt(windowHeight);
			array[i] = n;
		}
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
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
	
	public void swap(int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		super.paintComponent(g);
		graphics.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < array.length; i++) {
			int height = array[i];
			int xBegin = i + (BAR_WIDTH - 1) * i;
			int yBegin = windowHeight - height;
			graphics.fillRect(xBegin, yBegin, BAR_WIDTH, height);
		}
	}
	
}
