import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Wall {
	int x = 100;

	int y = 100;

	int width;

	int height;
	protected int shake;

	public void shake() {
		this.shake = 1;
	}

	public Wall(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		if (shake==1) {
			g.fillRect(x+2, y+2, width, height);
			shake=-1;
		}else if(shake==-1){
			g.fillRect(x-2, y-2, width, height);
			shake=0;
		}else{
			g.fillRect(x, y, width, height);			
		}

		g.setColor(c);
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
}

