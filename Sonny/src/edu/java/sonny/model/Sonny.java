package edu.java.sonny.model;

import java.io.Serializable;

public class Sonny implements Serializable {

	// 오라클 SONNY 테이블 컬럼이름 상수 정의
	public interface Entity {
		String TBL_NAME = "SONNY"; //테이블 이름
		String COL_SID = "SID"; // 기본키(PK)
		String COL_FOOTBALLSEASON = "FOOTBALLSEASON"; //시즌
		String COL_PARTICIPATION = "PARTICIPATION"; // 출전여부
		String COL_GOALS = "GOALS"; // 득점
		String COL_ASSISTS ="ASSISTS"; // 도움
		String COL_SHOTS = "SHOTS"; // 슈팅
		String COL_SHOTSONTARGET = "SHOTSONTARGET"; // 유효슈팅
		String COL_PASSES = "PASSES"; // 패스
		String COL_HELPER = "HELPER"; // 어시스트 도우미
		String COL_OPPOSINGTEAM = "OPPOSINGTEAM"; // 상대팀
		String COL_WEATHER = "WEATHER"; // 날씨
		String COL_FOOTBALLBOOT = "FOOTBALLBOOT"; // 축구화
		
	}
	
	
	// field
	private int sid;		//기본키로 활용할거
	private String footballSeason; // 시즌
	private String participation; // 경기 출전여부 
	private String goals; // 득점
	private String helper; // 어시스트 도우미
	private String assists; // 도움
	private String shots; // 슈팅
	private String shotsOnTarget; // 유효슈팅
	private String passes; //패스
	private String opposingTeam; // 상대팀
	private String weather; // 날씨
	private String footballBoot; // 축구화 종류
	
	
	// constructor
	public Sonny() {}
	
	public Sonny(int sid, String footbalSeason, String goals, String assists, String shots, String shotsOnTarget, String passes,
			String opposingTeam, String weather, String footballBoot, String helper, String participation) {
		this.sid = sid;
		this.footballSeason = footbalSeason;
		this.goals = goals;
		this.assists = assists;
		this.shots = shots;
		this.shotsOnTarget = shotsOnTarget;
		this.passes = passes;
		this.opposingTeam = opposingTeam;
		this.weather = weather;
		this.footballBoot = footballBoot;
		this.helper = helper;
		this.participation = participation;
	}
	

	public int getSid() {
		return sid;
	}

	public String getFootballSeason() {
		return footballSeason;
	}

	public void setFootballSeason(String footbalSeason) {
		this.footballSeason = footbalSeason;
	}

	public String getParticipation() {
		return participation;
	}

	public void setParticipation(String participation) {
		this.participation = participation;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public String getHelper() {
		return helper;
	}

	public void setHelper(String helper) {
		this.helper = helper;
	}

	public String getAssists() {
		return assists;
	}

	public void setAssists(String assists) {
		this.assists = assists;
	}

	public String getShots() {
		return shots;
	}

	public void setShots(String shots) {
		this.shots = shots;
	}

	public String getShotsOnTarget() {
		return shotsOnTarget;
	}

	public void setShotsOnTarget(String shotsOnTarget) {
		this.shotsOnTarget = shotsOnTarget;
	}

	public String getPasses() {
		return passes;
	}

	public void setPasses(String passes) {
		this.passes = passes;
	}

	public String getOpposingTeam() {
		return opposingTeam;
	}

	public void setOpposingTeam(String opposingTeam) {
		this.opposingTeam = opposingTeam;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getFootballBoot() {
		return footballBoot;
	}

	public void setFootballBoot(String footballBoot) {
		this.footballBoot = footballBoot;
	}

	// method
	@Override
	public String toString() {
		return "Sonny(sid= " + this.sid
				+ ", 시즌= " + this.footballSeason
				+ ", 출전여부= " + this.participation
				+ ", 골= " + this.goals
				+ ", 헬퍼= " + this.helper
				+ ", 도움= " + this.assists
				+ ", 슈팅= " + this.shots
				+ ", 유효슈팅= " + this.shotsOnTarget
				+ ", 패스= " + this.passes
				+ ", 상대팀= " + this.opposingTeam
				+ ", 날씨= " + this.weather
				+ ", 축구화= " + this.footballBoot
				+ ")";
	}

	
	
	
}
