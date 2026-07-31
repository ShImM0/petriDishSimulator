package simulator.model;

public class Vector2D {
	private double x;
	private double y;

	public Vector2D(Vector2D v) {
		this.x = v.x;
		this.y = v.y;
	}

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

}
