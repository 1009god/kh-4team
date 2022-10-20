<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 창</title>
</head>
<body>
	<div align="center">
		<h1>로그인</h1>
		<form action="login" method="post">
			<input type="text"  name="memEmail" placeholder="이메일" required><br><br>
			<input type="password"  name="memPw" placeholder="비밀번호" required><br><br>
			<button type="submit">로그인</button>	
			</form>
	</div>

</body>
</html>