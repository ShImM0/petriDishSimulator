package simulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RuleWeightsTest {

	@Test
	void acceptsLowerBoundForAllWeights() {
		RuleWeights w = new RuleWeights(RuleWeights.MIN_WEIGHT, RuleWeights.MIN_WEIGHT, RuleWeights.MIN_WEIGHT);
		assertEquals(RuleWeights.MIN_WEIGHT, w.getSeparation());
		assertEquals(RuleWeights.MIN_WEIGHT, w.getAlignment());
		assertEquals(RuleWeights.MIN_WEIGHT, w.getCohesion());
	}

	@Test
	void acceptsUpperBoundForAllWeights() {
		RuleWeights w = new RuleWeights(RuleWeights.MAX_WEIGHT, RuleWeights.MAX_WEIGHT, RuleWeights.MAX_WEIGHT);
		assertEquals(RuleWeights.MAX_WEIGHT, w.getSeparation());
		assertEquals(RuleWeights.MAX_WEIGHT, w.getAlignment());
		assertEquals(RuleWeights.MAX_WEIGHT, w.getCohesion());
	}

	@Test
	void storesAllThreeWeights() {
		RuleWeights w = new RuleWeights(1.0, 2.0, 3.0);
		assertEquals(1.0, w.getSeparation());
		assertEquals(2.0, w.getAlignment());
		assertEquals(3.0, w.getCohesion());
	}

	@ParameterizedTest
	@ValueSource(doubles = { -0.01, -5.0, 5.01, 100.0, Double.NaN })
	void rejectsOutOfRangeSeparation(double bad) {
		assertThrows(IllegalArgumentException.class, () -> new RuleWeights(bad, 1.0, 1.0));
	}

	@ParameterizedTest
	@ValueSource(doubles = { -0.01, -5.0, 5.01, 100.0, Double.NaN })
	void rejectsOutOfRangeAlignment(double bad) {
		assertThrows(IllegalArgumentException.class, () -> new RuleWeights(1.0, bad, 1.0));
	}

	@ParameterizedTest
	@ValueSource(doubles = { -0.01, -5.0, 5.01, 100.0, Double.NaN })
	void rejectsOutOfRangeCohesion(double bad) {
		assertThrows(IllegalArgumentException.class, () -> new RuleWeights(1.0, 1.0, bad));
	}

	@Test
	void withSeparationOnlyChangesSeparation() {
		RuleWeights original = new RuleWeights(1.0, 2.0, 3.0);
		RuleWeights updated = original.withSeparation(4.0);

		assertEquals(4.0, updated.getSeparation());
		assertEquals(2.0, updated.getAlignment());
		assertEquals(3.0, updated.getCohesion());

		assertEquals(1.0, original.getSeparation());
	}

	@Test
	void withAlignmentOnlyChangesAlignment() {
		RuleWeights original = new RuleWeights(1.0, 2.0, 3.0);
		RuleWeights updated = original.withAlignment(4.0);

		assertEquals(1.0, updated.getSeparation());
		assertEquals(4.0, updated.getAlignment());
		assertEquals(3.0, updated.getCohesion());

		assertEquals(2.0, original.getAlignment());
	}

	@Test
	void withCohesionOnlyChangesCohesion() {
		RuleWeights original = new RuleWeights(1.0, 2.0, 3.0);
		RuleWeights updated = original.withCohesion(4.0);

		assertEquals(1.0, updated.getSeparation());
		assertEquals(2.0, updated.getAlignment());
		assertEquals(4.0, updated.getCohesion());

		assertEquals(3.0, original.getCohesion());
	}

	@Test
	void withHelpersStillEnforceBounds() {
		RuleWeights original = new RuleWeights(1.0, 1.0, 1.0);
		assertThrows(IllegalArgumentException.class, () -> original.withSeparation(-1.0));
		assertThrows(IllegalArgumentException.class, () -> original.withAlignment(10.0));
		assertThrows(IllegalArgumentException.class, () -> original.withCohesion(Double.NaN));
	}
}
