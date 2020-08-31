package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * @author Brett Heithold
 *
 */
public class VisualizationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	// Window constants
	protected final int WINDOW_WIDTH = 800;
	protected final int WINDOW_HEIGHT = 600;

	// Delay constants
	public final int DEFAULT_DELAY = 100;
	public final int MIN_DELAY = 0;
	public final int MAX_DELAY = 250;
	public final int DELAY_MAJOR_TICK_SPACING = 125;
	public final int DELAY_MINOR_TICK_SPACING = 25;
	
	private int currentDelay;
	private String algorithmName;
	
	public VisualizationPanel() {
	}
	
	public VisualizationPanel(String name) {
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
}