package simulator.model;

import java.util.List;

public class Simulator implements Observable<DishObserver> {
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	
	private List<DishObserver> observers;
	private Dish dish;
	
	public Simulator() {
		dish = new Dish(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public void addOrganism(Organism o) {
		dish.registerOrganism(o);
	}
	
	public void addNutrient(Nutrient n) {
		dish.registerNutrient(n);
	}
	
	@Override
	public void addObserver(DishObserver t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeObserver(DishObserver t) {
		// TODO Auto-generated method stub

	}

	public void advance() {

	}
	
	public void reset() {
		
	}

}
