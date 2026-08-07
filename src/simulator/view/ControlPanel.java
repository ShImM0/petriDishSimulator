package simulator.view;

import java.awt.Color;
import java.awt.GridLayout;

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
	
	private JSlider separation;
	private JSlider alignment;
	private JSlider cohesion;
	
	private JLabel separationValue;
	private JLabel alignmentValue;
	private JLabel cohesionValue;
	
	public ControlPanel (Controller ctrl) {
		this.ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder("Rule weights"));
		
		RuleWeights initial = ctrl.getRuleWeights();

		JPanel rulesPanel = new JPanel();
		rulesPanel.setOpaque(false);

		separationValue = new JLabel();
		alignmentValue = new JLabel();
		cohesionValue = new JLabel();
		
		separation = new JSlider(SLIDER_MIN, SLIDER_MAX, (int) Math.round(initial.getSeparation() * SLIDER_SCALE));
		separationValue.setText(format(initial.getSeparation()));
		ChangeListener listener = e -> {
			double value = separation.getValue() / (double) SLIDER_SCALE;
			separationValue.setText(format(value));
			ctrl.setSeparationWeight(value);
			System.out.println(value);
		};
		separation.addChangeListener(listener);
		
		rulesPanel.add(separation);
		this.add(rulesPanel);
		
		
	}
	
	private static String format(double value) {
		return String.format("%.2f", value);
	}
}
