<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 수정페이지</title>
</head>
<body>


<h1>설정</h1>
<%--	이미지 디비랑 연결 어케 하지 ???
	<div class="row left">
		<label>profile img</label>
		<input class="input w-100" type="text" name="profileImg" value="${memDto.이미지}" >
	</div>
 --%>
 <form action="mypage/edit" method="post">
 
	<div class="row">
		<label>Nickname</label>
		<input type="text" name="memNick" value="${memDto.memNick}" required autocomplete="off">
	</div>
	<div class="row center">
		<button type="submit">수정</button>
	</div>
	
</form>

전화번호-> account로  
edit/profile, edit/account(비밀번호, 전화번호),edit/address- table 따로 존재

</body>
</html>