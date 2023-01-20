<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Request연습 Form(숫자 입력은 정수형으로 입력하세요.)</h2>
<hr>

<form action="<%=request.getContextPath() %>/requestTest02.do" method="post">
<input type="number" name="num1">
<select name="calc">
	<option value="+">+</option>
	<option value="-">-</option>
	<option value="*">*</option>
	<option value="/">/</option>
	<option value="%">%</option>
</select>
<input type="number" name="num2">
<input type="submit" value="전송">
</form>
</body>
</html>