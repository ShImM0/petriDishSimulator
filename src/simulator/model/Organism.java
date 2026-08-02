package simulator.model;

import java.awt.Color;

public class Organism implements OrganismInfo {

	public enum State {
		HEALTHY, HUNGRY, RECOVERING
	}

	private final String id;
	private State state;
	private Vector2D pos;
	private final Color color;
	private long age;
	private double speed;
	private double size;
	private double sight;
	private double strength;
	private double energy;
	private boolean alive;

	@Override
	public State getState() {
		return this.state;
	}

	@Override
	public Vector2D getPosition() {
		return this.pos;
	}

	@Override
	public String getId() {
		return this.id;
	}

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
	};

}
