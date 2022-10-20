<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>가입 정보 입력</h1>
	<form action="join" method="post">
		<input name="memEmail" type="text" placeholder="이메일" required> <br><br>
		<input name="memPw" type="password" placeholder="비밀번호"  required> <br><br>
		<input name="memNick" type="text" placeholder="닉네임" required> <br><br>
		<input name="memTel" type="tel" placeholder="전화번호" required> <br><br>
		<select name="memRoute" required > 
			<option>SNS</option>
			<option>검색</option>
			<option>친구추천</option>
			<option>광고</option>
			<option>기타</option>
		</select>
		<button>가입하기</button>
	</form>


</body>
</html>