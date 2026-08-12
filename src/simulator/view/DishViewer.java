package simulator.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.DishInfo;
import simulator.model.DishObserver;
import simulator.model.OrganismInfo;

public class DishViewer extends AbstractDishViewer implements DishObserver {

	private Controller ctrl;

	public DishViewer(Controller ctrl) {
		this.ctrl = ctrl;
		setVisible(true);
		ctrl.addObserver((DishObserver) this);
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

		int w = getWidth();
		int h = getHeight();

		gr.setColor(Theme.CANVAS_BG); // setBackground does not have the same effect
		gr.fillRect(0, 0, w, h);

		DishInfo dish = ctrl.getDishInfo();
		if (w > 0 && h > 0 && dish.getWidth() > 0 && dish.getHeight() > 0) {
			double sx = w / (double) dish.getWidth();
			double sy = h / (double) dish.getHeight();
			Graphics2D cg = (Graphics2D) g.create();
			cg.scale(sx, sy);
			cg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			paintDish(cg, dish);
			cg.dispose();
		}
	}

	// Using exact coordinates removes trembling
	private void paintDish(Graphics2D g, DishInfo dish) {
		for (OrganismInfo o : dish.getOrganisms()) {
			paintOrganism(g, o);
		}
	}

	private void paintOrganism(Graphics2D g, OrganismInfo o) {
		double x = o.getPosition().getX();
		double y = o.getPosition().getY();
		double r = o.getSize() / 2.0;

		g.setColor(o.getColor());
		Ellipse2D body = new Ellipse2D.Double(x - r, y - r, r * 2, r * 2);
		g.fill(body);
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
	public void onOrganismAdded(double time, DishInfo dish, OrganismInfo org) {
		SwingUtilities.invokeLater(() -> {
			this.update();
		});
	}

	@Override
	public void onWeightsChanged(double time, DishInfo flock) {

	}

	@Override
	public void onSettingsChanged(double time, DishInfo dish) {

	}

}
