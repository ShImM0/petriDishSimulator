package simulator.model;

import java.awt.Color;

import simulator.misc.Utils;

public enum Species {
	ALPHA("ALPHA-7F2", new Color(155, 89, 182)), // purple
	BETA("BETA-C91", new Color(46, 139, 87)), // sea green
	GAMMA("GAMMA-3D4", new Color(230, 126, 34)), // orange
	DELTA("DELTA-A08", new Color(52, 152, 219)), // blue
	EPSILON("EPSILON-5E6", new Color(192, 57, 43)); // red

	private final String geneticCode;
	private final Color color;

	Species(String geneticCode, Color color) {
		this.geneticCode = geneticCode;
		this.color = color;
	}

	public String getGeneticCode() {
		return geneticCode;
	}

	public Color getColor() {
		return color;
	}

	public static Species random() {
		Species[] values = values();
		return values[Utils.Rand.nextInt(values.length)];
	}
}
