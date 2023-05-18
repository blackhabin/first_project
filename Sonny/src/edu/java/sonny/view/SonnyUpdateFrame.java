package edu.java.sonny.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import edu.java.sonny.controller.SonnyDaoImpl;
import edu.java.sonny.model.Sonny;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SonnyUpdateFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblSeason;
	private JTextField textGoal;
	private JTextField textAssists;
	private JComboBox comboSeaseon;
	private JLabel lblpartcipation;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox comboHelper;
	private JRadioButton rbY;
	private JRadioButton rbN;
	private JTextField textShots;
	private JTextField textShotsOnTarget;
	private JTextField textPasses;
	private JLabel lblOpposingTeam;
	private JComboBox comboHelper_1;
	private JComboBox comboOpposintTeam;
	private JLabel lblWeather;
	private JComboBox comboWeather;
	private JLabel lblfootballBoot;
	private JComboBox comboBoot;
	private JPanel panel_1;

	private JButton btnupdate;
	private JButton btncancle;
	private JLabel lblShotsOnTarget_1;
	private JLabel lblPasses_1;
	private JLabel lblShots_1;
	private JLabel lblHelper_1;
	private JLabel lblAssists_1;
	private JLabel lblGoal_1;
		
	private Component parent;
	private int sid;
	private SonMain app;

	private final SonnyDaoImpl dao = SonnyDaoImpl.getInstance();
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	
	

	/**
	 * Launch the application.
	 */
	public static void showSonnyUpdateFrame(Component parent, int sid, SonMain app) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SonnyUpdateFrame frame = new SonnyUpdateFrame(parent, sid, app);
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
	public SonnyUpdateFrame(Component parent, int sid, SonMain app) {
		this.parent = parent;
		this.sid = sid;
		this.app = app;
		initialize();
		readSonny();
	}

	// 저장된 값을 읽어온다.
	private void readSonny() {
		
		Sonny son = dao.read(sid);
		
		comboSeaseon.setSelectedItem(son.getFootballSeason());
		textGoal.setText(son.getGoals());
		textAssists.setText(son.getAssists());
		comboHelper.setSelectedItem(son.getHelper());
		comboOpposintTeam.setSelectedItem(son.getOpposingTeam());
		comboWeather.setSelectedItem(son.getWeather());
		comboBoot.setSelectedItem(son.getFootballBoot());
		textPasses.setText(son.getPasses());
		textShots.setText(son.getShots());
		textShotsOnTarget.setText(son.getShotsOnTarget());
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("경기스탯 수정");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int x = 100;
		int y = 100;
		if(parent != null) {
			x = parent.getX();
			y = parent.getY();
		}		
		
		setBounds(x, y, 971, 785);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblSeason = new JLabel("시즌");
		lblSeason.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeason.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSeason.setBounds(12, 10, 97, 43);
		panel.add(lblSeason);
		
		comboSeaseon = new JComboBox();
		comboSeaseon.setFont(new Font("Dialog", Font.BOLD, 16));
		String [] seasons = {"22/23","21/22", "20/21", "19/20"};
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(seasons);
		comboSeaseon.setBounds(145, 10, 127, 37);
		comboSeaseon.setModel(model);
		panel.add(comboSeaseon);
		
		lblpartcipation = new JLabel("출전여부");
		lblpartcipation.setHorizontalAlignment(SwingConstants.CENTER);
		lblpartcipation.setFont(new Font("Dialog", Font.BOLD, 20));
		lblpartcipation.setBounds(12, 63, 97, 43);
		panel.add(lblpartcipation);
		
		rbY = new JRadioButton("Y");
		rbY.setSelected(true);
		buttonGroup.add(rbY);
		rbY.setFont(new Font("Dialog", Font.BOLD, 12));
		rbY.setBounds(153, 66, 53, 43);
		panel.add(rbY);
		rbY.setOpaque(false);
		
		rbN = new JRadioButton("N");
		buttonGroup.add(rbN);
		rbN.setFont(new Font("Dialog", Font.BOLD, 12));
		rbN.setBounds(209, 66, 53, 43);
		panel.add(rbN);
		rbN.setOpaque(false);
		
		comboHelper = new JComboBox();
		comboHelper.setFont(new Font("Dialog", Font.BOLD, 16));
		String [] helper = {"없음", "해리케인", "올리버스킵", "호이비에르", "산체스", "히샬리송", "에메르송",
				"페리시치", "다이어", "로메로", "세세뇽", "클루셉스키", "포로", "탕강가", "모우라", "사르",
				"벤탕쿠르", "벤데이비스", "랑글레", "비수마", "요리스", "레길론", "윙크스", "브라이언힐",
				"로셀소", "조로던", "은돔벨레"};
		model = new DefaultComboBoxModel<>(helper);
		comboHelper.setBounds(145, 222, 127, 43);
		comboHelper.setModel(model);
		panel.add(comboHelper);
				
		
		
		lblGoal_1 = new JLabel("골");
		lblGoal_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGoal_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblGoal_1.setBounds(12, 116, 97, 43);
		panel.add(lblGoal_1);
		
		textGoal = new JTextField();
		textGoal.setColumns(10);
		textGoal.setBounds(145, 116, 127, 40);
		panel.add(textGoal);
		
		lblAssists_1 = new JLabel("어시스트");
		lblAssists_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssists_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblAssists_1.setBounds(12, 169, 97, 43);
		panel.add(lblAssists_1);
		
		textAssists = new JTextField();
		textAssists.setColumns(10);
		textAssists.setBounds(145, 166, 127, 40);
		panel.add(textAssists);
		
		lblHelper_1 = new JLabel("헬퍼");
		lblHelper_1.setForeground(new Color(0, 0, 0));
		lblHelper_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelper_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblHelper_1.setBounds(12, 222, 97, 43);
		panel.add(lblHelper_1);
		
		lblShots_1 = new JLabel("슈팅");
		lblShots_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblShots_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblShots_1.setBounds(12, 275, 97, 43);
		panel.add(lblShots_1);
		
		textShots = new JTextField();
		textShots.setColumns(10);
		textShots.setBounds(145, 280, 127, 40);
		panel.add(textShots);
		
		lblShotsOnTarget_1 = new JLabel("유효슈팅");
		lblShotsOnTarget_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblShotsOnTarget_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblShotsOnTarget_1.setBounds(12, 328, 97, 43);
		panel.add(lblShotsOnTarget_1);
		
		textShotsOnTarget = new JTextField();
		textShotsOnTarget.setColumns(10);
		textShotsOnTarget.setBounds(145, 328, 127, 40);
		panel.add(textShotsOnTarget);
		
		lblPasses_1 = new JLabel("패스");
		lblPasses_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasses_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPasses_1.setBounds(12, 381, 97, 43);
		panel.add(lblPasses_1);
		
		textPasses = new JTextField();
		textPasses.setColumns(10);
		textPasses.setBounds(145, 381, 127, 40);
		panel.add(textPasses);
		
		lblOpposingTeam = new JLabel("상대팀");
		lblOpposingTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpposingTeam.setForeground(new Color(0, 0, 0));
		lblOpposingTeam.setFont(new Font("Dialog", Font.BOLD, 20));
		lblOpposingTeam.setBounds(12, 431, 97, 43);
		panel.add(lblOpposingTeam);
		
		comboOpposintTeam = new JComboBox();
		comboOpposintTeam.setFont(new Font("Dialog", Font.BOLD, 16));
		String [] opposinTeam = {"아스날","맨체스터시티", "맨체스터유나이티드", "뉴캐슬", "아스톤빌라", "브라이튼"
				,"리버풀", "브렌트포드", "풀럼", "첼시", "크리스탈펠리스", "울버햄튼", "본머스", "웨스트햄",
				"리즈", "에버턴", "노팅엄포레스트", "레스터", "사우스햄튼"};
		model = new DefaultComboBoxModel<>(opposinTeam);
		comboOpposintTeam.setModel(model);
		comboOpposintTeam.setBounds(145, 431, 127, 43);
		panel.add(comboOpposintTeam);
		
		lblWeather = new JLabel("날씨");
		lblWeather.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeather.setForeground(new Color(0, 0, 0));
		lblWeather.setFont(new Font("Dialog", Font.BOLD, 20));
		lblWeather.setBounds(12, 484, 97, 43);
		panel.add(lblWeather);
		
		comboWeather = new JComboBox();
		comboWeather.setFont(new Font("Dialog", Font.BOLD, 16));
		String [] weather = {"맑음", "흐림", "비", "눈"};
		model = new DefaultComboBoxModel<>(weather);
		comboWeather.setModel(model);
		comboWeather.setBounds(145, 484, 127, 43);
		panel.add(comboWeather);
		
		lblfootballBoot = new JLabel("축구화");
		lblfootballBoot.setHorizontalAlignment(SwingConstants.CENTER);
		lblfootballBoot.setForeground(new Color(0, 0, 0));
		lblfootballBoot.setFont(new Font("Dialog", Font.BOLD, 20));
		lblfootballBoot.setBounds(12, 537, 97, 43);
		panel.add(lblfootballBoot);
		
		comboBoot = new JComboBox();
		comboBoot.setFont(new Font("Dialog", Font.BOLD, 16));
		String [] boot = {"미출전", "퓨마", "나이키", "아디다스"};
		model = new DefaultComboBoxModel<>(boot);
		comboBoot.setModel(model);
		comboBoot.setBounds(145, 537, 127, 43);
		panel.add(comboBoot);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 613, 284, 121);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		
		btnupdate = new JButton("수정");
		btnupdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateSonny();
			}
		});
		btnupdate.setFont(new Font("Dialog", Font.BOLD, 22));
		btnupdate.setBounds(27, 37, 97, 49);
		panel_1.add(btnupdate);
		
		btncancle = new JButton("취소");
		btncancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btncancle.setFont(new Font("Dialog", Font.BOLD, 22));
		btncancle.setBounds(147, 37, 97, 49);
		panel_1.add(btncancle);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("images\\background.jpg"));
		lblNewLabel_3.setBounds(0, 0, 284, 736);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("images\\06main.jpg"));
		lblNewLabel_2.setBounds(0, 0, 945, 736);
		panel.add(lblNewLabel_2);
		
		
	}

	private void updateSonny() {
		StringBuffer buffer = new StringBuffer();
		String participation = "";	
		
		// 출전여부 확인
		if(rbY.isSelected()) {
			buffer.append(rbY.getText());
			participation = rbY.getText();
		} else {
			buffer.append(rbN.getText());
			participation = rbN.getText();
		}
		
		// 시즌
		String season = String.valueOf(comboSeaseon.getSelectedItem());

		// 축구화
		String footballBoot = String.valueOf(comboBoot.getSelectedItem());
		// 골
		String goal = textGoal.getText();

		// 도우미
		String helper = String.valueOf(comboHelper.getSelectedItem());
		
		// 어시스트
		String assists = textAssists.getText();
		
		// 슈팅
		String shots = textShots.getText();
		//유효슈팅
		String shotsOnTarget = textShotsOnTarget.getText();
		
		//패스
		String passes = textPasses.getText();
		
		// 상대팀
		String opposingTeam = String.valueOf(comboOpposintTeam.getSelectedItem());
				
		// 날씨
		String weather = String.valueOf(comboWeather.getSelectedItem());
		
		
		
		int intShots = 0;
		int intShotsOnTarget = 0;
		int intGoals = 0;
		int intPasses = 0;
		int intAssists = 0;
		
		try  {
		
		intShots = Integer.parseInt(textShots.getText());
		intShotsOnTarget = Integer.parseInt(textShotsOnTarget.getText());
		intGoals = Integer.parseInt(textGoal.getText());
		intPasses = Integer.parseInt(textPasses.getText());
		intAssists = Integer.parseInt(textAssists.getText());
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(parent, "정수로 입력해주세요");
			return;
		}
		
		if (intGoals == 0 && !helper.equals("없음")) {
			JOptionPane.showMessageDialog(parent, "골이 없는데 어떻게 도우미가 있냐");
			return;
		}
		
		if (intGoals > intShotsOnTarget) {
			JOptionPane.showMessageDialog(
					 parent,
					"득점 수는 유효 슈팅 수보다 많을 수 없습니다", 
					"입력 에러", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (intGoals > intShots) {
			JOptionPane.showMessageDialog(
					 parent,
					"득점 수는 슈팅 수보다 많을 수 없습니다", 
					"입력 에러", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (intShotsOnTarget > intShots) {
			JOptionPane.showMessageDialog(
					 parent,
					"유효슈팅 수는 슈팅 수보다 많을 수 없습니다", 
					"입력 에러", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (intAssists > intPasses) {
			JOptionPane.showMessageDialog(
					 parent,
					"도움 수는 패스 수보다 많을 수 없습니다", 
					"입력 에러", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (!participation.equals(rbY.getText())  && !participation.equals(rbN.getText())) {
			JOptionPane.showMessageDialog(
					 parent,
					"출전 여부를 체크해주세요", 
					"입력 에러", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		
		
		Sonny sonnyData = new Sonny(sid, season, goal, assists, shots, shotsOnTarget, passes, 
				opposingTeam, weather, footballBoot, helper, participation);
		
		int confirm = JOptionPane.showConfirmDialog(
				SonnyUpdateFrame.this, 
				"수정 할까요?", 
				"수정 되었습니다", 
				JOptionPane.YES_NO_OPTION
				);
		if (confirm == JOptionPane.YES_OPTION) {
			dao.update(sonnyData);
			app.notifySonnyUpdated();
			dispose();
		}
		
		
	}
}
