package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @author Brett Heithold
 *
 */
public class PathFindingMenu extends JMenu implements ActionListener {
	private static final long serialVersionUID = 1L;

	// Path Finding Menu Items
	private JMenuItem algorithmItem0, algorithmItem1, algorithmItem2, algorithmItem3;
	
	public PathFindingMenu() {
		setText("Path Finding");
		initializePathFindingMenuItems();
	}
	
	private void initializePathFindingMenuItems() {
		algorithmItem0 = new JMenuItem("Algorithm 0");
		algorithmItem0.addActionListener(this);
		add(algorithmItem0);
		algorithmItem1 = new JMenuItem("Algorithm 1");
		algorithmItem1.addActionListener(this);
		add(algorithmItem1);
		algorithmItem2 = new JMenuItem("Algorithm 2");
		algorithmItem2.addActionListener(this);
		add(algorithmItem2);
		algorithmItem3 = new JMenuItem("Algorithm 3");
		algorithmItem3.addActionListener(this);
		add(algorithmItem3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
