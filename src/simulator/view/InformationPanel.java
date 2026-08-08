package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.DishInfo;
import simulator.model.DishObserver;
import simulator.model.OrganismInfo;

public class InformationPanel extends JPanel implements DishObserver {

	private Controller ctrl;
	// TODO as StatusBar

	private JLabel timeValue;
	private JLabel organismCountValue;
	private JLabel averageSize;
	private JLabel averageSightRadius; // TODO

	public InformationPanel(Controller ctrl) {
		this.ctrl = ctrl;
		initGUI();
		ctrl.addObserver((DishObserver) this);
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);
		this.setBorder(BorderFactory.createTitledBorder("Simulation info"));
	}

	private void update(double time, DishInfo dish) {
		timeValue.setText(String.format("%.2f", time));
		organismCountValue.setText(String.valueOf(dish.getOrganisms().size()));
		double avgSize = dish.getOrganisms().stream().mapToInt(o -> o.getSize()).average().orElse(0.0);
		averageSize.setText(String.format("%.2f", avgSize));
		double avgSightRadius = dish.getOrganisms().stream().mapToDouble(o -> o.getSightRadius()).average().orElse(0.0);
		averageSightRadius.setText(String.format("%.2f", avgSightRadius));
	}

	@Override
	public void onRegister(double time, DishInfo dish) {
		SwingUtilities.invokeLater(() -> {
			this.update(time, dish);
		});
	}

	@Override
	public void onReset(double time, DishInfo dish) {
		SwingUtilities.invokeLater(() -> {
			this.update(time, dish);
		});
	}

	@Override
	public void onAdvance(double time, DishInfo dish, double dt) {
		SwingUtilities.invokeLater(() -> {
			this.update(time, dish);
		});
	}

	@Override
	public void onWeightsChanged(double time, DishInfo flock) {
	}

}
