package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;

public class MainWindow extends JFrame {

	private Controller ctrl;

	public MainWindow(Controller ctrl) {
		super("Petri Dish Simulator");
		this.ctrl = ctrl;
		initGUI();
	}

	private void initGUI() {

		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);

		this.setMinimumSize(new Dimension(800, 600)); // TODO

		JPanel headerPanel = new HeaderPanel(ctrl);
		mainPanel.add(headerPanel, BorderLayout.PAGE_START);

		JPanel contentPanel = new JPanel(new BorderLayout(16, 0));
		contentPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

		JPanel environmentPanel = new EnvironmentPanel(ctrl);
		JPanel controlPanel = new ControlPanel(ctrl); // TODO
		JPanel infoPanel = new InformationPanel(ctrl);

		infoPanel.setPreferredSize(new Dimension(250, 500));

		contentPanel.add(environmentPanel, BorderLayout.CENTER);
		contentPanel.add(infoPanel, BorderLayout.EAST);

		mainPanel.add(contentPanel, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
