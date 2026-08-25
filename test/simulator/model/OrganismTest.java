package simulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class OrganismTest {
	
	private static Organism defaultOrganism(Vector2D pos, Vector2D velocity) {
		return new Organism(Species.ALPHA, pos, velocity, 5, 20, 10, 10, 5);
	}

	@Test
	void rejectsNullSpecies() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> new Organism(null, Vector2D.zero(), Vector2D.zero(), 5, 10, 10, 10, 5));
		assertEquals(Organism.INVALID_SPECIES, ex.getMessage());
	}

	@Test
	void rejectsNullPosition() {
		assertThrows(IllegalArgumentException.class,
				() -> new Organism(Species.ALPHA, null, Vector2D.zero(), 5, 10, 10, 10, 5));
	}

	@Test
	void rejectsNullVelocity() {
		assertThrows(IllegalArgumentException.class,
				() -> new Organism(Species.ALPHA, Vector2D.zero(), null, 5, 10, 10, 10, 5));
	}

	@Test
	void rejectsNonPositiveSize() {
		assertThrows(IllegalArgumentException.class,
				() -> new Organism(Species.ALPHA, Vector2D.zero(), Vector2D.zero(), 0, 10, 10, 10, 5));
		assertThrows(IllegalArgumentException.class,
				() -> new Organism(Species.ALPHA, Vector2D.zero(), Vector2D.zero(), -1, 10, 10, 10, 5));
	}

	@Test
	void rejectsNonPositiveMaxSpeed() {
		assertThrows(IllegalArgumentException.class,
				() -> new Organism(Species.ALPHA, Vector2D.zero(), Vector2D.zero(), 5, 0, 10, 10, 5));
	}

	@Test
	void rejectsNonPositiveMaxForce() {
		assertThrows(IllegalArgumentException.class,
				() -> new Organism(Species.ALPHA, Vector2D.zero(), Vector2D.zero(), 5, 10, 0, 10, 5));
	}

	@Test
	void rejectsNonPositiveSightRadius() {
		assertThrows(IllegalArgumentException.class,
				() -> new Organism(Species.ALPHA, Vector2D.zero(), Vector2D.zero(), 5, 10, 10, 0, 5));
	}

	@Test
	void rejectsNonPositiveSeparationRadius() {
		assertThrows(IllegalArgumentException.class,
				() -> new Organism(Species.ALPHA, Vector2D.zero(), Vector2D.zero(), 5, 10, 10, 10, 0));
	}

	@Test
	void acceptsValidArguments() {
		Organism o = new Organism(Species.BETA, new Vector2D(1, 2), new Vector2D(3, 4), 5, 10, 10, 10, 5);
		assertEquals(new Vector2D(1, 2), o.getPosition());
		assertEquals(new Vector2D(3, 4), o.getVelocity());
		assertEquals(5, o.getSize());
		assertEquals(10, o.getSightRadius());
		assertEquals(5, o.getSeparationRadius());
	}
}