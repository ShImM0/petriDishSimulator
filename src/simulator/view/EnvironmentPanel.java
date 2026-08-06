package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.DishInfo;
import simulator.model.DishObserver;
import simulator.model.OrganismInfo;

public class EnvironmentPanel extends JPanel implements DishObserver {

	private Controller ctrl;
	private DishViewer dishViewer;

	public EnvironmentPanel(Controller ctrl) {
		this.ctrl = ctrl;
		initGUI();
		ctrl.addObserver((DishObserver) this);
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		dishViewer = new DishViewer(ctrl);
		dishViewer.setPreferredSize(new Dimension(500, 500));
		this.add(dishViewer, BorderLayout.CENTER);
		this.setBackground(Color.blue);
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
	public void onOrganismAdded(double time, DishInfo dish, OrganismInfo o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAdvance(double time, DishInfo dish, double dt) {
		// TODO Auto-generated method stub

	}

}
