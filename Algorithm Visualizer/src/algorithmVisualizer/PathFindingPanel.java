package algorithmVisualizer;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * @author Brett Heithold
 *
 */
public class PathFindingPanel extends VisualizationPanel {
	private static final long serialVersionUID = 1L;
	private JLabel helloLabel;

	public PathFindingPanel() {
		helloLabel = createHelloLabel();
	}
	
	@Override
	public JLabel createHelloLabel() {
		JLabel label = new JLabel("Hello Path Finding Panel");
		label.setBackground(new Color(255, 255, 255));
		label.setOpaque(true);
		add(label);
		setBackground(new Color(150, 12, 12));
		return label;
	}
	
}
