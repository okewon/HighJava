package kr.or.ddit.basic;

import java.util.List;

public class BoardServiceImp implements IBoardService{

	
	private IBoardDao boardDao;
	
	public BoardServiceImp() {
		boardDao = new BoardDaoImpl();
	}

	@Override
	public int insertBoard(BoardVO bo) {
		return boardDao.insertBoard(bo);
	}

	@Override
	public boolean getBoard(int boardNo) {
		return boardDao.getBoard(boardNo);
	}

	@Override
	public List<BoardVO> getAllBoard() {
		return boardDao.getAllBoard();
	}

	@Override
	public int updateBoard(BoardVO bo) {
		return boardDao.updateBoard(bo);
	}

	@Override
	public int deleteBoard(String board_no) {
		return boardDao.deleteBoard(board_no);
	}
}
