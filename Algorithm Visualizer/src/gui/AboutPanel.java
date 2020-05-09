package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * @author Brett Heithold
 *
 */
public class AboutPanel extends VisualizationPanel {
	private static final long serialVersionUID = 1L;
	private static final String ABOUT_TEXT_FILEPATH = "static/text/aboutText.txt";
	private JLabel aboutLabel;
	private JTextArea aboutTextArea;
	
	public AboutPanel() {
		aboutLabel = new JLabel("About");
		add(aboutLabel);
		aboutTextArea = createAboutTextArea();
		add(aboutTextArea);
	}
	
	public JTextArea createAboutTextArea() {
		JTextArea textArea = readTextFileToTextArea(ABOUT_TEXT_FILEPATH);
		textArea.setLineWrap(true);
		textArea.setSize(windowWidth - 100, windowHeight - 100);
		return textArea;
	}
	
	private JTextArea readTextFileToTextArea(String filename) {
		JTextArea textArea = new JTextArea();
		try {
			FileReader reader = new FileReader(filename);
			BufferedReader br = new BufferedReader(reader);
			textArea.read(br, null);
			br.close();
		} catch (FileNotFoundException e) {
			textArea.setText("aboutText.txt file NOT FOUND!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return textArea;
	}

}
