package edu.java.sonny.view;

import edu.java.sonny.model.Sonny;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import edu.java.sonny.controller.SonnyDaoImpl;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Main {

	private JFrame frame;
	private final SonnyDaoImpl dao = SonnyDaoImpl.getInstance();
	static Main window;
	private JLabel lblNewLabel;
	private JButton btnStats;
	private JButton btnInformation;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 592, 751);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnInformation = new JButton("선수정보");
		btnInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SonnyTotalInfoFrame.showSonnyTotalInfoFrame(frame, Main.this);
				window.frame.setVisible(false);
			}
		});
		btnInformation.setFont(new Font("Dialog", Font.BOLD, 18));
		btnInformation.setBounds(154, 600, 127, 54);
		frame.getContentPane().add(btnInformation);
		
		btnStats = new JButton("스탯");
		btnStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SonMain.showSonMain(frame, Main.this);
				window.frame.setVisible(false);
			}
		});
		btnStats.setFont(new Font("Dialog", Font.BOLD, 18));
		btnStats.setBounds(302, 600, 127, 54);
		frame.getContentPane().add(btnStats);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images\\00main.jpg"));
		lblNewLabel.setBounds(0, 0, 579, 712);
		frame.getContentPane().add(lblNewLabel);
		
		
	}

}
