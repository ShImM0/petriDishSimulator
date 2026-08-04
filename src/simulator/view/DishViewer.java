package simulator.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import simulator.control.Controller;
import simulator.model.DishInfo;
import simulator.model.DishObserver;
import simulator.model.Nutrient;
import simulator.model.OrganismInfo;

public class DishViewer extends AbstractDishViewer implements DishObserver{

	private int width;
	private int height;
	
	private List<OrganismInfo> organisms;
	private List<Nutrient> nutrients;

	public DishViewer(Controller ctrl) {
		ctrl.addObserver((DishObserver) this);
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

	public void paintComponent(Graphics g) {
		Graphics2D gr = (Graphics2D) g;
	}

	/*
	 * DishObserver interface
	 */
	
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
