package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
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

	private Controller ctrl;

	private JSlider separationSlider;
	private JSlider alignmentSlider;
	private JSlider cohesionSlider;

	private JLabel separationValue;
	private JLabel alignmentValue;
	private JLabel cohesionValue;
	
	private JCheckBox discriminationCheck;
	private JCheckBox wrapCheck;

	public ControlPanel(Controller ctrl) {
		this.ctrl = ctrl;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);

		RuleWeights initial = ctrl.getRuleWeights();

		JPanel rulesPanel = new JPanel(new GridLayout(6, 1, 0, 0));
		rulesPanel.setBorder(BorderFactory.createTitledBorder("Rule weights"));
		rulesPanel.setOpaque(false);
		rulesPanel.setBackground(Color.GRAY);

		separationValue = new JLabel();
		alignmentValue = new JLabel();
		cohesionValue = new JLabel();
		
		separationSlider = buildSlider(initial.getSeparation(), separationValue, ctrl::setSeparationWeight);
		alignmentSlider = buildSlider(initial.getAlignment(), alignmentValue, ctrl::setAlignmentWeight);
		cohesionSlider = buildSlider(initial.getCohesion(), cohesionValue, ctrl::setCohesionWeight);

		rulesPanel.add(new JLabel("Separation"));
		rulesPanel.add(labeledSlider(separationSlider, separationValue));
		rulesPanel.add(new JLabel("Alignment"));
		rulesPanel.add(labeledSlider(alignmentSlider, alignmentValue));
		rulesPanel.add(new JLabel("Cohesion"));
		rulesPanel.add(labeledSlider(cohesionSlider, cohesionValue));

		this.add(rulesPanel, BorderLayout.NORTH);
		
		JPanel togglesPanel = new JPanel(new GridLayout(2,1,0,0));
		togglesPanel.setBorder(BorderFactory.createTitledBorder("Rule weights"));
		togglesPanel.setBackground(Color.GRAY);
		
		discriminationCheck = buildCheckBox("Discrimination", ctrl.isDiscriminationEnabled(),
				ctrl::setDiscriminationEnabled);
		wrapCheck = buildCheckBox("Wrap", ctrl.isWrapEnabled(), ctrl::setWrapEnabled);

		togglesPanel.add(discriminationCheck);
		togglesPanel.add(wrapCheck);
		this.add(togglesPanel, BorderLayout.SOUTH);
	}
	
	private JPanel labeledSlider(JSlider slider, JLabel valueLabel) {
		JPanel row = new JPanel(new BorderLayout());
		row.add(slider, BorderLayout.WEST);
		row.add(valueLabel, BorderLayout.EAST);
		return row;
	}
	
	private JCheckBox buildCheckBox(String label, boolean initial, Consumer<Boolean> onChange) {
		JCheckBox box = new JCheckBox(label, initial);
		box.setAlignmentX(LEFT_ALIGNMENT);
		box.addActionListener(e -> onChange.accept(box.isSelected()));
		return box;
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
