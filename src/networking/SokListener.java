package networking;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;

import main.Game;
import main.Start;


public class SokListener implements Runnable {  //Reads Message From Server
	BufferedReader br;
	String ip;

	public SokListener(BufferedReader x, String i) {
		br = x;
		ip = i;
	}

	@Override
	public void run() {
		try { // HOW DO YOU EDIT SUPERDATA FROM HERE!!!!  make a setter
			Gson g = new Gson();
			String message = "";
			while ((message = br.readLine()) != null) {
//				superData.getTanks().set(1, g.fromJson(message, Data.class).getTanks().get(0));
//				//superData.getTanks().set(1, g.fromJson(message, Data.class).getTanks().get(0));
//				TankGame.theirData = g.fromJson(message, Data.class).getBullets();
				if(Start.name.equals("Bill"))
				//	System.out.println(message);
				main.Game.handler.setTanks(g.fromJson(message, ServerData.class).getTanks());
				main.Game.handler.setEntity(g.fromJson(message, ServerData.class).getEntity());
				
			}
		} catch (IOException e) {
		}

	}

}