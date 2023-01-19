package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	private SqlMapClient smc;

	private static JdbcBoardDaoImpl dao;
	private JdbcBoardDaoImpl() { 
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	public static JdbcBoardDaoImpl getInstance() {
		if(dao == null) dao = new JdbcBoardDaoImpl();
		return dao;
	}
	
	
	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		Object obj;
		try {
			obj = smc.insert("board.insertBoard", boardVo);
			
			if(obj==null) {
				cnt = 1;
			}		
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("board.deleteBoard", boardNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("board.updateBoard", boardVo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<JdbcBoardVO> getAllboardList() {
		List<JdbcBoardVO> boardList = null; // 반환값
		
		try {
			boardList = smc.queryForList("board.getAllboardList");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		JdbcBoardVO boardVo = null;
		
		try {
			boardVo = (JdbcBoardVO)smc.queryForObject("board.getBoard", boardNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {
		List<JdbcBoardVO> boardList = null; // 반환값
		
		try {
			boardList = smc.queryForList("board.getSearchBoardList", title);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public int setCountIncrement(int BoardNo) {
		int cnt = 0; 	// 반환값
		
		try {
			cnt = smc.update("board.setCountIncrement", BoardNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
