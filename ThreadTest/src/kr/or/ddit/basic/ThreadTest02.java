package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		// 멀티 쓰레드 프로그램
		
		// Thread 사용방법
		
		// 방법1)
		// Thread클래스를 상속한 class를 작성하고 이 class의 인스턴스 생성한 후
		// 이 인스턴스의 start()메서드를 호출해서 실행
		MyThread1 th1 = new MyThread1(); // 이 class의 인스턴스 생성
		th1.start(); 	// 이 인스턴스의 start()메서드를 호출해서 실행
		
		// 방법 2-1)
		// Runnable인터페이스를 구현한 class를 작성하고 이  class의 인스턴스를 생성
		// 그리고  Thread클래스의 인스턴스를 생성할 때 Runnable을 구현한 클래스의 인스턴스를 생성자에 넣어서 생성
		// 이 때 생성된 Thread클래스의 인스턴스의 start()메서드를 호출해서 실행
		MyRunner1 r = new MyRunner1(); // 이 class의 인스턴스 생성
		Thread th2 = new Thread(r);	// Thread클래스의 인스턴스를 생성할 때 Runnable을 구현한 클래스의 인스턴스를 생성자에 넣어서 생성
		th2.start();	// 생성된 Thread클래스의 인스턴스의 start()메서드를 호출해서 실행
		
		// 방법 2-2) ==> 익명 구현체 (일회성)
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for(int i =1; i<=200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
				}
			}
		};
		Thread th3 = new Thread(r2);
		th3.start();
		
		System.out.println("main메서드 끝 ! ");
	}

}

// 방법1 ==> Thread클래스를 상속한 class를 작성
class MyThread1 extends Thread{
	@Override
	public void run() {
			// 이 run()메서드를 재정의하는데, 이 메서드에는 쓰레드가 처리할 내용을 기술하면 됨
		for(int i =1; i<=200; i++) {
			System.out.print("*");
		try {
			// Thread.sleep(시간)메서드는 주어진 시간동안 작업을 잠시 멈춘다.
			// '시간'은 밀리세컨드 단위 사용 (즉, 1000은 1초 의미) 
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO: handle exception
			}
		}
	}
}

// 방법2 ==> Runnable
class MyRunner1 implements Runnable{
	@Override
	public void run() {
		// 쓰레드가 처리할 내용 기술
		for(int i =1; i<=200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
	}
}







