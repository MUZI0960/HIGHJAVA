package kr.or.ddit.test.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/city/cityList.do")
public class CityList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		List<String> strList = new ArrayList<String>();
		strList.add("서울");
		strList.add("대전");
		strList.add("울산");
		strList.add("광주");
		request.setAttribute("cityList", strList);
		
		
		request.setAttribute("viewPage", "/WEB-INF/view/city/cityList.jsp");
		
		
		request.getRequestDispatcher("/layout/layout.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
