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
 
 <a href="//edit/profile">프로필</a>
 <a href="/edit/account">계정</a>
 <a href="/edit/address_list">배송지</a>
 
 
 <div>프로필 이미지 수정</div>
 <form method="post">
	<div class="row">
		<label>닉네임</label>
		<input type="text" name="memNick" value="${memDto.memNick}" required autocomplete="off">
		<input type="hidden" name="memNo" value="${memDto.memNo}" > 
	</div>
	<div class="row center">
	
		<button type="submit" >수정</button>
	
	</div>
</form>



</body>
</html>