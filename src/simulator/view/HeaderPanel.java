package simulator.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import icons.ICONS;
import simulator.control.Controller;

public class HeaderPanel extends JPanel {
	
	private Controller ctrl;
	private JButton runStopButton, resetButton;
	private JTextField deltaTimeField;
	private boolean stopped;
	
	public HeaderPanel(Controller ctrl) {
		this.ctrl = ctrl;
		stopped = true;
		initGUI();
	}
	
	private void initGUI() {
		setBackground(Color.DARK_GRAY);
		JPanel buttons = new JPanel(new FlowLayout());
		// Run button
		this.runStopButton = new JButton();
		this.runStopButton.setToolTipText("Run simulation");
		this.runStopButton.setIcon(loadIcon("run.png"));
		this.runStopButton.addActionListener((e) -> runStop());
		
		// Reset button
		this.resetButton = new JButton();
		this.resetButton.setToolTipText("Reset simulation");
		this.resetButton.setIcon(loadIcon("reset.png"));
		this.resetButton.addActionListener((e) -> reset());
		
		buttons.add(runStopButton);
		buttons.add(resetButton);
		
		this.add(buttons);
		this.setVisible(true);
	}
	
	private ImageIcon loadIcon(String name) {
		return new ImageIcon(ICONS.class.getResource(name));
	}
	
	private void runStop() {
		stopped = !stopped;
		if(stopped) {
			this.runStopButton.setToolTipText("Run simulation");
			this.runStopButton.setIcon(loadIcon("run.png"));
		}
		else {
			this.runStopButton.setToolTipText("Pause simulation");
			this.runStopButton.setIcon(loadIcon("pause.png"));
			try {
			double dt = Double.parseDouble(deltaTimeField.getText());
			if (dt <= 0) {
				throw new IllegalArgumentException("Invalid Negative value");
			}
			this.runSimulation(dt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void runSimulation(double dt) {
		this.ctrl.advance(dt);
	}
	
	private void reset() {
		this.ctrl.reset(WIDTH, HEIGHT); // TODO
	}
}
