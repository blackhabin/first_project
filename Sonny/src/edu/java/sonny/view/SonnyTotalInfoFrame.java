package edu.java.sonny.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextArea;

import javax.swing.border.EmptyBorder;

import edu.java.sonny.controller.SonnyDaoImpl;
import edu.java.sonny.model.SonnyBodyInfo;


import javax.swing.ImageIcon;
import javax.swing.JButton;


public class SonnyTotalInfoFrame extends JFrame {
	
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblSeason;

	
	private Component parent;
	private SonMain app;
	
	private SonnyBodyInfo info = new SonnyBodyInfo();
	
	private final SonnyDaoImpl dao = SonnyDaoImpl.getInstance();
	private JLabel lblphoto;
	private JTextArea textSonName;
	private JLabel lblSeason_1;
	private JTextArea textSonHeight;
	private JLabel lblSeason_2;
	private JTextArea textSonWeight;
	private JLabel lblSeason_3;
	private JTextArea textSonDate;
	private JLabel lblSeason_4;
	private JTextArea textSonClub;
	private JPanel panel_1;
	private JButton btnReturnMain;
	
	String totalMatches = String.valueOf(dao.totalMatches());
	String totalGoal = String.valueOf(dao.totalGoal());
	String totalAssists = String.valueOf(dao.totalAssists());
	private JLabel lblINFOLABEL;
	private JLabel lblSonmatch;
	private JTextArea textSonmatch;
	private JLabel lblSonGoals;
	private JTextArea textSonGoals;
	private JLabel lblSonassists;
	private JTextArea textSonassists;
	
	
	public static void showSonnyTotalInfoFrame(Component parent, Main apps) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					SonnyTotalInfoFrame frame = new SonnyTotalInfoFrame(parent, apps);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
		});
		
	}

	
	public SonnyTotalInfoFrame(Component parent, Main apps) {
		this.parent = parent;
		this.app = app;
		initialize();
	}


	private void initialize() {
		setTitle("선수 정보");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 100;
		int y = 100;
		if(parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		
		setBounds(x, y, 971, 761);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblSeason = new JLabel("선수이름");
		lblSeason.setFont(new Font("Dialog", Font.BOLD | Font.BOLD, 22));
		lblSeason.setBounds(24, 39, 109, 29);
		panel.add(lblSeason);
		
		lblphoto = new JLabel("");
		lblphoto.setIcon(new ImageIcon("images\\05main.jpg"));
		lblphoto.setBounds(431, 0, 533, 714);
		panel.add(lblphoto);
		
		textSonName = new JTextArea();
		textSonName.setBounds(163, 40, 132, 27);
		panel.add(textSonName);
		textSonName.append(info.getName());
		textSonName.setFont(new Font("Dialog", Font.BOLD, 21));
		textSonName.setOpaque(false);
				
		lblSeason_1 = new JLabel("키");
		lblSeason_1.setFont(new Font("Dialog", Font.BOLD, 22));
		lblSeason_1.setBounds(24, 93, 109, 29);
		panel.add(lblSeason_1);
		
		textSonHeight = new JTextArea();
		textSonHeight.append(info.getHeight());
		textSonHeight.setOpaque(false);
		textSonHeight.setFont(new Font("Dialog", Font.BOLD, 21));
		textSonHeight.setBounds(163, 94, 132, 27);
		panel.add(textSonHeight);
		
		lblSeason_2 = new JLabel("무게");
		lblSeason_2.setFont(new Font("Dialog", Font.BOLD, 22));
		lblSeason_2.setBounds(24, 148, 109, 29);
		panel.add(lblSeason_2);
		
		textSonWeight = new JTextArea();
		textSonWeight.append(info.getWeight());
		textSonWeight.setOpaque(false);
		textSonWeight.setFont(new Font("Dialog", Font.BOLD, 21));
		textSonWeight.setBounds(163, 149, 132, 27);
		panel.add(textSonWeight);
		
		lblSeason_3 = new JLabel("생년월일");
		lblSeason_3.setFont(new Font("Dialog", Font.BOLD, 22));
		lblSeason_3.setBounds(24, 204, 109, 29);
		panel.add(lblSeason_3);
		
		textSonDate = new JTextArea();
		textSonDate.append(info.getDate());
		textSonDate.setOpaque(false);
		textSonDate.setFont(new Font("Dialog", Font.BOLD, 21));
		textSonDate.setBounds(163, 205, 132, 27);
		panel.add(textSonDate);
		
		lblSeason_4 = new JLabel("소속팀");
		lblSeason_4.setFont(new Font("Dialog", Font.BOLD, 22));
		lblSeason_4.setBounds(24, 259, 109, 29);
		panel.add(lblSeason_4);
		
		textSonClub = new JTextArea();
		textSonClub.append(info.getClub());
		textSonClub.setOpaque(false);
		textSonClub.setFont(new Font("Dialog", Font.BOLD, 21));
		textSonClub.setBounds(163, 260, 132, 27);
		panel.add(textSonClub);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		panel_1.setBounds(0, 619, 419, 95);
		panel.add(panel_1);
		
		btnReturnMain = new JButton("이전화면");
		btnReturnMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.main(null);
				dispose();
				
			}
		});
		btnReturnMain.setBackground(new Color(192, 192, 192));
		btnReturnMain.setFont(new Font("Dialog", Font.BOLD, 19));
		btnReturnMain.setBounds(27, 26, 120, 45);
		panel_1.add(btnReturnMain);
		
		lblSonGoals = new JLabel("득점");
		lblSonGoals.setFont(new Font("Dialog", Font.BOLD, 22));
		lblSonGoals.setBounds(24, 372, 109, 29);
		panel.add(lblSonGoals);
		
		lblSonmatch = new JLabel("경기 수");
		lblSonmatch.setFont(new Font("Dialog", Font.BOLD, 22));
		lblSonmatch.setBounds(24, 316, 109, 29);
		panel.add(lblSonmatch);
		
		textSonmatch = new JTextArea();
		textSonmatch.setOpaque(false);
		textSonmatch.setFont(new Font("Dialog", Font.BOLD, 21));
		textSonmatch.setBounds(163, 317, 132, 27);
		textSonmatch.append(totalMatches + " 경기");
		panel.add(textSonmatch);
		
		textSonGoals = new JTextArea();
		textSonGoals.setOpaque(false);
		textSonGoals.setFont(new Font("Dialog", Font.BOLD, 21));
		textSonGoals.setBounds(163, 373, 132, 27);
		textSonGoals.append(totalGoal + " 골");
		panel.add(textSonGoals);
		
		lblSonassists = new JLabel("어시스트");
		lblSonassists.setFont(new Font("Dialog", Font.BOLD, 22));
		lblSonassists.setBounds(24, 429, 109, 29);
		panel.add(lblSonassists);
		
		textSonassists = new JTextArea();
		textSonassists.setOpaque(false);
		textSonassists.setFont(new Font("Dialog", Font.BOLD, 21));
		textSonassists.setBounds(163, 430, 132, 27);
		textSonassists.append(totalAssists + " 도움");
		panel.add(textSonassists); 
		
		lblINFOLABEL = new JLabel("");
		lblINFOLABEL.setIcon(new ImageIcon("images\\background.jpg"));
		lblINFOLABEL.setForeground(Color.WHITE);
		lblINFOLABEL.setBounds(0, 0, 1007, 758);
		panel.add(lblINFOLABEL);
		
		
	
		
	}
}
