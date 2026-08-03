package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dish implements DishInfo{

	private int width;
	private int height;
	
	private List<Organism> organisms;
	private List<Nutrient> nutrients;
	
	public Dish(int width, int height) {
		this.width = width;
		this.height = height;
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
