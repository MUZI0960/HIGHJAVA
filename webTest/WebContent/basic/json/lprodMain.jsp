<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 

	'Lprod자료 가져오기'버튼을 클릭하면 DB에 저장된 LPROD테이블의 전체 데이터를 
	가져와서 아래의 id속성값이 'result'인 div태그 영역에 출력하는 페이지를 작성하시오.
	
	Ajax를 사용하고 응답 데이터는 JSON으로 받아서 처리하시오.

 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<form>
	<input type="button" id="lprodBtn" value="Lprod자료 가져오기">
</form>
<h3>Lprod 자료 목록</h3>
<div id="result"></div>
</body>
</html>