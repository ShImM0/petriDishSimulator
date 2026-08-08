package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.function.DoubleConsumer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import simulator.control.Controller;
import simulator.model.RuleWeights;

public class ControlPanel extends JPanel {

	private static final int SLIDER_SCALE = 100;
	private static final int SLIDER_MIN = (int) (RuleWeights.MIN_WEIGHT * SLIDER_SCALE);
	private static final int SLIDER_MAX = (int) (RuleWeights.MAX_WEIGHT * SLIDER_SCALE);
	// TODO to each their own max

	private Controller ctrl;

	private JSlider separationSlider;
	private JSlider alignmentSlider;
	private JSlider cohesionSlider;

	private JLabel separationValue;
	private JLabel alignmentValue;
	private JLabel cohesionValue;

	public ControlPanel(Controller ctrl) {
		this.ctrl = ctrl;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);
		this.setBorder(BorderFactory.createTitledBorder("Rule weights"));

		RuleWeights initial = ctrl.getRuleWeights();

		JPanel rulesPanel = new JPanel(new GridLayout(6, 1, 0, 0));
		rulesPanel.setOpaque(false);

		separationValue = new JLabel();
		alignmentValue = new JLabel();
		cohesionValue = new JLabel();
		
		separationSlider = buildSlider(initial.getSeparation(), separationValue, ctrl::setSeparationWeight);
		alignmentSlider = buildSlider(initial.getAlignment(), alignmentValue, ctrl::setAlignmentWeight);
		cohesionSlider = buildSlider(initial.getCohesion(), cohesionValue, ctrl::setCohesionWeight);

		rulesPanel.add(new JLabel("Separation"));
		rulesPanel.add(buildVisualSlider(separationSlider, separationValue));
		rulesPanel.add(new JLabel("Alignment"));
		rulesPanel.add(buildVisualSlider(alignmentSlider, alignmentValue));
		rulesPanel.add(new JLabel("Cohesion"));
		rulesPanel.add(buildVisualSlider(cohesionSlider, cohesionValue));

		this.add(rulesPanel);
	}
	
	private JPanel buildVisualSlider(JSlider slider, JLabel valueLabel) {
		JPanel row = new JPanel(new BorderLayout());
		row.add(slider, BorderLayout.WEST);
		row.add(valueLabel, BorderLayout.EAST);
		return row;
	}


	// DoubleConsumer is a functional interface with the method accept
	// Used for operations that consume primitive double values without returning
	// anything
	// Like in our case, calling Controller's setters
	private JSlider buildSlider(double initialValue, JLabel valueLabel, DoubleConsumer onChange) {
		JSlider slider = new JSlider(SLIDER_MIN, SLIDER_MAX, (int) Math.round(initialValue * SLIDER_SCALE));
		valueLabel.setText(format(initialValue));
		ChangeListener listener = e -> {
			double value = slider.getValue() / (double) SLIDER_SCALE;
			valueLabel.setText(format(value));
			onChange.accept(value);
		};
		slider.addChangeListener(listener);
		return slider;
	}

	private static String format(double value) {
		return String.format("%.2f", value);
	}
}
