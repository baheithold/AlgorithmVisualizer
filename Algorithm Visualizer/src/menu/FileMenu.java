package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @author Brett Heithold
 *
 */
public class FileMenu extends JMenu implements ActionListener {
	private static final long serialVersionUID = 1L;

	// File Menu Items
	private JMenuItem exitItem;
	
	public FileMenu() {
		setText("File");
		initializeFileMenuItems();
	}
	
	private void initializeFileMenuItems() {
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		add(exitItem);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitItem) {
			System.exit(0);
		}
	}
	
}
