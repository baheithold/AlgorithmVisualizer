package gui.graphing;

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
	private JMenuItem bfsJMenuItem, dfsJMenuItem;
	
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
	}
	
	private void constructGraphMenu() {
		add(bfsJMenuItem);
		add(dfsJMenuItem);
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
		else {
			algorithmName = "unknown algorithm";
		}
		
		//instantiate GraphPanel
		window.getContentPane().removeAll();
		window.getContentPane().add(new GraphPanel(algorithmName));
		window.revalidate();
		
	}

}
