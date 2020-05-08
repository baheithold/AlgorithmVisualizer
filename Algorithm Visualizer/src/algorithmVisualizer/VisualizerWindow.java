package algorithmVisualizer;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 * @author Brett Heithold
 *
 */
public class VisualizerWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private Toolkit toolkit;
	private static final int DEFAULT_WIDTH = 800; 
	private static final int DEFAULT_HEIGHT = 600;

	public VisualizerWindow() {
		toolkit = Toolkit.getDefaultToolkit();
		initializeWindow();
		createMenuBar();
		add(new SortingPanel());
		setVisible(true);
	}
	
	private void initializeWindow() {
		setTitle("Algorithm Visualizer");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setFocusable(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private void createMenuBar() {
		// instantiate menubar and add it to the window
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		// create file menu and add it to the menubar
		menubar.add(new FileMenu());
		
		// create sorting menu and add it to the menubar
		menubar.add(new SortingMenu());
		
		// create pathfinding menu and add it to the menubar
		menubar.add(new PathFindingMenu());
		
		// create help menu and add it to the menubar
		menubar.add(new HelpMenu());
	}
	
}
