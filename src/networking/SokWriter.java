package networking;

import java.io.PrintStream;

import com.google.gson.Gson;

import entity.mob.Tank;
import main.Start;


public class SokWriter implements Runnable {
	PrintStream ps;
	String ip;

	public SokWriter(PrintStream stream, String i) {
		ps = stream;
		ip = i;
	}

	@Override
	public void run() {
		System.out.println("readed writer");
		try {
			Gson g = new Gson();
			while (true) {
				Tank toSend = main.Game.handler.getMyTank();
				toSend.handler = null;
				ip = Start.name;
				Data out = new Data(main.Game.handler.getEntity(), toSend, ip);
				Thread.sleep(20);

				String messageOut = g.toJson(out); //Handler.superdata
				//System.out.println("not dead");
				//System.out.println(out.getTank().getX());
				if(Start.name.equals("Bill"))
					//System.out.println("Bill: " + messageOut);
				ps.println(messageOut);
			}

		} catch (Exception e) {
		}

	}

}