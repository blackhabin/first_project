package edu.java.sonny.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import edu.java.sonny.controller.SonnyDaoImpl;

public class SonnyUnderStatsFrame extends JFrame {

	private JPanel contentPane;
	private Component parent;
	private SonMain app;

	private final SonnyDaoImpl dao = SonnyDaoImpl.getInstance();
	
	private JFrame frame;
	
	String totalMatches = String.valueOf(dao.totalMatches());
	String totalGoal = String.valueOf(dao.totalGoal());
	String goalsPerMatch = String.valueOf(dao.goalsPerMatch());
	String totalShots = String.valueOf(dao.totalShots());
	String shotsPerMatch = String.valueOf(dao.shotsPerMatch());
	String totalShotsOnTarget = String.valueOf(dao.totalShotsOnTarget());
	String shootingAccuracy = String.valueOf(dao.shootingAccuracy());
	String totalAssists = String.valueOf(dao.totalAssists());
	String totalPasses = String.valueOf(dao.totalPasses());
	String passesPerMatch = String.valueOf(dao.passPerMatch());
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextArea textTotalGoal;
	private JTextArea textGoalsPerMatch;
	private JTextArea textTotalShots;
	private JTextArea textShotsPerMatch;
	private JTextArea textShotsOnTarget;
	private JLabel lblNewLabel_5;
	private JTextArea textShootingAccuracy;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_1_1;
	private JTextArea textTotalAssists;
	private JTextArea textTotalPasses;
	private JButton btnNewButton;
	private JLabel lblNewLabel_2_1_1;
	private JTextArea textPassesPerMatch;
	private JLabel lblNewLabel_6;
	private JTextArea textTotalMatches;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;

