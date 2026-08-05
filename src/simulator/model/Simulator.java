package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulator implements Observable<DishObserver> {
	public static final String INVALID_SIMULATOR_WIDTH = "Invalid simulator width: %";
	public static final String INVALID_SIMULATOR_HEIGHT = "Invalid simulator height: %";

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
		if (width <= 0)
			throw new IllegalArgumentException(INVALID_SIMULATOR_WIDTH.formatted(width));
		if (height <= 0)
			throw new IllegalArgumentException(INVALID_SIMULATOR_HEIGHT.formatted(height));

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

	public void advance(double dt) {
		this.time += dt;
		dish.advance(dt);
		for (DishObserver o : this.observers) {
			o.onAdvance(time, dish, dt);
		}
	}

	public void reset() {
		reset(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	private void reset(int width, int height) {
		if (width <= 0)
			throw new IllegalArgumentException(INVALID_SIMULATOR_WIDTH.formatted(width));
		if (height <= 0)
			throw new IllegalArgumentException(INVALID_SIMULATOR_HEIGHT.formatted(height));

		this.dish = new Dish(width, height);
		// TODO
		this.time = 0.0;
		for (DishObserver o : this.observers) {
			o.onReset(time, dish);
		}

	}

	public DishInfo getDishInfo() {
		return this.dish;
	}

	public List<OrganismInfo> getOrganisms() {
		return Collections.unmodifiableList(dish.getOrganisms());
	}
	
	public List<Nutrient> getNutrients() {
		return Collections.unmodifiableList(dish.getNutrients());
	}

	public double getTime() {
		return this.time;
	}

	/*
	 * Observable interface
	 */

	@Override
	public void addObserver(DishObserver obs) {
		if (!observers.contains(obs) && obs != null) {
			observers.add(obs);
			obs.onRegister(time, dish);
		}
	}

	@Override
	public void removeObserver(DishObserver obs) {
		observers.remove(obs);
	}

}
