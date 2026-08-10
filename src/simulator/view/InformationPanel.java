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

	private JLabel time;
	private JLabel organismCountValue;
	private JLabel distinctSpecies;
	private JLabel averageSize;
	private JLabel averageSpeed;
	private JLabel averageSightRadius;

	public InformationPanel(Controller ctrl) {
		this.setBorder(new RoundedBorder(Theme.RADIUS_PANEL));
		initGUI();
		ctrl.addObserver((DishObserver) this);
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBackground(Theme.SIDEBAR_BG);

		JPanel grid = new JPanel(new GridLayout(6, 2, 8, 4));
		grid.setOpaque(false);

		time = new JLabel("0,00");
		organismCountValue = new JLabel("0");
		distinctSpecies = new JLabel("0");
		averageSize = new JLabel("0.00");
		averageSpeed = new JLabel("0.00");
		averageSightRadius = new JLabel("0.00");

		styleValueLabel(time);
		styleValueLabel(organismCountValue);
		styleValueLabel(distinctSpecies);
		styleValueLabel(averageSize);
		styleValueLabel(averageSpeed);
		styleValueLabel(averageSightRadius);

		grid.add(nameLabel("Time: "));
		grid.add(this.time);
		grid.add(nameLabel("Population size: "));
		grid.add(this.organismCountValue);
		grid.add(nameLabel("Species"));
		grid.add(this.distinctSpecies);
		grid.add(nameLabel("Average size: "));
		grid.add(this.averageSize);
		grid.add(nameLabel("Average speed: "));
		grid.add(this.averageSpeed);
		grid.add(nameLabel("Average sight: "));
		grid.add(this.averageSightRadius);

		this.add(grid);
	}

	private JLabel nameLabel(String labelName) {
		JLabel label = new JLabel(labelName);
		label.setForeground(Theme.SIDEBAR_TEXT);
		label.setFont(Theme.FONT_RULE_TITLE);
		return label;
	}

	private void styleValueLabel(JLabel label) {
		label.setForeground(Theme.SIDEBAR_SUBTEXT);
		label.setFont(Theme.FONT_SIDE_LABEL);
	}

	private void update(double time, DishInfo dish) {
		this.time.setText(format(time));
		organismCountValue.setText(String.valueOf(dish.getOrganisms().size()));

		distinctSpecies
				.setText(String.valueOf(dish.getOrganisms().stream().map(o -> o.getGeneticCode()).distinct().count()));

		double avgSize = dish.getOrganisms().stream().mapToInt(o -> o.getSize()).average().orElse(0.0);
		averageSize.setText(format(avgSize));

		double avgSpeed = dish.getOrganisms().stream().mapToDouble(o -> o.getVelocity().magnitude()).average()
				.orElse(0.0);
		averageSpeed.setText(format(avgSpeed));

		double avgSightRadius = dish.getOrganisms().stream().mapToDouble(o -> o.getSightRadius()).average().orElse(0.0);
		averageSightRadius.setText(format(avgSightRadius));

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
	public void onOrganismAdded(double time, DishInfo dish, OrganismInfo org) {
		SwingUtilities.invokeLater(() -> {
			this.update(time, dish); // updates even if the simulation is not running
		});
	}

	@Override
	public void onWeightsChanged(double time, DishInfo flock) {

	}

	@Override
	public void onSettingsChanged(double time, DishInfo dish) {

	}

	private String format(double num) {
		return String.format("%.2f", num);
	}

}
