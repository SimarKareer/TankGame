package networking;

import java.io.PrintStream;
import java.util.ArrayList;

import com.google.gson.Gson;

import entity.Entity;
import entity.mob.Tank;

public class ServWriter implements Runnable {
	PrintStream ps;
	public ArrayList<Entity> entitys = new ArrayList<Entity>();
	public ArrayList<Tank> tanks = new ArrayList<Tank>();
	
	public ServWriter(PrintStream p, ArrayList<Entity> entitys2, ArrayList<Tank> tanks2) {
		ps = p;
		entitys = entitys2;
		tanks = tanks2;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ServerData out = new ServerData(entitys, tanks); 
		try {
			Gson g = new Gson();
			while (true) {
				Thread.sleep(20);
				String messageOut = g.toJson(out); //Handler.superdata
				//if(superData.bullets.size() > 0)
					//System.out.println("writer: " + messageOut);
				ps.println(messageOut);
			}

		} catch (Exception e) {
		}
	}

}
