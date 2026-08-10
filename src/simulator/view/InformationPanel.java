package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.DishInfo;
import simulator.model.DishObserver;
import simulator.model.OrganismInfo;

public class InformationPanel extends JPanel implements DishObserver {

	private JLabel timeValue;
	private JLabel organismCountValue;
	private JLabel averageSize;
	private JLabel averageSightRadius; // TODO

	public InformationPanel(Controller ctrl) {
		this.setBorder(new RoundedBorder(Theme.RADIUS_PANEL));
		initGUI();
		ctrl.addObserver((DishObserver) this);
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBackground(Theme.SIDEBAR_BG);
		//this.setBorder(BorderFactory.createTitledBorder("Simulation info"));
		
		JPanel grid = new JPanel(new GridLayout(4, 2, 0,0));
		
		timeValue = new JLabel("0.00");
		organismCountValue = new JLabel("0");
		averageSize = new JLabel ("0.00");
		averageSightRadius = new JLabel ("0.00");
		
		// TODO Add style to labels
		
		grid.add(new JLabel("Time: "));
		grid.add(this.timeValue);
		grid.add(new JLabel("Population size: "));
		grid.add(this.organismCountValue);
		grid.add(new JLabel("Average size: "));
		grid.add(this.averageSize);
		grid.add(new JLabel("Average Sight Radius: "));
		grid.add(this.averageSightRadius);
		
		this.add(grid);
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

	@Override
	public void onSettingsChanged(double time, DishInfo dish) {

	}

}
