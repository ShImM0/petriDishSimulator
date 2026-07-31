package simulator.view;

import simulator.control.Controller;

public class DishViewer extends AbstractDishViewer {

	private Controller ctrl;

	public DishViewer(Controller ctrl) {
		this.ctrl = ctrl;
		initGUI();
	}

	private void initGUI() {
		setVisible(true);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
