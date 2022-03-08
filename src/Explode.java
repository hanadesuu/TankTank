import java.awt.Color;
import java.awt.Graphics;

public class Explode {
	int x = 100;

	int y = 100;

	Color c = Color.green;

	int step;

	private TankClient tc;

	public Explode(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public void draw(Graphics g) {
		if (step >= 10) {
			this.die();
		}

		Color c = g.getColor();
		g.setColor(this.c = this.c.darker());
		g.fillOval(x, y, Tank.WIDTH, Tank.HEIGHT);
		step++;
		g.setColor(c);
	}

	private void die() {
		this.tc.removeExplode(this);
	}

}
