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
		constructHelpMenu();
	}
	
	private void initializeHelpMenu() {
		about = new JMenuItem("About");
		about.addActionListener(this);
		item1 = new JMenuItem("Item1");
		item1.addActionListener(this);
		item2 = new JMenuItem("Item2");
		item2.addActionListener(this);
		item3 = new JMenuItem("Item3");
		item3.addActionListener(this);
	}
	
	private void constructHelpMenu() {
		add(about);
		add(item1);
		add(item2);
		add(item3);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == about) {
			// Remove previous content and add about content to content pane
			window.setTitle("Algorithm Visualizer");
			window.getContentPane().removeAll();
			window.getContentPane().add(new AboutPanel());
			window.revalidate();
		}
	}

}
