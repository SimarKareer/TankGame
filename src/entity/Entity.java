package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.Id;
import main.Handler;

public abstract class Entity {

	public int x, y, width, height, velX, velY;
	public Id id;
	public Handler handler;
	public boolean solid;

	public Entity(int x, int y, int width, int height, int velX, int velY, boolean solid, Id id, Handler handler) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
		this.id = id;
		this.handler = handler;
		this.solid = solid;
	}

	public abstract void render(Graphics g);

	public abstract void tick();

	// error
	public void die() {
		handler.removeMyEntity(this);
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

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
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

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
	}

}
