package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @author Brett Heithold
 *
 */
public class HelpMenu extends JMenu implements ActionListener {
	private static final long serialVersionUID = 1L;

	// Help Menu Items
	private JMenuItem item0, item1, item2, item3;
	
	public HelpMenu() {
		setText("Help");
		initializeHelpMenu();
	}
	
	private void initializeHelpMenu() {
		item0 = new JMenuItem("Item0");
		item0.addActionListener(this);
		add(item0);
		item1 = new JMenuItem("Item1");
		item1.addActionListener(this);
		add(item1);
		item2 = new JMenuItem("Item2");
		item2.addActionListener(this);
		add(item2);
		item3 = new JMenuItem("Item3");
		item3.addActionListener(this);
		add(item3);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
