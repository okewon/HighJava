package assignment;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "Ock";
			String password = "java";
			return DriverManager.getConnection(url, userId, password);
		}catch (SQLException e) {
			System.out.println("DB 연결 실패!!!");
			e.printStackTrace();
			return null;
		}
	}
}
