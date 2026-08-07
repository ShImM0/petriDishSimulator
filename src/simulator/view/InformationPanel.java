package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.DishInfo;
import simulator.model.DishObserver;
import simulator.model.OrganismInfo;

public class InformationPanel extends JPanel implements DishObserver {

	private Controller ctrl;
	
	private JLabel timeValue;
	private JLabel boidCountValue;
	
	
	public InformationPanel(Controller ctrl) {
		this.ctrl = ctrl;
		initGUI();
		ctrl.addObserver((DishObserver) this);
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);
		this.setBorder(BorderFactory.createTitledBorder("Simulation info"));
	}

	@Override
	public void onRegister(double time, DishInfo dish) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReset(double time, DishInfo dish) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAdvance(double time, DishInfo dish, double dt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onWeightsChanged(double time, DishInfo flock) {
		// TODO Auto-generated method stub
		
	}

}
