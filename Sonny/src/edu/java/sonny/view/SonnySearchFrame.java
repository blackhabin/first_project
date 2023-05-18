package edu.java.sonny.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.java.sonny.model.Sonny;
import edu.java.sonny.controller.SonnyDaoImpl;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class SonnySearchFrame {

	private static final String[] COLUMN_NAME = { "시즌", "출전여부", "골", "도우미", "어시스트", "슈팅", "유효슈팅", "패스", "상대팀", "날씨",
			"축구화" };

	private List<Sonny> sonnyLists;
	private JFrame frame;
	private SonMain app;
	private Component parent;
	private final SonnyDaoImpl dao = SonnyDaoImpl.getInstance();
	private JButton btnSearch;
	private DefaultTableModel model2;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField textHelp;
	private JTextField textopp;
	private JTextField textweather;
	private JTextField textboot;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_2;
	private JButton btnSearch_1;

	/**
	 * Launch the application.
	 */
	public static void showSonnySearchFrame(Component parent, SonMain app) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SonnySearchFrame window = new SonnySearchFrame(parent, app);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void resetTableModel() {
		// 데이터가 비워진 모델을 새로 생성
		model2 = new DefaultTableModel(null, COLUMN_NAME);
		// 파일에 저장된 데이터를 다시 읽고 테이블 모델에 추가.
		loadSonnyData();
		// 새롭게 만들어진 테이블 모델을 테이블에 세팅한다.
		table.setModel(model2);

	}

	/**
	 * Create the application.
	 */
	public SonnySearchFrame(Component parent, SonMain app) {
		this.parent = parent;
		this.app = app;

		initialize();
		loadSonnyData();
	}

	public void loadSonnyData() {
		sonnyLists = dao.read();
		for (Sonny s : sonnyLists) {
			Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(), s.getAssists(),
					s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(), s.getWeather(),
					s.getFootballBoot() };
			model2.addRow(row);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1059, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(136, 4, 801, 76);
		frame.getContentPane().add(panel);
		String[] boot = {"퓨마", "나이키", "아디다스" };
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(boot);
		panel.setLayout(null);
		String[] helper = {"없음", "해리케인", "올리버스킵", "호이비에르", "산체스", "히샬리송", "에메르송",
				"페리시치", "다이어", "로메로", "세세뇽", "클루셉스키", "포로", "탕강가", "모우라", "사르",
				"벤탕쿠르", "벤데이비스", "랑글레", "비수마", "요리스", "레길론", "윙크스", "브라이언힐",
				"로셀소", "조로던", "은돔벨레"};
		model = new DefaultComboBoxModel<>(helper);
		String[] weather = { "맑음", "흐림", "비", "눈" };
		model = new DefaultComboBoxModel<>(weather);
		String[] opposinTeam =  {"아스날","맨체스터시티", "맨체스터유나이티드", "뉴캐슬", "아스톤빌라", "브라이튼"
				,"리버풀", "브렌트포드", "풀럼", "첼시", "크리스탈펠리스", "울버햄튼", "본머스", "웨스트햄",
				"리즈", "에버턴", "노팅엄포레스트", "레스터", "사우스햄튼"};
		model = new DefaultComboBoxModel<>(opposinTeam);

		textHelp = new JTextField();
		textHelp.setBounds(114, 9, 116, 22);
		panel.add(textHelp);
		textHelp.setColumns(10);

		btnSearch = new JButton("검색");
		btnSearch.setFont(new Font("Dialog", Font.BOLD, 18));
		btnSearch.setBounds(504, 6, 137, 44);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchMethod();
			}
		});
		panel.add(btnSearch);

		textopp = new JTextField();
		textopp.setBounds(114, 41, 116, 22);
		panel.add(textopp);
		textopp.setColumns(10);

		textweather = new JTextField();
		textweather.setColumns(10);
		textweather.setBounds(344, 9, 116, 22);
		panel.add(textweather);

		textboot = new JTextField();
		textboot.setColumns(10);
		textboot.setBounds(344, 41, 116, 22);
		panel.add(textboot);

		lblNewLabel = new JLabel("도우미");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(38, 12, 64, 19);
		panel.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("상대팀");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setBounds(38, 44, 64, 19);
		panel.add(lblNewLabel_1);

		lblNewLabel_1_1 = new JLabel("축구화");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(268, 44, 64, 19);
		panel.add(lblNewLabel_1_1);

		lblNewLabel_2 = new JLabel("날씨");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_2.setBounds(268, 12, 64, 19);
		panel.add(lblNewLabel_2);
		
		btnSearch_1 = new JButton("이전화면");
		btnSearch_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SonMain.showSonMain(parent, null);
				frame.setVisible(false);
			}
		});
		btnSearch_1.setFont(new Font("Dialog", Font.BOLD, 18));
		btnSearch_1.setBounds(653, 6, 137, 44);
		panel.add(btnSearch_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 104, 1045, 487);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Dialog", Font.PLAIN, 16));
		model2 = new DefaultTableModel(null, COLUMN_NAME); // TablModel 객체 생성
		table.setModel(model2);

		scrollPane.setViewportView(table);

	}

	private void searchMethod() {
		String helper = textHelp.getText();
		String opposingteam = textopp.getText();
		String weather = textweather.getText();
		String footballboot = textboot.getText();


		
		// tfff
		if (!helper.isBlank() && opposingteam.isBlank() && weather.isBlank() && footballboot.isBlank()) {

			sonnyLists = dao.readOnlyHelper(helper);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);

			// tftf
		} else if (!helper.isBlank() && opposingteam.isBlank() && !weather.isBlank() && footballboot.isBlank()) {

			sonnyLists = dao.readHelperWeather(helper, weather);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);

			// tfft
		} else if (!helper.isBlank() && opposingteam.isBlank() && weather.isBlank() && !footballboot.isBlank()) {

			sonnyLists = dao.readHelperFootballboot(helper, footballboot);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);

			// tftt
		} else if (!helper.isBlank() && opposingteam.isBlank() && !weather.isBlank() && !footballboot.isBlank()) {

			sonnyLists = dao.readHelperWeatherFootballboot(helper, weather, footballboot);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);

			// ttff
		} else if (!helper.isBlank() && !opposingteam.isBlank() && weather.isBlank() && footballboot.isBlank()) {

			sonnyLists = dao.readHelperOpposingTeam(helper, opposingteam);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);

			// tttf
		} else if (!helper.isBlank() && !opposingteam.isBlank() && !weather.isBlank() && footballboot.isBlank()) {

			sonnyLists = dao.readHelperOpposingTeamWeather(helper, opposingteam, weather);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);

			// ttft
		} else if (!helper.isBlank() && !opposingteam.isBlank() && weather.isBlank() && !footballboot.isBlank()) {

			sonnyLists = dao.readHelperOpposingTeamFootballboot(helper, opposingteam, footballboot);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);

			// tttt
		} else if (!helper.isBlank() && !opposingteam.isBlank() && !weather.isBlank() && !footballboot.isBlank()) {

			sonnyLists = dao.readHelperOpposingTeamWeatherFootballboot(helper, opposingteam, weather, footballboot);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);

			// fttt
		} else if (helper.isBlank() && !opposingteam.isBlank() && !weather.isBlank() && !footballboot.isBlank()) {

			sonnyLists = dao.readOpposingTeamWeatherFootballboot(opposingteam, weather, footballboot);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);

			// fttf
		} else if (helper.isBlank() && !opposingteam.isBlank() && !weather.isBlank() && footballboot.isBlank()) {

			sonnyLists = dao.readOpposingTeamWeather(opposingteam, weather);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);

			// ftft
		} else if (helper.isBlank() && !opposingteam.isBlank() && weather.isBlank() && !footballboot.isBlank()) {

			sonnyLists = dao.readOpposingTeamFootballboot(opposingteam, footballboot);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);

			// ftff
		} else if (helper.isBlank() && !opposingteam.isBlank() && weather.isBlank() && footballboot.isBlank()) {

			sonnyLists = dao.readOnlyOpposingTeam(opposingteam);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);
		
			//fftt
		} else if (helper.isBlank() && opposingteam.isBlank() && !weather.isBlank() && !footballboot.isBlank())  {

			sonnyLists = dao.readWeatherFootballboot(weather, footballboot);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);			
		
			//fftf
		} else if (helper.isBlank() && opposingteam.isBlank() && !weather.isBlank() && footballboot.isBlank())  {

			sonnyLists = dao.readOnlyWeather(weather);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);		
			
			//ffft
		}else if (helper.isBlank() && opposingteam.isBlank() && weather.isBlank() && !footballboot.isBlank())  {

			sonnyLists = dao.readOnlyFootballboot(footballboot);

			model2 = new DefaultTableModel(null, COLUMN_NAME);
			for (Sonny s : sonnyLists) {
				Object[] row = { s.getFootballSeason(), s.getParticipation(), s.getGoals(), s.getHelper(),
						s.getAssists(), s.getShots(), s.getShotsOnTarget(), s.getPasses(), s.getOpposingTeam(),
						s.getWeather(), s.getFootballBoot() };
				model2.addRow(row);
			}
			table.setModel(model2);		
		// ffff
		} else {
			JOptionPane.showMessageDialog(
					parent, 
					"검색어를 입력해주세요", 
					"입력 에러", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
