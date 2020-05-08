package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import algorithmVisualizer.AboutPanel;

/**
 * @author Brett Heithold
 *
 */
public class HelpMenu extends JMenu implements ActionListener {
	private static final long serialVersionUID = 1L;

	// Window
	private JFrame window;
	
	// Help Menu Items
	private JMenuItem about, item1, item2, item3;
	
	public HelpMenu(JFrame window) {
		this.window = window;
		setText("Help");
		initializeHelpMenu();
	}
	
	private void initializeHelpMenu() {
		about = new JMenuItem("About");
		about.addActionListener(this);
		add(about);
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
		if (e.getSource() == about) {
			window.getContentPane().removeAll();
			window.getContentPane().add(new AboutPanel());
			window.revalidate();
		}
	}

}
