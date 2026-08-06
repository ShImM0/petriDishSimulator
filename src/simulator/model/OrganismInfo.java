package simulator.model;

import java.awt.Color;

public interface OrganismInfo {

	public Vector2D getPosition();

	public Vector2D getVelocity();

	public String getId();

	public String getGeneticCode();

	public Color getColor();
	
	public int getSize();
	
	public double getSightRadius();

}
