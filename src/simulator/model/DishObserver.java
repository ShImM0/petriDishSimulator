package simulator.model;

public interface DishObserver {
	void onRegister();

	void onReset();

	void onAdvance();
}
