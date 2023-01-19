package kr.or.ddit.basic;

// 	은행의 입출금을 쓰레드로 처리하는 예제 (동기화 처리 예제)

public class ThreadTest16 {
	private int balance;		// 잔액이 저장될 변수
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금을 처리하는 메서드
	public synchronized void deposit(int money) {
		balance += money;
	}
	
	// 공통으로 사용하는 중요한 내용 -> 동기화 처리 해주는 것이 좋음
	
	// 출금을 처리하는 메서드 (반환값 : 출금 성공(true), 출금 실패(false))
//	public synchronized boolean withdraw(int money) {
	public boolean withdraw(int money) {
		
		synchronized(this) {
		
		if(balance>=money) {	// 출금 가능 여부 검사
			
			for(int i = 1; i<=100_000_000; i++) { } // 시간 지연용
			
			balance -= money;
			System.out.println("메서드 안에서 balance = " + balance);
			return true;
		}else {
			return false;
		}
	  } // 동기화 블럭 끝
	}

	public static void main(String[] args) {
		ThreadTest16 test = new ThreadTest16();
		test.setBalance(10000);		// 잔액을 10000원으로 설정
		
		// 익명 구현체로 쓰레드 구현
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				boolean result = test.withdraw(6000);	// 6000원을 출금
				System.out.println("쓰레드에서 출금 성공 여부 : " + result + ", balance : " + test.getBalance());
			}
		};
		//----------------------------
		
		Thread th1 = new Thread(runner);
		Thread th2 = new Thread(runner);
		
		th1.start();
		th2.start();
		
	}

}














