<%@page import="kr.or.ddit.vo.LprodVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// 컨트롤러가 보내준 데이터 받기
	List<LprodVO> lprodlist = 
		(List<LprodVO>)request.getAttribute("LprodList");

%>
<h3>Lprod 자료 목룍</h3>
<table border="1">
<tr>
	<td>LPROD_ID</td><td>LPROD_GU</td><td>LPROD_NM</td>
</tr>

<%

	for(LprodVO lvo : lprodlist){
		
		%>
		
		<tr>
			<td><%=lvo.getLprod_id() %></td>
			<td><%=lvo.getLprod_gu() %></td>
			<td><%=lvo.getLprod_nm() %></td>
			
		</tr>
		
		<%
		
	}

%>

</table>


</body>
</html>