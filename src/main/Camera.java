package main;

import entity.mob.Tank;

public class Camera {
	public int x, y;
	
	

	public void tick(Tank t){
		int width = Game.map.getWidth() * 64;
		int height = Game.map.getHeight() * 64;
		if(main.Game.handler.getMyTank().getY() >= (Game.HEIGHT/2 * Game.SCALE)+100 && main.Game.handler.getMyTank().getY() <= height - (Game.HEIGHT / 2 * Game.SCALE) - 100 ){
			setY((Game.HEIGHT * Game.SCALE/2) - t.getY());
		}
		if(main.Game.handler.getMyTank().getX() >= (Game.WIDTH/2 * Game.SCALE) + 100 && main.Game.handler.getMyTank().getX() <= width - (Game.WIDTH / 2 * Game.SCALE) - 100)
			setX((Game.WIDTH * Game.SCALE)/2 - t.getX());

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

}
