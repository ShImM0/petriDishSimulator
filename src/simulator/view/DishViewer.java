package simulator.view;

import javax.swing.JFrame;

import simulator.control.Controller;

public class DishViewer extends JFrame {

	private Controller ctrl;

	public DishViewer(Controller ctrl) {
		super("Petri Dish Simulator");
		this.ctrl = ctrl;
		initGUI();
	}

	private void initGUI() {
		setVisible(true);
	}
}
