package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import gui.graphing.GraphMenu;
import gui.pathfinding.PathFindingMenu;
import gui.sorting.SortingMenu;

/**
 * @author Brett Heithold
 *
 */
public class VisualizerWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 800; 
	private static final int DEFAULT_HEIGHT = 600;
	
	private JCheckBox darkModeCheckbox;

	public VisualizerWindow() {
		initializeWindow(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		createMenuBar();
		add(new AboutPanel());
		setVisible(true);
	}
	
	public VisualizerWindow(int width, int height) {
		initializeWindow(width, height);
		createMenuBar();
		add(new AboutPanel());
		setVisible(true);
	}
	
	private void initializeWindow(int width, int height) {
		setTitle("Algorithm Visualizer");
		setSize(width, height);
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
		darkModeCheckbox = new JCheckBox();
		darkModeCheckbox.setText("Dark Mode");
		darkModeCheckbox.addActionListener(this);
		menubar.add(Box.createHorizontalStrut(450));
		menubar.add(darkModeCheckbox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == darkModeCheckbox) {
			System.out.println("Dark Mode: " + darkModeCheckbox.isSelected());
		}
	}
	
}
