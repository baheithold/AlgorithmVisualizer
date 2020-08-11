package gui;

import java.awt.Checkbox;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 * @author Brett Heithold
 *
 */
public class VisualizerWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 800; 
	private static final int DEFAULT_HEIGHT = 600;
	
	private Checkbox darkModeCheckbox;

	public VisualizerWindow() {
		initializeWindow();
		createMenuBar();
		add(new AboutPanel());
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
		// instantiate menu bar and add it to the window
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		// create file menu and add it to the menu bar
		menubar.add(new FileMenu());
		
		// create sorting menu and add it to the menu bar
		menubar.add(new SortingMenu(this));
		
		// create graph menu and add it to the menu bar
		menubar.add(new GraphMenu(this));
		
		// create path finding menu and add it to the menu bar
		menubar.add(new PathFindingMenu(this));
		
		// create help menu and add it to the menu bar
		menubar.add(new HelpMenu(this));
		
		// Add Dark Mode Checkbox
		darkModeCheckbox = new Checkbox("Dark Mode");
		menubar.add(Box.createHorizontalStrut(450));
		menubar.add(darkModeCheckbox);
	}
	
}
