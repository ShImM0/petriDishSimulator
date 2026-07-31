package simulator.model;

import java.awt.Color;

public class Organism implements OrganismInfo {
	
	public enum State{
		HEALTHY, HUNGRY, RECOVERING
	};

	@Override
	public Vector2D getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getAge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

}
