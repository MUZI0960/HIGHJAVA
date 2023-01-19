package kr.or.ddit.basic;

import java.util.Random;
import javax.swing.JOptionPane;


/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
	
	컴퓨터의 가위 바위 보는 난수를 이용해서 구하고
	사용자의 가위 바위 보는 showInputDialog()메서드를 이용해서 입력 받는다.
	
	입력시간은 5초로 제한하고 카운트 다운을 진행한다.
	5초 입력이 없으면 게임에 진 것으로 처리한다.
	
	5초안에 입력에 완료되면 승패를 구해서 결과를 출력한다.
	
	결과 예시) 
	1) 5초 안에 입력을 못 했을 때
		- 결 과 -
	시간 초과로 당신이 졌습니다...
	
	2) 5초 안에 입력이 되었을 경우
	    - 결과 -
	컴퓨터 : 가위
	사용자 : 바위
	결   과 : 당신이 이겼습니다...
*/

public class ThreadTest07 {

	public static void main(String[] args) {
		Thread th1 = new Count();
		Thread th2 = new Game();
		
		th1.start();
		th2.start();
	}

}

// 카운트 다운
class Count extends Thread{
	
	@Override
	public void run() {
		
		for(int i =5; i>=1; i--) {
			
			if(Game.inputCheck==true) {
				return;
			}
			
			System.out.println(i);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	System.out.println("입력 시간이 지났습니다.");
	System.out.println("당신이 졌습니다 !");
	System.exit(0);
		
	}
}

// 가위바위보 입력
class Game extends Thread{
	
	public static boolean inputCheck = false;
//	public static boolean correctInput = false;
	
	@Override
	public void run() {
		int input = 0;
		
		String str = JOptionPane.showInputDialog("가위 / 바위 / 보 입력하세요");
		inputCheck = true;
		
		if(str.equals("가위")) {
			input = 1;
		}else if(str.equals("바위")) {
			input = 2; 
		}else if (str.equals("보")) {
			input = 3;
		}else if (!(str.equals("가위")||str.equals("바위")||str.equals("보"))) {
			System.out.println("정확한 입력이 아닙니다 ! ");
//			correctInput = true;
			return;
		}
		
		Random rnd = new Random();
		int com = rnd.nextInt(3)+1;
		
		if(input==com) {
			System.out.println("당신은 비겼습니다.");
		}else if ((input==1 && com==3) || (input==2 && com==1) || (input==3 && com==2)) {
			System.out.println("당신은 이겼습니다.");
		}else {
			System.out.println("당신은 졌습니다.");
		}
		
		String comOutput = "";
		
		if(com==1) {
			comOutput = "가위";
		}else if (com==2) {
			comOutput = "바위";
		}else if (com==3) {
			comOutput = "보";
		}
		
		System.out.println("--- 결과 ---");
		System.out.println("당   신 : " + str);
		System.out.println("컴퓨터 : " + comOutput);
		System.out.println("----------");
		
	}
	
}



