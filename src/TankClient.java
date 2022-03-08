import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TankClient extends Frame {

	private static final long serialVersionUID = 1L;

	static int GAME_WIDTH = 800;

	static int GAME_HEIGHT = 600;

	Image off;

	Tank myTank;

	private List<Tank> tanks = new ArrayList<Tank>();

	private List<Explode> explodes = new ArrayList<Explode>();

	private List<Missile> missiles = new ArrayList<Missile>();

	private List<Wall> walls = new ArrayList<Wall>();
	
	MenuBar jb=new MenuBar();
	Menu op=new Menu("加密策略");
	MenuItem exit=new MenuItem("退出");
	MenuItem s2=new MenuItem("减5法");
	MenuItem s1=new MenuItem("加2法");
	MenuItem s3=new MenuItem("乘2法");
	
	
	
	

	public void addWall(Wall w) {
		this.walls.add(w);
	}

	public void removeWall(Wall w) {
		this.walls.remove(w);
	}

	public void addMissile(Missile m) {
		this.missiles.add(m);
	}

	public void removeMissile(Missile m) {
		this.missiles.remove(m);
	}

	public void addExplode(Explode e) {
		this.explodes.add(e);
	}

	public void removeExplode(Explode e) {
		this.explodes.remove(e);
	}

	public void addTank(Tank t) {
		this.tanks.add(t);
	}

	public void removeTank(Tank t) {
		this.tanks.remove(t);
	}

	@Override
	public void update(Graphics g) {
		// double buffering
		// 1. 得到缓冲图象
		if (this.off == null) {
			this.off = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		// 2. 得到缓冲图象的画笔
		Graphics _g = this.off.getGraphics();
		// 3. 绘制缓冲图象
		
		_g.setColor(Color.BLACK);
		if(myTank.live==false)
		{
			_g.setColor(Color.RED);
		}
		else if(tanks.size()==1)
		{
			_g.setColor(Color.YELLOW);
		}
		_g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		// 4. 调用paint(),将缓冲图象的画笔传入
		paint(_g);
		// 5. 再将此缓冲图像一次性绘到代表屏幕的Graphics对象，即该方法传入的“g”上.
		g.drawImage(this.off, 0, 0, null);
	}

	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.launchFrame();
		
		// new Thread(tc.new PaintThread()).start();
	}

	@Override
	public void paint(Graphics g) {
		

		Color c = g.getColor();
		// if (this.myTank != null) {
		// this.myTank.draw(g);
		// }

		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (t != null /* && t.live */) {
				t.draw(g);
				t.collideWalls(this.walls);
				t.collideTanks(this.tanks);
			}
		}

		for (int i = 0; i < this.explodes.size(); i++) {
			Explode e = this.explodes.get(i);
			if (e != null) {
				e.draw(g);
			}
		}
		for (int i = 0; i < this.missiles.size(); i++) {
			Missile m = this.missiles.get(i);
			if (m != null) {
				m.draw(g);
				m.hitTanks(this.tanks);
				m.hitWalls(this.walls);
			}
		}

		for (int i = 0; i < this.walls.size(); i++) {
			Wall w = this.walls.get(i);
			if (w != null) {
				w.draw(g);
			}
		}
		g.setColor(Color.white);
		g.drawString("同屏子弹数：" + this.missiles.size(), 40, 60);
		g.drawString("剩余敌机数："+ (this.tanks.size()-1), 40, 80);
		g.drawString("剩余血量："+ this.myTank.blood, 40, 100);
		g.setColor(Color.RED);
		g.drawRect(130, 90, 100, 12);
		g.fillRect(130, 90, myTank.blood, 12);
		g.setColor(c);
		if(myTank.live==false)
		{
			g.drawString("NMSL", 400, 315); 
		}
		else if(tanks.size()==1)
		{
			g.drawString("你赢了哈哈哈", 400, 315);
		}
		// y+=1;
		
	}

	private void launchFrame() {
		this.setBounds(100, 100, GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("TankWar");
		this.setBackground(Color.BLACK);
		this.setResizable(false);
		initTank();
		initWall();
		this.addKeyListener(new KeyMoniter());
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				TankClient.this.setVisible(false);
				System.exit(0);
			}
		});
		this.setVisible(true);
		// new Thread(this.new PaintThread()).start();
		new Thread(new PaintThread()).start();

	}

	private void initWall() {
		this.addWall(new Wall(300, 200, 25, 400));
		this.addWall(new Wall(100, 300, 550, 25));
		this.addWall(new Wall(500, 100, 25, 300));

	}

	private void initTank() {
		this.myTank = new Tank(100, 100, this, true);
		for (int i = 0; i < 10; i++) {
			Tank t = new Tank(150 + i * 60, 100, this, false);
			this.addTank(t);
		}
		this.addTank(this.myTank);
	}

	class PaintThread implements Runnable {

		public void run() {
			while (true) {
				TankClient.this.repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class KeyMoniter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (myTank.live) {
				myTank.keyPressed(key);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if (myTank.live) {
				myTank.keyReleased(key);
			}
		}
	}
}
