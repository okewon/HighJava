package assignment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class board {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 전체 게시글 출력");
		System.out.println("  2. 게시글 작성");
		System.out.println("  3. 게시글 삭제");
		System.out.println("  4. 게시글 수정");
		System.out.println("  5. 게시글 검색");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 : 
					displayMemberAll();
					break;
				case 2 :  
					insertMember();
					break;
				case 3 :  
					deleteMember();
					break;
				case 4 :  
					updateMember();
					break;
				case 5 :  
					searchMember();
					break;					
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	private void searchMember() {
		boolean chk = false;
		int num;
		
		do {
			System.out.println();
			System.out.println("검색할 게시글 번호를 입력하세요.");
			System.out.println("게시글 번호 >");
			num = Integer.parseInt(scan.next());
			chk = getBoard(num);
			 if(!chk) {
				 System.out.println("게시글이 존재하지 않습니다.");
				 System.out.println("다시 입력하세요.");
			 }
		}while(chk == false);
	
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from jdbc_board where board_no = " + num;
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int num1 = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String content = rs.getString("board_content");
				System.out.println(num1 + "\t" + title + "\t" + writer + "\t" + date + "\t" + content);
				System.out.println("-------------------------------------------------------");
			}
			System.out.println("출력 작업 끝..");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	private void deleteMember() {
		System.out.println();
		System.out.print("삭제할 게시글 번호를 입력하세요 >>");
		int no = Integer.parseInt(scan.next());
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println("삭제 성공");
				
			}
			else {
				System.out.println("삭제 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void updateMember() {
		boolean chk = false;
		int num;
		
		do {
			System.out.println();
			System.out.println("수정할 게시글 번호를 입력하세요.");
			System.out.println("게시글 번호 >");
			num = Integer.parseInt(scan.next());
			chk = getBoard(num);
			 if(!chk) {
				 System.out.println("게시글이 존재하지 않습니다.");
				 System.out.println("다시 입력하세요.");
			 }
		}while(chk == false);
		
		System.out.println("수정할 내용을 입력하세요.");
		System.out.println("새로운 게시글 제목 >");
		String title = scan.next();
		
		scan.nextLine();
		System.out.println("새로운 게시글 작성자 >>");
		String writer = scan.nextLine();
		
		System.out.println("새로운 내용 >>");
		String content = scan.nextLine();		
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update jdbc_board set board_title = ?, board_writer = ?,"
					+ " board_date = sysdate, board_content = ? where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			pstmt.setInt(4, num);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println("게시글의 정보가 수정되었습니다.");				
			}else {
				System.out.println("수정 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void displayMemberAll() {
		System.out.println();		
		System.out.println("-------------------------------------------------------");		
		System.out.println("게시글 번호 \t 제목\t 작성자\t 작성일자\t\t\t 내용");		
		System.out.println("-------------------------------------------------------");		
	
		try {
			conn =DBUtil.getConnection();
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String num = rs.getString("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String content = rs.getString("board_content");
				System.out.println(num + "\t" + title + "\t" + writer + "\t" + date + "\t" + content);
				System.out.println("-------------------------------------------------------");
			}
			System.out.println("출력 작업 끝..");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertMember() {
		boolean chk = false;		
		
		System.out.println("게시글 정보를 입력해주세요.");
		System.out.println("게시글 제목 >");
		String title = scan.next();
		scan.nextLine();
		
		System.out.println("게시글 작성자이름 >>");
		String writer= scan.next();
		scan.nextLine();
		
		System.out.println("게시글 내용 >>");
		String content = scan.nextLine();
	


		try {
			conn = DBUtil.getConnection();
			String sql = "insert into jdbc_board values(board_seq.nextVal,?,?,sysdate,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);			
			pstmt.setString(3, content);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("게시글 작성 성공");
			}
			else {
				System.out.println("게시글 작성 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}		
	}
	
	
	private boolean getBoard(int num) {
		boolean chk = false;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) as cnt from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt > 0 ) {
				chk = true;
			}
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return chk;
	}

	private void disConnect() {
		if (stmt != null) {try {stmt.close();} catch (SQLException e){}}
		if (pstmt != null) {try {pstmt.close();} catch (SQLException e){}}
		if (conn != null) {try {conn.close();} catch (SQLException e){}}		
	}

	public static void main(String[] args) {
		board memObj = new board();
		memObj.start();
	}

}






