package input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import entity.bullet.Bullet;
import main.Game;
import main.Handler;
import main.Id;

public class MouseInput implements MouseListener {
	private boolean firstShot;
	private boolean mouseDown;
	private boolean canFire = true;
	private int lastShot;
	static public int weapon = 1;
	public static int x, y;
	public static int mouseX, mouseY;
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent m) {
		// TODO Auto-generated method stub
		firstShot = true;
		mouseDown = true;

		if (/* canFire && */main.Game.handler.getMyTank().alive) {
			// if (weapon == 1) { // fire weapon 1

			// lastShot = main.Game.ticks;
			// canFire = false;
			// PointerInfo a = MouseInfo.getPointerInfo();
			// Point b = a.getLocation();
			x = main.Game.handler.getMyTank().x + main.Game.handler.getMyTank().width / 2;
			y = main.Game.handler.getMyTank().y + main.Game.handler.getMyTank().height / 2;
//			int mouseX = m.getX() - x;
//			int mouseY = m.getY() - y;
			if  (m.getX() >= Game.WIDTH / 2)
				mouseX = x + m.getX() - Game.WIDTH / 2;		
			else
				mouseX = x - (Game.WIDTH /2 - m.getX());
			
			if  (m.getY() >= Game.HEIGHT / 2)
				mouseY = y + m.getY() - Game.HEIGHT / 2;		
			else
				mouseY = y - (Game.HEIGHT /2 - m.getY());
			
			float xMotion = mouseX - x;
			float yMotion = mouseY - y;
//			float xMotion = mouseX;
//			float yMotion = mouseY;

			float length = (float) Math.hypot(xMotion, yMotion);
			xMotion /= length;
			yMotion /= length;

			xMotion *= 15;
			yMotion *= 15;

			int bulletX = main.Game.handler.getMyTank().x + 20 + (int) xMotion * 5;
			int bulletY = main.Game.handler.getMyTank().y + 20 + (int) yMotion * 5;

			Game.handler.addMyEntity(new Bullet(bulletX, bulletY, 10, 10, (int) xMotion, (int) yMotion, true, Id.bullet,
					Game.handler, 15));
			// System.out.println(son.toJson(superData));
			// sendBullets = true;
			// }
			// if (weapon == 2 && ticks >= mouseDownStart + 20) { // prime
			// weapon
			// 2, record
			// mouse down
			// mouseDownStart = ticks;
			// }
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
