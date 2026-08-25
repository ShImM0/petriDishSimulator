package simulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimulatorTest {
	
	private Simulator simulator;
	
	@BeforeEach
	void init() {
		simulator = new Simulator(400, 300);
	}

	@Test
	void defaultConstructorUsesDefaultDimensions() {
		Simulator s = new Simulator();
		assertEquals(Simulator.DEFAULT_WIDTH, s.getDishInfo().getWidth());
		assertEquals(Simulator.DEFAULT_HEIGHT, s.getDishInfo().getHeight());
	}

	@Test
	void explicitDimensionsArePropagatedToTheDish() {
		assertEquals(400, simulator.getDishInfo().getWidth());
		assertEquals(300, simulator.getDishInfo().getHeight());
	}

	@Test
	void rejectsNonPositiveWidth() {
		assertThrows(IllegalArgumentException.class, () -> new Simulator(0, 100));
		assertThrows(IllegalArgumentException.class, () -> new Simulator(-5, 100));
	}

	@Test
	void rejectsNonPositiveHeight() {
		assertThrows(IllegalArgumentException.class, () -> new Simulator(100, 0));
		assertThrows(IllegalArgumentException.class, () -> new Simulator(100, -5));
	}

	@Test
	void startsAtTimeZero() {
		assertEquals(0.0, simulator.getTime());
	}
	
	@Test
	void addNullObserverHasNoEffect() {
		simulator.addObserver(null);
		assertEquals(0, simulator.observers.size());
	}

	
}
