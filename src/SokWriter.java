import java.io.PrintStream;
import java.util.Scanner;

import com.google.gson.Gson;

public class SokWriter implements Runnable {
	PrintStream ps;
	Data superData;

	public SokWriter(PrintStream stream, Data superData) {
		ps = stream;
		this.superData = superData;
	}

	@Override
	public void run() {

		try {
			Gson g = new Gson();
			while (true) {
				Thread.sleep(20);
				String messageOut = g.toJson(superData);
				//if(superData.bullets.size() > 0)
					//System.out.println("writer: " + messageOut);
				ps.println(messageOut);
			}

		} catch (Exception e) {
		}

	}

}