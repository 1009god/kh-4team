<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>계정관련 수정페이지</h1>


 <form method="post">
	<div class="row">
		
		<input type="hidden" name="memNo" value="${memDto.memNo}" > 
		
		<div>
			<label>비밀번호</label>
			<input type="text" name="memPw" value="${memDto.memPw}" required autocomplete="off">
		</div>
		<div>
			<label>전화번호</label>
			<input type="text" name="memTel" value="${memDto.memTel}" required autocomplete="off">
		</div>
		
	</div>
	
	<div class="row center">	
		<button type="submit" >수정</button>	
	</div>
	
</form>

<div>
 회원 탈퇴
<button type="button" onclick="/mypage/goodbye">탈퇴</button>
 
</div>


</body>
</html>