package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeListener;

import simulator.control.Controller;
import simulator.model.RuleWeights;

public class ControlPanel extends JPanel{

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
		this.setBorder(new RoundedBorder(Theme.RADIUS_PANEL));
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBackground(Theme.SIDEBAR_BG);

		RuleWeights initial = ctrl.getRuleWeights();
		JPanel rulesDescription = new JPanel();
		rulesDescription.setLayout(new BoxLayout(rulesDescription, BoxLayout.Y_AXIS));
	    rulesDescription.setBackground(Theme.SIDEBAR_BG);
		rulesDescription.add(ruleDescription("Separation","Steering to avoid crowding neighbors"));
		rulesDescription.add(ruleDescription("Alignment","Steering towards the average heading of neighbors"));
		rulesDescription.add(ruleDescription("Cohesion","Steering to move toward the average position of neighbors"));
		
		this.add(rulesDescription, BorderLayout.NORTH);
		JPanel rulesPanel = new JPanel(new GridLayout(3, 1, 0, 12));
		//rulesPanel.setBorder(BorderFactory.createTitledBorder("Rule weights"));
		rulesPanel.setOpaque(false);
		rulesPanel.setBackground(Theme.SIDEBAR_BG);

		separationValue = new JLabel();
		alignmentValue = new JLabel();
		cohesionValue = new JLabel();
		
		separationSlider = buildSlider(initial.getSeparation(), separationValue, ctrl::setSeparationWeight);
		alignmentSlider = buildSlider(initial.getAlignment(), alignmentValue, ctrl::setAlignmentWeight);
		cohesionSlider = buildSlider(initial.getCohesion(), cohesionValue, ctrl::setCohesionWeight);

		rulesPanel.add(labeledSlider("Separation", separationSlider, separationValue));
		rulesPanel.add(labeledSlider("Alignment", alignmentSlider, alignmentValue));
		rulesPanel.add(labeledSlider("Cohesion", cohesionSlider, cohesionValue));

		this.add(rulesPanel, BorderLayout.CENTER);
		
		JPanel togglesPanel = new JPanel(new GridLayout(2,1,0,0));
		//togglesPanel.setBorder(BorderFactory.createTitledBorder("Simulation options"));
		togglesPanel.setBackground(Color.GRAY);
		
		discriminationCheck = buildCheckBox("Discrimination", ctrl.isDiscriminationEnabled(),
				ctrl::setDiscriminationEnabled);
		wrapCheck = buildCheckBox("Wrap", ctrl.isWrapEnabled(), ctrl::setWrapEnabled);

		togglesPanel.add(discriminationCheck);
		togglesPanel.add(wrapCheck);
		
		this.add(togglesPanel, BorderLayout.SOUTH);
	}
	private JPanel ruleDescription(String title, String body) {
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    panel.setOpaque(false);
	    panel.setAlignmentX(LEFT_ALIGNMENT);

	    JLabel titleLabel = new JLabel(title);
	    titleLabel.setForeground(Theme.SIDEBAR_TEXT);
	    titleLabel.setFont(Theme.FONT_RULE_TITLE);
	    titleLabel.setAlignmentX(LEFT_ALIGNMENT);

	    JLabel bodyLabel = new JLabel(
	        "<html><div style='width:180px;'>" + body + "</div></html>"
	    );
	    bodyLabel.setForeground(Theme.SIDEBAR_SUBTEXT);
	    bodyLabel.setFont(Theme.FONT_RULE_BODY);
	    bodyLabel.setAlignmentX(LEFT_ALIGNMENT);

	    panel.add(titleLabel);
	    panel.add(bodyLabel);

	    return panel;
	}
	
	private JPanel labeledSlider(String name, JSlider slider, JLabel valueLabel) {
		JPanel row = new JPanel(new BorderLayout());
		row.setOpaque(false);
		
		JLabel nameLabel = new JLabel(name);
		nameLabel.setForeground(Theme.SIDEBAR_TEXT);;
		nameLabel.setFont(Theme.FONT_RULE_TITLE);
		
		slider.setBackground(Theme.SIDEBAR_BG);
		
		valueLabel.setFont(Theme.FONT_SIDE_LABEL);
		valueLabel.setForeground(Theme.SIDEBAR_TEXT);
		
		row.add(nameLabel, BorderLayout.NORTH);
		row.add(slider, BorderLayout.CENTER);
		row.add(valueLabel, BorderLayout.EAST);
		return row;
	}
	
	private JCheckBox buildCheckBox(String label, boolean initial, Consumer<Boolean> onChange) {
		JCheckBox box = new JCheckBox(label, initial);
		box.setFont(Theme.FONT_SIDE_LABEL);
		box.setForeground(Theme.SIDEBAR_TEXT);
		box.setBackground(Theme.SIDEBAR_BG);
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
