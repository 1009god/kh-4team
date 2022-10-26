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


 
	<div class="row">
		
		<input type="hidden" name="memNo" value="${memDto.memNo}" > 
		
		<div>
			<label>비밀번호</label>
			<input type="text" name="memPw" value="${memDto.memPw}" required autocomplete="off">
		</div>
		<div class="row center">	
			<button type="button" ><a href="/edit/account_change_pw">수정</a></button>	
		</div>
		<div>
			<label>전화번호</label>
			<input type="text" name="memTel" value="${memDto.memTel}" required autocomplete="off">
		</div>
		<div class="row center">	
			<button type="button" ><a href="/edit/account_change_tel">수정</a></button>	
		</div>
		
	</div>
	
	


<div>
 회원 탈퇴
<button type="button" ><a href="/mypage/goodbye_content">탈퇴</a></button>
 
</div>


</body>
</html>