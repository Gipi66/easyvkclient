package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class MainPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

	}

}
