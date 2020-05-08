package algorithmVisualizer;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * @author Brett Heithold
 *
 */
public class SortingPanel extends VisualizationPanel {
	private static final long serialVersionUID = 1L;
	private static JLabel helloLabel;
	
	public SortingPanel() {
		helloLabel = createHelloLabel();
	}
	
	@Override
	public JLabel createHelloLabel() {
		JLabel label = new JLabel("Hello Sorting Panel");
		label.setBackground(new Color(255, 255, 255));
		label.setOpaque(true);
		add(label);
		setBackground(new Color(150, 12, 12));
		return label;
	}
	
}
