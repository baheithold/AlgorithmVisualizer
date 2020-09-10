package gui.graphing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import gui.VisualizerWindow;

/**
 * @author Brett Heithold
 *
 */
public class GraphMenu extends JMenu implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	// Window
	private VisualizerWindow window;
	
	// Graph Menu Items
	private JMenuItem bfsJMenuItem, dfsJMenuItem, dijkstraJMenuItem, primJMenuItem, kruskalJMenuItem, bellmanfordJMenuItem;
	
	public GraphMenu(VisualizerWindow window) {
		this.window = window;
		setText("Graph");
		initializeGraphMenuItems();
		constructGraphMenu();
	}
	
	private void initializeGraphMenuItems() {
		bfsJMenuItem = new JMenuItem("Breadth First Search");
		bfsJMenuItem.addActionListener(this);
		dfsJMenuItem = new JMenuItem("Depth First Search");
		dfsJMenuItem.addActionListener(this);
		dijkstraJMenuItem = new JMenuItem("Dijkstra (Coming Soon!)");
		dijkstraJMenuItem.addActionListener(this);
		primJMenuItem = new JMenuItem("Prim (Coming Soon!)");
		primJMenuItem.addActionListener(this);
		kruskalJMenuItem = new JMenuItem("Kruskal (Coming Soon!)");
		kruskalJMenuItem.addActionListener(this);
		bellmanfordJMenuItem = new JMenuItem("Bellman-Ford (Coming Soon!)");
		bellmanfordJMenuItem.addActionListener(this);
		
	}
	
	private void constructGraphMenu() {
		add(bfsJMenuItem);
		add(dfsJMenuItem);
		add(dijkstraJMenuItem);
		add(primJMenuItem);
		add(kruskalJMenuItem);
		add(bellmanfordJMenuItem);
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
		}
		else if (e.getSource() == primJMenuItem) {
			algorithmName = "prim";
		}
		else if (e.getSource() == kruskalJMenuItem) {
			algorithmName = "kruskal";
		}
		else if (e.getSource() == bellmanfordJMenuItem) {
			algorithmName = "bellmanford";
		}
		else {
			algorithmName = "unknown algorithm";
		}
		
		// if user clicks on an algorithm that is "Coming Soon" then notify user and stay on current screen
		switch (algorithmName) {
			case "unknown algorithm":
				JOptionPane.showMessageDialog(window, "How did you get here? Be you hacker?");
				break;
			case "dijkstra":
			case "prim":
			case "kruskal":
			case "bellmanford":
				JOptionPane.showMessageDialog(window, "Coming Soon!");
				break;
			default:
				//instantiate GraphPanel
				window.getContentPane().removeAll();
				window.getContentPane().add(new GraphPanel(algorithmName, window.getCurrentWindowWidth(), window.getCurrentWindowHeight()));
				window.revalidate();
				break;
		}
	}

}
