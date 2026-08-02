package simulator.model;

import java.util.List;

public interface DishObserver {
	void onRegister(double time, List<OrganismInfo> organisms);

	void onReset(double time, List<OrganismInfo> organisms);
	
	void onOrganismAdded(double time, List<OrganismInfo> organisms, OrganismInfo o);

	void onAdvance(double time, List<OrganismInfo> organisms, double dt);
}
