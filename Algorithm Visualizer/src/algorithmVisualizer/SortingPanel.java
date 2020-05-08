package algorithmVisualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JLabel;

import sortingAlgorithms.BubbleSort;

/**
 * @author Brett Heithold
 *
 */
public class SortingPanel extends VisualizationPanel {
	private static final long serialVersionUID = 1L;
	private static final int BAR_WIDTH = 8;
	private static int windowWidth = 800;
	private static int windowHeight = 600;
	private static JLabel helloLabel;
	
	// Array
	int[] array;
	
	public SortingPanel() {
		helloLabel = createHelloLabel();
		array = new int[windowWidth / BAR_WIDTH];
		randomizeArray();
		new BubbleSort(array).Run();
		repaint();
	}
	
	@Override
	public JLabel createHelloLabel() {
		JLabel label = new JLabel("Hello Sorting Panel");
		label.setBackground(new Color(255, 255, 255));
		label.setOpaque(true);
		add(label);
		setBackground(new Color(150, 12, 12));
		return label;
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
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		super.paintComponent(g);
		graphics.setColor(Color.white);
		for (int i = 0; i < array.length; i++) {
			int height = array[i];
			int xBegin = i + (BAR_WIDTH - 1) * i;
			int yBegin = windowHeight - height;
			graphics.fillRect(xBegin, yBegin, BAR_WIDTH, height);
		}
	}
	
}
