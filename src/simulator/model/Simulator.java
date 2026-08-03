package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class Simulator implements Observable<DishObserver> {
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	
	private List<DishObserver> observers;
	private Dish dish;
	private double time;
	
	public Simulator() {
		this.dish = new Dish(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.observers = new ArrayList<>();
		this.time = 0.0;
	}
	
	public Simulator(int width, int height) {
		this.dish = new Dish(width, height);
		this.observers = new ArrayList<>();
		this.time = 0.0;
	}
	
	public void addOrganism(Organism o) {
		dish.registerOrganism(o);
	}
	
	public void addNutrient(Nutrient n) {
		dish.registerNutrient(n);
	}
	
	
	public void advance() {

	}
	
	public void reset() {
		
	}
	
	/*
	 * Observable interface
	 */
	
	@Override
	public void addObserver(DishObserver obs) {
		if (!observers.contains(obs) && obs != null) {
			observers.add(obs);
			obs.onRegister(time, dish.getOrganisms());
		}
	}

	@Override
	public void removeObserver(DishObserver obs) {
		observers.remove(obs);
	}


}
