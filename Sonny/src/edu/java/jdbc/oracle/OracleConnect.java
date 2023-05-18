package edu.java.jdbc.oracle;

public interface OracleConnect {
	
	// Sonny 꺼에 접속할거야
	String URL = "jdbc:oracle:thin:@192.168.20.7:1521:xe";
	
	// Oracle DB 접속 계정
	String USER = "scott";
	
	// Oracle DB 접속 계정의 비밀번호
	String PASSWORD = "tiger";
	
}
