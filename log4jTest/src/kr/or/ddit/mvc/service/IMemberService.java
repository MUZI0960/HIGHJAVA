package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;

/**
 * Service객체는 DAO에 설정된 메서드를 원하는 작업에 맞게 호출하여 그 결과를 받아오고, 
 * 받아온 결과를 Controller에게 보내주는 역할을 한다.
 * 
 * 보통은 DAO의 메서드 구조와 같게 만든다. (자바고급시간에 한해서...)
 * 
 * @author PC-04
 *
 */


public interface IMemberService {
	
	/**
	 * MemeberVO에 담겨진 데이터를 DB에 insert하는 메서드
	 * 
	 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
//	public int insertMember(String id, String pass); => 나열하지 말고 한묶음으로
	public int insertMember(MemberVO memVo);
	
	
	/**
	 * 회원ID를 인수값으로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회워ID
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	// 필요한 데이터가 하나기 때문에 굳이 객체로 받을 필요 없다.. 두개 이상이 되지 않도록 묶기
	public int deleteMember(String memId);
	
	
	/**
	 * MemberVO의 자료를 이용하여 DB의 자료를 Update하는 메서드
	 * @param memVo update할 회원 정보가 저장된 MemeberVO객체 
	 * @return 작업 성공 : 1, 작업 실행 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	
	/**
	 * DB의 전체 회원 정보를 가져와 List에 담아서 반환하는 메서드
	 * 
	 * @return MemberVO객체가 저장된 List객체
	 */
	// 쿼리문에 넣어줄 데이터 없으므로 매개변수 없어도 됨
	public List<MemberVO> getAllMember();

	
	/**
	 * 회원ID를 인수값으로 받아서 해당 회원ID의 갯수를 반환하는 메서드
	 * 
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원ID의 갯수
	 */
	public int getMemberCount(String memId);
	
	
	/**
	 * Map의 정보를 이용하여 회원 정보 중 원하는 컬럼을 수정하는 메서드
	 * 		key값 ==> 회원ID(id), 수정할컬럼명(field), 수정할데이터(data) 	
	 * 
	 * @param paramMap 회원ID, 수정할 컬럼명, 수정할데이터가 저장된 Map객체 
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember2(Map<String, String> paramMap);
	
}
