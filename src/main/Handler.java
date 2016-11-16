package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Entity;
import entity.mob.Tank;
import tile.Tile;
import tile.Wall;

public class Handler {
	public ArrayList<Entity> entity = new ArrayList<Entity>();
	public ArrayList<Entity> myEntity = new ArrayList<Entity>();
	public ArrayList<Tank> tanks = new ArrayList<Tank>();
	public Tank myTank;
	public ArrayList<Tile> tile = new ArrayList<Tile>();
	

	

	public Handler() {

	}

	public void createMap(BufferedImage map) {
		int width = map.getWidth();
		int height = map.getHeight();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = map.getRGB(x, y);

				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				if (red == 0 && green == 0 && blue == 0)
					addTile(new Wall(x * 64, y * 64, 64, 64, true, Id.border, this, false));
				if (red == 255 && green == 0 && blue == 0)
					addTile(new Wall(x * 64, y * 64, 64, 64, true, Id.wall, this, true));
			}
		}
	}

	public void render(Graphics g) {
		myTank.render(g);
		for (Entity en : entity) {
			en.render(g);
		}
		for(Entity en : myEntity){
			en.render(g);
		}
		for (Tile ti : tile) {
			ti.render(g);
		}
		for (Tank t : tanks) {
			t.render(g);
		}
	}

	public void tick() {

		for (int i = 0; i < entity.size(); i++) {  //Dont tick entity.  Just my entity
			entity.get(i).tick();
		}
		for (int i = 0; i < myEntity.size(); i++) {
			myEntity.get(i).tick();
		}
		
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).tick();
		}
		for (int i = 0; i < tile.size(); i++) {
			tile.get(i).tick();
		}
		myTank.tick();
	}

	public void setMyTank(Tank t){
		myTank = t;
	}
	
	public Tank getMyTank(){
		return myTank;
	}
	
	public void addMyEntity(Entity en) {
		myEntity.add(en);
	}
	

	public ArrayList<Entity> getEntity() {
		return entity;
	}

	public void setEntity(ArrayList<Entity> entity) {
		this.entity = entity;
	}

	public ArrayList<Tank> getTanks() {
		return tanks;
	}

	// error
	public void removeMyEntity(Entity en) {
		myEntity.remove(en);
	}

	public void addTile(Tile ti) {
		tile.add(ti);
	}

	public void removeTile(Tile ti) {
		tile.remove(ti);
	}

	public void addTank(Tank t) {
		tanks.add(t);
	}

	public void removeTank(Tank t) {
		tanks.remove(t);
	}
	
	public void setTanks(ArrayList<Tank> t){
		tanks = t;
	}

	public void killTank(Tank t) {
		t.setAlive(false);
	}
	public ArrayList<Tile> getTile(){
		return tile;
	}
}