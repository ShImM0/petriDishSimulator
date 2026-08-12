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

	private static final int INITIAL_ORGANISMS = 200;

	private static final int MIN_SIZE = 3;
	private static final int MAX_SIZE = 20;

	private static final double MIN_SPEED = 25.0;
	private static final double MAX_SPEED = 75.0;

	public static final double MIN_SIGHT = 25.0;
	public static final double MAX_SIGHT = 125.0;

	// Proportional for each organism
	private static final double MIN_FORCE_TO_SPEED_RATIO = 2.5;
	private static final double MAX_FORCE_TO_SPEED_RATIO = 4.0;

	private static final RuleWeights DEFAULT_WEIGHTS = new RuleWeights(1.5, 0.5, 0.5);

	private int width;
	private int height;

	private List<Organism> organisms;
	private RuleWeights weights;

	private boolean discriminationEnabled;
	private boolean wrapEnabled;

	public Dish(int width, int height) {
		if (width <= 0)
			throw new IllegalArgumentException(INVALID_DISH_WIDTH.formatted(width));
		if (height <= 0)
			throw new IllegalArgumentException(INVALID_DISH_HEIGHT.formatted(height));

		this.width = width;
		this.height = height;
		this.discriminationEnabled = true;
		this.wrapEnabled = true;
		this.weights = DEFAULT_WEIGHTS;
		this.organisms = new ArrayList<>();

		for (int i = 0; i < INITIAL_ORGANISMS; i++) {
			registerOrganism(randomOrganism());
		}
	}

	public Organism addRandomOrganism() {
		Organism o = randomOrganism();
		organisms.add(o);
		return o;
	}

	private Organism randomOrganism() {
		Vector2D pos = randomPoint();

		double maxSpeed = MIN_SPEED + Utils.Rand.nextDouble() * (MAX_SPEED - MIN_SPEED);
		double angle = Utils.Rand.nextDouble() * Math.PI * 2;
		Vector2D velocity = Vector2D.fromAngle(angle).scale(maxSpeed * (0.3 + Utils.Rand.nextDouble() * 0.4));

		int size = MIN_SIZE + Utils.Rand.nextInt(MAX_SIZE - MIN_SIZE + 1);

		double forceRatio = MIN_FORCE_TO_SPEED_RATIO
				+ Utils.Rand.nextDouble() * (MAX_FORCE_TO_SPEED_RATIO - MIN_FORCE_TO_SPEED_RATIO);
		double maxForce = maxSpeed * forceRatio;

		double sightRadius = MIN_SIGHT + Utils.Rand.nextDouble() * (MAX_SIGHT - MIN_SIGHT);
		double separationRadius = size * 2.0;

		Species species = Species.random();

		return new Organism(species, pos, velocity, size, maxSpeed, maxForce, sightRadius, separationRadius);
	}

	private Vector2D randomPoint() {
		return new Vector2D(Utils.Rand.nextDouble() * width, Utils.Rand.nextDouble() * height);
	}

	public void registerOrganism(Organism o) { // better for refactoring
		organisms.add(o);
	}

	public void advance(double dt) {
		for (Organism o : organisms) {
			List<Organism> neighbours = inRange(o);
			// calculate movement according to situation
			o.computeAcceleration(neighbours, weights);
		}
		for (Organism o : organisms) {
			o.moveWithin(dt, width, height, wrapEnabled);
		}
		// steer towards the average heading of the local flockmates
	}

	private List<Organism> inRange(Organism o) {
		List<Organism> neighbors = new ArrayList<>();
		double radius = o.getSightRadius();
		for (Organism other : organisms) {
			if (other == o)
				continue;
			if (discriminationEnabled && !other.getGeneticCode().equals(o.getGeneticCode()))
				continue;
			if (o.getPosition().distance(other.getPosition()) <= radius) {
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

	@Override
	public boolean getDiscriminationEnabled() {
		return this.discriminationEnabled;
	}

	@Override
	public void setDiscriminationEnabled(boolean enabled) {
		this.discriminationEnabled = enabled;
	}

	@Override
	public boolean getWrapEnabled() {
		return this.wrapEnabled;
	}

	@Override
	public void setWrapEnabled(boolean enabled) {
		this.wrapEnabled = enabled;
	}

}
