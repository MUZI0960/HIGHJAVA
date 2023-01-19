package kr.or.ddit.mvc.controller;

import java.util.List;
import java.util.Scanner;

import javax.swing.border.Border;

import kr.or.ddit.mvc.service.BoardServiceImpl;
import kr.or.ddit.mvc.service.IBoardService;
import kr.or.ddit.mvc.vo.BoardVO;

public class BoardController {
	private IBoardService service;
	private Scanner scan;
	
	public BoardController() {
		scan = new Scanner(System.in);
		service = new BoardServiceImpl();
	}
	
	public static void main(String[] args) {
		new BoardController().startBoard();
	}

	// 시작 메서드
	private void startBoard() {
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수   ");
		System.out.println("-------------------------------------------------------------");
		
		while(true) {
		viewBoardList();
		int choice = displayMenu();
		switch (choice) {
		case 1:
			insertPost(); break;
		case 2:
			viewPost(); break;
		case 3:
			searchPost(); break;
		case 0:
			System.out.println("프로그램 종료");
			return;

		default:
			System.out.println("번호를 잘못입력했습니다. 다시입력");
		
		}
		}	
	}

	private void searchPost() {
		System.out.println("검색 작업");
		System.out.println("--------------------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		String searchText = scan.next();
		
		List<BoardVO> postList = service.searchBoard(searchText);
		if(postList==null || postList.size()==0) {
			System.out.println("검색 결과가 없습니다 . . .");
		}else {
			for(BoardVO boardVo : postList) {
				System.out.println("- 제  목 : " + boardVo.getBoard_title());
				System.out.println("- 작성자 : " + boardVo.getBoard_writer());
				System.out.println("- 내  용 : " + boardVo.getBoard_content());
				System.out.println("- 작성일 : " + boardVo.getBoard_date());
				System.out.println("- 조회수 : " + boardVo.getBoard_cnt());
				System.out.println("--------------------------------------------");
			}
			System.out.println("--------------------------------------------");
		}
		
		
	}

	private void viewPost() {
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int choice = scan.nextInt();
		
		
		//int cnt = service.updateCnt(choice);
		
		
		System.out.println(choice+"번글 내용");
		System.out.println("------------------------------------------------------------");
		
		List<BoardVO> viewList = service.viewContent(choice);
		
		for(BoardVO boardVo2 : viewList) {
			System.out.println("- 제  목 : " + boardVo2.getBoard_title());
			System.out.println("- 작성자 : " + boardVo2.getBoard_writer());
			System.out.println("- 내  용 : " + boardVo2.getBoard_content());
			System.out.println("- 작성일 : " + boardVo2.getBoard_date());
			System.out.println("- 조회수 : " + boardVo2.getBoard_cnt());
		}
		System.out.println("-------------------------------------------------------------");
	}

	private void insertPost() {
		System.out.println();
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		String title = scan.next();
		
		System.out.print("- 작성자 : ");
		String writer = scan.next();
		
		System.out.print("- 내  용 : ");
		String content = scan.next();
		
		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt>0) System.out.println("등록 작업 성공 !");
		else 	  System.out.println("등록 작업 실패 . . .");
		System.out.println("-----------------------------------");
		
	}

	private int displayMenu() {
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업선택 >> ");
		return scan.nextInt();
	}

	private void viewBoardList() {
		List<BoardVO> boardList = service.boardLists();
		if(boardList==null || boardList.size()==0) {
			System.out.println("등록된 게시물이 없습니다.");
		}else {
			for(BoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no()+"\t"
									+ boardVo.getBoard_title()+"\t"
									+ boardVo.getBoard_writer()+"\t"
									+ boardVo.getBoard_cnt()+"\t");
			}
		}
		System.out.println("-------------------------------------------------------------");
	
		//detaileMenu();
	
	}

	private void detaileMenu() {
		System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
		System.out.print("작업선택 >> ");
		int choice = scan.nextInt();
		
		switch (choice) {
		case 1: updatePost(); break;
		// case 2: deletePost; break;
		case 3: 
			System.out.println("게시글 목록으로 돌아갑니다");
			return;

		default:
			System.out.println("잘못 입력했습니다. 다시입력");
		}
		
	}

	private void updatePost() {
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		String title = scan.next();
		System.out.print("- 내  용 : ");
		String content = scan.next();
		
	}

}
