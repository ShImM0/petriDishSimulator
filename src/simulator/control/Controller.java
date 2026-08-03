package simulator.control;

import simulator.model.DishObserver;
import simulator.model.Simulator;

public class Controller {
	final static String CONTROLLER_NULL_SIMULATOR = "Simulator cannot be null in Controller constructor";
	private Simulator sim;

	public Controller(Simulator sim) {
		if (sim == null)
			throw new IllegalArgumentException(CONTROLLER_NULL_SIMULATOR);
		this.sim = sim;
	}

	public void run() {
		// TODO
	}

	public void reset() {
		this.sim.reset();
	}

	public void advance() {
		this.sim.advance();
	}

	public void addObserver(DishObserver obs) {
		this.sim.addObserver(obs);
	}

	public void removeObserver(DishObserver obs) {
		this.sim.removeObserver(obs);
	}
}
