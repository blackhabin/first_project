package edu.java.sonny.model;

import java.sql.Date;

public class SonnyBodyInfo {

	// field
	private String name = "손 흥 민"; // 이름
	private String height = "183cm"; // 키
	private String weight = "77kg"; // 무게
	private String date = "1992.7.8"; // 생년월일
	private String club = "토트넘 홋스퍼 FC"; // 소속팀

	public SonnyBodyInfo() {}
	
	public SonnyBodyInfo(String name, String height, String weight, String date, String club) {
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.date = date;
		this.club = club;
	}
	
	
	
	public String getName() {
		return name;
	}

	public String getHeight() {
		return height;
	}

	public String getWeight() {
		return weight;
	}

	public String getDate() {
		return date;
	}

	public String getClub() {
		return club;
	}

	@Override
	public String toString() {
		return "Sonny(이름= " + this.name
				+ ", 키= " + this.height
				+ ", 몸무게= " + this.weight
				+ ", 생일= " + this.date
				+ ", 소속팀= " + this.club
				+ ")";
	}

}
