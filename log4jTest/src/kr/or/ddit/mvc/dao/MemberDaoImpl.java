package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {
	static Logger logger = Logger.getLogger(MemberDaoImpl.class);
	
	// 1번
	private static MemberDaoImpl dao;
	
	// 2번
	private MemberDaoImpl() { }
	
	// 3번
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		return dao;
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; 		// 반환값이 저장될 변수
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성");
			
			String sql = "insert into mymember "
					+ "(mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
					+ " values(?, ?, ?, ?, ?) ";
			
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_pass());
			pstmt.setString(3, memVo.getMem_name());
			pstmt.setString(4, memVo.getMem_tel());
			pstmt.setString(5, memVo.getMem_addr());
			
			logger.debug("PreparedStatement 객체 생성");
			logger.debug("실행 SQL ==> " + sql);
			logger.debug("사용 데이터 ==> " + memVo.getMem_id() + ", " 
						+ memVo.getMem_pass() + ", " + memVo.getMem_name() + ", "
						+ memVo.getMem_tel() + ", " + memVo.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			logger.info("insert 작업 성공");
			
		} catch (SQLException e) {
			logger.error("insert 작업 실패", e);
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try { 
				pstmt.close(); 
				logger.info("PreparedStatment객체 반납 . . .");
			} catch(SQLException e) {}
			if(conn!=null)try { 
				conn.close();
				logger.info("Connection객체 반납 . . .");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성");
			
			String sql = "delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try { 
				pstmt.close(); 
				logger.info("PreparedStatment객체 반납 . . .");
			} catch(SQLException e) {}
			if(conn!=null)try { 
				conn.close();
				logger.info("Connection객체 반납 . . .");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		int cnt = 0;
		
		try {
			
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성");
			
			String sql = " update mymember set mem_pass =?, mem_name = ?,"
					+ "	mem_tel = ?, mem_addr = ? where mem_id = ?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_pass());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			pstmt.setString(5, memVo.getMem_id());
			
			logger.debug("PreparedStatement 객체 생성");
			logger.debug("실행 SQL ==> " + sql);
			logger.debug("사용 데이터 ==> " + memVo.getMem_id() + ", " 
						+ memVo.getMem_pass() + ", " + memVo.getMem_name() + ", "
						+ memVo.getMem_tel() + ", " + memVo.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try { 
				pstmt.close(); 
				logger.info("PreparedStatment객체 반납 . . .");
			} catch(SQLException e) {}
			if(conn!=null)try { 
				conn.close();
				logger.info("Connection객체 반납 . . .");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<MemberVO> memList = null;
		
		try {
			
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성");
			
			String sql = " select * from mymember";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			memList = new ArrayList<MemberVO>();
			
			while(rs.next()) {
				// 1개의 레코드 결과를 VO객체에 담는다.
				MemberVO memVo = new MemberVO();
				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_pass(rs.getString("mem_pass"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_addr(rs.getString("mem_addr"));
				
				memList.add(memVo); 	// VO객체를 List에 추가
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try { 
				rs.close(); 
				logger.info("ResultSet객체 반납 . . .");
				} catch(SQLException e) {}
			if(stmt!=null)try { 
				stmt.close(); 
				logger.info("Statment객체 반납 . . .");
			} catch(SQLException e) {}
			if(conn!=null)try { 
				conn.close();
				logger.info("Connection객체 반납 . . .");
			} catch(SQLException e) {}
		}
		return memList;
		}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성");
			
			String sql = "select count(*) cnt from mymember where mem_id = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt =rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { 
				rs.close(); 
				logger.info("ResultSet객체 반납 . . .");
				} catch(SQLException e) {}
			if(pstmt!=null)try { 
				pstmt.close(); 
				logger.info("PreparedStatment객체 반납 . . .");
			} catch(SQLException e) {}
			if(conn!=null)try { 
				conn.close();
				logger.info("Connection객체 반납 . . .");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		// key값 ==> 회원ID(id), 수정할컬럼명(field), 수정할데이터(data) 	
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성");
			
			String sql = "update mymember set " 
							+ paramMap.get("field") + " = ? where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("id"));

			logger.debug("PreparedStatement 객체 생성");
			logger.debug("실행 SQL ==> " + sql);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try { 
				pstmt.close(); 
				logger.info("PreparedStatment객체 반납 . . .");
			} catch(SQLException e) {}
			if(conn!=null)try { 
				conn.close();
				logger.info("Connection객체 반납 . . .");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

}
