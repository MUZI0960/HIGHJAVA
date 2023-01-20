package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.api.pipe.Tube;

/**
 * Servlet implementation class RequestTest02
 */
@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int num1 = Integer.parseInt( request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		String calc = request.getParameter("calc");
		
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>RequestTest02</title></head>");
		out.println("<body>");
		
		out.println("<h2>계산 결과</h2><hr>");
		
		double result = 0;
		
		boolean calcOK = true; // 계산의 성공여부가 저장될 변수
		
		switch (calc) {
		case "+": result = num1+num2; break;
		case "-": result = num1-num2; break;
		case "*": result = num1*num2; break;
		case "/": 
			if(num2==0) {
				calcOK =false;
			}else {
				result = (double)num1/num2; 
			}
			break;
		case "%": 
			if(num2==0) {
				calcOK =false;
			}else {
				result = num1%num2;
			}
			 break;
		}
		
//		out.println("<p>" + num1 + calc + num2 + " = " + result );
		 out.printf("%d %s %d = ", num1,calc, num2);
		 
		 if(calcOK==true) {
			 out.println(result);
		 }else {
			out.println("게산 불가능");
		}
		
		out.println("</body>");
		out.println("</html>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
