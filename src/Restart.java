import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Restart extends JFrame{
	public static JButton start, close;
	public Random rand;
	
	public static void main(String[] args) {
		System.out.println("here");


	}
	public Restart(){
		super("Dank Tanks");
		setLayout(new FlowLayout());
		start = new JButton("Play Again");
		close = new JButton("Close");
		actionListener listener = new actionListener();
		start.addActionListener(listener);
		close.addActionListener(listener);
		start.setActionCommand("restart");
		close.setActionCommand("close");
		 add(start);
		 add(close);
		 rand = new Random();
	}
	private class actionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "restart"){
				TankGame.superData.getTanks().get(TankGame.myTank).health = 50;
				TankGame.superData.getTanks().get(TankGame.myTank).x = rand.nextInt(TankGame.WIDTH - 100);
				TankGame.superData.getTanks().get(TankGame.myTank).y = rand.nextInt(TankGame.HEIGHT - 100);
				TankGame.superData.getTanks().get(TankGame.myTank).alive = true;
			}
			if(e.getActionCommand() == "close"){
				
			}
			
		}
		
	}
}
