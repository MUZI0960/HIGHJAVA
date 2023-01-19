package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.CSS;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IJdbcBoardService;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardController {
	private IJdbcBoardService service;
	private Scanner scan;
	
	
	public JdbcBoardController() {
		scan = new Scanner(System.in);
		service = BoardServiceImpl.getInstance();
		
	}
	
	public static void main(String[] args) {
		new JdbcBoardController().boardStart();
	}

	// 시작 메서드
	private void boardStart() {
		String serarchData = null;
		while(true) {
			int choice = displayMenu(serarchData);
			
			switch (choice) {
			case 1: insertBoard(); serarchData = null; break;
			case 2: viewBoard(); serarchData = null; break;
			case 3: serarchData = serachBoard(); break;
			case 0: System.out.println("게시판 종료");

			default: System.out.println("번호를 잘못 입력했습니다. 다시 입력");
				
			}
		}
		 
	}

	private String serachBoard() {
		scan.nextLine();
		System.out.println();
		System.out.println("검색 작업");
		System.out.println("--------------------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		return scan.nextLine();
		
	}

	// 게시글 내용을 보여주는 메서드
	private void viewBoard() {
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int boardNo = scan.nextInt();
		
		JdbcBoardVO boardVo = service.getBoard(boardNo);
		if(boardVo == null) {
			System.out.println(boardNo + "번 게시글이 존재하지 않습니다. . .");
			return;
		}
		
		System.out.println();
		System.out.println(boardNo+ "번 게시글 내용");
		System.out.println("------------------------------------------------------------");
		System.out.println("- 제  목 : " + boardVo.getBoard_title());
		System.out.println("- 작성자 : " + boardVo.getBoard_writer());
		System.out.println("- 작성일 : " + boardVo.getBoard_date());
		System.out.println("- 조회수 : " + boardVo.getBoard_cnt());
		System.out.println("------------------------------------------------------------");
		System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
		System.out.print("작업선택 >> ");
		int choice = scan.nextInt();
		
		switch (choice) {
		case 1: updateBoard(boardNo); break; 	// 수정
		case 2: deleteBoard(boardNo); break;	// 삭제
		case 3:  return;

		default:
			break;
		}
		
	}
	
	// 게시글 삭제하는 메서드
	private void deleteBoard(int boardNo) {
		int cnt = service.deleteBoard(boardNo);
		
		if(cnt>0){
			System.out.println(boardNo + "번 글을 삭제했습니다.");
		}else {
			System.out.println(boardNo + "번 삭제 작업 실패 . . .");
		}
		
	}

	// 게시글을 수정하는 메서드
	private void updateBoard(int boardNo) {
		scan.nextLine(); // 입력 버퍼 비우기
		System.out.println();
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 내  용 : ");
		String content = scan.nextLine();
		
		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_no(boardNo);
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		
		int cnt = service.updateBoard(boardVo);
		
		if(cnt>0){
			System.out.println(boardNo + "번 글을 수정했습니다.");
		}else {
			System.out.println(boardNo + "번 수정 작업 실패 . . .");
		}
		
	}

	// 새 글 작성해서 저장하는 메서드
	private void insertBoard() {
		scan.nextLine(); // 입력 버퍼 비우기

		System.out.println();
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 작성자 : ");
		String writer = scan.nextLine();
		
		System.out.print("- 내  용 : ");
		String content = scan.nextLine();
												
		// 입력 받은 정보를 VO에 담는다.
		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt>0) {
			System.out.println("새 글이 추가되었습니다.");
		}else {
			System.out.println("새 글 추가 실패...");
		}
		
	}

	// 게시판 목록을 보여주고 메뉴를 출력하고 작업 번호를 반환하는 메서드
	private int displayMenu(String serarchData) {
		List<JdbcBoardVO> boardList;
		if(serarchData == null) {
		// 게시판  전체목록 가져오기
		boardList = service.getAllboardList();
		}else {
			// 검색한 목록 가져오기
			 boardList = service.getSearchBoardList(serarchData);
			 System.out.println();
			 System.out.println();
			 System.out.println("[" + serarchData + "] 로 찾은 결과입니다.");
		}
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수   ");
		System.out.println("-------------------------------------------------------------");
		
		if(boardList == null || boardList.size() == 0) {
			System.out.println(" 출력할 게시글이 하나도 업습니다.");
		}else {
			for(JdbcBoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t"
						+ boardVo.getBoard_title() + "\t"
						+ boardVo.getBoard_writer() + "\t"
						+ boardVo.getBoard_cnt());
			}
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업 선택 >> ");
		return scan.nextInt();
	}
	
}
