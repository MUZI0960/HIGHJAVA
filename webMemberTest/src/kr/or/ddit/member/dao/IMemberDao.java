package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface IMemberDao {

	public List<MemberVO> memberAllList();
	
	public String memberAdd(MemberVO vo);
	
	
}
