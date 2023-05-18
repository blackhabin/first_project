package edu.java.sonny.controller;

import java.util.List;

import edu.java.sonny.model.Sonny;


public interface SonnyDao {
	
	/**
	 * 연락처 정보를 DB의 sonny 테이블에 insert.
	 * 
	 * @param sonny 테이블은 저장할 시즌, 득점, 도움, 슈팅, 유효슈팅, 패스, 상대팀, 날씨, 축구화 종류, 
	 * 				어시스트 도우미, 경기 출전여부, 스코어 정보를 가지고 있는 객체.
	 * @return DB 테이블에 삽입된 행의 개수.
	 */
	int create(Sonny s);
	
	/**
	 * 정보 전체 검색.
	 * DB sonny 테이블에 저장된 모든 연락처 정보를 검색.
	 *  
	 * @return Sonny 타입을 원소로 갖는 List를 리턴.
	 */
	List<Sonny> read();
	
	/**
	 * DB sonny 테이블에서 primary key로 연락처 정보를 검색.
	 * 
	 * @param sid 가 검색한 primary key
	 * @return sid가 존재하면 Sonny 타입 객체를 리턴하고 만약 sid가 없으면 null을 리턴.
	 */
	Sonny read(int sid);
	
	
	/**
	 * 주어진 검색어가 sonny db에 있는 키워드와 일치하는 DB를 리턴.
	 * 검색어 대소문자 구별하지 않음
	 * 
	 * @param keyword
	 * @return 검색 결과리스트
	 */	
	List<Sonny> read(String keyword);


	/**
	 * primary key에 해당하는 해당 경기 정보를 수정(업데이트)하는 기능
	 * 
	 * @param sonny 에는 수정할 연락처의 PK(sid)가 반드시 있어야되고, 업데이트할 저장할 시즌, 득점, 도움, 슈팅, 유효슈팅, 패스, 상대팀, 날씨, 축구화 종류, 
	 * 				어시스트 도우미, 경기 출전여부, 스코어 정보를 저장한 객체
	 * @return DB 테이블에서 업데이트 된 행의 개수를 리턴.
	 */
	int update(Sonny sonnyDate);
	
	/**
	 * Primary key(sid)에 해당하는 연락처 정보를 DB 테이블에서 삭제.
	 * 
	 * @param cid 는 삭제할 연락처의 PK.
	 * @return 삭제된 행의 개수를 리턴.
	 */
	int delete(int sid);
	
	
	/**
	 * db의 전체 골을 리턴
	 * @return db 테이블에서 조회된 sum(goals)을 리턴. 제약조건은 경기 출전시
	 */
	int totalGoal();

	
	/**
	 * db의 경기당 득점율을 리턴
	 * @return db 테이블에서 조회된 avg(goals)을 리턴. 제약조건은 경기 출전시.
	 */
	double goalsPerMatch();


	/**
	 * db의 전체 경기수를 리턴
	 * @return db 테이블에서 조회된 count(sid)을 리턴. 제약조건은 경기 출전 y.
	 */
	int totalMatches();
	
	/**
	 * db의 전체 슈팅수를 리턴
	 * @return db 테이블에서 조회된 sum(shots)을 리턴. 제약조건은 경기출전시
	 */
	int totalShots();

	/**
	 * db의 경기당 평균 슈팅율 리턴
	 * @return db 테이블에서 조회된 avg(shots)을 리턴. 제약조건은 경기 출전시
	 */
	double shotsPerMatch();
	
	/**
	 * db의 전체 유효슈팅 수를 리턴
	 * @return db 테이블에서 조회된 sum(shotspershots)을 리턴. 제약조건은 경기 출전시
	 */
	int totalShotsOnTarget();


	/**
	 * 손흥민의 슈팅 정확도를 리턴한다.
	 * @return db에서 sum(shots)-슈팅수와 sum(shotspershots)-유효슈팅수를 나눈 값을 리턴 한다. 제약조건은 경기 출전시
	 */
	double shootingAccuracy();

	
	/**
	 * db의 전체 도움 수를 리턴
	 * @return db 테이블에서 조회된 sum(assists)를 리턴. 제약조건은 경기 출전
	 */
	int totalAssists();
	
	/**
	 * db의 전체 패스 수를 리턴
	 * @return db 테이블에서 조회된 sum(passes)를 리턴. 제약조건은 경기 출전
	 */
	int totalPasses();
	
	/**
	 * db의 경기당 평균 패스율을 리턴
	 * @return db에서 avg(passes)을 리턴 한다. 제약조건은 경기 출전
	 */
	double passPerMatch();
	
	// tfff
	List<Sonny> readOnlyHelper(String keyword);
	// tftf
	List<Sonny> readHelperWeather(String keyword1, String keyword2);
	
	// tfft
	List<Sonny> readHelperFootballboot(String keyword1, String keyword2);
	
	// ttff
	List<Sonny> readHelperWeatherFootballboot(String keyword1, String keyword2, String keyword3);
	
	// ttff
	List<Sonny> readHelperOpposingTeam(String keyword1, String keyword2);
	
	// tttf
	List<Sonny> readHelperOpposingTeamWeather(String keyword1, String keyword2, String keyword3);
	
	// ttft
	List<Sonny> readHelperOpposingTeamFootballboot(String keyword1, String keyword2, String keyword3);
	
	// tttt
	List<Sonny> readHelperOpposingTeamWeatherFootballboot(String keyword1, String keyword2, String keyword3, String keyword4);
	
	//fttt
	List<Sonny> readOpposingTeamWeatherFootballboot(String keyword1, String keyword2, String keyword3);
	
	//fttf
	List<Sonny> readOpposingTeamWeather(String keyword1, String keyword2);
	
	// ftft
	List<Sonny> readOpposingTeamFootballboot(String keyword1, String keyword2);
	
	// ftff
	List<Sonny> readOnlyOpposingTeam(String keyword);
	
	// fftt
	List<Sonny> readWeatherFootballboot(String keyword1, String keyword2);

	// fftf
	List<Sonny> readOnlyWeather(String keyword);
	
	// ffft
	List<Sonny> readOnlyFootballboot(String keyword);	
	
	
}

