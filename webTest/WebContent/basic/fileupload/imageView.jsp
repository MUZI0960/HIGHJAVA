<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Servlet으로 이미지 파일 불러오기</h3>
<img src="../images/sky.jpg" width="300"><br>
<img src="<%=request.getContextPath() %>/basic/images/sky.jpg" width="300">
<hr>
<img src="<%=request.getContextPath()%>/imageView.do?fileNo=3" width="300"><br>

</body>
</html>