package entity.bullet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entity.Entity;
import entity.mob.Tank;
import main.Handler;
import main.Id;
import tile.Tile;

public class Bullet extends Entity {
	int damage;
	public Bullet(int x, int y, int width, int height, int velX, int velY, boolean solid, Id id, Handler handler, int damage) {
		super(x, y, width, height, velX, velY, solid, id, handler);
		this.damage = damage;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		
		for(int i = 0; i < handler.tile.size(); i++){
			Tile ti = handler.tile.get(i);
			if(getBounds().intersects(ti.getBounds())){
				main.Game.handler.getEntity().remove(this);
			}
		}
		for(int i = 0; i < handler.tanks.size(); i++){
			Tank t = handler.tanks.get(i);
			if(getBounds().intersects(t.getBounds())){
				main.Game.handler.getEntity().remove(this);
				if(t.health > 0)
					t.health -= 20;
				//System.out.println(t.health);
			}
		}
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public int getDamage(){
		return damage;
	}
}
