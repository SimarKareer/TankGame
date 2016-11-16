package networking;

import java.util.ArrayList;

import entity.Entity;
import entity.mob.Tank;

public class Data {
	public ArrayList<Entity> entity;
	public Tank tank;
	public String ip;
	
	public Data(ArrayList<Entity> e, Tank t, String i){
		entity = e;
		tank = t;
		ip = i;
	}

	public ArrayList<Entity> getEntity() {
		return entity;
	}

	public void setEntity(ArrayList<Entity> entity) {
		this.entity = entity;
	}

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
