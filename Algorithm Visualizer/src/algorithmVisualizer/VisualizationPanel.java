package algorithmVisualizer;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Brett Heithold
 *
 */
public class VisualizationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public VisualizationPanel() {
	}
	
	public JLabel createHelloLabel() {
		JLabel label = new JLabel("Hello Visualization Panel");
		label.setBackground(new Color(255, 255, 255));
		label.setOpaque(true);
		add(label);
		setBackground(new Color(150, 12, 12));
		return label;
	}
}