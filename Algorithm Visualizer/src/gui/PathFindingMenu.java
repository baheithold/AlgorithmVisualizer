package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @author Brett Heithold
 *
 */
public class PathFindingMenu extends JMenu implements ActionListener {
	private static final long serialVersionUID = 1L;

	// Window
	private JFrame window;
	
	// Path Finding Menu Items
	private JMenuItem bfsJMenuItem, dfsJMenuItem, dijkstraJMenuItem, aStarJMenuItem;
	
	public PathFindingMenu(JFrame window) {
		this.window = window;
		setText("Path Finding");
		initializePathFindingMenuItems();
		constructPathFindingMenu();
	}
	
	private void initializePathFindingMenuItems() {
		bfsJMenuItem = new JMenuItem("Breadth First Search");
		bfsJMenuItem.addActionListener(this);
		dfsJMenuItem = new JMenuItem("Depth First Search");
		dfsJMenuItem.addActionListener(this);
		dijkstraJMenuItem = new JMenuItem("Dijkstra");
		dijkstraJMenuItem.addActionListener(this);
		aStarJMenuItem = new JMenuItem("A*");
		aStarJMenuItem.addActionListener(this);
	}

	private void constructPathFindingMenu() {
		add(bfsJMenuItem);
		add(dfsJMenuItem);
		add(dijkstraJMenuItem);
		add(aStarJMenuItem);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String algorithmName;
		if (e.getSource() == bfsJMenuItem) {
			algorithmName = "bfs";
			window.setTitle("Breadth First Search");
		}
		else if (e.getSource() == dfsJMenuItem) {
			algorithmName = "dfs";
			window.setTitle("Depth First Search");
		}
		else if (e.getSource() == dijkstraJMenuItem) {
			algorithmName = "dijkstra";
			window.setTitle("Dijkstra");
		}
		else if (e.getSource() == aStarJMenuItem) {
			algorithmName = "aStar";
			window.setTitle("A*");
		}
		else {
			algorithmName = "unknown algorithm";
		}
		
		// change content on content pane
		window.getContentPane().removeAll();
		window.getContentPane().add(new PathFindingPanel(algorithmName));
		window.revalidate();
	}

}
