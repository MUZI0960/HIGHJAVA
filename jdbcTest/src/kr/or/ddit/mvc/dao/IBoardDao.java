package kr.or.ddit.mvc.dao;

import java.util.List;

import kr.or.ddit.mvc.vo.BoardVO;

public interface IBoardDao {

	public int insertBoard(BoardVO boardVo);
	
	public List<BoardVO> boardLists();
	
	public List<BoardVO> viewContent(int board_no);
	
	public List<BoardVO> searchBoard(String searchText);
	
	public int updateBoard(BoardVO boardVo);
	
	public int deleteBoard(int board_no);
	
	public int updateCnt(int board_no);
}
