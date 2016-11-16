package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import entity.mob.Tank;
import input.KeyInput;
import input.MouseInput;
import networking.ClientSok;

/*
 * 
 * Separate my tank and my bullets from enemy tanks and bullets
 * 
 * Make Player Array.  Player array contains the index that their tank is in.
 */

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 * 
	 * 
	 * 
	 * MAKE A HUD MAKE A RADAR
	 * 
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 300, HEIGHT = WIDTH * 9 / 16, SCALE = 4; // 270
																				// orig
																				// 10/14
	public static final String TITLE = "Dank Tanks";
	private Thread thread;
	private boolean running = false;
	public static Handler handler;
	public int myTank;
	public static int ticks;
	public static BufferedImage map;
	public Camera cam;

	@Override
	public void run() {
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		int frames = 0;
		ticks = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {

				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}

	private synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this, "Thread");
		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("The End");
	}

	public void init() {
		//System.out.println("gothere");
		cam = new Camera();
		handler = new Handler();

		try {
			map = ImageIO.read(getClass().getResource("/Map1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handler.createMap(map);
		addKeyListener(new KeyInput());
		addMouseListener(new MouseInput());
		// System.out.println(handler.tile.size());
		handler.setMyTank(new Tank(1150, 800, 50, 50, 0, 0, true, Id.tank, handler, true, Start.name));// change
		
		System.out.println("made a tank");
		try {
			ClientSok.main(null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println("Break 1");
		//handler.addTank(new Tank(600, 600, 50, 50, 0, 0, true, Id.tank, handler, true, "conputer"));
		 // this

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3); // buffering
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK.darker());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.translate(cam.x, cam.y);
		g.setColor(Color.WHITE);
		handler.render(g);
		g.dispose();
		bs.show();
	}

	public void tick() {

		handler.tick();

		cam.tick(handler.myTank);
	}

	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}

	public static void main(String[] args) {
		
		Game game = new Game();
		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//handler.myTank = (new Tank(1150, 700, 50, 50, 0, 0, true, Id.tank, handler, true, Start.name));
		game.start();
		
	}
}