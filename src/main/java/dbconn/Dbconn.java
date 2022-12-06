package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconn {


	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";	//오라클 접속주소
	private String user = "c##apic";
	private String password = "1234";
	
	public Connection getConnect(){
		Connection conn = null;
		try {
	
			Class.forName("oracle.jdbc.driver.OracleDriver");	//해당 드라이버의 프로그램을 (동적로딩) 메모리에 올려놓는다
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e) {
		e.printStackTrace();
		}
	return conn;
	}
	

	
}
