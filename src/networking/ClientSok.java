package networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

import main.Start;

public class ClientSok {

	//public static Data superData = new Data(main.Game.handler.myEntity, main.Game.handler.myTank, "");
	public static void main(String[] args) throws Exception {
		ClientSok CLIENT = new ClientSok();
		CLIENT.run();

	}

	public void run() throws Exception {
		System.out.println("Reached client sok");
		Socket SOCK = new Socket(Start.ip, 1025);

		InputStreamReader ir = new InputStreamReader(SOCK.getInputStream());
		BufferedReader br = new BufferedReader(ir);

		PrintStream ps = new PrintStream(SOCK.getOutputStream());
		String ip = InetAddress.getLocalHost().toString();
		ip = ip.substring(ip.indexOf("/"));
		//superData.setIp(ip);
		//main.Game.init();
		Thread listener = new Thread(new SokListener(br, ip));
		Thread writer = new Thread(new SokWriter(ps, ip));

		listener.start();
		writer.start();
	}

}
