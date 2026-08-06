package simulator.view;

import javax.swing.JComponent;

public abstract class AbstractDishViewer extends JComponent {
	public abstract void update();

	public abstract void reset();
}
