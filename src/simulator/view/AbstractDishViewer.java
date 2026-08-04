package simulator.view;

import java.util.List;

import javax.swing.JComponent;

import simulator.model.DishInfo;
import simulator.model.Nutrient;
import simulator.model.OrganismInfo;

public abstract class AbstractDishViewer extends JComponent {
	public abstract void update(List<OrganismInfo> organisms, List<Nutrient> nutrients);

	public abstract void reset(DishInfo dish, List<OrganismInfo> organisms, List<Nutrient> nutrients);
}
