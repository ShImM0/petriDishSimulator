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

public class DishViewer extends AbstractDishViewer implements DishObserver{

	private int width;
	private int height;
	private double time;
	
	private Controller ctrl;
	private List<OrganismInfo> organisms;
	private List<Nutrient> nutrients;
	
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
		// gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); TODO

		g.setColor(Color.gray);
		g.setFont(textFont);
		gr.setBackground(Color.WHITE);
		gr.fillRect(0, 0, width, height); // fill vs clear
		
		if (organisms != null)
			drawOrganisms(gr, ctrl.getDishInfo());
	}
	
	private void drawOrganisms(Graphics2D g, DishInfo dish) {
		for(OrganismInfo o: organisms) {
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
		SwingUtilities.invokeLater(() ->{
			this.reset();
		});
	}

	@Override
	public void onReset(double time, DishInfo dish) {
		SwingUtilities.invokeLater(() ->{
			this.reset();
		});
	}

	@Override
	public void onOrganismAdded(double time, DishInfo dish, OrganismInfo o) {
		SwingUtilities.invokeLater(() ->{
			this.reset();
		});
	}

	@Override
	public void onAdvance(double time, DishInfo dish, double dt) {
		SwingUtilities.invokeLater(() -> {
			this.update();
		});
	}
}
