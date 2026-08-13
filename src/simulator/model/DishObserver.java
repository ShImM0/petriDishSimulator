package simulator.model;

public interface DishObserver {
	void onRegister(double time, DishInfo dish);

	void onReset(double time, DishInfo dish);

	void onAdvance(double time, DishInfo dish, double dt);

	void onOrganismAdded(double time, DishInfo dish, OrganismInfo org);

	void onWeightsChanged(double time, DishInfo dish);

	void onSettingsChanged(double time, DishInfo dish);
}
