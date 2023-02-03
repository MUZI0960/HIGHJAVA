<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>페이지 레이아웃 연습</title>


<!-- common lib 영역 시작 -->

<!-- Bootstrap core CSS 파일 등록 -->
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/resources/css/dashboard.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/resources/css/blog.css" rel="stylesheet">

<!-- javascript 파일 등록 -->
<script defer src="<%=request.getContextPath() %>/resources/js/jquery-3.2.1.min.js"></script>
<script defer src="<%=request.getContextPath() %>/resources/js/bootstrap.js"></script>

<!-- common lib 영역 끝 -->


</head>

<body>
	<!-- top 영역 시작 -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
<%-- 				<a class="navbar-brand" href="<%=request.getContextPath() %>/index.do">JAVA고급/SERVLET</a><span id="loginUser">홍길동</span> --%>
				<a class="navbar-brand" href="<%=request.getContextPath() %>/페이지레이아웃_기본화면.jsp">JAVA고급/SERVLET</a><span id="loginUser">홍길동</span>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Dashboard</a></li>
					<li><a href="#">Settings</a></li>
					<li><a href="#">Profile</a></li>
					<li><a href="#">Help</a></li>
					<li><a href="<%=request.getContextPath() %>/member/login.do">Login 또는 Logout</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>    
	<!-- top 영역 끝 -->
	
	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
			
				<!-- left 영역 시작 -->
				<ul class="nav nav-sidebar">
<%-- 					<li class="active"><a href="<%=request.getContextPath() %>/member/memberInfo.do">회원정보 조회</a></li> --%>
<%-- 					<li class="active"><a href="<%=request.getContextPath() %>/city/cityList.do">도시 리스트</a></li> --%>
					<li class="active"><a href="<%=request.getContextPath() %>/페이지레이아웃_회원정보.jsp">회원정보 조회</a></li>
					<li class="active"><a href="<%=request.getContextPath() %>/페이지레이아웃_도시목록.jsp">도시 리스트</a></li>					<li class="active"><a href="#">메뉴 3</a></li>
					<li class="active"><a href="#">메뉴 4</a></li>
					<li class="active"><a href="#">메뉴 5</a></li>
					<li class="active"><a href="#">메뉴 6</a></li>
				
				</ul>
				<!-- left 영역 끝 -->
			
			</div>
			
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			
				<!-- view(본문) 영역 시작 -->
	
				<h2>회원 정보 조회</h2>

				이름 : dispatcher_홍길동<br>
				나이 : 30<hr>
	
				<!-- view(본문) 영역 끝 -->
			</div>
			
		</div>
	</div>
</body>
</html>
