import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Start extends JFrame {
	public static JButton join, host;
	public static JTextField tf, nameField;
	public static String ip;
	public static String name;
	public static boolean start = false;
	public Start() {
		super("Dank Tanks");

		setLayout(new FlowLayout());
		join = new JButton("Join");

		host = new JButton("Host");
		

		actionListener listener = new actionListener();
		host.addActionListener(listener);
		host.setActionCommand("host");
		join.addActionListener(listener);
		join.setActionCommand("join");

		add(join);
		add(host);
		
	}
	
	private class actionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "join") {
				while (ip == null){
					ip = JOptionPane.showInputDialog("Enter IP of host");
				}
				while(name == null){
					name = JOptionPane.showInputDialog("Enter your name");
				}


				try {
					dispose();
					TankGame.main(null, false, ip);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			else if (e.getActionCommand() == "host") {
				name = JOptionPane.showInputDialog("Enter your name");

				try {
					dispose();
					TankGame.main(null, true, null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}

	}

	public static void main(String[] args) {

		Start start = new Start();
		start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.setLocation(500, 300);
		start.setSize(450, 350);
		start.setVisible(true);
		
	}
}
