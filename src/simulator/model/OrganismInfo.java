package simulator.model;

import java.awt.Color;

import simulator.model.Organism.State;

public interface OrganismInfo {
	public State getState();
	public Vector2D getPosition();
	public String getId();
	public Color getColor();
	public long getAge();
	public double getSpeed();
	public double getSize();
	public double getSight();
	public double getStrength();
	public double getEnergy();
	public boolean isAlive();
}
