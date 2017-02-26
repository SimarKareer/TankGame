
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Renderer extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		TankGame.tankGame.repaint((Graphics2D)g);
	}
	

}