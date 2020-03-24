package assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Hotel {
	private Scanner s;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public Hotel() {
		s = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new Hotel().reservationStart();
	}

	public void reservationStart() {
		String choice = null;

		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		do {
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.print("메뉴 선택 >> ");
			choice = s.nextLine();

			switch (choice) {
			case "1":
				checkin();
				break;
			case "2":
				checkOut();
				break;
			case "3":
				roomStatus();
				break;
			case "4":
				System.out.println("**************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				return;
			}
		} while (true);
	}

	private void roomStatus() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "Ock";
			String password = "java";

			conn = DriverManager.getConnection(url, userId, password);

			stmt = conn.createStatement();

			String sql = "select room_number, reservation_name from hotel";

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("room_number : " + rs.getInt("room_number"));
				System.out.println("reservation_name : " + rs.getString("reservation_name"));
				System.out.println("---------------------------------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 6. 종료(사용했던 자원을 모두 반납한다.)
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	private void checkOut() {
		try {
			System.out.println();
			System.out.println("어느방을 체크아웃 하시겠습니까?");
			System.out.print("방번호 입력 => ");
			int roomNumber = Integer.parseInt(s.nextLine());

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "Ock";
			String password = "java";

			conn = DriverManager.getConnection(url, userId, password);

			stmt = conn.createStatement();

			String sql = "select * from hotel where room_number = " + roomNumber;

			rs = stmt.executeQuery(sql);

			if(rs.next()) {
				if (rs.getString("room_status").equals("Y")) {
					update(roomNumber);
					System.out.println("체크아웃 되었습니다.");
				}
			} else {
				System.out.println(roomNumber + "방에는 체크인한 사람이 없습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 6. 종료(사용했던 자원을 모두 반납한다.)
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	private void checkin() {
		try {
			System.out.println();
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.print("방번호 입력 => ");
			int roomNumber = Integer.parseInt(s.nextLine());

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "Ock";
			String password = "java";

			conn = DriverManager.getConnection(url, userId, password);

			stmt = conn.createStatement();

			String sql = "select * from hotel where room_number = " + roomNumber + " AND room_status = 'Y'";

			rs = stmt.executeQuery(sql);

			if(rs.next()) {
				if (rs.getString("room_status").equals("Y")) {
					System.out.println(roomNumber + "방에는 이미 사람이 있습니다.");
				}
				return;
			}else {
				System.out.println("누구를 체크인 하시겠습니까?");
				System.out.print("이름 입력 => ");
				String name = s.nextLine();	
				insert(name, roomNumber);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 6. 종료(사용했던 자원을 모두 반납한다.)
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void update(int roomNumber) {
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "Ock";
			String password = "java";

			conn = DriverManager.getConnection(url, userId, password);

			stmt = conn.createStatement();

			String sql = "update hotel set reservation_name = null, room_status = 'N' where room_number = " + roomNumber;

			rs = stmt.executeQuery(sql);


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 6. 종료(사용했던 자원을 모두 반납한다.)
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void insert(String name, int roomNumber) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "Ock";
			String password = "java";
	
			conn = DriverManager.getConnection(url, userId, password);
	
			stmt = conn.createStatement();
			
			String insertSql = "insert into hotel values (" + roomNumber + ", '" + name + "', 'Y')";
	
			stmt.executeQuery(insertSql);
	
			System.out.println("체크인 되었습니다.");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				// 6. 종료(사용했던 자원을 모두 반납한다.)
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if (stmt != null)
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			
		}
}

class HotelInfo {

	private int room;
	private String name;

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}