package simulator.model;

import java.util.List;

public interface DishObserver {
	void onRegister(double time, DishInfo dish);

	void onReset(double time, DishInfo dish);

	void onOrganismAdded(double time, DishInfo dish, OrganismInfo o);

	void onAdvance(double time, DishInfo dish, double dt);
}
