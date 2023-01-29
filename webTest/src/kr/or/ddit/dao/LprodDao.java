package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.LprodVO;

public class LprodDao {
	
	private SqlMapClient smc;
	
	private static LprodDao dao;
	
	private LprodDao() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static LprodDao getInstance() {
		if(dao == null) dao = new LprodDao();
		return dao;
	}
	
	
	public List<LprodVO> selectAll(){
		List<LprodVO> list = null;
	 
		 try {
			list = smc.queryForList("lprod.selectAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 
		return list;
	 
 }
}