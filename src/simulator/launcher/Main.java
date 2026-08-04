package simulator.launcher;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import simulator.control.Controller;
import simulator.model.Simulator;
import simulator.view.MainWindow;

public class Main {
	private static Simulator simulator;
	private static Controller controller;

	public static void main(String[] args) {
		try {
			startGUIMode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void startGUIMode() throws Exception {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		simulator = new Simulator();
		controller = new Controller(simulator);

		SwingUtilities.invokeAndWait(() -> new MainWindow(controller));

	}
}