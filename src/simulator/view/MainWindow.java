package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

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

		this.setMinimumSize(new Dimension(800, 600));

		JPanel headerPanel = new HeaderPanel(ctrl);
		mainPanel.add(headerPanel,BorderLayout.PAGE_START); 
		
		JPanel contentPanel = new JPanel();
		JPanel environmentPanel = new EnvironmentPanel(ctrl);
		JPanel infoPanel = new InformationPanel(ctrl);
		contentPanel.add(environmentPanel, BorderLayout.CENTER);
		contentPanel.add(infoPanel, BorderLayout.EAST);
		
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
