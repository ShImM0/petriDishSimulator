package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dish implements DishInfo {
	public static final String INVALID_DISH_WIDTH = "Invalid dish width: %";
	public static final String INVALID_DISH_HEIGHT = "Invalid dish height: %";

	private int width;
	private int height;

	private List<Organism> organisms;
	private List<Nutrient> nutrients;

	public Dish(int width, int height) {
		if (width <= 0)
			throw new IllegalArgumentException(INVALID_DISH_WIDTH.formatted(width));
		if (height <= 0)
			throw new IllegalArgumentException(INVALID_DISH_HEIGHT.formatted(height));

		this.width = width;
		this.height = height;
		organisms = new ArrayList<>();
		nutrients = new ArrayList<>();
	}

	public void registerOrganism(Organism o) {
		organisms.add(o);
	}

	public void registerNutrient(Nutrient n) {
		nutrients.add(n);
	}

	public List<OrganismInfo> getOrganisms() {
		return Collections.unmodifiableList(new ArrayList<OrganismInfo>(organisms));
	}
	
	public List<Nutrient> getNutrients(){
		return Collections.unmodifiableList(nutrients);
	}
	
	public void advance(double dt) {
		for(Organism o: organisms) {
			o.update(dt);
		}
	}

	/*
	 * DishInfo interface
	 */

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

}
