package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * @author Brett Heithold
 *
 */
public class AboutPanel extends VisualizationPanel {
	private static final long serialVersionUID = 1L;
	private JLabel aboutLabel;
	
	public AboutPanel(int width, int height) {
		super(width, height);
		setLayout(new BorderLayout());
		aboutLabel = new JLabel("Welcome!", (int) CENTER_ALIGNMENT);
		aboutLabel.setFont(new Font("", Font.PLAIN, 30));
		add(aboutLabel, BorderLayout.NORTH);
	}

}
