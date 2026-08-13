package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import simulator.control.Controller;

public class EnvironmentPanel extends JPanel {

	private Controller ctrl;
	private DishViewer dishViewer;

	public EnvironmentPanel(Controller ctrl) {
		this.ctrl = ctrl;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBackground(Theme.CANVAS_BG);
		Dimension dishSize = new Dimension(500, 500);
		dishViewer = new DishViewer(ctrl);
		dishViewer.setPreferredSize(dishSize);
		dishViewer.setMinimumSize(dishSize);
		dishViewer.setMaximumSize(dishSize);

		// Default GridBagLayout keeps the child component at its preferred size
		// Avoids stretching

		JPanel canvasHolder = new JPanel(new GridBagLayout());
		canvasHolder.setBackground(Theme.CANVAS_BG);
		canvasHolder.add(dishViewer);

		this.add(canvasHolder, BorderLayout.CENTER);
	}

}
