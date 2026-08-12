package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;

public class MainWindow extends JFrame {

	private static final int SIDEBAR_WIDTH = 300;
	private Controller ctrl;

	public MainWindow(Controller ctrl) {
		super("Petri Dish Simulator");
		this.ctrl = ctrl;
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Theme.APP_BG);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		setContentPane(mainPanel);

		this.setMinimumSize(new Dimension(800, 700));

		JPanel headerPanel = new HeaderPanel(ctrl);
		mainPanel.add(headerPanel, BorderLayout.PAGE_START);

		JPanel contentPanel = new JPanel(new BorderLayout(16, 0));
		contentPanel.setBackground(Theme.APP_BG);
		contentPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

		JPanel environmentPanel = new EnvironmentPanel(ctrl);

		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setPreferredSize(new Dimension(SIDEBAR_WIDTH, 1));

		JPanel controlPanel = new ControlPanel(ctrl);
		controlPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		controlPanel.setMaximumSize(new Dimension(SIDEBAR_WIDTH, Integer.MAX_VALUE)); // max space, aligned left
		JPanel infoPanel = new InformationPanel(ctrl);
		infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.setMaximumSize(new Dimension(SIDEBAR_WIDTH, Integer.MAX_VALUE));

		sidePanel.add(controlPanel);
		sidePanel.add(Box.createVerticalStrut(12));
		sidePanel.add(infoPanel);

		contentPanel.add(environmentPanel, BorderLayout.CENTER);
		contentPanel.add(sidePanel, BorderLayout.WEST);

		mainPanel.add(contentPanel, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
