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
	private JMenuItem aStarJMenuItem;
	
	public PathFindingMenu(JFrame window) {
		this.window = window;
		setText("Path Finding");
		initializePathFindingMenuItems();
		constructPathFindingMenu();
	}
	
	private void initializePathFindingMenuItems() {
		aStarJMenuItem = new JMenuItem("A*");
		aStarJMenuItem.addActionListener(this);
	}

	private void constructPathFindingMenu() {
		add(aStarJMenuItem);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String algorithmName;
		if (e.getSource() == aStarJMenuItem) {
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
