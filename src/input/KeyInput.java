package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;

public class KeyInput implements KeyListener {
	boolean vertical = false, horizontal = false;

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {

		// switch Game.handler.myTank to Game.handler.myTank.get(myTank)

		case KeyEvent.VK_W: // up
			Game.handler.myTank.setVelY(0);
			// vertical = false;
			break;
		case KeyEvent.VK_S: // down
			Game.handler.myTank.setVelY(0);
			// vertical = false;
			break;
		case KeyEvent.VK_A: // left
			Game.handler.myTank.setVelX(0);
			// horizontal = false;
			break;
		case KeyEvent.VK_D: // right
			Game.handler.myTank.setVelX(0);
			// horizontal = false;
			break;

		}

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {

		// switch Game.handler.myTank to Game.handler.myTank.get(myTank)

		case KeyEvent.VK_W: // up

			Game.handler.myTank.setVelY(-7);
			vertical = true;
			break;
		case KeyEvent.VK_S: // down
			Game.handler.myTank.setVelY(7);
			vertical = true;
			break;
		case KeyEvent.VK_A: // left
			Game.handler.myTank.setVelX(-7);
			horizontal = true;
			break;
		case KeyEvent.VK_D: // right
			Game.handler.myTank.setVelX(7);
			horizontal = true;
			break;
		case KeyEvent.VK_1:
			MouseInput.weapon = 1;
			break;
		case KeyEvent.VK_2:
			MouseInput.weapon = 2;
			break;
		}
		// if (!vertical)
		// Game.handler.myTank.setVelY(0);
		// if (!horizontal)
		// Game.handler.myTank.setVelX(0);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
