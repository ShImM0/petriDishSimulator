package simulator.control;

import java.util.List;

import javax.swing.Timer;

import simulator.model.DishInfo;
import simulator.model.DishObserver;
import simulator.model.OrganismInfo;
import simulator.model.RuleWeights;
import simulator.model.Simulator;

public class Controller {
	final static String CONTROLLER_NULL_SIMULATOR = "Simulator cannot be null in Controller constructor";

	private static final int TICK_MS = 30;

	private Simulator sim;
	private Timer timer;

	private boolean running;

	public Controller(Simulator sim) {
		if (sim == null)
			throw new IllegalArgumentException(CONTROLLER_NULL_SIMULATOR);
		this.sim = sim;
		running = false;
	}

	public void run(double dt) {
		if (timer != null) {
			timer.stop();
		}
		timer = new Timer(TICK_MS, e -> advance(dt));
		timer.start();
		running = true;
	}

	public void pause() {
		if (timer != null) {
			timer.stop();
		}
		running = false;
	}

	public boolean isRunning() {
		return running;
	}

	public void reset() {
		pause();
		this.sim.reset();
	}

	public void advance(double dt) {
		this.sim.advance(dt);
	}

	public void addOrganism() {
		this.sim.addRandomOrganism();
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

	public List<OrganismInfo> getOrganisms() {
		return sim.getOrganisms();
	}

	public double getTime() {
		return sim.getTime();
	}

	/*
	 * Three movement rules: separation, alignment and cohesion
	 */

	public RuleWeights getRuleWeights() {
		return sim.getRuleWeights();
	}

	public void setSeparationWeight(double value) {
		sim.setRuleWeights(sim.getRuleWeights().withSeparation(value));
	}

	public void setAlignmentWeight(double value) {
		sim.setRuleWeights(sim.getRuleWeights().withAlignment(value));
	}

	public void setCohesionWeight(double value) {
		sim.setRuleWeights(sim.getRuleWeights().withCohesion(value));
	}

	/*
	 * Discrimination (flock only with matching genetic code) and Wrap (wrap around
	 * edges vs. bounce off them) toggles.
	 */

	public boolean isDiscriminationEnabled() {
		return sim.isDiscriminationEnabled();
	}

	public void setDiscriminationEnabled(boolean enabled) {
		sim.setDiscriminationEnabled(enabled);
	}

	public boolean isWrapEnabled() {
		return sim.isWrapEnabled();
	}

	public void setWrapEnabled(boolean enabled) {
		sim.setWrapEnabled(enabled);
	}
}
