package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.DishInfo;
import simulator.model.DishObserver;
import simulator.model.OrganismInfo;

public class EnvironmentPanel extends JPanel{

	private Controller ctrl;
	private DishViewer dishViewer;

	public EnvironmentPanel(Controller ctrl) {
		this.ctrl = ctrl;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		dishViewer = new DishViewer(ctrl);
		dishViewer.setPreferredSize(new Dimension(500, 500));
		this.add(dishViewer, BorderLayout.CENTER);
		this.setBackground(Theme.CANVAS_BG);
	}

}
