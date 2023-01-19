package kr.or.ddit.mvc.service;

import java.util.List;

import kr.or.ddit.mvc.dao.BoardDaoLImpl;
import kr.or.ddit.mvc.dao.IBoardDao;
import kr.or.ddit.mvc.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao dao;
	
	public BoardServiceImpl() {
		dao = new BoardDaoLImpl();
	}
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public List<BoardVO> viewContent(int board_no) {
		return dao.viewContent(board_no);
	}

	@Override
	public List<BoardVO> searchBoard(String searchText) {
		return dao.searchBoard(searchText);
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int board_no) {
		return dao.deleteBoard(board_no);
	}

	@Override
	public List<BoardVO> boardLists() {
		return dao.boardLists();
	}

	@Override
	public int updateCnt(int board_no) {
		return dao.updateCnt(board_no);
	}

}
