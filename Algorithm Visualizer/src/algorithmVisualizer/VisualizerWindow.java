package algorithmVisualizer;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

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
		menubar.add(createSortingMenu());
		
		// create pathfinding menu and add it to the menubar
		menubar.add(createPathfindingMenu());
		
		// create help menu and add it to the menubar
		menubar.add(createHelpMenu());
	}
	
	private JMenu createSortingMenu() {
		JMenu menu = new JMenu("Sorting");
		
		// add comparison based sorting algorithm items to sorting menu
		menu.add(new JSeparator(SwingConstants.HORIZONTAL));
		menu.add(new JLabel("Comparison Based"));
		menu.add(new JSeparator(SwingConstants.HORIZONTAL));
		menu.add(new JMenuItem("Algorithm 0"));
		menu.add(new JMenuItem("Algorithm 1"));
		menu.add(new JMenuItem("Algorithm 2"));
				
		// add non-comparison based sorting algorithm items to sorting menu
		menu.add(new JSeparator(SwingConstants.HORIZONTAL));
		menu.add(new JLabel("Non-comparison Based"));
		menu.add(new JSeparator(SwingConstants.HORIZONTAL));
		menu.add(new JMenuItem("Algorithm 3"));
		menu.add(new JMenuItem("Algorithm 4"));
		menu.add(new JMenuItem("Algorithm 5"));
		
		return menu;
	}
	
	private JMenu createPathfindingMenu() {
		JMenu menu = new JMenu("Path finding");
		
		// add comparison based sorting algorithm items to sorting menu
		menu.add(new JMenuItem("Algorithm 0"));
		menu.add(new JMenuItem("Algorithm 1"));
		menu.add(new JMenuItem("Algorithm 2"));
		
		return menu;
	}
	
	private JMenu createHelpMenu() {
		JMenu menu = new JMenu("Help");
		JMenuItem aboutItem = new JMenuItem("About");
		menu.add(aboutItem);
		return menu;
	}
	
}
