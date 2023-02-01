package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberAdd.do")
public class MemberAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("view/memberAdd.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		MemberVO vo = new MemberVO();
		
		String memId = request.getParameter("mem_id");
		vo.setMem_id(memId);
		
		String memPass = request.getParameter("mem_pass");
		vo.setMem_pass(memPass);
		
		String memName = request.getParameter("mem_name");
		vo.setMem_name(memName);
		
		String memAddr = request.getParameter("mem_addr");
		vo.setMem_addr(memAddr);
		
		String memTel = request.getParameter("mem_tel");
		vo.setMem_tel(memTel);
		
		
		IMemberService service = MemberServiceImpl.getinstance();
		
		int cnt = service.memberAdd(vo);
		
		response.sendRedirect( request.getContextPath() + "/member/memberList.do" );
//		request.getRequestDispatcher("/view/memberAdd.jsp").forward(request, response);
	}

}
