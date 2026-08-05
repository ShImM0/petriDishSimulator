package simulator.model;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.Random;

import simulator.misc.Utils;

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

	private static final String ALPHANUMERIC_CHARACTERS_ID = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final SecureRandom SecRand = new SecureRandom();
	
	private static final int ID_LENGTH = 6;
	
	public static final double MIN_SPEED = 0.5;
	public static final double MAX_SPEED = 5.0;
	public static final int MIN_SIZE = 2;
	public static final int MAX_SIZE = 20;
	public static final double MIN_SIGHT = 30;
	public static final double MAX_SIGHT = 150;
	public static final double MIN_STRENGTH = 0.5;
	public static final double MAX_STRENGTH = 10.0;
	public static final double MAX_ENERGY = 100.0;

	private final String id;
	private final String geneticCode;
	private State state;
	private Vector2D pos;
	private Vector2D velocity;
	private final Color color;
	private long age;
	private int size;
	private double speed;
	private double sight;
	private double strength;
	private double energy;
	private boolean alive;

	public Organism(String geneticCode, Vector2D pos, Color color, int size, double speed, double sight,
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
		double angle = Utils.Rand.nextDouble() * Math.PI * 2; // Math.PI * 2 is 360º
		this.velocity = new Vector2D(Math.cos(angle), Math.sin(angle));
		this.color = color;
		this.age = 0;
		this.speed = speed;
		this.size = size;
		this.sight = sight;
		this.strength = strength;
		this.energy = 50.0 + Utils.Rand.nextDouble() * 50;
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

	static String randomGeneticCode() {
		return randomId(7); // TODO
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
	public int getSize() {
		return this.size;
	}

	@Override
	public double getSpeed() {
		return this.speed;
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
