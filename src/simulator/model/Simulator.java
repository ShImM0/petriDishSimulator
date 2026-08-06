package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulator implements Observable<DishObserver> {
	public static final String INVALID_SIMULATOR_WIDTH = "Invalid simulator width: %";
	public static final String INVALID_SIMULATOR_HEIGHT = "Invalid simulator height: %";

	public static final int DEFAULT_WIDTH = 500;
	public static final int DEFAULT_HEIGHT = 500;

	private final int width; // Non-updateable
	private final int height;

	List<DishObserver> observers;
	private Dish dish;
	private double time;

	public Simulator() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public Simulator(int width, int height) {
		if (width <= 0)
			throw new IllegalArgumentException(INVALID_SIMULATOR_WIDTH.formatted(width));
		if (height <= 0)
			throw new IllegalArgumentException(INVALID_SIMULATOR_HEIGHT.formatted(height));

		this.width = width;
		this.height = height;
		this.dish = new Dish(width, height);
		this.observers = new ArrayList<>();
		this.time = 0.0;
	}

	/*
	 * public void addOrganism(Organism o) { dish.registerOrganism(o); }
	 */

	public void advance(double dt) {
		this.time += dt;
		dish.advance(dt);
		for (DishObserver o : this.observers) {
			o.onAdvance(time, dish, dt);
		}
	}

	public void reset() {
		this.dish = new Dish(width, height);
		this.time = 0.0;
		for (DishObserver o : this.observers) {
			o.onReset(time, dish);
		}

	}
	
	public void setRuleWeights(RuleWeights weights) {
		dish.setRuleWeights(weights);
		for (DishObserver o : this.observers) {
			o.onWeightsChanged(time, dish);
		}
	}

	public RuleWeights getRuleWeights() {
		return this.dish.getRuleWeights();
	}

	public DishInfo getDishInfo() {
		return this.dish;
	}

	public List<OrganismInfo> getOrganisms() {
		return Collections.unmodifiableList(dish.getOrganisms());
	}

	public double getTime() {
		return this.time;
	}

	/*
	 * Observable interface
	 */

	@Override
	public void addObserver(DishObserver obs) {
		if (obs != null && !observers.contains(obs)) {
			observers.add(obs);
			obs.onRegister(time, dish);
		}
	}

	@Override
	public void removeObserver(DishObserver obs) {
		observers.remove(obs);
	}

}
