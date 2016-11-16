package main;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import networking.ClientSok;
import networking.ServerSok;

public class Start extends JFrame{
	public static JButton join, host;
	public static JTextField tf, nameField;
	public static String ip;
	public static String name;
	public static boolean start = false;
	
	/*
	 * 
	 * 
	 * SUUUUUUUUPPPPPEEERRR IMPORTANT.  MAKE THE HOST BUTTON LAUNCH SERVERSOK
	 * 
	 * no more bullet collision.  RIP
	 * 
	 */
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

	public static void main(String[] args) {

		
		Start start = new Start();
		start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.setLocation(500, 300);
		start.setSize(450, 350);
		start.setVisible(true);
		

	}
	
	private class actionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "join"){
				ip = JOptionPane.showInputDialog("Enter IP of host");
				name = JOptionPane.showInputDialog("Enter your name");

				Game.main(null);
				dispose();
			}
			if(e.getActionCommand() == "host") {
				//name = JOptionPane.showInputDialog("Enter your name");
				try {
					ServerSok.main(null);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//dispose();
			}			
		}
		
	}
	private class mouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
