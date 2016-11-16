package networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import entity.Entity;
import entity.mob.Tank;

public class ServerSok {
	public static ArrayList<Entity> entitys = new ArrayList<Entity>();
	public static ArrayList<Tank> tanks = new ArrayList<Tank>();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ServerSok SERVER = new ServerSok();

		SERVER.run();

	}

	public void run() throws Exception {
		System.out.println(InetAddress.getLocalHost().toString());
		ServerSocket SRVSOCK = new ServerSocket(1025);
		
		Socket SOCK = SRVSOCK.accept();
		//System.out.println("here");
		InputStreamReader ir = new InputStreamReader(SOCK.getInputStream());
		BufferedReader br = new BufferedReader(ir);

		PrintStream ps = new PrintStream(SOCK.getOutputStream());

		Thread listener = new Thread(new ServListener(br, entitys, tanks));
		Thread writer = new Thread(new ServWriter(ps, entitys, tanks));

		listener.start();
		writer.start();
		
		//ServerSocket SRVSOCK2 = new ServerSocket(1025);
		
		Socket SOCK2 = SRVSOCK.accept();
		//System.out.println("here");
		InputStreamReader ir2 = new InputStreamReader(SOCK2.getInputStream());
		BufferedReader br2 = new BufferedReader(ir2);

		PrintStream ps2 = new PrintStream(SOCK2.getOutputStream());

		Thread listener2 = new Thread(new ServListener(br2, entitys, tanks));
		Thread writer2 = new Thread(new ServWriter(ps2, entitys, tanks));

		listener2.start();
		writer2.start();
	}

}
