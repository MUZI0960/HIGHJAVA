package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {

	private SqlMapClient smc;
	private static MemberDaoImpl dao;
	
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static MemberDaoImpl getInstance() {
		if(dao == null) dao = new MemberDaoImpl();
		
		return dao;
	}
	
	@Override
	public List<MemberVO> memberAllList() {
		List<MemberVO> list = null;
		
		try {
			list = smc.queryForList("member.memberAllList");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public String memberAdd(MemberVO vo) {
		String cnt = null;
		
		try {
			cnt = (String) smc.insert("member.memberAdd");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

}
