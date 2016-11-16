package entity.mob;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import entity.Entity;
import entity.bullet.Bullet;
import input.MouseInput;
import main.Handler;
import main.Id;
import tile.Tile;

public class Tank extends Entity {
	public boolean alive;
	public int frame = 0;
	public int frameDelay = 0;
	public int count = 0;
	public int health = 100;
	public int maxHealth = 100;
	public String name;

	public Tank(int x, int y, int width, int height, int velX, int velY, boolean solid, Id id, Handler handler,
			boolean alive, String name) {
		super(x, y, width, height, velX, velY, solid, id, handler);
		this.alive = alive;
		this.name = name;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, (int) (((float) health / maxHealth) * height));
		g.drawString(name, this.x, this.y - 15);
		g.setColor(Color.RED);
		g.drawRect(getBoundsTop().x, getBoundsTop().y, getBoundsTop().width, getBoundsTop().height);
		g.setColor(Color.ORANGE);
		g.drawRect(getBoundsBottom().x, getBoundsBottom().y, getBoundsBottom().width, getBoundsBottom().height);
		g.setColor(Color.YELLOW);
		g.drawRect(getBoundsLeft().x, getBoundsLeft().y, getBoundsLeft().width, getBoundsLeft().height);
		g.setColor(Color.GREEN);
		g.drawRect(getBoundsRight().x, getBoundsRight().y, getBoundsRight().width, getBoundsRight().height);
		// g.drawLine(this.x + this.width/2, this.y + this.height/2,
		// MouseInput.x, MouseInput.y);
	}

	@Override
	public void tick() {
		//System.out.printf("x: %d, y: %d\n", x, y);
		x += velX;
		y += velY;
		//System.out.println(handler.tile.size());
		if(handler != null){
			//System.out.println("here");
			for (int i = 0; i < handler.getTile().size(); i++) {
				Tile ti = handler.tile.get(i);
				count++;
				if (ti.getId() == Id.wall || ti.getId() == Id.border) {
					if (getVelY() != 0) {
						if (getBoundsTop().intersects(ti.getBounds())) { // bottom
																			// of
																			// tile
							y = ti.getY() + ti.getHeight();
							setVelY(0);
						}
						if (getBoundsBottom().intersects(ti.getBounds())) { // top
																			// of
																			// tile
							y = ti.getY() - height;
							setVelY(0);
						}
					}
					if (getVelX() != 0) {
						if (getBoundsRight().intersects(ti.getBounds())) { // left
																			// of
																			// tile
	
							x = ti.getX() - getWidth();
							setVelX(0);
						}
						if (getBoundsLeft().intersects(ti.getBounds())) { // right
																			// of
																			// tile
	
							x = ti.getX() + ti.getWidth();
							setVelX(0);
	
						}
					}
				}
			}
			for (int i = 0; i < handler.tanks.size(); i++) {
				Tank t = handler.tanks.get(i);
				count++;
	
				if (getVelY() != 0) {
					if (getBoundsTop().intersects(t.getBounds())) { // bottom
																	// of
																	// tile
						y = t.getY() + t.getHeight();
						setVelY(0);
					}
					if (getBoundsBottom().intersects(t.getBounds())) { // top
																		// of
																		// tile
						y = t.getY() - height;
						setVelY(0);
					}
				}
				if (getVelX() != 0) {
					if (getBoundsRight().intersects(t.getBounds())) { // left
																		// of
																		// tile
	
						x = t.getX() - getWidth();
						setVelX(0);
					}
					if (getBoundsLeft().intersects(t.getBounds())) { // right
																		// of
																		// tile
	
						x = t.getX() + t.getWidth();
						setVelX(0);
	
					}
				}
			}
		}
	}

	public Rectangle getBoundsTop() {
		return new Rectangle(x + 5, y - 5, width - 10, 3);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle(x + 5, y + height + 2, width - 10, 3);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle(x - 5, y + 5, 3, height - 10);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle(x + width + 2, y + 5, 3, height - 10);
	}

	public Rectangle getBuffer() {
		return new Rectangle(x - 5, y - 5, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getxMotion() {
		return velX;
	}

	public void setxMotion(int xMotion) {
		this.velX = xMotion;
	}

	public int getyMotion() {
		return velY;
	}

	public void setyMotion(int yMotion) {
		this.velY = yMotion;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
