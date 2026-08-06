package simulator.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.DishInfo;
import simulator.model.DishObserver;
import simulator.model.Nutrient;
import simulator.model.OrganismInfo;

public class DishViewer extends AbstractDishViewer implements DishObserver {

	private Controller ctrl;

	private Font textFont = new Font("Arial", Font.BOLD, 12);

	public DishViewer(Controller ctrl) {
		this.ctrl = ctrl;
		initGUI();
		ctrl.addObserver((DishObserver) this);
	}

	private void initGUI() {
		DishInfo dish = ctrl.getDishInfo();
		setVisible(true);
	}

	@Override
	public void update() {
		repaint();
	}

	@Override
	public void reset() {
		repaint();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		// RenderingHints.VALUE_TEXT_ANTIALIAS_ON); TODO

		int w = getWidth();
		int h = getHeight();
		g.setColor(Color.gray);
		g.setFont(textFont);
		gr.setBackground(Color.WHITE);
		gr.fillRect(0, 0, w, h); // fill vs clear

		DishInfo dish = ctrl.getDishInfo();
		if (w > 0 && h > 0 && dish.getWidth() > 0 && dish.getHeight() > 0) {
			double sx = w / (double) dish.getWidth();
			double sy = h / (double) dish.getHeight();
			Graphics2D cg = (Graphics2D) g.create();
			cg.scale(sx, sy);
			paintDish(cg, dish);
			cg.dispose();
		}
		g.dispose();
	}

	private void paintDish(Graphics2D g, DishInfo dish) {
		for (OrganismInfo o : ctrl.getOrganisms()) {
			g.setColor(o.getColor());
			int size = o.getSize();
			int x = (int) o.getPosition().getX();
			int y = (int) o.getPosition().getY();
			g.fillOval(x, y, size, size);
		}

	}

	/*
	 * DishObserver interface
	 */

	@Override
	public void onRegister(double time, DishInfo dish) {
		SwingUtilities.invokeLater(() -> {
			this.reset();
		});
	}

	@Override
	public void onReset(double time, DishInfo dish) {
		SwingUtilities.invokeLater(() -> {
			this.reset();
		});
	}

	@Override
	public void onAdvance(double time, DishInfo dish, double dt) {
		SwingUtilities.invokeLater(() -> {
			this.update();
		});
	}

	@Override
	public void onWeightsChanged(double time, DishInfo flock) {
		// TODO Auto-generated method stub
		
	}
}
