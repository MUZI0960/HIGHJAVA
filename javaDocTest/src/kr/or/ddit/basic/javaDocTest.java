package kr.or.ddit.basic;
// JavaDoc 파일 만들기 예제 ==> 프로그램과 메뉴얼을 같이 만드는 방법

/**
 * 
 * @author 홍길동
 * @version 1.0
 * 
 * <p>
 * 파일명 : JavaDocTest.java<br>
 * 설 명 : JavaDoc문서 작성을 위한 연습용 interface<br><br>
 * 
 * 수정 이력<br>
 * -----------------------------------------<br>
 * 수정 일자 : 2023-01-10
 * 작 성 자 : 홍길동<br>
 * 변경 내용 : 최초 작성<br>
 * -----------------------------------------<br>
 * </p>
 *
 */
 
public interface javaDocTest {
	/**
	 * 메서드명 : methodTest<br>
	 * 설 명 : 반환값이 없는 메서드<br>
	 * 
	 * @param a 첫번째 매개변수 (정수형)
	 * @param b 두번째 매개변수 (정수형)
	 */
	public void methodTest(int a, int b);
	
	/**
	 * 메서드명 : methodAdd<br>
	 * 설 명 : 반환값이 있는 메서드<br> 
	 * @param x 정수형 첫번째 데이터
	 * @param y 정수형 두번째 데이터
	 * @return 처리된 결과를 정수형으로 반환
	 */
	
	public int methodAdd(int x, int y);
	
	
	/**
	 * 메서드명 : methodInput<br>
	 * 설 명 : 매개변수가 없는 메서드<br>
	 * 
	 * @return
	 */
	public int methodInput();
	
}







