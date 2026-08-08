package simulator.model;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.Random;

import simulator.misc.Utils;

public class Organism implements OrganismInfo, Entity {

	public static final String INVALID_GENETIC_CODE = "Invalid genetic code";
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
	private final String id;
	private final String geneticCode; // TODO color according to genetic code, factory
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

	public Organism(String geneticCode, Vector2D pos, Vector2D velocity, Color color, int size, double maxSpeed,
			double maxForce, double sightRadius, double separationRadius) {

		if (geneticCode == null || geneticCode.isBlank())
			throw new IllegalArgumentException(INVALID_GENETIC_CODE);
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
		this.geneticCode = geneticCode;
		this.pos = pos;
		this.velocity = velocity;
		this.color = color;
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

	void moveWithin(double dt, double width, double height) {
		velocity = velocity.plus(acceleration.scale(dt)).limit(maxSpeed);

		double newX = pos.getX() + velocity.getX() * dt * 25.0;
		double newY = pos.getY() + velocity.getY() * dt * 25.0;

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
		pos = new Vector2D(newX, newY);
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
		return this.getGeneticCode();
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

	/*
	 * Entity interface
	 */

	@Override
	public void update(double dt) {

	}


}
