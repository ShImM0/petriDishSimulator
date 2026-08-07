package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
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
		this.setPreferredSize(new Dimension(800, 50));
		setLayout(new BorderLayout());
		setBackground(Color.DARK_GRAY);

		JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

		JLabel title = new JLabel("Petri Dish Simulator");
		titlePanel.add(title);
		left.add(titlePanel);

		JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel time = new JPanel(new FlowLayout());

		time.add(new JLabel("Delta-time:"));
		this.deltaTimeField = new JTextField();
		this.deltaTimeField.setPreferredSize(new Dimension(60, 30));
		this.deltaTimeField.setText(String.valueOf(0.03));

		time.add(deltaTimeField);
		right.add(time);

		JPanel buttons = new JPanel(new FlowLayout());
		
		// Run button
		this.runStopButton = new JButton();
		this.runStopButton.setToolTipText("Run simulation");
		this.runStopButton.setIcon(loadIconScaledDefault("run.png"));
		this.runStopButton.addActionListener((e) -> runStop());

		// Reset button
		this.resetButton = new JButton(); // TODO run after reset does not pause/run
		this.resetButton.setToolTipText("Reset simulation");
		this.resetButton.setIcon(loadIconScaledDefault("reset.png"));
		this.resetButton.addActionListener((e) -> reset());

		buttons.add(runStopButton);
		buttons.add(resetButton);

		right.add(buttons);
		this.add(left, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);
		this.setVisible(true);
	}

	private ImageIcon loadIconScaledDefault(String name) {
		return loadIconScaled(name, 25, 25);
	}

	private ImageIcon loadIconScaled(String name, int width, int height) {
		try {
			ImageIcon icon = new ImageIcon(ICONS.class.getResource(name));
			return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		} catch (NullPointerException npe) {
			npe.printStackTrace(); // TODO;
		}
		return null;
	}

	private void runStop() {
		stopped = !stopped;
		if (stopped) {
			this.runStopButton.setToolTipText("Run simulation");
			this.runStopButton.setIcon(loadIconScaledDefault("run.png"));
			ctrl.pause();
		} else {
			this.runStopButton.setToolTipText("Pause simulation");
			this.runStopButton.setIcon(loadIconScaledDefault("pause.png"));
			try {
				double dt = Double.parseDouble(deltaTimeField.getText());
				if (dt <= 0) {
					throw new IllegalArgumentException("Invalid Negative value");
				}
				ctrl.run(dt);
			} catch (Exception e) {
				// TODO
				e.printStackTrace();
			}
		}
	}

	private void reset() {
		stopped = true;
		this.runStopButton.setToolTipText("Run simulation");
		this.runStopButton.setIcon(loadIconScaledDefault("run.png"));
		this.ctrl.reset();
	}
}
