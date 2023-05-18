package edu.java.sonny.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.java.sonny.model.Sonny;
import edu.java.sonny.controller.SonnyDaoImpl;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.ButtonGroup;
public class SonnyCreateFrame extends JFrame {

	private final SonnyDaoImpl dao = SonnyDaoImpl.getInstance();
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
	private JButton btnCreateMatch;
	private JButton btnCancleMatch;
	private JLabel lblNewLabel_1;
	private JLabel lblcreatemini;
	private JLabel lblGoal;
	private JLabel lblAssists;
	private JLabel lblHelper;
	private JLabel lblShots;
	private JLabel lblShotsOnTarget;
	private JLabel lblPasses;
	
	private Component parent;
	private SonMain app;



	/**
	 * Launch the application.
	 */
	public static void showSonnyCreateFrame(Component parent, SonMain app) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SonnyCreateFrame frame = new SonnyCreateFrame(parent, app);
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
	public SonnyCreateFrame(Component parent, SonMain app) {
		this.parent = parent;
		this.app = app;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setTitle("새 경기 저장");
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
		rbY.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				textGoal.setText("");
				textAssists.setText("");
				textShots.setText("");
				textShotsOnTarget.setText("");
				textPasses.setText("");
				
			}
		});
		buttonGroup.add(rbY);
		rbY.setFont(new Font("Dialog", Font.BOLD, 12));
		rbY.setBounds(153, 66, 53, 43);
		panel.add(rbY);
		rbY.setOpaque(false);
		
		rbN = new JRadioButton("N");
		rbN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textGoal.setText("0");
				textAssists.setText("0");
				textShots.setText("0");
				textShotsOnTarget.setText("0");
				textPasses.setText("0");
