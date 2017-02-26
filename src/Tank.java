import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JFrame;

public class Tank extends Rectangle {
	boolean alive;
	Rectangle buffer;
	String name;
	int health, maxHealth;
	boolean wPressed = false, aPressed = false, sPressed = false, dPressed = false;

	public Tank(int a, int b, int c, int d, boolean e, String f) {
		setRect(a, b, c, d);
		alive = true;
		buffer = new Rectangle(x - 5, y - 5, 60, 60);

		maxHealth = 50;
		health = maxHealth;
		name = f;
	}

	public void destroy(Tank toDamage, int damage) {

		if (toDamage.health > 0)
			toDamage.health -= damage;
		if (health <= 0) {
			alive = false;
			health = 0;
			Renderer r = new Renderer();
			r.repaint();
			TankGame.restart();
		}

	}

	public boolean isAlive() {
		return alive;
	}

	public Rectangle getBuffer() {
		buffer.x = this.x - 5;
		buffer.y = this.y - 5;
		return buffer;
	}

}
