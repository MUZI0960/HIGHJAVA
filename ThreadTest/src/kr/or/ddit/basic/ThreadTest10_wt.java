package kr.or.ddit.basic;

import java.util.Arrays;
import java.util.Random;

/*
10마리의 말들이 경주하는 경마 프로그램을 작성하시오.

말은 Horse라는 이름의 쓰레드 클래스로 작성하는데, 
이 클래스는 말이름(String), 등수(int), 현재 위치(int)를 멤버변수로 갖는다.
그리고 이 클래스는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다. (Comparable 인터페이스 구현)

경주 구간은 1 ~ 50 구간으로 되어있다. 

경기 중 중간중간 각 말들의 위치를 다음과 같이 나타낸다.
예)
01번말 : -->----------------------- x 50
02번말 : ----->-------------------- x 50
		:
10번말 : ---->--------------------- x 50

경기가 끝나면 등수순으로 정렬하여 출력
*/

public class ThreadTest10_wt {

	public static void main(String[] args) {
		Horse[] horses = new Horse[] {
				new Horse("01번말"),
				new Horse("02번말"),
				new Horse("03번말"),
				new Horse("04번말"),
				new Horse("05번말"),
				new Horse("06번말"),
				new Horse("07번말"),
				new Horse("08번말"),
				new Horse("09번말"),
				new Horse("10번말"),
		};		
		
		GameState gs = new GameState(horses);
		
		for(Horse h : horses) {
			h.start();
		}
		gs.start();
		
		for(Horse h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		try {
			gs.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println();
		System.out.println("경기 끝 !");
		System.out.println();

		// 등수의 오름차순 정렬하기
		Arrays.sort(horses); 		// 배열을 이용한 정렬처리
		
		// 결과 출력
		for(Horse h : horses) {
			System.out.println(h);
		}
		
	}

}

class Horse extends Thread implements Comparable<Horse>{
	public static int currentRank;		// 말의 현재 등수를 구하기 위한 변수
	
	private String horseName;	//말이름
	private int rank;			//등수
	private int location;		//현재위치
	
	//생성자
	public Horse(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return horseName + "은(는) "  + rank + "등 입니다.";
	}
	
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(this.rank, horse.getRank());
	}

	@Override
	public void run() {
		Random rnd = new Random();
		// 경주 구간은 1 ~50 구간으로 되어 있다.
		for(int i = 1; i<=50; i++) {
			this.location = i;		//말의 현재위치 저장
			try {
				Thread.sleep(rnd.nextInt(600));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		// 한 마리의 말이 경기가 끝나면 등수를 구해서 저장
		currentRank++;
		this.rank = currentRank;
	}
	
}

// 경기 중에 말의 현재 위치를 나타내는 스레드
class GameState extends Thread{
	private Horse[] horses;		//경주에 참가하는 말들이 저장될 배열
	
	//생성자
	public GameState(Horse[] horses) {
		this.horses = horses;
	}
	@Override
	public void run() {
		while(true) {
			if(Horse.currentRank == horses.length) break;
			
			for(int i = 1; i<=10; i++) {
				System.out.println();
			}
			
			for(int i = 0; i<horses.length; i++) {
				System.out.print(horses[i].getHorseName() + " : ");
				for(int j =1; j<=50; j++) {
					if(horses[i].getLocation() == j) {		//현재 위치
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
				System.out.println(); 		//줄바꿈
			}
			
			try {
				sleep(150);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}

		}	
		}
	
}










