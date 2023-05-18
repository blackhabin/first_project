package edu.java.sonny.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.java.sonny.model.Sonny;
import oracle.jdbc.OracleDriver;

import static edu.java.sonny.model.Sonny.Entity.*;
import static edu.java.jdbc.oracle.OracleConnect.*;

public class SonnyDaoImpl implements SonnyDao {					
	// singleton 파일유틸 뺀다
	private static SonnyDaoImpl instance = null; 
	private SonnyDaoImpl() {}	 
	
	public static SonnyDaoImpl getInstance() {   
		if(instance == null) {
			instance = new SonnyDaoImpl();
		}
		
		return instance;
	}
	
	// 오라클 db에 있는 connection 객체 리턴
	private Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
		return conn;
	}
	
	private void closeResources(Connection conn, Statement stmt) throws SQLException { 	
		stmt.close();
		conn.close();
		
	}
	
	private void closeResources(Connection conn, Statement stmt, ResultSet rs) 	
			throws SQLException {
		rs.close();
		closeResources(conn, stmt);
	}


	/**
	 * 어떤 인덱스가 검색 ,수정, 삭제할 때 사용 가능한 범위 안에 있는 인덱스인 지를 리턴해주는 메서드.
	 * @param index 유효한 인덱스인지 검사할 인데스
	 * @return 사용가능한 인덱스는 true, 그렇지 않으면 false.
	 */
	
	
	
	// 경기기록 쏘니 데이터 삽입
	private static final String SQL_INSERT =
			"insert into " +
			TBL_NAME +
			" (" +
			COL_FOOTBALLSEASON +
			", " +
			COL_PARTICIPATION +
			", " +
			COL_GOALS +
			", " +
			COL_ASSISTS +
			", " +
			COL_SHOTS +
			", " +
			COL_SHOTSONTARGET +
			", " +
			COL_PASSES +
			", " +
			COL_HELPER +
			", " +
			COL_OPPOSINGTEAM +
			", " +
			COL_WEATHER +
			", " +
			COL_FOOTBALLBOOT +
			") " +
			" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	
	@Override
	public int create(Sonny s) {
		int result = 0; // 입력한 결과를 저장 후 리턴할 변수
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			System.out.println(SQL_INSERT); // 확인 작업
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, s.getFootballSeason());
			stmt.setString(2, s.getParticipation());
			stmt.setString(3, s.getGoals()); // 요자리에 삼항
			stmt.setString(4, s.getAssists());
			stmt.setString(5, s.getShots());
			stmt.setString(6, s.getShotsOnTarget());
			stmt.setString(7, s.getPasses());
			stmt.setString(8, s.getHelper());
			stmt.setString(9, s.getOpposingTeam());
			stmt.setString(10, s.getWeather());
			stmt.setString(11, s.getFootballBoot());
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	
	// sid 순으로 sonny 테이블로부터 전체 조회
	private static final String SQL_SELECT_ALL =
			"select * from " + TBL_NAME + " order by " + COL_SID;
	@Override
	public List<Sonny> read() {
		LinkedList<Sonny> list = new LinkedList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL);
			System.out.println(SQL_SELECT_ALL);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, shots, 
						shotsOnTarget, passes, opposingTeam, weather, footballBoot, 
						helper, participation);
				list.add(son);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	
	
	// sid를 통한 조회(수정시 클릭한 데이터를 그대로 가져와야해서 필요)
	
	private static final String SQL_SELECT_BY_ID =
			"select * from " + TBL_NAME + " where " + COL_SID + " = ?";
	@Override
	public Sonny read(int sid) {
		Sonny son = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_BY_ID); // 확인 작업
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1, sid);
			rs = stmt.executeQuery();
			if(rs.next()) {
				int id = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				son = new Sonny(id, footballseason, goals, assists, shots, 
						shotsOnTarget, passes, opposingTeam, weather, 
						footballBoot, helper, participation);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return son;		
	}
	
	// 클릭시 클릭한 sid의 데이터를 조건으로 받아 수정한다.
	private static final String SQL_UPDATE = 
			"update " +
			TBL_NAME +
			" set " +
			COL_FOOTBALLSEASON + " = ?, " +
			COL_PARTICIPATION + " = ?, " +
			COL_GOALS + " = ?, " +
			COL_ASSISTS + " = ?, " +
			COL_SHOTS + " = ?, " +
			COL_SHOTSONTARGET + " = ?, " +
			COL_PASSES + " = ?, " +
			COL_HELPER + " = ?, " +
			COL_OPPOSINGTEAM + " = ?, " +
			COL_WEATHER + " = ?, " +
			COL_FOOTBALLBOOT + " = ? " +
			" where " + COL_SID + " = ?";
	
	@Override
	public int update(Sonny sonnyDate) {
		int result = 0; // 수정결과 저장하고 리턴.
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_UPDATE);
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, sonnyDate.getFootballSeason());
			stmt.setString(2, sonnyDate.getParticipation());
			stmt.setString(3, sonnyDate.getGoals());
			stmt.setString(4, sonnyDate.getAssists());
			stmt.setString(5, sonnyDate.getShots());
			stmt.setString(6, sonnyDate.getShotsOnTarget());
			stmt.setString(7, sonnyDate.getPasses());
			stmt.setString(8, sonnyDate.getHelper());
			stmt.setString(9, sonnyDate.getOpposingTeam());
			stmt.setString(10, sonnyDate.getWeather());
			stmt.setString(11, sonnyDate.getFootballBoot());
			stmt.setInt(12, sonnyDate.getSid());
			result = stmt.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
		
	}
	
	// 클릭하면 해당 데이터의 sid를 조건으로 받아서 삭제
	private static final String SQL_DELETE = 
			"delete from " + TBL_NAME
			+ " where " + COL_SID + " = ?";
	
	@Override
	public int delete(int sid) {
		int result = 0; // 삭제 결과를 저장하고 리턴할 변수.
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			conn = getConnection();
			System.out.println(SQL_DELETE);
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, sid);
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
		
	}

	
	// 키워드를 통한 검색 
	// TODO 각 컬럼별로 검색하고 싶은데 해결하자
	private static final String SQL_SELECT_BY_KEYWORD =
			"select * from SONNY " +
			" where lower(FOOTBALLSEASON) like lower(?) " +
			" or lower(PARTICIPATION) like lower(?) " +
			" or lower(GOALS) like lower(?) " +
			" or lower(ASSISTS) like lower(?) " +
			" or lower(SHOTS) like lower(?) " +
			" or lower(SHOTSONTARGET) like lower(?) " +
			" or lower(PASSES) like lower(?) " +
			" or lower(HELPER) like lower(?) " +
			" or lower(OPPOSINGTEAM) like lower(?) " +
			" or lower(WEATHER) like lower(?) " +
			" or lower(FOOTBALLBOOT) like lower(?) " +
			" order by SID";
	// TODO 우선 이렇게 해놓는데 숫자로 입력받는 값은 저거 필요없을거로 예상
	@Override
	public List<Sonny> read(String keyword) {
		ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_BY_KEYWORD); // 확인작업
			stmt = conn.prepareStatement(SQL_SELECT_BY_KEYWORD);
			
			String key = "%" + keyword + "%";
			System.out.println("keyword = " + keyword + ", key = " + key);
			
			stmt.setString(1, key);
			stmt.setString(2, key);
			stmt.setString(3, key);
			stmt.setString(4, key);
			stmt.setString(5, key);
			stmt.setString(6, key);
			stmt.setString(7, key);
			stmt.setString(8, key);
			stmt.setString(9, key);
			stmt.setString(10, key);
			stmt.setString(11, key);
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				list.add(son);
						
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	
	//전체 경기수
	private static final String SQL_SELECT_TOTALMATCHES =
			"SELECT COUNT(SID) FROM SONNY WHERE PARTICIPATION = 'Y'";
	
	@Override
	public int totalMatches() {
		int result = 0;
		

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_TOTALMATCHES); 
			stmt = conn.prepareStatement(SQL_SELECT_TOTALMATCHES);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}	
	
	// 총 득점
	private static final String SQL_SELECT_TOTALGOAL =
			"SELECT ROUND(SUM(GOALS),3) FROM SONNY WHERE PARTICIPATION = 'Y'";
	
	@Override
	public int totalGoal() {
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_TOTALGOAL); 
			stmt = conn.prepareStatement(SQL_SELECT_TOTALGOAL);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	};

	// 경기당 득점율
	private static final String SQL_SELECT_GOALSPERMATCH =
			"SELECT ROUND(AVG(GOALS), 2) FROM SONNY WHERE PARTICIPATION = 'Y'";
	
	@Override
	public double goalsPerMatch() {
		double result = 0;
		

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_GOALSPERMATCH); 
			stmt = conn.prepareStatement(SQL_SELECT_GOALSPERMATCH);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getDouble(1);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	

	//전체 슈팅수
	private static final String SQL_SELECT_TOTALSHOTS =
			"SELECT SUM(SHOTS) FROM SONNY WHERE PARTICIPATION = 'Y'";
	
	@Override
	public int totalShots() {
		int result = 0;
		

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_TOTALSHOTS); 
			stmt = conn.prepareStatement(SQL_SELECT_TOTALSHOTS);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 경기당 평균 슈팅
	private static final String SQL_SELECT_SHOTSPERMATCH =
			"SELECT ROUND(AVG(SHOTS), 2) FROM SONNY WHERE PARTICIPATION = 'Y'";
	
	@Override
	public double shotsPerMatch() {
		double result = 0.0;
		

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_SHOTSPERMATCH); 
			stmt = conn.prepareStatement(SQL_SELECT_SHOTSPERMATCH);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getDouble(1);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	
	// 전체 유효슈팅
	private static final String SQL_SELECT_TOTALSHOTSONTARGET =
			"SELECT SUM(SHOTSONTARGET) FROM SONNY WHERE PARTICIPATION = 'Y'";
	
	@Override
	public int totalShotsOnTarget() {
		int result = 0;
		

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_TOTALSHOTSONTARGET); 
			stmt = conn.prepareStatement(SQL_SELECT_TOTALSHOTSONTARGET);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 슈팅 정확도
	private static final String SQL_SELECT_SHOOTINGACCURACY =
			"SELECT ROUND(SUM(SHOTSONTARGET)/SUM(SHOTS), 2) FROM SONNY WHERE PARTICIPATION = 'Y'";
	
	@Override
	public double shootingAccuracy() {
		double result = 0;
		

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_SHOOTINGACCURACY); 
			stmt = conn.prepareStatement(SQL_SELECT_SHOOTINGACCURACY);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getDouble(1);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 전체 도움수
	private static final String SQL_SELECT_TOTALASSISTS =
			"SELECT SUM(ASSISTS) FROM SONNY WHERE PARTICIPATION = 'Y'";
	
	@Override
	public int totalAssists() {
		int result = 0;
		

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_TOTALASSISTS); 
			stmt = conn.prepareStatement(SQL_SELECT_TOTALASSISTS);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	
	// 전체 패스수
	private static final String SQL_SELECT_TOTALPASSES =
			"SELECT SUM(PASSES) FROM SONNY WHERE PARTICIPATION = 'Y'";
	
	@Override
	public int totalPasses() {
		int result = 0;
		

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_TOTALPASSES); 
			stmt = conn.prepareStatement(SQL_SELECT_TOTALPASSES);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}
	
	
	// 경기당 평균 패스
	private static final String SQL_SELECT_PASSESPERMATCH =
			"SELECT ROUND(AVG(PASSES), 2) FROM SONNY WHERE PARTICIPATION = 'Y'";
	
	@Override
	public double passPerMatch() {
		double result = 0;
		

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT_PASSESPERMATCH); 
			stmt = conn.prepareStatement(SQL_SELECT_PASSESPERMATCH);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getDouble(1);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	
	}
	
	// tfff(헬퍼만 참)
	private static final String SQL_SELECT_KEYWORD_OnlyHelper =
			"select * from SONNY WHERE HELPER  like ? " + " order by SID";
	
	@Override
	public List<Sonny> readOnlyHelper(String keyword) {
		ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_OnlyHelper);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_OnlyHelper);
			
			String key = "%" + keyword + "%";
			System.out.println("keyword = " + keyword + ", key = " + key);
			
			stmt.setString(1, key);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	
	//tftf 헬퍼 날씨만 참
	private static final String SQL_SELECT_KEYWORD_HelperWeather =
			"select * from SONNY WHERE HELPER like ? and " 
					+ " Weather like ?"
					+ " order by SID";
	@Override
	public List<Sonny> readHelperWeather(String keyword1, String keyword2) {
		ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_HelperWeather);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_HelperWeather);
			
			String key1 = "%" + keyword1 + "%";
			String key2 = "%" + keyword2 + "%";
			System.out.println("keyword1 = " + keyword1 + ", key1 = " + key1);
			System.out.println("keyword2 = " + keyword2 + ", key2 = " + key2);
			
			stmt.setString(1, key1);
			stmt.setString(2, key2);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// tfft 헐퍼 축구화만 참
	private static final String SQL_SELECT_KEYWORD_HelperFootballBoot =
			"select * from SONNY WHERE HELPER like ? and " 
					+ " FOOTBALLBOOT like ?"
					+ " order by SID";
	@Override
	public List<Sonny> readHelperFootballboot(String keyword1, String keyword2) {
		ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_HelperFootballBoot);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_HelperFootballBoot);
			
			String key1 = "%" + keyword1 + "%";
			String key2 = "%" + keyword2 + "%";
			System.out.println("keyword1 = " + keyword1 + ", key1 = " + key1);
			System.out.println("keyword2 = " + keyword2 + ", key2 = " + key2);
			
			stmt.setString(1, key1);
			stmt.setString(2, key2);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	//tftt
	private static final String SQL_SELECT_KEYWORD_HelperWeatherFootballboot =
			"select * from SONNY WHERE HELPER like ? and " 
					+ " WEATHER like ? and "
					+ " FOOTBALLBOOT like ? "
					+ " order by SID";
	@Override
	public List<Sonny> readHelperWeatherFootballboot(String keyword1, String keyword2, String keyword3) {
		ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_HelperWeatherFootballboot);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_HelperWeatherFootballboot);
			
			String key1 = "%" + keyword1 + "%";
			String key2 = "%" + keyword2 + "%";
			String key3 = "%" + keyword3 + "%";
			System.out.println("keyword1 = " + keyword1 + ", key1 = " + key1);
			System.out.println("keyword2 = " + keyword2 + ", key2 = " + key2);
			System.out.println("keyword3 = " + keyword3 + ", key3 = " + key3);
			
			stmt.setString(1, key1);
			stmt.setString(2, key2);
			stmt.setString(3, key3);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	//ttff
	private static final String SQL_SELECT_KEYWORD_HelperOpposingTeam =
			"select * from SONNY WHERE HELPER like ? and " 
					+ " OPPOSINGTEAM like ?"
					+ " order by SID";
	
	@Override
	public List<Sonny> readHelperOpposingTeam(String keyword1, String keyword2) {
		ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_HelperOpposingTeam);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_HelperOpposingTeam);
			
			String key1 = "%" + keyword1 + "%";
			String key2 = "%" + keyword2 + "%";
			System.out.println("keyword1 = " + keyword1 + ", key1 = " + key1);
			System.out.println("keyword2 = " + keyword2 + ", key2 = " + key2);
			
			stmt.setString(1, key1);
			stmt.setString(2, key2);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	//tttf
	private static final String SQL_SELECT_KEYWORD_HelperOpposingTeamWeather =
			"select * from SONNY WHERE HELPER like ? and " 
					+ " OPPOSINGTEAM like ? and "
					+ " WEATHER like ?"
					+ " order by SID";
	@Override
	public List<Sonny> readHelperOpposingTeamWeather(String keyword1, String keyword2, String keyword3) {
		ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_HelperOpposingTeamWeather);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_HelperOpposingTeamWeather);
			
			String key1 = "%" + keyword1 + "%";
			String key2 = "%" + keyword2 + "%";
			String key3 = "%" + keyword3 + "%";
			System.out.println("keyword1 = " + keyword1 + ", key1 = " + key1);
			System.out.println("keyword2 = " + keyword2 + ", key2 = " + key2);
			System.out.println("keyword3 = " + keyword3 + ", key3 = " + key3);
			
			stmt.setString(1, key1);
			stmt.setString(2, key2);
			stmt.setString(3, key3);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	//ttft
	private static final String SQL_SELECT_KEYWORD_HelperOpposingTeamFootballboot =
			"select * from SONNY WHERE HELPER like ? and " 
					+ " OPPOSINGTEAM like ? and "
					+ " FOOTBALLBOOT like ?"
					+ " order by SID";
	
	@Override
	public List<Sonny> readHelperOpposingTeamFootballboot(String keyword1, String keyword2, String keyword3) {
		ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_HelperOpposingTeamFootballboot);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_HelperOpposingTeamFootballboot);
			
			String key1 = "%" + keyword1 + "%";
			String key2 = "%" + keyword2 + "%";
			String key3 = "%" + keyword3 + "%";
			System.out.println("keyword1 = " + keyword1 + ", key1 = " + key1);
			System.out.println("keyword2 = " + keyword2 + ", key2 = " + key2);
			System.out.println("keyword3 = " + keyword3 + ", key3 = " + key3);
			
			stmt.setString(1, key1);
			stmt.setString(2, key2);
			stmt.setString(3, key3);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	//tttt
	private static final String SQL_SELECT_KEYWORD_HelperOpposingTeamWeatherFootballboot =
			"select * from SONNY WHERE HELPER like ? and " 
					+ " OPPOSINGTEAM like ? and "
					+ " WEATHER like ? and "
					+ " FOOTBALLBOOT like ?"
					+ " order by SID";
	@Override
	public List<Sonny> readHelperOpposingTeamWeatherFootballboot(String keyword1, String keyword2, String keyword3,
			String keyword4) {
		
		ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_HelperOpposingTeamWeatherFootballboot);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_HelperOpposingTeamWeatherFootballboot);
			
			String key1 = "%" + keyword1 + "%";
			String key2 = "%" + keyword2 + "%";
			String key3 = "%" + keyword3 + "%";
			String key4 = "%" + keyword4 + "%";
			System.out.println("keyword1 = " + keyword1 + ", key1 = " + key1);
			System.out.println("keyword2 = " + keyword2 + ", key2 = " + key2);
			System.out.println("keyword3 = " + keyword3 + ", key3 = " + key3);
			System.out.println("keyword4 = " + keyword4 + ", key4 = " + key4);
			
			stmt.setString(1, key1);
			stmt.setString(2, key2);
			stmt.setString(3, key3);
			stmt.setString(4, key4);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	//fttt
	private static final String SQL_SELECT_KEYWORD_OpposingTeamWeatherFootballboot =
			"select * from SONNY WHERE OPPOSINGTEAM like ? and " 
					+ " WEATHER like ? and "
					+ " FOOTBALLBOOT like ?"
					+ " order by SID";
	@Override
	public List<Sonny> readOpposingTeamWeatherFootballboot(String keyword1, String keyword2, String keyword3) {
		ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_OpposingTeamWeatherFootballboot);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_OpposingTeamWeatherFootballboot);
			
			String key1 = "%" + keyword1 + "%";
			String key2 = "%" + keyword2 + "%";
			String key3 = "%" + keyword3 + "%";
			System.out.println("keyword1 = " + keyword1 + ", key1 = " + key1);
			System.out.println("keyword2 = " + keyword2 + ", key2 = " + key2);
			System.out.println("keyword3 = " + keyword3 + ", key3 = " + key3);
			
			stmt.setString(1, key1);
			stmt.setString(2, key2);
			stmt.setString(3, key3);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;	
		
	}
	
	// fttf
	private static final String SQL_SELECT_KEYWORD_OpposingTeamWeather =
			"select * from SONNY WHERE OPPOSINGTEAM like ? and " 
					+ " WEATHER like ?"
					+ " order by SID";	
	
	@Override
	public List<Sonny> readOpposingTeamWeather(String keyword1, String keyword2) {
		ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_OpposingTeamWeather);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_OpposingTeamWeather);
			
			String key1 = "%" + keyword1 + "%";
			String key2 = "%" + keyword2 + "%";
			System.out.println("keyword1 = " + keyword1 + ", key1 = " + key1);
			System.out.println("keyword2 = " + keyword2 + ", key2 = " + key2);
			
			stmt.setString(1, key1);
			stmt.setString(2, key2);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	
	// ftft
	private static final String SQL_SELECT_KEYWORD_OpposingTeamFootballboot =
			"select * from SONNY WHERE OPPOSINGTEAM like ? and " 
					+ " FOOTBALLBOOT like ?"
					+ " order by SID";	
	
	@Override
	public List<Sonny> readOpposingTeamFootballboot(String keyword1, String keyword2) {
		ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_OpposingTeamFootballboot);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_OpposingTeamFootballboot);
			
			String key1 = "%" + keyword1 + "%";
			String key2 = "%" + keyword2 + "%";
			System.out.println("keyword1 = " + keyword1 + ", key1 = " + key1);
			System.out.println("keyword2 = " + keyword2 + ", key2 = " + key2);
			
			stmt.setString(1, key1);
			stmt.setString(2, key2);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	
	// ftff
	private static final String SQL_SELECT_KEYWORD_OnlyOpposingTeam =
			"select * from SONNY WHERE OPPOSINGTEAM like ? " + " order by SID";	
	
	@Override
	public List<Sonny> readOnlyOpposingTeam(String keyword) {
	ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_OnlyOpposingTeam);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_OnlyOpposingTeam);
			
			String key = "%" + keyword + "%";
			System.out.println("keyword = " + keyword + ", key = " + key);
			
			stmt.setString(1, key);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	//fftt
	private static final String SQL_SELECT_KEYWORD_WeatherFootballboot =
			"select * from SONNY WHERE WEATHER like ? and " 
					+ " FOOTBALLBOOT like ?"
					+ " order by SID";	
	@Override
	public List<Sonny> readWeatherFootballboot(String keyword1, String keyword2) {
ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_WeatherFootballboot);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_WeatherFootballboot);
			
			String key1 = "%" + keyword1 + "%";
			String key2 = "%" + keyword2 + "%";
			System.out.println("keyword1 = " + keyword1 + ", key1 = " + key1);
			System.out.println("keyword2 = " + keyword2 + ", key2 = " + key2);
			
			stmt.setString(1, key1);
			stmt.setString(2, key2);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	//fftf
	private static final String SQL_SELECT_KEYWORD_OnlyWeather =
			"select * from SONNY WHERE WEATHER like ? " + " order by SID";		
	
	@Override
	public List<Sonny> readOnlyWeather(String keyword) {
	ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_OnlyWeather);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_OnlyWeather);
			
			String key = "%" + keyword + "%";
			System.out.println("keyword = " + keyword + ", key = " + key);
			
			stmt.setString(1, key);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	private static final String SQL_SELECT_KEYWORD_OnlyFootballboot =
			"select * from SONNY WHERE FOOTBALLBOOT like ? " + " order by SID";
	
	@Override
	public List<Sonny> readOnlyFootballboot(String keyword) {
	ArrayList<Sonny> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			System.out.println(SQL_SELECT_KEYWORD_OnlyFootballboot);
			stmt = conn.prepareStatement(SQL_SELECT_KEYWORD_OnlyFootballboot);
			
			String key = "%" + keyword + "%";
			System.out.println("keyword = " + keyword + ", key = " + key);
			
			stmt.setString(1, key);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int sid = rs.getInt(COL_SID);
				String footballseason = rs.getString(COL_FOOTBALLSEASON);
				String participation = rs.getString(COL_PARTICIPATION);
				String goals = rs.getString(COL_GOALS);
				String assists = rs.getString(COL_ASSISTS);
				String shots = rs.getString(COL_SHOTS);
				String shotsOnTarget = rs.getString(COL_SHOTSONTARGET);
				String passes = rs.getString(COL_PASSES);
				String helper = rs.getString(COL_HELPER);
				String opposingTeam = rs.getString(COL_OPPOSINGTEAM);
				String weather = rs.getString(COL_WEATHER);
				String footballBoot = rs.getString(COL_FOOTBALLBOOT);
				
				Sonny son = new Sonny(sid, footballseason, goals, assists, 
						shots, shotsOnTarget, passes, 
						opposingTeam, weather, footballBoot,
						helper, participation);
				
				list.add(son);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
}
