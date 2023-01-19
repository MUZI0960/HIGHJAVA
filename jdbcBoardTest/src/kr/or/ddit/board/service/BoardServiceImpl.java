package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IJdbcBoardDao;
import kr.or.ddit.board.dao.JdbcBoardDaoImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class BoardServiceImpl implements IJdbcBoardService {
	private IJdbcBoardDao boardDAO;
	private static IJdbcBoardService boardService;
	
	private BoardServiceImpl() {
		boardDAO = JdbcBoardDaoImpl.getInstance();
	}
	public static IJdbcBoardService getInstance() {
		if(boardService==null) boardService = new BoardServiceImpl();
		return boardService;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String board_title) {
		return boardDAO.getSearchBoardList(board_title);
	}

	@Override
	public int setCountIncrement(int board_no) {
		return boardDAO.setCountIncrement(board_no);
	}
	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		return boardDAO.insertBoard(boardVo);
	}
	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		return boardDAO.updateBoard(boardVo);
	}
	@Override
	public List<JdbcBoardVO> getAllboardList() {
		return boardDAO.getAllboardList();
	}
	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		int cnt = boardDAO.setCountIncrement(boardNo);
		JdbcBoardVO boardVo = null;
		if(cnt>0) {
			boardVo = boardDAO.getBoard(boardNo);
		}
		return boardVo;
	}
	@Override
	public int deleteBoard(int boardNo) {
		return boardDAO.deleteBoard(boardNo);
	}
}
