package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface IMemberService {
	
	public List<MemberVO> memberAllList();
	
	public String memberAdd(MemberVO vo);
	
}
