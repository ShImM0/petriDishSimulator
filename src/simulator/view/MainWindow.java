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
		
		this.setMinimumSize(new Dimension(800,600)); // TODO
		
		// TODO add DishViewer and other information
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}

}
