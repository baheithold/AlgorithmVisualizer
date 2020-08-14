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
public class GraphMenu extends JMenu implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	// Window
	private JFrame window;
	
	// Graph Menu Items
	private JMenuItem bfsJMenuItem, dfsJMenuItem, dijkstraJMenuItem, primJMenuItem, kruskalJMenuItem, bellmanfordJMenuItem;
	
	public GraphMenu(JFrame window) {
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
		dijkstraJMenuItem = new JMenuItem("Dijkstra");
		dijkstraJMenuItem.addActionListener(this);
		primJMenuItem = new JMenuItem("Prim");
		primJMenuItem.addActionListener(this);
		kruskalJMenuItem = new JMenuItem("Kruskal");
		kruskalJMenuItem.addActionListener(this);
		bellmanfordJMenuItem = new JMenuItem("Bellman-Ford");
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
			window.setTitle("Dijkstra");
		}
		else if (e.getSource() == primJMenuItem) {
			algorithmName = "prim";
			window.setTitle("Prim");
		}
		else if (e.getSource() == kruskalJMenuItem) {
			algorithmName = "kruskal";
			window.setTitle("Kruskal");
		}
		else if (e.getSource() == bellmanfordJMenuItem) {
			algorithmName = "bellman-ford";
			window.setTitle("Bellman-Ford");
		}
		else {
			algorithmName = "unknown algorithm";
		}
		
		//instantiate GraphPanel
		window.getContentPane().removeAll();
		window.getContentPane().add(new GraphPanel(algorithmName));
		window.revalidate();
		
	}

}
