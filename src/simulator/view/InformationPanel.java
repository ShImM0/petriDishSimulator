package simulator.view;

import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.DishInfo;
import simulator.model.DishObserver;
import simulator.model.OrganismInfo;

public class InformationPanel extends JPanel implements DishObserver {

	private Controller ctrl;
	private OrganismInfo organism;
	
	public InformationPanel(Controller ctrl) {
		this.ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		// TODO
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
