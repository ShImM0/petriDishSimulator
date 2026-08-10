package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import icons.ICONS;
import simulator.control.Controller;
import simulator.model.DishInfo;
import simulator.model.DishObserver;

public class HeaderPanel extends JPanel{

	private Controller ctrl;
	private JButton addButton, runStopButton, resetButton;
	private JTextField deltaTimeField;
	private boolean stopped;

	public HeaderPanel(Controller ctrl) {
		this.ctrl = ctrl;
		stopped = true;
		initGUI();
	}

	private void initGUI() {
		this.setPreferredSize(new Dimension(800, 50));
		this.setBackground(Theme.HEADER_BG);
		this.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 16));
		setLayout(new BorderLayout());

		// LEFT
		JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
		left.setOpaque(false);
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		titlePanel.setOpaque(false);

		JLabel title = new JLabel("Petri Dish Simulator");
		title.setFont(Theme.FONT_TITLE);
		title.setForeground(Theme.HEADER_TEXT);
		title.setAlignmentX(LEFT_ALIGNMENT);
		JLabel subtitle = new JLabel("Boids Simulator \u2022 SEPARATION / ALIGNMENT / COHESION");
		subtitle.setFont(Theme.FONT_SUBTITLE);
		subtitle.setForeground(Theme.HEADER_SUBTEXT);
		subtitle.setAlignmentX(LEFT_ALIGNMENT);
		
		titlePanel.add(title);
		titlePanel.add(subtitle);
		left.add(titlePanel);
		
		// RIGHT
		JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		right.setOpaque(false);
		JPanel time = new JPanel(new FlowLayout());
		time.setOpaque(false);
		JLabel dtLabel = new JLabel("Delta-time:");
		dtLabel.setForeground(Theme.HEADER_SUBTEXT);
		dtLabel.setFont(Theme.HEADER_LABEL);
		time.add(dtLabel);
		this.deltaTimeField = new JTextField();
		this.deltaTimeField.setPreferredSize(new Dimension(60, 30));
		this.deltaTimeField.setText(String.valueOf(0.03));

		time.add(deltaTimeField);
		right.add(time);

		JPanel buttons = new JPanel(new FlowLayout());
		buttons.setOpaque(false);
		
		// Add button
		this.addButton = new JButton();
		this.addButton.setToolTipText("Add an organism");
		this.addButton.setIcon(loadIconScaledDefault("add.png"));
		this.addButton.addActionListener((e) -> ctrl.addOrganism());
		
		// Run button
		this.runStopButton = new JButton();
		this.runStopButton.setToolTipText("Run simulation");
		this.runStopButton.setIcon(loadIconScaledDefault("run.png"));
		this.runStopButton.addActionListener((e) -> runStop());

		// Reset button
		this.resetButton = new JButton();
		this.resetButton.setToolTipText("Reset simulation");
		this.resetButton.setIcon(loadIconScaledDefault("reset.png"));
		this.resetButton.addActionListener((e) -> reset());

		buttons.add(addButton);
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
