package simulator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simulator.misc.Utils;

public class Dish implements DishInfo{
	public static final String INVALID_DISH_WIDTH = "Invalid dish width: %";
	public static final String INVALID_DISH_HEIGHT = "Invalid dish height: %";

	private static final int INITIAL_ORGANISMS = 100;

	private int width;
	private int height;

	private List<Organism> organisms;

	public Dish(int width, int height) {
		if (width <= 0)
			throw new IllegalArgumentException(INVALID_DISH_WIDTH.formatted(width));
		if (height <= 0)
			throw new IllegalArgumentException(INVALID_DISH_HEIGHT.formatted(height));

		this.width = width;
		this.height = height;
		organisms = new ArrayList<>();
		
		for (int i = 0; i < INITIAL_ORGANISMS; i++) {
			registerOrganism(randomOrganism());
		}
	}
	
	/* testing
	 * 
	 */

	
	private Organism randomOrganism() {
		Vector2D pos = randomPoint();
		double speed = Organism.MIN_SPEED + Utils.Rand.nextDouble() * 2.0;
		//int size = Organism.MIN_SIZE + Utils.Rand.nextInt() * 8;
		int size = 8;
		double sight = 60 + Utils.Rand.nextDouble() * 60;
		double strength = 1 + Utils.Rand.nextDouble() * 5;
		Color color = Color.getHSBColor(Utils.Rand.nextFloat(), 0.55f, 0.85f);
		String code = Organism.randomGeneticCode();
		return new Organism(code, pos, color, size, speed, sight, strength);
	}
	

	private Vector2D randomPoint() {
		return new Vector2D(Utils.Rand.nextDouble() * width, Utils.Rand.nextDouble() * height);
	}


	public void registerOrganism(Organism o) {
		organisms.add(o);
	}

	public List<OrganismInfo> getOrganisms() {
		return Collections.unmodifiableList(new ArrayList<OrganismInfo>(organisms));
	}

	public void advance(double dt) {
		for(Organism o: organisms) {
			o.update(dt);
			o.moveWithin(dt, width, height);
		}
		
		organisms.removeIf(o -> !o.isAlive());
		
		// steer towards the average heading of the local flockmates
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
