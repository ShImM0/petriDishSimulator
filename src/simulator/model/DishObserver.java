package simulator.model;

import java.util.List;

public interface DishObserver {
	void onRegister(double time, DishInfo dish);

	void onReset(double time, DishInfo dish);

	void onAdvance(double time, DishInfo dish, double dt);
	
	void onWeightsChanged(double time, DishInfo flock);
}
