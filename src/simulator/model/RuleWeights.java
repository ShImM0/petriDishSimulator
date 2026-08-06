package simulator.model;

public final class RuleWeights {

	public static final double MIN_WEIGHT = 0.0;
	public static final double MAX_WEIGHT = 5.0;

	public static final String INVALID_WEIGHT = "Invalid rule weight: %s (must be in [" + MIN_WEIGHT + ", " + MAX_WEIGHT
			+ "])";

	private final double separation;
	private final double alignment;
	private final double cohesion;

	public RuleWeights(double separation, double alignment, double cohesion) {
		this.separation = checkWeight(separation);
		this.alignment = checkWeight(alignment);
		this.cohesion = checkWeight(cohesion);
	}

	private static double checkWeight(double w) {
		if (w < MIN_WEIGHT || w > MAX_WEIGHT || Double.isNaN(w))
			throw new IllegalArgumentException(INVALID_WEIGHT.formatted(w));
		return w;
	}
	
	public double getSeparation() {
		return separation;
	}

	public double getAlignment() {
		return alignment;
	}

	public double getCohesion() {
		return cohesion;
	}

	
}
