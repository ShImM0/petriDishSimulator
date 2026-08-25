package simulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class SpeciesTest {
	@Test
	void everySpeciesHasAGeneticCodeAndColor() {
		for (Species s : Species.values()) {
			assertNotNull(s.getGeneticCode());
			assertTrue(!s.getGeneticCode().isEmpty());
			assertNotNull(s.getColor());
		}
	}

	@Test
	void geneticCodesAreUniqueAcrossSpecies() {
		Set<String> codes = new HashSet<>();
		for (Species s : Species.values()) {
			codes.add(s.getGeneticCode());
		}
		assertEquals(Species.values().length, codes.size());
	}
	
	@Test
	void colorsAreUniqueAcrossSpecies() {
		Set<Color> colors = new HashSet<>();
		for (Species s : Species.values()) {
			colors.add(s.getColor());
		}
		assertEquals(Species.values().length, colors.size());
	}
}
