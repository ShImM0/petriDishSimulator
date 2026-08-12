package simulator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel {

	private final int radius;
	private final Color fill;

	public RoundedPanel(int radius, Color fill) {
		this.radius = radius;
		this.fill = fill;
		setOpaque(false);
		setBorder(new RoundedBorder(radius));
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(fill);
		g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
		g2.dispose();
		super.paintComponent(g);
	}
}