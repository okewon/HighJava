package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class T02_JdbcTest {
/*
 * 문제1) 사용자로부터 lprod_id 값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하시오.
 * 
 * 문제2) lprod_id 값을 2개 입력받아서 두 값 중 작은 값부터 큰 값 사이의 자료를 출력하시오.
 */
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =  null;
		Scanner s = new Scanner(System.in);
		
		try {
			System.out.print("lprod_id 값을 입력하시오. >> ");
			int lprod_id = Integer.parseInt(s.nextLine());
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "Ock";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			
			stmt = conn.createStatement();
			
			String sql = "select * from lprod where lprod_id > " + lprod_id;
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("생성할 쿼리문 : " + sql);
			System.out.println("=== 쿼리문 실행 결과 ===");
			
			while(rs.next()) {
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("---------------------------------------------");
			}
			
			System.out.println("출력 끝...");
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			// 6. 종료(사용했던 자원을 모두 반납한다.)
			if(rs != null) try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			if(stmt != null) try {
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			if(conn != null) try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println();
		
		try {
			System.out.print("lprod_id 값을 입력하시오. >> ");
			int lprod_id1 = Integer.parseInt(s.nextLine());
			System.out.print("lprod_id 값을 입력하시오. >> ");
			int lprod_id2 = Integer.parseInt(s.nextLine());
			int min, max;
			
			/*if(lprod_id1 > lprod_id2) {
				max = lprod_id1;
				min = lprod_id2;
			}else {
				max = lprod_id2;
				min = lprod_id1;				
			}*/
			
			max = Math.max(lprod_id1, lprod_id2);
			min = Math.min(lprod_id1, lprod_id2);
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "Ock";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			
			stmt = conn.createStatement();
			
			String sql = "select * from lprod where lprod_id BETWEEN " + min + " AND " + max;
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("생성할 쿼리문 : " + sql);
			System.out.println("=== 쿼리문 실행 결과 ===");
			
			while(rs.next()) {
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("---------------------------------------------");
			}
			
			System.out.println("출력 끝...");
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			// 6. 종료(사용했던 자원을 모두 반납한다.)
			if(rs != null) try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			if(stmt != null) try {
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			if(conn != null) try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
