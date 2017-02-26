import java.io.BufferedReader;
import java.io.IOException;
import com.google.gson.Gson;

public class SokListener implements Runnable {
	BufferedReader br;
	Data superData;

	public SokListener(BufferedReader x, Data y) {
		br = x;
		superData = y;
	}

	@Override
	public void run() {
		try { // HOW DO YOU EDIT SUPERDATA FROM HERE!!!!  make a setter
			Gson g = new Gson();
			String message = "";
			while ((message = br.readLine()) != null) {
				superData.getTanks().set(1, g.fromJson(message, Data.class).getTanks().get(0));
				//superData.getTanks().set(1, g.fromJson(message, Data.class).getTanks().get(0));
				TankGame.theirData = g.fromJson(message, Data.class).getBullets();
				/*
				int superSize = superData.getBullets().size();
				int messageSize = g.fromJson(message, Data.class).getBullets().size();

				for(int i = superSize; i < superSize + messageSize  ; i++){
					superData.getBullets().add(g.fromJson(message, Data.class).getBullets().get(i - superSize));
				}
				*/

			}
		} catch (IOException e) {
		}

	}

}