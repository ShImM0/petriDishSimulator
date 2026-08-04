package simulator.control;

import java.util.Timer;

import simulator.model.DishInfo;
import simulator.model.DishObserver;
import simulator.model.OrganismInfo;
import simulator.model.Simulator;

public class Controller {
	final static String CONTROLLER_NULL_SIMULATOR = "Simulator cannot be null in Controller constructor";
	
	private Simulator sim;
	private Timer timer;
	
	private boolean running;

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

	public void advance(double dt) {
		this.sim.advance(dt);
	}

	public void addObserver(DishObserver obs) {
		this.sim.addObserver(obs);
	}

	public void removeObserver(DishObserver obs) {
		this.sim.removeObserver(obs);
	}
	
	public DishInfo getDishInfo() {
		return sim.getDishInfo();
	}
}
