<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>

<script type="text/javascript">
$(function() {
	$("#btnList").on("click", function(){
		location.href="<%=request.getContextPath()%>/member/memberList.do";
	});
})



</script>

</head>
<body>

<h2>회원 정보 입력 폼</h2>
<form id="memberForm" method="post" 
action="<%=request.getContextPath()%>/member/memberInsert.do" 
enctype="multipart/form-data">
<table border="1">
<tbody>
	<tr>
		<td>회원ID</td>
		<td>
			<input type="text" name="mem_id" id="mem_id">
			<input id="idCheck" type="button" value="중복확인"><br>
			<span id="idChkResult"></span>
		</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="mem_pass" id="mem_pass"></td>
	</tr>
	<tr>
		<td>비밀번호 확인</td>
		<td><input type="password" name="mem_pass2" id="mem_pass2"></td>
	</tr>	
	<tr>
		<td>회원이름</td>
		<td><input type="text" name="mem_name"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" name="mem_tel"></td>
	</tr>
	<tr>
		<td>회원주소</td>
		<td><input type="text" name="mem_addr"></td>
	</tr>
	<tr>
		<td>프로필 사진</td>
		<td><input type="file" name="mem_photo"></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;">
			<input type="submit" value="저장"> <input type="reset" value="취소">
			<input type="button" id="btnList" value="회원목록"></td>
	</tr>
</tbody>
</table>
</form>

</body>
</html>