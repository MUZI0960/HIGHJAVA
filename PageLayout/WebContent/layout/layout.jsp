<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /layout/layout.jsp 문서 --%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%-- 
	페이지 레이아웃 처리를 표준 액션 태그 <jsp:include />를 이용하여 처리하는 예제
--%>


<title>Java고급/servlet</title>

<%-- 
	공통으로 사용할 CSS파일과 JavaScript파일을 등록하는 부분
--%>
<jsp:include page="/layout/common_lib.jsp" flush="false"/>

</head>

<body>
	<%
		// 각 레이아웃 영역에 나타낼 문서를 가져오고 지정된 문서가 없을 때 기본적으로 나타낼 문서를 지정하는 부분
		String topPage = (String)request.getAttribute("topPage");
		if(topPage == null || "".equals(topPage)) topPage = "/layout/header.jsp";
		
		String leftPage = (String)request.getAttribute("leftPage");
		if(leftPage == null || "".equals(leftPage)) leftPage = "/layout/left.jsp";
	
		String viewPage = (String)request.getAttribute("viewPage");
		if(viewPage == null || "".equals(viewPage)) viewPage = "/WEB-INF/view/main/main.jsp";
	%>

	<%-- 상단 영역 등록 (거의 고정적인 영역) --%>
	<jsp:include page="<%=topPage %>" flush="false"/>
	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
			<%-- 왼쪽 메뉴 영역 등록 (거의 고정적인 영역) --%>
			<jsp:include page="<%=leftPage %>" flush="false"/>
			</div>
			
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<%-- 
				본문(body) 영역 등록
				==> 이 영역은 서블릿에서 처리한 내용이 나타날 영역
			 --%>
			<jsp:include page="<%=viewPage %>" flush="false"/>
			</div>
			
		</div>
	</div>
</body>
</html>
