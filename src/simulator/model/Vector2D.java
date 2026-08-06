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

	public Vector2D direction() {
		if (magnitude() > 0.0) {
			return scale(1.0 / magnitude());
		} else
			return new Vector2D(this);
	}

	// Limits a direction position, so that its magnitude does not exceed max
	// To cap steering forces and speeds
	
	public Vector2D limit(double max) {
		double mag = magnitude();
		if (mag > max && mag > 0.0) {
			return scale(max / mag);
		}
		return new Vector2D(this);
	}
	
	// Angle of this vector around the origin (0,0)
	// Uses the format atan2(double y, double x)
	
	public double angle() {
		return Math.atan2(y, x);
	}
	
	@Override
	public String toString() {
		return String.format("[%.2f, %.2f]", this.x, this.y);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2D other = (Vector2D) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

}
