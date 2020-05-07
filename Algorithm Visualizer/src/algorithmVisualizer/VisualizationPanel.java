package algorithmVisualizer;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Brett Heithold
 *
 */
public class VisualizationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static JLabel helloLabel;
	
	public VisualizationPanel() {
		helloLabel = new JLabel("Hello Label");
		helloLabel.setBackground(new Color(0, 0, 150));
		helloLabel.setOpaque(true);
		add(helloLabel);
		setBackground(new Color(150, 12, 12));
	}
}