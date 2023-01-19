package kr.or.ddit.basic;

/*
 	Singleton패턴 ==> 객체가 1개만 만들어지게 하는 패턴
 	(외부에서 new 명령을 사용하지 못하도록 한다.)
 	
 	- Singleton클래스를 만드는 방법 (필수 구성 요소)
 	1) 자신 class의 참조값이 저장될 변수를 private static으로 선언한다.
 	
 	2) 모든 생성자의 접근 제한자를 private으로 한다.
       (생성자가 없으면 기본 생성자를 반드시 만들어야 한다.)
 	3) 자신 class의 인스턴스를 생성하고 반환하는 메서드를 public static으로 작성한다.
 	   (이 메서드의 이름은 보통  getInstance로 한다.)
*/
public class MySingleton {
	// 1번
	private static MySingleton single;
	
	//2번
	private MySingleton() {
		System.out.println("이것은 생성자 입니다.");
		}
	
	// 3번
	public static MySingleton getInstance() {
		if(single==null) single = new MySingleton();
		return single;
	}
	
	// 기타 이 클래스가 처리할 내용을 작성
	public void displayTest() {
		System.out.println("싱글톤 클래스의 메서드 호출입니다.");
	}
	
	
	
	
	}
