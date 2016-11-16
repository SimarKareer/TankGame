package networking;

import java.util.ArrayList;

import entity.Entity;
import entity.mob.Tank;

public class ServerData {
	public ArrayList<Entity> entity;
	public ArrayList<Tank> tank;
	
	public ServerData(ArrayList<Entity> entitys, ArrayList<Tank> tanks){
		entity = entitys;
		tank = tanks;
	}

	public ArrayList<Entity> getEntity() {
		return entity;
	}

	public void setEntity(ArrayList<Entity> entity) {
		this.entity = entity;
	}

	public ArrayList<Tank> getTanks() {
		return tank;
	}

	public void setTanks(ArrayList<Tank> tank) {
		this.tank = tank;
	}
}
