package simulator.model;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import simulator.misc.Utils;

public class Organism implements OrganismInfo, Entity {

	public static final String INVALID_SPECIES = "Invalid species";
	public static final String INVALID_POSITION = "Invalid position";
	public static final String INVALID_VELOCITY = "Invalid velocity";
	public static final String INVALID_SIZE = "Invalid size";
	public static final String INVALID_SIGHT_RADIUS = "Invalid sight radius";
	public static final String INVALID_SEPARATION_RADIUS = "Invalid separation radius";
	public static final String INVALID_MAX_SPEED = "Invalid max speed";
	public static final String INVALID_MAX_FORCE = "Invalid max force";

	private static final String ALPHANUMERIC_CHARACTERS_ID = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final SecureRandom SecRand = new SecureRandom();
	private static final int ID_LENGTH = 6;

	// Visual Representation
	private final String id; // Possible ampliation to see a organism's properties
	private final Species species;
	private final Color color;
	private int size;

	// Positioning
	private Vector2D pos;
	private Vector2D velocity;
	private Vector2D acceleration;

	// Movement
	private double maxSpeed;
	private double maxForce;
	private double sightRadius;
	private double separationRadius;

	public Organism(Species species, Vector2D pos, Vector2D velocity, int size, double maxSpeed, double maxForce,
			double sightRadius, double separationRadius) {

		if (species == null)
			throw new IllegalArgumentException(INVALID_SPECIES);
		if (pos == null)
			throw new IllegalArgumentException(INVALID_POSITION);
		if (velocity == null)
			throw new IllegalArgumentException(INVALID_VELOCITY);
		if (size <= 0)
			throw new IllegalArgumentException(INVALID_SIZE);
		if (maxSpeed <= 0)
			throw new IllegalArgumentException(INVALID_MAX_SPEED);
		if (maxForce <= 0)
			throw new IllegalArgumentException(INVALID_MAX_FORCE);
		if (sightRadius <= 0)
			throw new IllegalArgumentException(INVALID_SIGHT_RADIUS);
		if (separationRadius <= 0)
			throw new IllegalArgumentException(INVALID_SEPARATION_RADIUS);

		this.id = randomId(ID_LENGTH);
		this.species = species;
		this.color = species.getColor();
		this.pos = pos;
		this.velocity = velocity;
		this.size = size;
		this.maxSpeed = maxSpeed;
		this.maxForce = maxForce;
		this.sightRadius = sightRadius;
		this.separationRadius = separationRadius;
		this.acceleration = Vector2D.zero();
	}

	private static String randomId(int idLength) {
		StringBuilder idString = new StringBuilder();
		for (int i = 0; i < idLength; i++) {
			int index = SecRand.nextInt(ALPHANUMERIC_CHARACTERS_ID.length());
			idString.append(ALPHANUMERIC_CHARACTERS_ID.charAt(index));
		}
		return idString.toString();
	}

	static String randomGeneticCode() {
		return randomId(7);
	}

	/*
	 * Rule weights functions
	 */

	void computeAcceleration(List<Organism> neighbors, RuleWeights weights) {
		Vector2D separation = separation(neighbors);
		Vector2D alignment = alignment(neighbors);
		Vector2D cohesion = cohesion(neighbors);

		Vector2D steer = separation.scale(weights.getSeparation()).plus(alignment.scale(weights.getAlignment()))
				.plus(cohesion.scale(weights.getCohesion()));

		this.acceleration = steer.limit(maxForce);

	}

	// Neighbors already filtered by radius, lower bound
	private Vector2D separation(List<Organism> neighbors) {
		Vector2D steer = Vector2D.zero();
		int count = 0;

		for (Organism other : neighbors) {
			double d = this.pos.distance(other.getPosition());
			if (d > 0 && d < separationRadius) {
				// vector pointing away from neighbor, weighted inversely by distance
				Vector2D diff = this.pos.subtract(other.getPosition()).direction().scale(1.0 / d);
				steer = steer.plus(diff);
				count++;
			}
		}

		if (count > 0) {
			steer = steer.scale(1.0 / count);
		}

		if (steer.magnitude() > 0) {
			// Reynolds: steering = desired - velocity
			steer = steer.direction().scale(maxSpeed).subtract(this.velocity).limit(maxForce);
		}

		return steer;
	}

	private Vector2D alignment(List<Organism> neighbors) {
		Vector2D sum = Vector2D.zero();
		int count = 0;

		for (Organism other : neighbors) {
			sum = sum.plus(other.getVelocity());
			count++;
		}

		if (count > 0) {
			sum = sum.scale(1.0 / count).direction().scale(maxSpeed);
			return sum.subtract(this.velocity).limit(maxForce);
		}

		return Vector2D.zero();

	}

	private Vector2D cohesion(List<Organism> neighbors) {
		Vector2D sum = Vector2D.zero();
		int count = 0;

		for (Organism other : neighbors) {
			sum = sum.plus(other.getPosition());
			count++;
		}

		if (count > 0) {
			Vector2D target = sum.scale(1.0 / count);
			Vector2D desired = target.subtract(this.pos).direction().scale(maxSpeed);
			return desired.subtract(this.velocity).limit(maxForce);
		}

		return Vector2D.zero();
	}

	void moveWithin(double dt, double width, double height, boolean wrap) {
		velocity = velocity.plus(acceleration.scale(dt)).limit(maxSpeed);

		double newX = pos.getX() + velocity.getX() * dt;
		double newY = pos.getY() + velocity.getY() * dt;

		if (wrap) {
			newX = wrapCoordinate(newX, width);
			newY = wrapCoordinate(newY, height);
		} else {
			double vx = velocity.getX();
			double vy = velocity.getY();

			if (newX - size < 0) {
				newX = size;
				vx = Math.abs(vx);
			} else if (newX + size > width) {
				newX = width - size;
				vx = -Math.abs(vx);
			}
			if (newY - size < 0) {
				newY = size;
				vy = Math.abs(vy);
			} else if (newY + size > height) {
				newY = height - size;
				vy = -Math.abs(vy);
			}

			velocity = new Vector2D(vx, vy);
		}

		pos = new Vector2D(newX, newY);
	}

	private static double wrapCoordinate(double value, double max) {
		return ((value % max) + max) % max;
	}

	/*
	 * OrganismInfo interface
	 */

	@Override
	public Vector2D getPosition() {
		return this.pos;
	}

	@Override
	public Vector2D getVelocity() {
		return this.velocity;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getGeneticCode() {
		return this.species.getGeneticCode();
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public double getSightRadius() {
		return this.sightRadius;
	}

	@Override
	public double getSeparationRadius() {
		return this.separationRadius;
	}

	/*
	 * Entity interface
	 */

	@Override
	public void update(double dt) {

	}

}