//				comboHelp
			}
		});
		buttonGroup.add(rbN);
		rbN.setFont(new Font("Dialog", Font.BOLD, 12));
		rbN.setBounds(209, 66, 53, 43);
		panel.add(rbN);
		rbN.setOpaque(false);
		panel.setOpaque(false);
		
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
				
		
		
		lblGoal = new JLabel("골");
		lblGoal.setHorizontalAlignment(SwingConstants.CENTER);
		lblGoal.setFont(new Font("Dialog", Font.BOLD, 20));
		lblGoal.setBounds(12, 116, 97, 43);
		panel.add(lblGoal);
		
		textGoal = new JTextField();
		textGoal.setColumns(10);
		textGoal.setBounds(145, 116, 127, 40);
		panel.add(textGoal);
		
		lblAssists = new JLabel("어시스트");
		lblAssists.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssists.setFont(new Font("Dialog", Font.BOLD, 20));
		lblAssists.setBounds(12, 169, 97, 43);
		panel.add(lblAssists);
		
		textAssists = new JTextField();
		textAssists.setColumns(10);
		textAssists.setBounds(145, 166, 127, 40);
		panel.add(textAssists);
		
		lblHelper = new JLabel("헬퍼");
		lblHelper.setForeground(Color.BLACK);
		lblHelper.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelper.setFont(new Font("Dialog", Font.BOLD, 20));
		lblHelper.setBounds(12, 222, 97, 43);
		panel.add(lblHelper);
		
		lblShots = new JLabel("슈팅");
		lblShots.setHorizontalAlignment(SwingConstants.CENTER);
		lblShots.setFont(new Font("Dialog", Font.BOLD, 20));
		lblShots.setBounds(12, 275, 97, 43);
		panel.add(lblShots);
		
		textShots = new JTextField();
		textShots.setColumns(10);
		textShots.setBounds(145, 280, 127, 40);
		panel.add(textShots);
		
		lblShotsOnTarget = new JLabel("유효슈팅");
		lblShotsOnTarget.setHorizontalAlignment(SwingConstants.CENTER);
		lblShotsOnTarget.setFont(new Font("Dialog", Font.BOLD, 20));
		lblShotsOnTarget.setBounds(12, 328, 97, 43);
		panel.add(lblShotsOnTarget);
		
		textShotsOnTarget = new JTextField();
		textShotsOnTarget.setColumns(10);
		textShotsOnTarget.setBounds(145, 328, 127, 40);
		panel.add(textShotsOnTarget);
		
		lblPasses = new JLabel("패스");
		lblPasses.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasses.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPasses.setBounds(12, 381, 97, 43);
		panel.add(lblPasses);
		
		textPasses = new JTextField();
		textPasses.setColumns(10);
		textPasses.setBounds(145, 381, 127, 40);
		panel.add(textPasses);
		
		lblOpposingTeam = new JLabel("상대팀");
		lblOpposingTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpposingTeam.setForeground(Color.BLACK);
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
		lblWeather.setForeground(Color.BLACK);
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
		lblfootballBoot.setForeground(Color.BLACK);
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
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(0, 613, 284, 121);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		
		
		
		
		btnCreateMatch = new JButton("저장");
		btnCreateMatch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					createNewMatch();
					
			}
		});
		btnCreateMatch.setFont(new Font("Dialog", Font.BOLD, 20));
		btnCreateMatch.setBounds(57, 36, 77, 47);
		panel_1.add(btnCreateMatch);
				
		
		btnCancleMatch = new JButton("취소");
		btnCancleMatch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancleMatch.setFont(new Font("Dialog", Font.BOLD, 20));
		btnCancleMatch.setBounds(146, 36, 77, 47);
		panel_1.add(btnCancleMatch);
		
		lblcreatemini = new JLabel("");
		lblcreatemini.setIcon(new ImageIcon("images\\background.jpg"));
		lblcreatemini.setOpaque(true); 
		contentPane.setBackground(Color.DARK_GRAY);
		lblcreatemini.setForeground(new Color(192, 192, 192));
		lblcreatemini.setBounds(-13, -21, 323, 755);
		panel.add(lblcreatemini);
		
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("images\\03main.jpg"));
		lblNewLabel_1.setBounds(0, 0, 945, 734);
		panel.add(lblNewLabel_1);


	}


	private void createNewMatch() {
		
		StringBuffer buffer = new StringBuffer();

		// 출전여부(라디오버튼 저장할 변수
		String participation = "";	


		// 출전여부 확인
		if(rbY.isSelected()) {
			buffer.append(rbY.getText());
			participation = rbY.getText();
		} else if (rbN.isSelected()) {
			buffer.append(rbN.getText());
			participation = rbN.getText();
		}
		//TODO 어쩌라는거야 
		
		
		String footballSeason = (String) comboSeaseon.getSelectedItem();
		String goals = textGoal.getText();
		String helper = (String) comboHelper.getSelectedItem();
		// TODO 도우미 여러명일 가능성
		String assists = textAssists.getText();
		String shots = textShots.getText();
		String shotsOnTarget = textShotsOnTarget.getText();
		String passes = textPasses.getText();
		String opposingTeam = (String) comboOpposintTeam.getSelectedItem();
		String weather = (String) comboWeather.getSelectedItem();
		String footballBoot = (String) comboBoot.getSelectedItem();
		

		
		int intShots = 0;
		int intShotsOnTarget = 0;
		int intGoals = 0;
		int intPasses = 0;
		int intAssists = 0;
		
		try {
		intShots = Integer.parseInt(textShots.getText());
		intShotsOnTarget = Integer.parseInt(textShotsOnTarget.getText());
		intGoals = Integer.parseInt(textGoal.getText());
		intPasses = Integer.parseInt(textPasses.getText());
		intAssists = Integer.parseInt(textAssists.getText());
		} catch(Exception e) {
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
		
		if (!participation.equals(rbY.getText())  && !participation.equals(rbN.getText())){
			JOptionPane.showMessageDialog(
					 parent,
					"출전 여부를 체크해주세요", 
					"입력 에러", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		
		
		Sonny son = new Sonny(0, footballSeason, goals, assists, shots, shotsOnTarget, 
				passes, opposingTeam, weather, footballBoot, helper, participation);
		
		dao.create(son);
		
		app.notifySonnyCreated();
		
		dispose();
		
		
		
		
	}
}
