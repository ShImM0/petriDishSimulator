package simulator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simulator.misc.Utils;

public class Dish implements DishInfo {
	public static final String INVALID_DISH_WIDTH = "Invalid dish width: %";
	public static final String INVALID_DISH_HEIGHT = "Invalid dish height: %";
	public static final String INVALID_RULE_WEIGHTS = "Rule weights cannot be null";

	private static final int INITIAL_ORGANISMS = 100;
	
	public static final double MIN_SPEED = 0.5;
	public static final double MAX_SPEED = 5.0;
	public static final int MIN_SIZE = 2;
	public static final int MAX_SIZE = 20;
	public static final double MIN_SIGHT = 30;
	public static final double MAX_SIGHT = 150;
	public static final double MAX_ENERGY = 100.0;

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

		int size = MIN_SIZE + Utils.Rand.nextInt(MAX_SIZE - MIN_SIZE + 1);

		double maxForce = 0.1 + Utils.Rand.nextDouble() * 0.9;
		
		double separationRadius = size * 2.0;

		Color color = Color.getHSBColor(Utils.Rand.nextFloat(), 0.55f, 0.85f);

		String code = Organism.randomGeneticCode();

		return new Organism(code, pos, velocity, color, size, MAX_SPEED, maxForce, MAX_SIGHT, separationRadius);
	}

	private Vector2D randomPoint() {
		return new Vector2D(Utils.Rand.nextDouble() * width, Utils.Rand.nextDouble() * height);
	}

	public void registerOrganism(Organism o) { // TODO better for refactoring
		organisms.add(o);
	}


	public void advance(double dt) {
		for (Organism o : organisms) {
			List<Organism> neighbours = inRange(o);
			o.moveWithin(dt, width, height);
		}
		// steer towards the average heading of the local flockmates
	}

	private List<Organism> inRange(Organism o) {
		List<Organism> neighbors = new ArrayList<>();
		double radius = o.getSightRadius();
		for (Organism other : organisms) {
			if (other != o && o.getPosition().distance(other.getPosition()) <= radius) {
				neighbors.add(other);
			}
		}
		return neighbors;
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
	public List<OrganismInfo> getOrganisms() {
		return Collections.unmodifiableList(new ArrayList<OrganismInfo>(organisms));
	}
	
	@Override
	public RuleWeights getRuleWeights() {
		return this.weights;
	}

	@Override
	public void setRuleWeights(RuleWeights weights) {
		if (weights == null)
			throw new IllegalArgumentException(INVALID_RULE_WEIGHTS);
		this.weights = weights;
	}

}
