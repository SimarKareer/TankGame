package tile;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import main.Handler;
import main.Id;

public class Wall extends Tile {
	public boolean breakable;

	public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler, boolean breakable) {
		super(x, y, width, height, solid, id, handler);
		this.breakable = breakable;

	}

	public void render(Graphics g) {
		if (breakable)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GRAY.brighter());
		g.fillRect(x, y, width, height);
		if (breakable) {
			g.setColor(Color.WHITE);
			g.drawRect(x, y, width, height);
		}

	}

	public void tick() {

	}

}
