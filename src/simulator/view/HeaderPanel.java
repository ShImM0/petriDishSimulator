package simulator.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import simulator.control.Controller;

public class HeaderPanel extends JPanel {
	
	private Controller ctrl;
	private JButton runStopButton, resetButton;
	
	public HeaderPanel(Controller ctrl) {
		this.ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		
		// setLayout(new BorderLayout());
	}
}
