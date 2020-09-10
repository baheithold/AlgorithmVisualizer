package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * @author Brett Heithold
 *
 */
public class VisualizationPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	// Delay constants
	public final int DEFAULT_DELAY = 100;
	public final int MIN_DELAY = 0;
	public final int MAX_DELAY = 250;
	public final int DELAY_MAJOR_TICK_SPACING = 125;
	public final int DELAY_MINOR_TICK_SPACING = 25;
	
	// window dimensions
	protected int currentWidth;
	protected int currentHeight;
	
	private int currentDelay;
	private String algorithmName;
	
	public VisualizationPanel(int width, int height) {
		currentWidth = width;
		currentHeight = height;
		currentDelay = DEFAULT_DELAY;
		setLayout(new BorderLayout());
	}
	
	public VisualizationPanel(String name, int width, int height) {
		currentWidth = width;
		currentHeight = height;
		currentDelay = DEFAULT_DELAY;
		algorithmName = name;
		setLayout(new BorderLayout());
	}
	
	public String getAlgorithmName() {
		return algorithmName;
	}
	
	public int getCurrentDelay() {
		return currentDelay;
	}
	
	public void setCurrentDelay(int delay) {
		currentDelay = delay;
	}
	
	public int getCurrentWidth() {
		return currentWidth;
	}
	
	public int getCurrentHeight() {
		return currentHeight;
	}
}