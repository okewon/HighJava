package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.util.DBUtil;

public class BoardDaoImpl implements IBoardDao{
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	int cnt = 0;
	static int board_no = 1;

	@Override
	public int insertBoard(BoardVO bo) {
		try {
			conn = DBUtil.getConnection();
			
			String sql = "insert into jdbc_board (board_no, board_title, board_writer, boared_date, board_content) values (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board_no);
			pstmt.setString(2, bo.getBoard_title());
			pstmt.setString(3, bo.getBoard_writer());
			pstmt.setDate(4, bo.getBoard_date());
			pstmt.setString(5, bo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			board_no++;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public boolean getBoard(int boardNo) {
		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) as cnt from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				chk = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		return chk;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO bo = new BoardVO();
				
				bo.setBoard_no(rs.getInt("board_no"));
				bo.setBoard_title(rs.getString("board_title"));
				bo.setBoard_writer(rs.getString("board_writer"));
				bo.setBoard_date(rs.getDate("board_date"));
				bo.setBoard_content(rs.getString("board_content"));
				
				boardList.add(bo);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return boardList;
	}

	@Override
	public int updateBoard(BoardVO bo) {
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update jdbc_board set board_title = ?, board_writer = ?, board_date = ?, board_content = ? where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bo.getBoard_title());
			pstmt.setString(2, bo.getBoard_writer());
			pstmt.setDate(3, bo.getBoard_date());
			pstmt.setString(4, bo.getBoard_content());
			pstmt.setInt(5, bo.getBoard_no());
			
			cnt = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(String board_no) {
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_no);
			
			cnt = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		return cnt;
	}
	
	private void disConnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}

}
