<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#send').on('click', function(){
		
		
		id = $('#memId').val().trim();
		pass = $('#memPass').val().trim();
		name = $('#memName').val().trim();
		tel = $('#memTel').val().trim();
		addr = $('#memAddr').val().trim();
		
		
		$.ajax({
			
			url : '<%=request.getContextPath()%>/member/memberAdd.do',
			type : 'get',
			data : {	"id" : id,
						"pass" : pass,
						"name" : name,
						"tel" : tel,
						"addr" : addr
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			success : function(res){
				alert("회원가입 성공");
			},
			dataType: 'json'
			
		})
		
	})
	
})
</script>

<style type="text/css">
table{
 margin : 0 auto;
}
.btnTd{
	text-align: center;
}
</style>

</head>
<body>

<form action="<%=request.getContextPath()%>/member/memberAdd.do" method="get">
<table border="1">

<tr>
	<td>회원ID</td>
	<td><input type="text" name = "memId" id="memId"><input type="button" value="중복확인"></td>
</tr>

<tr>
	<td>비밀번호</td>
	<td><input type="text" name="memPass" id="memPass"></td>
</tr>
<tr>
	<td>비밀번호 확인</td>
	<td><input type="text"></td>
</tr>
<tr>
	<td>회원이름</td>
	<td><input type="text" name="memName" id="memName"></td>
</tr>
<tr>
	<td>전화번호</td>
	<td><input type="text" name="memTel" id="memTel"></td>
</tr>
<tr>
	<td>회원주소</td>
	<td><input type="text" name="memAddr" id="memAddr"></td>
</tr>

<tr>
	<td colspan="2">
	<input type="button" id="send" value="저 장">
	<input type="reset" value="취 소">
	<input type="button" value="회원목록">
	</td>
</tr>

</table>
</form>
</body>
</html>