	/**
	 * Launch the application.
	 */
	public static void showSonnyUnderStatsFrame(Component parent, SonMain app) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SonnyUnderStatsFrame frame = new SonnyUnderStatsFrame(parent, app);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SonnyUnderStatsFrame(Component parent, SonMain app) {
		this.parent = parent;
		this.app = app;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
setTitle("세부 스탯");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 100;
		int y = 100;
		if(parent != null) {
			x = parent.getX();
			y = parent.getY();
		}
		
		setBounds(x, y, 668, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("총 득점");
		lblNewLabel.setFont(new Font("DialogInput", Font.BOLD, 18));
		lblNewLabel.setBounds(12, 182, 151, 42);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("경기당 득점율");
		lblNewLabel_1.setFont(new Font("DialogInput", Font.BOLD, 18));
		lblNewLabel_1.setBounds(12, 257, 151, 42);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("전체 슈팅수");
		lblNewLabel_2.setFont(new Font("DialogInput", Font.BOLD, 18));
		lblNewLabel_2.setBounds(12, 333, 151, 42);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("경기당 평균슈팅");
		lblNewLabel_3.setFont(new Font("DialogInput", Font.BOLD, 18));
		lblNewLabel_3.setBounds(12, 405, 151, 42);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("전체 유효슈팅");
		lblNewLabel_4.setFont(new Font("DialogInput", Font.BOLD, 18));
		lblNewLabel_4.setBounds(12, 478, 151, 42);
		contentPane.add(lblNewLabel_4);
		
		textTotalGoal = new JTextArea();
		textTotalGoal.setBounds(178, 187, 105, 39);
		textTotalGoal.append(totalGoal +" 골");
		textTotalGoal.setOpaque(false);
		textTotalGoal.setFont(new Font("Dialog", Font.BOLD, 21));
		contentPane.add(textTotalGoal);
		
		textGoalsPerMatch = new JTextArea();
		textGoalsPerMatch.setBounds(178, 262, 105, 39);
		textGoalsPerMatch.append(goalsPerMatch + " %");
		textGoalsPerMatch.setOpaque(false);
		textGoalsPerMatch.setFont(new Font("Dialog", Font.BOLD, 21));
		contentPane.add(textGoalsPerMatch);
		
		textTotalShots = new JTextArea();
		textTotalShots.setBounds(178, 338, 105, 39);
		textTotalShots.append(totalShots + " 슈팅");
		textTotalShots.setOpaque(false);
		textTotalShots.setFont(new Font("Dialog", Font.BOLD, 21));
		contentPane.add(textTotalShots);
		
		textShotsPerMatch = new JTextArea();
		textShotsPerMatch.setBounds(178, 410, 105, 39);
		textShotsPerMatch.append(shotsPerMatch + " 개");
		textShotsPerMatch.setOpaque(false);
		textShotsPerMatch.setFont(new Font("Dialog", Font.BOLD, 21));
		contentPane.add(textShotsPerMatch);
		
		textShotsOnTarget = new JTextArea();
		textShotsOnTarget.setBounds(178, 483, 105, 39);
		textShotsOnTarget.append(totalShotsOnTarget + " 슈팅");
		textShotsOnTarget.setOpaque(false);
		textShotsOnTarget.setFont(new Font("Dialog", Font.BOLD, 21));
		contentPane.add(textShotsOnTarget);
		
		lblNewLabel_5 = new JLabel("총 어시스트");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_5.setBounds(341, 107, 151, 42);
		contentPane.add(lblNewLabel_5);
		
		textShootingAccuracy = new JTextArea();
		textShootingAccuracy.setBounds(178, 549, 105, 39);
		textShootingAccuracy.append(shootingAccuracy + " %");
		textShootingAccuracy.setOpaque(false);
		textShootingAccuracy.setFont(new Font("Dialog", Font.BOLD, 21));
		contentPane.add(textShootingAccuracy);
		
		lblNewLabel_2_1 = new JLabel("슈팅정확도");
		lblNewLabel_2_1.setFont(new Font("DialogInput", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(12, 544, 151, 42);
		contentPane.add(lblNewLabel_2_1);
		
		lblNewLabel_1_1 = new JLabel("전체 패스수");
		lblNewLabel_1_1.setFont(new Font("DialogInput", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(341, 182, 151, 42);
		contentPane.add(lblNewLabel_1_1);
		
		textTotalAssists = new JTextArea();
		textTotalAssists.setBounds(507, 112, 105, 39);
		textTotalAssists.append(totalAssists + " 도움");
		textTotalAssists.setOpaque(false);
		textTotalAssists.setFont(new Font("Dialog", Font.BOLD, 21));
		contentPane.add(textTotalAssists);
		
		textTotalPasses = new JTextArea();
		textTotalPasses.setBounds(507, 187, 105, 39);
		textTotalPasses.append(totalPasses + " 패스");
		textTotalPasses.setOpaque(false);
		textTotalPasses.setFont(new Font("Dialog", Font.BOLD, 21));
		contentPane.add(textTotalPasses);
		
		btnNewButton = new JButton("이전화면");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SonMain.showSonMain(frame, null);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 19));
		btnNewButton.setBounds(22, 641, 131, 52);
		contentPane.add(btnNewButton);
		
		lblNewLabel_2_1_1 = new JLabel("경기당 평균패스");
		lblNewLabel_2_1_1.setFont(new Font("DialogInput", Font.BOLD, 18));
		lblNewLabel_2_1_1.setBounds(341, 258, 151, 42);
		contentPane.add(lblNewLabel_2_1_1);
		
		textPassesPerMatch = new JTextArea();
		textPassesPerMatch.setBounds(507, 263, 105, 39);
		textPassesPerMatch.append(passesPerMatch + " 개");
		textPassesPerMatch.setOpaque(false);
		textPassesPerMatch.setFont(new Font("Dialog", Font.BOLD, 21));
		contentPane.add(textPassesPerMatch);
		
		lblNewLabel_6 = new JLabel("전체 경기수");
		lblNewLabel_6.setFont(new Font("DialogInput", Font.BOLD, 18));
		lblNewLabel_6.setBounds(12, 107, 151, 42);
		contentPane.add(lblNewLabel_6);
		
		textTotalMatches = new JTextArea();
		textTotalMatches.setBounds(178, 112, 105, 39);
		textTotalMatches.append(totalMatches + " 경기");
		textTotalMatches.setOpaque(false);
		textTotalMatches.setFont(new Font("Dialog", Font.BOLD, 21));
		contentPane.add(textTotalMatches);
		
		lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setForeground(new Color(89, 120, 183));
		lblNewLabel_9.setBackground(new Color(89, 120, 183));
		lblNewLabel_9.setBounds(312, 107, 3, 479);
		lblNewLabel_9.setOpaque(true);
		contentPane.add(lblNewLabel_9);
		
		lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setIcon(new ImageIcon("images\\02main.jpg"));
		lblNewLabel_7.setBounds(290, 265, 333, 456);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("images\\whitemain.jpg"));
		lblNewLabel_8.setBounds(0, 0, 652, 721);
		contentPane.add(lblNewLabel_8);
		
		
		
		
		
	}
}
