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
	private JMenuItem primJMenuItem, kruskalJMenuItem, bellmanfordJMenuItem;
	
	public GraphMenu(JFrame window) {
		this.window = window;
		setText("Graph");
		initializeGraphMenuItems();
		constructGraphMenu();
	}
	
	private void initializeGraphMenuItems() {

		primJMenuItem = new JMenuItem("Prim");
		primJMenuItem.addActionListener(this);
		kruskalJMenuItem = new JMenuItem("Kruskal");
		kruskalJMenuItem.addActionListener(this);
		bellmanfordJMenuItem = new JMenuItem("Bellman-Ford");
		bellmanfordJMenuItem.addActionListener(this);
	}
	
	private void constructGraphMenu() {
		add(primJMenuItem);
		add(kruskalJMenuItem);
		add(bellmanfordJMenuItem);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String algorithmName;
		if (e.getSource() == primJMenuItem) {
			algorithmName = "prim";
			window.setTitle("Prim");
		}
		else if (e.getSource() == kruskalJMenuItem) {
			algorithmName = "Kruskal";
			window.setTitle("Kruskal");
		}
		else if (e.getSource() == bellmanfordJMenuItem) {
			algorithmName = "Bellman-Ford";
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
