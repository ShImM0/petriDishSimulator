package simulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class DishTest {

	@Test
	void rejectsNonPositiveWidth() {
		assertThrows(IllegalArgumentException.class, () -> new Dish(0, 100));
		assertThrows(IllegalArgumentException.class, () -> new Dish(-10, 100));
	}

	@Test
	void rejectsNonPositiveHeight() {
		assertThrows(IllegalArgumentException.class, () -> new Dish(100, 0));
		assertThrows(IllegalArgumentException.class, () -> new Dish(100, -10));
	}

	@Test
	void acceptsValidArguments() {
		Dish d = new Dish(500, 800);
		assertEquals(500, d.getWidth());
		assertEquals(800, d.getHeight());
	}

	@Test
	void startsWithOrganisms() {
		Dish dish = new Dish(500, 500);
		assertNotEquals(0, dish.getOrganisms().size());
	}

	@Test
	void startsWithDiscriminationAndWrapEnabled() {
		Dish dish = new Dish(500, 500);
		assertTrue(dish.getDiscriminationEnabled());
		assertTrue(dish.getWrapEnabled());
	}

	@Test
	void initialOrganismIsWithinBounds() {
		Dish dish = new Dish(200, 150);
		for (OrganismInfo o : dish.getOrganisms()) {
			assertTrue(o.getPosition().getX() >= 0 && o.getPosition().getX() <= 200);
			assertTrue(o.getPosition().getY() >= 0 && o.getPosition().getY() <= 150);
		}
	}

	@Test
	void registerOrganismAddsItToTheDish() {
		Dish dish = new Dish(500, 500);
		int before = dish.getOrganisms().size();

		Organism o = new Organism(Species.DELTA, new Vector2D(10, 10), Vector2D.zero(), 5, 20, 20, 20, 5);
		dish.registerOrganism(o);

		List<OrganismInfo> after = dish.getOrganisms();
		assertEquals(before + 1, after.size());
		assertTrue(after.stream().anyMatch(info -> info.getId().equals(o.getId())));
	}

	@Test
	void addRandomOrganismIncreasesPopulationAndIsRetrievable() {
		Dish dish = new Dish(500, 500);
		int before = dish.getOrganisms().size();

		Organism added = dish.addRandomOrganism();

		List<OrganismInfo> after = dish.getOrganisms();
		assertEquals(before + 1, after.size());
		assertTrue(after.stream().anyMatch(info -> info.getId().equals(added.getId())));
	}

	@Test
	void addRandomOrganismStaysWithinDishBounds() {
		Dish dish = new Dish(200, 150);
		Organism added = dish.addRandomOrganism();

		assertTrue(added.getPosition().getX() >= 0 && added.getPosition().getX() <= 200);
		assertTrue(added.getPosition().getY() >= 0 && added.getPosition().getY() <= 150);
	}

	@Test
	void getOrganismsIsUnmodifiable() {
		Dish dish = new Dish(500, 500);
		List<OrganismInfo> organisms = dish.getOrganisms();

		assertThrows(UnsupportedOperationException.class,
				() -> organisms.add(new Organism(Species.ALPHA, Vector2D.zero(), Vector2D.zero(), 5, 5, 5, 5, 5)));
	}
	
	@Test
	void setRuleWeightsRejectsNull() {
		Dish dish = new Dish(500, 500);
		assertThrows(IllegalArgumentException.class, () -> dish.setRuleWeights(null));
	}

	@Test
	void setRuleWeightsUpdatesTheStoredWeights() {
		Dish dish = new Dish(500, 500);
		RuleWeights newWeights = new RuleWeights(2.0, 1.0, 0.0);
		dish.setRuleWeights(newWeights);

		assertEquals(2.0, dish.getRuleWeights().getSeparation());
		assertEquals(1.0, dish.getRuleWeights().getAlignment());
		assertEquals(0.0, dish.getRuleWeights().getCohesion());
	}
	
	@Test
	void discriminationCanBeToggled() {
		Dish dish = new Dish(500, 500);
		dish.setDiscriminationEnabled(false);
		assertFalse(dish.getDiscriminationEnabled());
		dish.setDiscriminationEnabled(true);
		assertTrue(dish.getDiscriminationEnabled());
	}

	@Test
	void wrapCanBeToggled() {
		Dish dish = new Dish(500, 500);
		dish.setWrapEnabled(false);
		assertFalse(dish.getWrapEnabled());
		dish.setWrapEnabled(true);
		assertTrue(dish.getWrapEnabled());
	}
	
}
