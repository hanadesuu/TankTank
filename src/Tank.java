import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

public class Tank {
	int x = 100;

	int y = 100;

	int offx;

	int offy;

	static int WIDTH = 50;

	static int HEIGHT = 50;

	int offxGun;

	int offyGun = Tank.HEIGHT / 2;

	public int SPEED = 5;

	private TankClient tc;

	boolean good;

	Random r = new Random();

	boolean live = true;

	int oldx, oldy;
	
	int blood=100;

	public void draw(Graphics g) {
		// if (!live) {
		// return;
		// }
		Color c = g.getColor();
		if (good) {
			g.setColor(Color.red);
		} else {
			g.setColor(Color.green);
		}

		if (!good) {

			if (r.nextInt(10) == 0) {
				offx = (r.nextInt(3) - 1) * SPEED;
			}
			if (r.nextInt(10) == 0) {
				offy = (r.nextInt(3) - 1) * SPEED;
			}
			if (r.nextInt(15) == 0) {
				fire();
			}
		}
		this.oldx = x;
		this.oldy = y;
		g.fillOval(x += offx, y += offy, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		setGun();
		drawGun(g);
		checkBounds();
		g.setColor(c);

	}

	private void checkBounds() {
		if (this.x < -Tank.WIDTH) {
			this.x = TankClient.GAME_WIDTH;
		}
		if (this.x > TankClient.GAME_WIDTH) {
			this.x = -Tank.WIDTH;
		}

		if (this.y < -Tank.HEIGHT) {
			this.y = TankClient.GAME_HEIGHT;
		}
		if (this.y > TankClient.GAME_HEIGHT) {
			this.y = -Tank.HEIGHT;
		}
	}

	private void drawGun(Graphics g) {
		int x = this.x + Tank.WIDTH / 2;
		int y = this.y + Tank.HEIGHT / 2;
		g.drawLine(x, y, x + offxGun, y + offyGun);
	}

	public Tank(int x, int y, TankClient tc, boolean good) {
		this.x = x;
		this.y = y;
		this.tc = tc;
		this.good = good;
	}

	public void keyPressed(int key) {
		System.out.println(key);
		switch (key) {
		case KeyEvent.VK_J:
			offx = -SPEED;
			break;
		case KeyEvent.VK_I:
			offy = -SPEED;
			break;
		case KeyEvent.VK_L:
			offx = SPEED;
			break;
		case KeyEvent.VK_K:
			offy = SPEED;
			break;
		case KeyEvent.VK_1:
			fire1();
			break;
		case KeyEvent.VK_CONTROL:
			fire();
			break;
		default:
			break;
		}

	}

	private void fire() {
		int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
		int y = this.y + Tank.HEIGHT / 2 - Missile.WIDTH / 2;
		this.tc.addMissile(new Missile(x, y, this.offxGun, this.offyGun,this.tc, this.good));

	}
	private void fire1() {
		int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
		int y = this.y + Tank.HEIGHT / 2 - Missile.WIDTH / 2;
		this.tc.addMissile(new Missile(x, y, this.offxGun, this.offyGun,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y, -this.offxGun, -this.offyGun,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y, this.offxGun+100, this.offyGun+100,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y, this.offxGun-100, this.offyGun-100,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y, this.offxGun+100, this.offyGun-100,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y, this.offxGun-100, this.offyGun+100,this.tc, this.good));
		this.tc.addMissile(new Missile(x+10, y, this.offxGun, this.offyGun,this.tc, this.good));
		this.tc.addMissile(new Missile(x+10, y, -this.offxGun, -this.offyGun,this.tc, this.good));
		this.tc.addMissile(new Missile(x+10, y, this.offxGun+100, this.offyGun+100,this.tc, this.good));
		this.tc.addMissile(new Missile(x+10, y, this.offxGun-100, this.offyGun-100,this.tc, this.good));
		this.tc.addMissile(new Missile(x+10, y, this.offxGun+100, this.offyGun-100,this.tc, this.good));
		this.tc.addMissile(new Missile(x+10, y, this.offxGun-100, this.offyGun+100,this.tc, this.good));
		this.tc.addMissile(new Missile(x-10, y, this.offxGun, this.offyGun,this.tc, this.good));
		this.tc.addMissile(new Missile(x-10, y, -this.offxGun, -this.offyGun,this.tc, this.good));
		this.tc.addMissile(new Missile(x-10, y, this.offxGun+100, this.offyGun+100,this.tc, this.good));
		this.tc.addMissile(new Missile(x-10, y, this.offxGun-100, this.offyGun-100,this.tc, this.good));
		this.tc.addMissile(new Missile(x-10, y, this.offxGun+100, this.offyGun-100,this.tc, this.good));
		this.tc.addMissile(new Missile(x-10, y, this.offxGun-100, this.offyGun+100,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y+10, this.offxGun, this.offyGun,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y+10, -this.offxGun, -this.offyGun,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y+10, this.offxGun+100, this.offyGun+100,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y+10, this.offxGun-100, this.offyGun-100,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y+10, this.offxGun+100, this.offyGun-100,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y+10, this.offxGun-100, this.offyGun+100,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y-10, this.offxGun, this.offyGun,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y-10, -this.offxGun, -this.offyGun,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y-10, this.offxGun+100, this.offyGun+100,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y-10, this.offxGun-100, this.offyGun-100,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y-10, this.offxGun+100, this.offyGun-100,this.tc, this.good));
		this.tc.addMissile(new Missile(x, y-10, this.offxGun-100, this.offyGun+100,this.tc, this.good));

	}

	public void keyReleased(int key) {
		switch (key) {
		case KeyEvent.VK_J:
			offx = 0;
			break;
		case KeyEvent.VK_I:
			offy = 0;
			break;
		case KeyEvent.VK_L:
			offx = 0;
			break;
		case KeyEvent.VK_K:
			offy = 0;
			break;
		//case KeyEvent.VK_CONTROL:
			//fire();
			//break;


		default:
			break;
		}

	}

	private void setGun() {
		if (offx == 0 && offy == 0) {
			return;
		}

		if (offx == 0) {
			this.offxGun = 0;
		} else if (offx < 0) {
			this.offxGun = -Tank.WIDTH / 2;
		} else {
			this.offxGun = Tank.WIDTH / 2;
		}
		if (offy == 0) {
			this.offyGun = 0;
		} else if (offy < 0) {
			this.offyGun = -Tank.HEIGHT / 2;
		} else {
			this.offyGun = Tank.HEIGHT / 2;
		}
	}

	public Rectangle getRect() {
		return new Rectangle(this.x, this.y, Tank.WIDTH, Tank.HEIGHT);
	}

	public void die() {
		if(this.live)
		{
			this.blood-=10;
		}
		if(this.blood==0)
		{
			this.live = false;
			this.tc.removeTank(this);
		}
	}

	public void collideWalls(List<Wall> walls) {
		for (int i = 0; i < walls.size(); i++) {
			Wall w = walls.get(i);
			collideWall(w);
		}

	}

	private void collideWall(Wall w) {
		if (this.getRect().intersects(w.getRect())) {
			this.goBack();
		}
	}

	private void goBack() {
		this.x = this.oldx;
		this.y = this.oldy;
	}

	public void collideTanks(List<Tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			this.collideTank(t);
		}
	}

	private void collideTank(Tank t) {
		if (this == t) {
			return;
		}
		if (this.getRect().intersects(t.getRect())) {
			this.goBack();
			t.goBack();
		}
	}
}
