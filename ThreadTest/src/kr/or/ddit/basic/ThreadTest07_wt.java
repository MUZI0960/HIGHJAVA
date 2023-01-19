package kr.or.ddit.basic;

import java.util.Random;
import javax.swing.JOptionPane;

public class ThreadTest07_wt {

	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		GameTimer gt = new GameTimer();
		
		Random rnd = new Random();
		// 난수 이용 컴퓨터의 가위바위보  정하기
		String[] data = {"가위", "바위", "보"};
		int index = rnd.nextInt(data.length); 	// 0 ~ 2 사이의 난수 만들기
		String com = data[index];		// 배열에서 난수번째 데이터를 컴퓨터의 가위 바위 보로 정한다.
		
		// 사용자의 가위 바위 보 입력 받기
		gt.start();  // 카운트다운 시작
		String user =null;
		do {
		user = JOptionPane.showInputDialog("가위 바위 보 입력");
		}while( ! ("가위".equals(user) || "바위".equals(user) || "보".equals(user)));
		
		inputCheck = true;
		
		// 승패 판정
		String result = "";
		if(com.equals(user)) {
			result = "비겼습니다. . .";
		}else if ( user.equals("가위") && com.equals("보") || 
				   user.equals("바위") && com.equals("가위") ||
				   user.equals("보") && com.equals("바위")) {
			result = "당신이 이겼습니다 !";		
		}else {
			result = "당신이 졌습니다 . . .";
		}
		// 결과 출력
		System.out.println("----- 결 과 -----");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + user);
		System.out.println("결   과 : " + result);
	}
}

class GameTimer extends Thread{
	@Override
	public void run() {
		System.out.println("카운트다운 시작 ! ");
		for(int i = 5; i>=1; i--) {
			if(ThreadTest07_wt.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println();
		System.out.println(" ----- 결 과 -----");
		System.out.println("시간 초과로 당신이 졌습니다. . .");
		System.exit(0);
	}
}

