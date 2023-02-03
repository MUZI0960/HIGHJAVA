package kr.or.ddit.test.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member/memberInfo.do")
public class MemberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//	DB에 관련된 작업을 수행한 후 View 페이지로 가져갈 데이터를 구성한다.
		
		request.setAttribute("name", "dispatcher_홍길동");
		request.setAttribute("age", 30);
		/*
			- 레이아웃의 각 영역(예: top영역, left영역)이 변경되면 해당 페이지를 다음과 같이 설정한다.
			1) top영역은 'topPage'라는 key값에 저장한다.
				예) request.setAttribute("topPage", "/layout/header.jsp");
			2) left영역은 'leftPage'라는 key값에 저장한다.
				예) request.setAttribute("leftPage", "/layout/left.jsp");
			
		*/
		
		// View 페이지 정보를 'viewPage'라는 key값에 저장한다.
		request.setAttribute("viewPage", "/WEB-INF/view/member/memberInfo.jsp");
		
		// 레이아웃을 처리하는 페이지로 forwarding한다. (이 부분의 페이지는 바뀌지 않는다.)
		request.getRequestDispatcher("/layout/layout.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
