package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	private IMemberDao dao;
	private static MemberServiceImpl service;
	
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getinstance() {
		if(service == null) service = new MemberServiceImpl();
		
		return service;
	}
	
	@Override
	public List<MemberVO> memberAllList() {

		return dao.memberAllList();
	}

	@Override
	public int memberAdd(MemberVO vo) {
		return dao.memberAdd(vo);
	}

	

}
