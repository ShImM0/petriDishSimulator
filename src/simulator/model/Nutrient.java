package simulator.model;

public class Nutrient implements Entity {
	
	private Vector2D pos;
	private boolean consumed;
	
	public Nutrient(Vector2D pos) {
		this.pos = pos;
		consumed = false;
	}

	public Vector2D getPosition() {
		return pos;
	}
	
	@Override
	public void update() {

	}
	
	boolean isConsumed() {
		return consumed;
	}
	
	void consume() {
		consumed = true;
	}

}
