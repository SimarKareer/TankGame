import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.google.gson.Gson;

/**
 * 
 * !!!!
 ****
 *
 * 
 * MAKE A HUD OBJECT
 ****
 *
 * TRIM ARRAYLIST TO SIZE !!!!
 * 
 */

public class TankGame implements ActionListener, KeyListener, MouseListener, MouseWheelListener {
	public static Data superData;
	public static ArrayList<Bullet> theirData;
	public Renderer renderer;
	public static TankGame tankGame;
	public static int ticks, myTank;
	public int lastShot, weapon = 1, mouseDownStart;
	public final static int WIDTH = 1376;
	public final static int HEIGHT = 768;
	public final int FIRERATE = 20;
	//public static int[] score = {0,0};
	// public final int WIDTH = 500, HEIGHT = 500, FIRERATE = 30;
	public static boolean alive = true, rapid = false, canFire = true, canFireLaser;
	public String name = "";
	public boolean mouseDown = false;
	public static boolean sendBullets = false;
	public static Random rand;

	public static void main(String[] args, boolean hosting, String ip) throws Exception {
		tankGame = new TankGame();

		Socket SOCK;
		if (hosting) {
			System.out.println(InetAddress.getLocalHost().toString());
			ServerSocket SRVSOCK = new ServerSocket(1025);
			SOCK = SRVSOCK.accept();
		} else {
			SOCK = new Socket(ip, 1025);
		}

		InputStreamReader ir = new InputStreamReader(SOCK.getInputStream());
		BufferedReader br = new BufferedReader(ir);

		PrintStream ps = new PrintStream(SOCK.getOutputStream());

		Thread listener = new Thread(new SokListener(br, superData));
		Thread writer = new Thread(new SokWriter(ps, superData));

		listener.start();
		writer.start();

	}

	public boolean firstShot = false;

	public TankGame() throws Exception {
		JFrame frame = new JFrame();
		Timer timer = new Timer(20, (ActionListener) this);
		renderer = new Renderer();
		ArrayList<Bullet> bullets = null;
		ArrayList<Tank> tanks;
		frame.setVisible(true);
		frame.add(renderer);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Dank Tanks");
		frame.addKeyListener(this);
		frame.addMouseListener(this);

		tanks = new ArrayList<Tank>();
		bullets = new ArrayList<Bullet>();
		theirData = new ArrayList<Bullet>();
		superData = new Data(tanks, bullets);
		rand = new Random();
		superData.getTanks().add(new Tank(rand.nextInt(WIDTH - 100), rand.nextInt(HEIGHT - 100), 50, 50, true, Start.name));

		myTank = superData.getTanks().size() - 1;
		superData.getTanks().add(new Tank(500, 500, 50, 50, true, "Computer"));

		timer.start();
	}

	public void actionPerformed(ActionEvent arg0) {
		ticks++;
		if (ticks > lastShot + FIRERATE)
			canFire = true;
		boolean tankAbove = false, tankBelow = false, tankLeft = false, tankRight = false;

		for (int i = 0; i < superData.getTanks().size(); i++) { // tank
																// intersection
			if (i != myTank) {
				if (superData.getTanks().get(myTank).getBuffer().intersects(superData.getTanks().get(i))) {
					if (superData.getTanks().get(i).y + 25 < superData.getTanks().get(myTank).y + 25)
						tankAbove = true;
					if (superData.getTanks().get(i).y + 25 > superData.getTanks().get(myTank).y + 25)
						tankBelow = true;
					if (superData.getTanks().get(i).x + 25 < superData.getTanks().get(myTank).x + 25)
						tankLeft = true;
					if (superData.getTanks().get(i).x + 25 > superData.getTanks().get(myTank).x + 25)
						tankRight = true;
				}
			}
		}

		if (ticks == mouseDownStart + 50 && mouseDown == true && superData.tanks.get(myTank).alive) { // shoot
																										// laser
			mouseDown = false;
			PointerInfo a = MouseInfo.getPointerInfo();
			Point b = a.getLocation();
			int x = (int) b.getX();
			int y = (int) b.getY() - 25;
			float xMotion = x - (superData.getTanks().get(myTank).x + 25);
			float yMotion = y - (superData.getTanks().get(myTank).y + 25);

			float length = (float) Math.sqrt(xMotion * xMotion + yMotion * yMotion);
			xMotion /= length;
			yMotion /= length;

			xMotion *= 10;
			yMotion *= 10;

			int bulletX = superData.getTanks().get(myTank).x + 20 + (int) xMotion * 5;
			int bulletY = superData.getTanks().get(myTank).y + 20 + (int) yMotion * 5;
			superData.getBullets().add(new Bullet(bulletX, bulletY, 6, 6, xMotion, yMotion, 3, 20));
			sendBullets = true;
		}

		if (superData.getTanks().get(myTank).isAlive()) { // move tank
			if (superData.getTanks().get(myTank).wPressed && superData.getTanks().get(myTank).y >= 0) {
				if (!tankAbove) {
					superData.getTanks().get(myTank).y -= 5;
				}
			}
			if (superData.getTanks().get(myTank).aPressed && superData.getTanks().get(myTank).x >= 0) {
				if (!tankLeft)
					superData.getTanks().get(myTank).x -= 5;
			}
			if (superData.getTanks().get(myTank).sPressed && superData.getTanks().get(myTank).y <= HEIGHT - 70) {
				if (!tankBelow)
					superData.getTanks().get(myTank).y += 5;
			}
			if (superData.getTanks().get(myTank).dPressed && superData.getTanks().get(myTank).x <= WIDTH - 60) {
				if (!tankRight)
					superData.getTanks().get(myTank).x += 5;
			}
		}
		tankAbove = false;
		tankBelow = false;
		tankLeft = false;
		tankRight = false;

		if (firstShot) { // hits
			int indexBullets = 0;
			bulletLoop: for (Bullet bullet : superData.getBullets()) {

				int indexTanks = 0;
				for (Tank current : superData.getTanks()) {
					if (bullet.intersects(current)) {
						int damage = bullet.damage;
						superData.getBullets().remove(bullet);
						renderer.repaint();
						current.destroy(current, damage);

						break bulletLoop;
					}
					indexTanks++;
				}
				bullet.x += (bullet.xMotion) * bullet.speed;
				bullet.y += (bullet.yMotion) * bullet.speed;
				indexBullets++;
			}

			bulletLoop: for (Bullet bullet : theirData) {

				int indexTanks = 0;
				for (Tank current : superData.getTanks()) {
					if (bullet.intersects(current)) {
						int damage = bullet.damage;
						superData.getBullets().remove(bullet);
						renderer.repaint();
						current.destroy(current, damage);

						break bulletLoop;
					}
					indexTanks++;
				}
				bullet.x += (bullet.xMotion) * bullet.speed;
				bullet.y += (bullet.yMotion) * bullet.speed;
				indexBullets++;
			}
		}
		renderer.repaint();
	}

