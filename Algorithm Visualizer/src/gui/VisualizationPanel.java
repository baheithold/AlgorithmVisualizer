package gui;

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
	public final int MIN_DELAY = 1;
	public final int MAX_DELAY = 500;
	
	private int currentDelay;
	
	public VisualizationPanel() {
		currentDelay = DEFAULT_DELAY;
	}
	
	public int getCurrentDelay() {
		return currentDelay;
	}
	
	public void setCurrentDelay(int delay) {
		currentDelay = delay;
	}
	
}