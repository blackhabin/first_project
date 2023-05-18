package edu.java.sonny.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import edu.java.sonny.model.Sonny;
import edu.java.sonny.controller.SonnyDaoImpl;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class SonMain extends JFrame {

	// 테이블 컬럼이름
	private static final String[] COLUMN_NAME = 
		{"시즌", "출전여부", "골", "도우미", "어시스트", "슈팅", "유효슈팅", "패스", "상대팀", "날씨", "축구화"};

	private JFrame frame;
	private JPanel buttonPanel;
	private JButton btnCreate;
	private JButton btnCreate_1;
	private JButton btnCreate_2;
	private JButton btnCreate_3;
	private JButton btnCreate_4;
	private JButton btnCreate_5;
	private JScrollPane scrollPane;
	
	private JTable table;
	private DefaultTableModel model;	
	private List<Sonny> sonnyLists;
	
	private Component parent;
	private Main apps;
	
	private final SonnyDaoImpl dao = SonnyDaoImpl.getInstance();
	
	private JLabel lblNewLabel;
	private JButton btnCreate_6;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void showSonMain(Component parent, Main apps) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SonMain window = new SonMain(parent, apps);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void notifySonnyUpdated() {
		resetTableModel();	// JTable을 처음부터 새로 그림.
		JOptionPane.showMessageDialog(frame, "경기 결과 업데이트 성공");

	}	
	
	// CreaterFrame에서 새 연락처 저장을 성공했을 때 호출할 메서드.
	public void notifySonnyCreated() {
		// JTabel을 새로 그림.
		resetTableModel();
		JOptionPane.showMessageDialog(frame, "경기 결과 저장 성공");
	}
	
	public void resetTableModel()	{
		// 데이터가 비워진 모델을 새로 생성
		model = new DefaultTableModel(null, COLUMN_NAME);
		// 파일에 저장된 데이터를 다시 읽고 테이블 모델에 추가.
		loadSonnyData();
		// 새롭게 만들어진 테이블 모델을 테이블에 세팅한다.
		table.setModel(model);

	}
	
	

	/**
	 * Create the application.
	 */
	public SonMain(Component parent, Main apps) {
		this.parent = parent;
		this.apps = apps;
		
		initialize();
		loadSonnyData();
	}
	
	public void loadSonnyData() {
		sonnyLists = dao.read();
		for (Sonny s : sonnyLists) {
			Object[] row = { s.getFootballSeason(), s.getParticipation(),
					s.getGoals(), s.getHelper(), s.getAssists(),
					s.getShots(), s.getShotsOnTarget(), s.getPasses(), 
					s.getOpposingTeam(), s.getWeather(), s.getFootballBoot()};
			model.addRow(row);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1073, 752);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Sonny");
		frame.getContentPane().setLayout(null);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 0, 1045, 81);
		frame.getContentPane().add(buttonPanel);
		
		btnCreate = new JButton("새 경기");
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SonnyCreateFrame.showSonnyCreateFrame(frame, SonMain.this);
			}
		});
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		// TODO
		
		btnCreate.setFont(new Font("Dialog", Font.BOLD, 20));
		buttonPanel.add(btnCreate);
		
		btnCreate_1 = new JButton("검색");
		btnCreate_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectSonnyByKeyword();
			}
		});
		
		btnCreate_2 = new JButton("수정");
		btnCreate_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateSonny();
			}
		});
		
		btnCreate_4 = new JButton("세부스탯");
		btnCreate_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SonnyUnderStatsFrame.showSonnyUnderStatsFrame(frame, SonMain.this);
				frame.setVisible(false);
			}
		});
		btnCreate_4.setFont(new Font("Dialog", Font.BOLD, 20));
		buttonPanel.add(btnCreate_4);
		btnCreate_2.setFont(new Font("Dialog", Font.BOLD, 20));
		buttonPanel.add(btnCreate_2);
		
		btnCreate_3 = new JButton("삭제");
		btnCreate_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteSonny();
			}
		});
		btnCreate_3.setFont(new Font("Dialog", Font.BOLD, 20));
		buttonPanel.add(btnCreate_3);
		btnCreate_1.setFont(new Font("Dialog", Font.BOLD, 20));
		buttonPanel.add(btnCreate_1);
		
		btnCreate_6 = new JButton("세부검색");
		btnCreate_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SonnySearchFrame.showSonnySearchFrame(frame, SonMain.this);
				frame.setVisible(false);
			}
		});
		btnCreate_6.setFont(new Font("Dialog", Font.BOLD, 20));
		buttonPanel.add(btnCreate_6);
		
		btnCreate_5 = new JButton("이전화면");
		btnCreate_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.main(null);
				frame.setVisible(false);
			}
		});
		btnCreate_5.setFont(new Font("Dialog", Font.BOLD, 20));
		buttonPanel.add(btnCreate_5);
				
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 87, 1045, 626);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Dialog", Font.PLAIN, 16));
		model = new DefaultTableModel(null, COLUMN_NAME); // TablModel 객체 생성
		table.setModel(model);
		
		scrollPane.setViewportView(table);

	}

	private void selectSonnyByKeyword() {
		String keyword = JOptionPane.showInputDialog(frame, "검색");	
		System.out.println("keyword= " + keyword);
		if (keyword == null) {
			JOptionPane.showMessageDialog(frame, "검색어를 입력하지 않으셨습니다.");
			return;
		}
		sonnyLists = dao.read(keyword);
		
		model = new DefaultTableModel(null, COLUMN_NAME);
		for (Sonny s : sonnyLists) {
			Object[] row = {s.getFootballSeason(), s.getParticipation(),
					s.getGoals(), s.getHelper(), s.getAssists(),
					s.getShots(), s.getShotsOnTarget(), s.getPasses(), 
					s.getOpposingTeam(), s.getWeather(), s.getFootballBoot()};
			model.addRow(row);
		}
		table.setModel(model);
	}

	protected void deleteSonny() {
		// 삭제 할 행 인덱스찾기
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showConfirmDialog(
					frame, 
					"삭제할 데이터를 선택해주세요.", 
					"경고", 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(
				frame, 
				"선택하신 정보를 정말 삭제합니까?", 
				"삭제 확인", 
				JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION) {
			int sid = sonnyLists.get(row).getSid();
			dao.delete(sid);
			model.removeRow(row);
			
			JOptionPane.showMessageDialog(frame, "삭제 완료");
		}
		
	}

	private void updateSonny() {
		int row = table.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(frame, 
					"수정할 행을 선택해주세요",
					"경고",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		int sid = sonnyLists.get(row).getSid();
		SonnyUpdateFrame.showSonnyUpdateFrame(frame, sid, SonMain.this);
	}
}
