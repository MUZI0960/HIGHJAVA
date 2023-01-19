package kr.or.ddit.basic;

public class SingletonTest {

	public static void main(String[] args) {
		// 외부에서 new 명령 사용 불가
//		MySingleton test = new MySingleton();
		
		MySingleton test2 = MySingleton.getInstance();
		MySingleton test3 = MySingleton.getInstance();
		// 두 개 만들어도 생성자는 하나만 생성됨
		
		System.out.println("test2 ==> " + test2.toString());
		System.out.println("test3 ==> " + test3.toString());
		System.out.println();
		System.out.println(test2.equals(test3));
		System.out.println(test3 == test2);
		
		test2.displayTest();
		
		
	}

}
