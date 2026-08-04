package simulator.model;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.Random;

public class Organism implements OrganismInfo, Entity {

	public enum State {
		HEALTHY, HUNGRY, RECOVERING
	}

	public static final String INVALID_GENETIC_CODE = "Invalid or empty genetic code";
	public static final String INVALID_POSITION = "Invalid position";
	public static final String INVALID_SPEED = "Invalid speed";
	public static final String INVALID_SIZE = "Invalid size";
	public static final String INVALID_SIGHT = "Invalid sight";
	public static final String INVALID_STRENGTH = "Invalid strength";

	private static final int ID_LENGTH = 6;

	private static final String ALPHANUMERIC_CHARACTERS_ID = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final Random Rand = new Random();
	private static final SecureRandom SecRand = new SecureRandom();

	private final String id;
	private final String geneticCode;
	private State state;
	private Vector2D pos;
	private Vector2D velocity;
	private final Color color;
	private long age;
	private double speed;
	private double size;
	private double sight;
	private double strength;
	private double energy;
	private boolean alive;

	public Organism(String geneticCode, Vector2D pos, Color color, double speed, double size, double sight,
			double strength) {

		if (geneticCode == null || geneticCode.isBlank())
			throw new IllegalArgumentException(INVALID_GENETIC_CODE);
		if (pos == null)
			throw new IllegalArgumentException(INVALID_POSITION);
		if (speed <= 0)
			throw new IllegalArgumentException(INVALID_SPEED);
		if (size <= 0)
			throw new IllegalArgumentException(INVALID_SIZE);
		if (sight <= 0)
			throw new IllegalArgumentException(INVALID_SIGHT);
		if (strength <= 0)
			throw new IllegalArgumentException(INVALID_STRENGTH);

		this.id = randomId(ID_LENGTH);
		this.geneticCode = geneticCode;
		this.state = State.HEALTHY;
		this.pos = pos;
		double angle = Rand.nextDouble() * Math.PI * 2; // Math.PI * 2 is 360º
		this.velocity = new Vector2D(Math.cos(angle), Math.sin(angle));
		this.color = color;
		this.age = 0;
		this.speed = speed;
		this.size = size;
		this.sight = sight;
		this.strength = strength;
		this.energy = 50.0 + Rand.nextDouble() * 50;
		this.alive = true;
	}

	private static String randomId(int idLength) {
		StringBuilder idString = new StringBuilder();
		for (int i = 0; i < idLength; i++) {
			int index = SecRand.nextInt(ALPHANUMERIC_CHARACTERS_ID.length());
			idString.append(ALPHANUMERIC_CHARACTERS_ID.charAt(index));
		}
		return idString.toString();
	}

	/*
	 * OrganismInfo interface
	 */

	@Override
	public State getState() {
		return this.state;
	}

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
		return this.geneticCode;
	};

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public long getAge() {
		return this.age;
	}

	@Override
	public double getSpeed() {
		return this.speed;
	}

	@Override
	public double getSize() {
		return this.size;
	}

	@Override
	public double getSight() {
		return this.sight;
	}

	@Override
	public double getStrength() {
		return this.strength;
	}

	@Override
	public double getEnergy() {
		return this.energy;
	}

	@Override
	public boolean isAlive() {
		return this.alive;
	}

	/*
	 * Entity interface
	 */

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		if (this.energy <= 0)
			alive = false;
	}

}
