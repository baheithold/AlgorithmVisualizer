package gui;

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

	// Window
	private VisualizerWindow window;
	
	// Help Menu Items
	private JMenuItem about;
	
	public HelpMenu(VisualizerWindow window) {
		this.window = window;
		setText("Help");
		initializeHelpMenu();
		constructHelpMenu();
	}
	
	private void initializeHelpMenu() {
		about = new JMenuItem("Welcome");
		about.addActionListener(this);
	}
	
	private void constructHelpMenu() {
		add(about);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == about) {
			// Remove previous content and add about content to content pane
			window.setTitle("Algorithm Visualizer");
			window.getContentPane().removeAll();
			window.getContentPane().add(new AboutPanel(window.getCurrentWindowWidth(), window.getCurrentWindowHeight()));
			window.revalidate();
		}
	}

}
