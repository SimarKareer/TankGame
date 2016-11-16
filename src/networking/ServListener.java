package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import entity.Entity;
import entity.mob.Tank;

public class ServListener implements Runnable {
	BufferedReader br;
	public ArrayList<Entity> entitys = new ArrayList<Entity>();
	public ArrayList<Tank> tanks = new ArrayList<Tank>();
	
	public ServListener(BufferedReader b, ArrayList<Entity> entitys2, ArrayList<Tank> tanks2) {
		br = b;
		entitys = entitys2;
		tanks = tanks2;
	}

	@Override
	public void run() {
		int players = 0;
		Gson g = new Gson();
		String message = "";
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		//System.out.println("reached");
		try {
			while ((message = br.readLine()) != null) {
				
				Data in = g.fromJson(message, Data.class);
				if(!hm.containsKey(in.getIp())){
					hm.put(in.getIp(), players);
					players ++;
					tanks.add(in.getTank());
					System.out.println(in.getIp() + " has connected");
				}
				else{
					int index = hm.get(in.getIp());
					//System.out.println(in.getTank().getX());
					tanks.set(index, in.getTank());//send bullets too
				}
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
