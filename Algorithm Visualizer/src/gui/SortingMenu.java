package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import algorithmVisualizer.SortingArray;

/**
 * @author Brett Heithold
 *
 */
public class SortingMenu extends JMenu implements ActionListener {
	static final long serialVersionUID = 1L;
	
	// Window
	private JFrame window;
	
	// Sorting Menu Items
	private JMenuItem algorithm0Item, algorithm1Item, algorithm2Item, algorithm3Item, algorithm4Item, algorithm5Item;
	
	public SortingMenu(JFrame window) {
		this.window = window;
		setText("Sorting");
		initializeSortingMenuItems();
	}
	
	private void initializeSortingMenuItems() {
		// Comparison Based Sorting Algorithms
		add(new JSeparator(SwingConstants.HORIZONTAL));
		JLabel comparisonLabel = new JLabel("Comparison Based");
		add(comparisonLabel);
		add(new JSeparator(SwingConstants.HORIZONTAL));
		algorithm0Item = new JMenuItem("Algorithm0");
		algorithm0Item.addActionListener(this);
		add(algorithm0Item);
		algorithm1Item = new JMenuItem("Algorithm1");
		algorithm1Item.addActionListener(this);
		add(algorithm1Item);
		algorithm2Item = new JMenuItem("Algorithm2");
		algorithm2Item.addActionListener(this);
		add(algorithm2Item);
		// Non-comparison Based Sorting Algorithms
		add(new JSeparator(SwingConstants.HORIZONTAL));
		JLabel noncomparisonLabel = new JLabel("Non-comparison Based");
		add(noncomparisonLabel);
		add(new JSeparator(SwingConstants.HORIZONTAL));
		algorithm3Item = new JMenuItem("Algorithm3");
		algorithm3Item.addActionListener(this);
		add(algorithm3Item);
		algorithm4Item = new JMenuItem("Algorithm4");
		algorithm4Item.addActionListener(this);
		add(algorithm4Item);
		algorithm5Item = new JMenuItem("Algorithm5");
		algorithm5Item.addActionListener(this);
		add(algorithm5Item);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		window.getContentPane().removeAll();
		window.getContentPane().add(new SortingArray());
		window.revalidate();
	}
	
}
