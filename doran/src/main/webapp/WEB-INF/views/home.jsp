<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도란도란 - 펀딩 사이트</title>
</head>
<body>
	<h1>어서오세요!</h1>
	<!-- 로그인 상태 확인 -->
	<h2>loginId = ${sessionScope.loginId }</h2>
	
	
	<h2><a href="mem/login">회원 로그인</a></h2>
	<h2><a href="mem/logout">회원 로그아웃</a></h2>

</body>
</html>