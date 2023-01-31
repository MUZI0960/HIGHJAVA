<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%

List<MemberVO> list = (List<MemberVO>)request.getAttribute("list");

%>




<style type="text/css">
h3{
	text-align: center;
	
}

table {
	margin: 0 auto;
}
#memAddBtn{
	float: right;
}
.header>td{
	text-align: center;
}
</style>

</head>
<body>

<h3>회원 목록 보기</h3>

<table border="1">

<tr>
<td colspan="5"><input type="button" value="회원추가" id="memAddBtn" onclick="location.href='<%=request.getContextPath()%>/member/memberAdd.do'"></td>
</tr>

<tr class="header">
	<td>ID</td>
	<td>비밀번호</td>
	<td>이 름</td>
	<td>전 화</td>
	<td>주 소</td>
</tr>

<%

	if(list == null || list.size() == 0){
		
		%>
		
		<tr>
			<td colspan="5" style="text-align:center;">회원 정보가 없습니다.</td>		
		</tr>
		
		<%
		
	}else{
		for(MemberVO vo : list){
	

%>

<tr>
	<td><%=vo.getMem_id() %></td>
	<td><%=vo.getMem_pass() %></td>
	<td><%=vo.getMem_name() %></td>
	<td><%=vo.getMem_tel() %></td>
	<td><%=vo.getMem_addr() %></td>
</tr>

<%

}
}
		
%>
		
</table>

</body>
</html>