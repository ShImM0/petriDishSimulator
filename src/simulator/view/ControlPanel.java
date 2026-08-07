package simulator.view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;

import simulator.control.Controller;

public class ControlPanel extends JPanel {
	private JSlider separation;
	private JSlider alignment;
	private JSlider cohesion;
	
	public ControlPanel (Controller ctrl) {
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder("Rule weights"));
		
	}
}
