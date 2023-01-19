package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.mvc.vo.BoardVO;
import kr.or.ddit.util.DBUtil3;

public class BoardDaoLImpl implements IBoardDao {

	
	@Override
	public int insertBoard(BoardVO boardVo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = " insert into jdbc_board "
					+ " ( board_no, board_title, board_writer, board_date, board_cnt, board_content) "
					+ " values (board_seq.nextval, ?, ?, to_date(sysdate,'YYYY-MM-DD HH24:MI:SS'), 0, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null)try { conn.close(); } catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> viewContent(int board_no) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BoardVO> viewList = null;
		
		try {
			
			conn = DBUtil3.getConnection();
			
			String sql = " select * from jdbc_board "
					+ " where board_no = " + board_no;
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			viewList = new ArrayList<BoardVO>();
			
			while(rs.next()) {
				
				BoardVO boardVo = new BoardVO();
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_content(rs.getString("board_content"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				
				viewList.add(boardVo);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(stmt!=null) try { stmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		return viewList;
	}

	@Override
	public List<BoardVO> searchBoard(String searchText) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BoardVO> searchedList = null;
		
		
		try {
			
			conn = DBUtil3.getConnection();
			
			String sql = " select board_no, board_title, board_writer, board_cnt from JDBC_BOARD where BOARD_TITLE like '%" + searchText + "%' ";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			searchedList = new ArrayList<BoardVO>();
			while(rs.next()) {
			BoardVO boardVo = new BoardVO();
			boardVo.setBoard_no(rs.getInt("board_no"));
			boardVo.setBoard_title(rs.getString("board_title"));
			boardVo.setBoard_writer(rs.getString("board_writer"));
			boardVo.setBoard_cnt(rs.getInt("board_cnt"));
			searchedList.add(boardVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(stmt!=null) try { stmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return searchedList;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			
			conn = DBUtil3.getConnection();
			
			String sql = " update jdbc_board set board_title =?, board_writer=? where board_no =? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setInt(3, boardVo.getBoard_no());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null)try { conn.close(); } catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int board_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			
			conn = DBUtil3.getConnection();
			
			String sql = " delete from jdbc_board where = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null)try { conn.close(); } catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> boardLists() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BoardVO> viewList = null;
		
		try {
			
			conn = DBUtil3.getConnection();
			
			String sql = " select board_no, board_title, board_writer, board_cnt "
							+ "	from jdbc_board";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			viewList = new ArrayList<BoardVO>();
			
			while(rs.next()) {
				BoardVO boardVo = new BoardVO();
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				
				viewList.add(boardVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				if(rs!=null) try { rs.close(); } catch(SQLException e) {}
				if(stmt!=null) try { stmt.close(); } catch(SQLException e) {}
				if(conn!=null) try { conn.close(); } catch(SQLException e) {}
			}
		return viewList;
	}

	@Override
	public int updateCnt(int board_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			
			conn = DBUtil3.getConnection();
			
			String sql = "update jdbc_board set  board_cnt = (board_cnt+1) where BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null)try { conn.close(); } catch(SQLException e) {}
		}
		
		return cnt;
	}
}

