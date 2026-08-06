package simulator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simulator.misc.Utils;

public class Dish implements DishInfo {
	public static final String INVALID_DISH_WIDTH = "Invalid dish width: %";
	public static final String INVALID_DISH_HEIGHT = "Invalid dish height: %";

	private static final int INITIAL_ORGANISMS = 100;

	private static final RuleWeights DEFAULT_WEIGHTS = new RuleWeights(1.5, 0.5, 3.0);
	
	private int width;
	private int height;

	private List<Organism> organisms;
	private RuleWeights weights;

	public Dish(int width, int height) {
		if (width <= 0)
			throw new IllegalArgumentException(INVALID_DISH_WIDTH.formatted(width));
		if (height <= 0)
			throw new IllegalArgumentException(INVALID_DISH_HEIGHT.formatted(height));

		this.width = width;
		this.height = height;
		this.weights = DEFAULT_WEIGHTS;
		this.organisms = new ArrayList<>();

		for (int i = 0; i < INITIAL_ORGANISMS; i++) {
			registerOrganism(randomOrganism());
		}
	}

	/*
	 * testing
	 */

	private Organism randomOrganism() {
		Vector2D pos = randomPoint();

		// Random starting velocity
		Vector2D velocity = new Vector2D(Utils.Rand.nextDouble() * 2 - 1, Utils.Rand.nextDouble() * 2 - 1);

		int size = Organism.MIN_SIZE + Utils.Rand.nextInt(Organism.MAX_SIZE - Organism.MIN_SIZE + 1);

		double maxSpeed = Organism.MIN_SPEED + Utils.Rand.nextDouble() * (Organism.MAX_SPEED - Organism.MIN_SPEED);

		double maxForce = 0.1 + Utils.Rand.nextDouble() * 0.9;

		double sightRadius = Organism.MIN_SIGHT + Utils.Rand.nextDouble() * (Organism.MAX_SIGHT - Organism.MIN_SIGHT);

		double separationRadius = size * 2.0;

		Color color = Color.getHSBColor(Utils.Rand.nextFloat(), 0.55f, 0.85f);
		
		String code = Organism.randomGeneticCode();
		
		return new Organism(code, pos, velocity, color, size, maxSpeed, maxForce, sightRadius, separationRadius);
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
		for (Organism o : organisms) {
			o.update(dt);
			o.moveWithin(dt, width, height);
		}

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

	@Override
	public RuleWeights getRuleWeights() {
		return this.weights;
	}

}
