package simulator.model;

public class Nutrient implements Entity {
	
	public static final double ENERGY_VALUE = 30.0;
	
	private Vector2D pos;
	private boolean consumed;
	
	public Nutrient(Vector2D pos) {
		this.pos = pos;
		consumed = false;
	}

	public Vector2D getPosition() {
		return pos;
	}
	
	boolean isConsumed() {
		return consumed;
	}
	
	void consume() {
		consumed = true;
	}

	/*
	 * Entity interface 
	 */
	
	@Override
	public void update(double dt) {
		
	}

}
