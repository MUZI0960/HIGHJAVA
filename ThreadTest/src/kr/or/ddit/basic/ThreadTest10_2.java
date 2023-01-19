package kr.or.ddit.basic;

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

public class ThreadTest10_2 {

	public static void main(String[] args) {
		Horse1[] horses = new Horse1[] {
			new Horse1("1번말"),	
			new Horse1("2번말"),	
			new Horse1("3번말"),	
			new Horse1("4번말"),	
			new Horse1("5번말"),	
			new Horse1("6번말"),	
			new Horse1("7번말"),	
			new Horse1("8번말"),	
			new Horse1("9번말"),	
			new Horse1("10번말")	
		};

		LocationHorse lh = new LocationHorse(horses);
		
		for(Horse1 h : horses) {
			h.start();
		}
		lh.start();
		
		for(Horse1 h : horses) {
			try {
				h.join();
			}catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		try {
			lh.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println();
		System.out.println("[경기 종료]");
		System.out.println();
		
		System.out.println("[경기 결과]");
		for(Horse1 h : horses) {
			System.out.println(h.getHorseName()+"은 "+h.getRank()+"등 입니다.");
		}
		
	}

}

class Horse1 extends Thread implements Comparable<Horse1>{
	private String horseName;
	private int rank;
	private int location;
	
	public static int currentRank = 0;
	
	Random rnd = new Random();
	
	public Horse1(String horseName) {
		this.horseName=horseName;
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
	public int compareTo(Horse1 h) {
		return Integer.compare(rank, h.getRank());
	}
	
	@Override
	public void run() {
		for(int i = 1; i<=50; i++) {
			location = i;
			try {
				sleep(rnd.nextInt(400)+100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		rank = Horse1.currentRank++;
	}
}

class LocationHorse extends Thread{
	private Horse1[] horses;
	
	public LocationHorse(Horse1[] horses) {
		this.horses = horses;
	}
	
	@Override
	public void run() {
		while(true) {
			if(Horse1.currentRank == horses.length) {
				break;
			}
			System.out.println();
			System.out.println();
			for(int i = 0; i<horses.length; i++) {
				System.out.print(horses[i].getHorseName()+" : ");
				for(int j = 1; j<=50; j++) {
					if(horses[i].getLocation() == j) {
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		
	}
}
