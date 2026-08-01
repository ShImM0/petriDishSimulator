package simulator.model;

public class Vector2D {
	private final double x;
	private final double y;

	public Vector2D() {
		x = y = 0.0;
	}

	public Vector2D(Vector2D v) {
		this.x = v.x;
		this.y = v.y;
	}

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Vector2D plus(Vector2D other) {
		return new Vector2D(this.x + other.x, this.y + other.y);
	}

	public Vector2D subtract(Vector2D other) {
		return new Vector2D(this.x - other.x, this.y - other.y);
	}

	public Vector2D scale(double factor) {
		return new Vector2D(this.x * factor, this.y * factor);
	}

	public double magnitude() {
		return Math.sqrt(dot(this));
	}

	public double dot(Vector2D that) {
		return x * that.x + y * that.y;
	}

	public double distance(Vector2D other) {
		return subtract(other).magnitude();
	}

	@Override
	public String toString() {
		return String.format("[%.2f, %.2f]", this.x, this.y);
	}

}
