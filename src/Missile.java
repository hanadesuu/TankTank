import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Missile {
	int x = 100;

	int y = 100;

	int offx;

	int offy;

	private TankClient tc;

	public static int SPEED = 10;

	static int WIDTH = 10;

	static int HEIGHT = 10;
	boolean good;

	public Missile(int x, int y, int offx, int offy, TankClient tc, boolean good) {
		this.x = x;
		this.y = y;
		this.good = good;
		this.tc = tc;
		// if (offx==0) {
		// this.offx = 0;
		// }else if(offx<0){
		// this.offx=-SPEED;
		// }else{
		// this.offx = SPEED;
		// }
		this.offx = offx == 0 ? 0 : offx < 0 ? -SPEED : SPEED;
		// if (offy==0) {
		// this.offy = 0;
		// }else if(offy<0){
		// this.offy=-SPEED;
		// }else{
		// this.offy = SPEED;
		// }
		this.offy = offy == 0 ? 0 : offy < 0 ? -SPEED : SPEED;

		// this.offx = offx;
		// this.offy = offy;
	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillOval(x += offx, y += offy, WIDTH, HEIGHT);
		g.setColor(c);

		checkBounds();
		// y+=1;
	}

	private void checkBounds() {
		if (x < 0 || x > TankClient.GAME_WIDTH || y < 0
				|| y > TankClient.GAME_HEIGHT) {
			die();
		}
	}

	private void die() {
		this.tc.removeMissile(this);
	}

	public Rectangle getRect() {
		return new Rectangle(this.x, this.y, Missile.WIDTH, Missile.HEIGHT);
	}

	public void hitTank(Tank t) {
		if (this.good == t.good) {
			return;
		}
		if (t == null) {
			return;
		}

		if (this.getRect().intersects(t.getRect())) {
			this.die();
			t.die();
			if(t.live==false)
			{
				this.tc.addExplode(new Explode(t.x, t.y, this.tc));
			}
			
		}

	}

	public void hitTanks(List<Tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			hitTank(t);
		}

	}

	public boolean hitWalls(List<Wall> walls) {
		/*	if(this.getRect().intersects(w.getRect())){
				this.die();
				w.shake();
				return true;
			}
			return false;*/
			for (int i = 0; i < walls.size(); i++) {
				Wall w = walls.get(i);
				if(this.getRect().intersects(w.getRect())){
					this.die();
					w.shake();
					return true;
				}
			
		}
			return true;
	}

}
