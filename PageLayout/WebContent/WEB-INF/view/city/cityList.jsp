<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 
	View 페이지에서는 <body>태그 영역에 출력할 내용을 작성하면 된다.
	그래서 <head>태그와 <body>태그등을 생략한다. 
--%>


<h2>도시 리스트</h2>
<%
	List<String> cityList = (List<String>)request.getAttribute("cityList");
%>

<%
	for(String city : cityList){
		out.println(city + "<br>");
	}
%>
