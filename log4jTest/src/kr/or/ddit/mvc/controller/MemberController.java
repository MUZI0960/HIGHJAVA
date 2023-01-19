package kr.or.ddit.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberController {
	private Scanner scan;
	private IMemberService service; 		// Service객체가 저장될 변수 선언
	
	// 생성자
	public MemberController() {
		scan = new Scanner(System.in);
//		service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new MemberController().startMember();
	}

	// 시작 메서드
		public void startMember() {
			System.out.println();
			System.out.println("*********************************");
			System.out.println("      회   원   관   리   프   로  그   램");
			System.out.println("*********************************");
			System.out.println();
			
			while(true) {
				int choice = displayMenu();
				switch(choice){
					case 1 :		// 자료 추가 
						insertMember(); break;
					case 2 : 		// 자료 삭제
						deleteMember(); break;
					case 3 : 		// 자료 수정
						updateMember(); break;
					case 4 : 		// 전체 자료 출력
						displayAllMember(); break;
					case 5 : 		// 자료 수정2
						updateMember2(); break;					
					case 0 : 		// 프로그램 종료
						System.out.println("작업을 마칩니다");
						return;
					default :
						System.out.println("작업 번호를 잘못 입력했습니다. 다시 입력하세요...");
				}
			}
		}
		
		 private void updateMember2() {
		      System.out.println();
		         System.out.println("수정할 회원 정보를 입력하세요..");
		            
		         System.out.println("회원 id >>");
		         String id = scan.next();
		            
		         int count = service.getMemberCount(id);
		            
		         if(count==0) { // 없는 회원이면..
		         System.out.println(id + "은(는) 등록되지 않은 회원id입니다.");
		         System.out.println("수정 작업을 마칩니다...");
		         return;
		         }
		         
		         String updateField = null;      // 수정할 컬럼명이 저장될 변수
		         int num;                  // 수정할 컬럼명을 선택할 때 사용할 변수
		         String updateTitle = null;      // 수정할 값을 입력 받을 때 나타낼 제목이 저장될 변수
		            
		            do {
		               System.out.println();
		               System.out.println("수정할 항목을 선택하세요...");
		               System.out.println("1.비밀번호    2.회원이름    3.전화번호    4.회원주소 ");
		               System.out.println("-------------------------------------");
		               System.out.println("수정할 항목 선택>>");
		               num = scan.nextInt();
		               
		               switch(num) {
		                     case 1 : updateField = "mem_pass"; updateTitle = "비밀번호"; break;
		                     case 2 : updateField = "mem_name"; updateTitle = "회원이름"; break;
		                     case 3 : updateField = "mem_tel"; updateTitle = "전화번호"; break;
		                     case 4 : updateField = "mem_addr"; updateTitle = "회원주소"; break;
		                     default : System.out.println("수정할 항목을 잘못 선택했습니다. 다시 선택하세요.");
		               }
		               
		            }while(num <1 || num>4);
		            
		            scan.nextLine();    // 입력버퍼비우기
		            System.out.println();
		            System.out.println("수정할 내용을 입력하세요...");
		            System.out.print("새로운 " + updateTitle + ">>");
		            String updateData = scan.nextLine();      
		            
		            // 수정 작업에 필요한 정보들을 Map객체에 추가한다.
		            Map<String, String> paraMap = new HashMap<String, String>();
		            paraMap.put("id", id);
		            paraMap.put("field", updateField);
		            paraMap.put("data", updateData);
		            
		            count = service.updateMember2(paraMap);
		            
		               if(count>0) {
		                     System.out.println("수정 작업 성공");
		                  }else {
		                     System.out.println("수정 작업 실패~~");
		                  }
		   }

		private void displayAllMember() {
			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println(" 회원ID    비밀번호     회원이름    전화번호      회원주소");
			System.out.println("-----------------------------------------");
			
			List<MemberVO> memlist = service.getAllMember();
			if(memlist==null || memlist.size()==0) {
				System.out.println(" 출력할 회원 정보가 하나도 없습니다.");
			}else {
				for(MemberVO memVo : memlist) {
					System.out.println(memVo.getMem_id() + "\t" + 
									   memVo.getMem_pass() + "\t" +
									   memVo.getMem_name() + "\t" +
									   memVo.getMem_tel() + "\t" +
									   memVo.getMem_addr() + "\t");
				}
				System.out.println("-----------------------------------------");
			}
	}


		private void updateMember() {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요...");
			
			System.out.print("회원ID >> ");
			String id = scan.next();
			
			int count = service.getMemberCount(id);
			
			if(count==0) {  // 없는 회원이면...
				System.out.println(id + "은(는) 등록되지 않은 회원ID 입니다.");
				System.out.println("수정 작업을 마칩니다...");
				return;
			}
			
			System.out.println("수정할 내용을 입력하세요...");
			System.out.print("새로운 비밀번호 >> ");
			String newPass = scan.next();
			
			System.out.print("새로운 회원이름 >> ");
			String newName = scan.next();
			
			System.out.print("새로운 전화번호 >> ");
			String newTel = scan.next();
			
			scan.nextLine();
			System.out.print("새로운 회원주소 >> ");
			String newAddr = scan.nextLine();
			
			// 입력받은 수정할 데이터를 VO객체에 저장
			MemberVO memVo = new MemberVO();
			memVo.setMem_id(id);
			memVo.setMem_pass(newPass);
			memVo.setMem_name(newName);
			memVo.setMem_tel(newTel);
			memVo.setMem_addr(newAddr);
			
			int cnt = service.updateMember(memVo);
			
			if(cnt>0) System.out.println("수정 작업 성공 !");
			else 	  System.out.println("수정 작업 실패 . . .");
			
	}


		private void deleteMember() {
			System.out.println();
			System.out.println("삭제할 회원 정보를 입력하세요...");
			System.out.print("회원ID >> ");
			String id = scan.next();
			
			int cnt = service.deleteMember(id);

			if(cnt>0) {
				System.out.println("회원ID가 " + id + "인 회원 정보 삭제 성공!!");
			}else {
				System.out.println("회원ID가 " + id + "인 회원은 없는 회원이거나 삭제에 실패했습니다...");
			}
			
	}


		private void insertMember() {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요...");
			int count = 0;		// 회원ID의 개수가 저장될 변수
			String id = null;	// 입력한 회원ID가 저장될 변수
			
			do {
				System.out.print("회원ID >> ");
				id = scan.next();
				
				count = service.getMemberCount(id);
				
				if(count>0) {
					System.out.println("입력한 " + id + "은(는) 이미 등록된 회원ID입니다.");
					System.out.println("다른 회원ID를 입력하세요...");
					System.out.println();
				}
				
			}while(count>0);
			
			System.out.print("비밀번호 >> ");
			String pass = scan.next();
			
			System.out.print("회원이름 >> ");
			String name = scan.next();
			
			System.out.print("전화번호 >> ");
			String tel = scan.next();
			
			scan.nextLine();	// 입력 버퍼 비우기...
			System.out.print("회원주소 >> ");
			String addr = scan.nextLine();
			
			// 입력한 데이터들을 VO객체에 담기
			MemberVO memVo = new MemberVO();
			memVo.setMem_id(id);
			memVo.setMem_pass(pass);
			memVo.setMem_name(name);
			memVo.setMem_tel(tel);
			memVo.setMem_addr(addr);
			
			int cnt = service.insertMember(memVo);
			
			if(cnt>0) System.out.println("등록 작업 성공 !");
			else 	  System.out.println("등록 작업 실패 . . .");
			
		}


		// 메뉴를 출력하고 작업번호를 입력 받아 반화하는 메서드
		private int displayMenu() {
			System.out.println();
			System.out.println("----------------");
			System.out.println("1. 자료 추가	");
			System.out.println("2. 자료 삭제	");
			System.out.println("3. 자료 수정	");
			System.out.println("4. 전체 자료 출력");
			System.out.println("5. 자료 수정2	");
			System.out.println("0. 프로그램 종료.");
			System.out.println("----------------");
			System.out.print("작업 선택 >> ");
			return scan.nextInt();
		}
		
	
}
