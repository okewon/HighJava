package kr.or.ddit.basic;

import java.util.List;

public interface IBoardDao {
	
	public int insertBoard(BoardVO bo);
	
	public boolean getBoard(int boardNo);
	
	public List<BoardVO> getAllBoard();
	
	public int updateBoard(BoardVO bo);
	
	public int deleteBoard(String board_no);
}