	public void repaint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		for (Tank current : superData.getTanks()) { // draw tanks
			// if (current == superData.tanks.get(myTank)){
			g.setColor(Color.RED);
			g.fillRect(current.x, current.y, current.width, current.height);
			if (current.isAlive()) {
				g.setColor(Color.WHITE);
				g.fillRect(current.x, current.y, current.width, current.height / current.maxHealth * current.health);
				g.drawString(current.name, current.x, current.y - 10);
			} else {
				g.setColor(Color.RED);
				g.drawString(current.name, current.x, current.y - 10);
			}
			// }

		}

		g.setColor(Color.WHITE);
		if (superData.getBullets().size() >= 1) { // draw bullets
			ArrayList<Bullet> temp = new ArrayList<Bullet>();
			for (int i = 0; i < superData.getBullets().size(); i++) {
				temp.add(superData.getBullets().get(i));
			}
			for (Bullet bullet : temp) { // cant access bullets bc being used by
											// another thread. Need to correctly
											// make a temp and access that
				if (bullet.x >= -10 && bullet.x <= WIDTH + 10 && bullet.y >= -10 && bullet.y <= HEIGHT + 10)
					g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
				else {
					superData.getBullets().remove(bullet);

					break;
				}
			}
		}

		for (Bullet bullet : theirData) { 
			if (bullet.x >= -10 && bullet.x <= WIDTH + 10 && bullet.y >= -10 && bullet.y <= HEIGHT + 10)
				g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
			else {
				break;
			}
		}
		// HUD
		g.drawString("Weapon " + weapon, 10, 738);
		
	}

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			superData.getTanks().get(myTank).wPressed = true;
			break;
		case KeyEvent.VK_A:
			superData.getTanks().get(myTank).aPressed = true;
			break;
		case KeyEvent.VK_S:
			superData.getTanks().get(myTank).sPressed = true;
			break;
		case KeyEvent.VK_D:
			superData.getTanks().get(myTank).dPressed = true;
			break;
		}
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			superData.getTanks().get(myTank).wPressed = false;
			break;
		case KeyEvent.VK_A:
			superData.getTanks().get(myTank).aPressed = false;
			break;
		case KeyEvent.VK_S:
			superData.getTanks().get(myTank).sPressed = false;
			break;
		case KeyEvent.VK_D:
			superData.getTanks().get(myTank).dPressed = false;
			break;
		case KeyEvent.VK_1:
			weapon = 1;
			break;
		case KeyEvent.VK_2:
			weapon = 2;
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		firstShot = true;
		mouseDown = true;

		if (canFire && superData.tanks.get(myTank).alive) {
			if (weapon == 1) { // fire weapon 1

				lastShot = ticks;
				canFire = false;
				float xMotion = arg0.getX() - (superData.getTanks().get(myTank).x + 25);
				float yMotion = arg0.getY() - (superData.getTanks().get(myTank).y + 25);

				float length = (float) Math.sqrt(xMotion * xMotion + yMotion * yMotion);
				xMotion /= length;
				yMotion /= length;

				xMotion *= 10;
				yMotion *= 10;

				int bulletX = superData.getTanks().get(myTank).x + 20 + (int) xMotion * 5;
				int bulletY = superData.getTanks().get(myTank).y + 20 + (int) yMotion * 5;
				superData.getBullets().add(new Bullet(bulletX, bulletY, 10, 10, xMotion, yMotion, 1.5, 10));
				Gson son = new Gson();
				// System.out.println(son.toJson(superData));
				sendBullets = true;
			}
			if (weapon == 2 && ticks >= mouseDownStart + 20) { // prime weapon
																// 2, record
																// mouse down
				mouseDownStart = ticks;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mouseDown = false;
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println("hello");
		int notches = 0;
		notches += e.getWheelRotation();
		if (notches < 0) {
			if (weapon < 2)
				weapon++;
			else
				weapon = 1;
		} else if (notches > 0)
			if (weapon > 1)
				weapon--;
			else
				weapon = 2;
	}
	public static void restart(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		rand = new Random();
		for(int i = 0; i < superData.getTanks().size(); i++){
			superData.getTanks().get(i).health = 50;
			superData.getTanks().get(i).alive = true;
			superData.getTanks().get(i).x = rand.nextInt(WIDTH - 100) + 25;
			superData.getTanks().get(i).y = rand.nextInt(HEIGHT - 100) + 25;
			
		}
	}
}