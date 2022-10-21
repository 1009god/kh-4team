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
	<a href = "/mypage/edit/profile">
		<button type="button" onclick="/mypage/edit/profile">수정</button>
	</a>	
	</div>
	
</form>


</body>
</html>