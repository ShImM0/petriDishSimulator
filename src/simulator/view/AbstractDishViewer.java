package simulator.view;

import java.util.List;

import javax.swing.JComponent;

import simulator.model.DishInfo;
import simulator.model.Nutrient;
import simulator.model.OrganismInfo;

public abstract class AbstractDishViewer extends JComponent {
	public abstract void update();

	public abstract void reset();
}
