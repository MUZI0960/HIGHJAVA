package kr.or.ddit.member.controller;

import java.io.IOException;
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
		request.setCharacterEncoding("utf-8");
		
		MemberVO vo = new MemberVO();
		
		String memId = request.getParameter("id");
		vo.setMem_id(memId);
		
		String memPass = request.getParameter("pass");
		vo.setMem_pass(memPass);
		
		String memName = request.getParameter("name");
		vo.setMem_name(memName);
		
		String memAddr = request.getParameter("addr");
		vo.setMem_addr(memAddr);
		
		String memTel = request.getParameter("tel");
		vo.setMem_tel(memTel);
		
		
		IMemberService service = MemberServiceImpl.getinstance();
		
		String cnt = service.memberAdd(vo);
		
		request.setAttribute("list", cnt);
		
		request.getRequestDispatcher("/view/memberAdd.jsp").forward(request, response);
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
