package simulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
		Dish d = new Dish(500,800);
		assertEquals(500, d.getWidth());
		assertEquals(800, d.getHeight());
		
	}
	

}
