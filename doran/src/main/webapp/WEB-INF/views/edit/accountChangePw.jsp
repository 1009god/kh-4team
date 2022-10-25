<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
비밀번호 변경 페이지

 <form method="post">
	<div class="row">
		
		<input type="hidden" name="memNo" value="${memDto.memNo}" > 
		
		<div>
			<label>비밀번호</label>
			<input type="text" name="memPw" value="${memDto.memPw}" required autocomplete="off">
		</div>
		
		
	</div>
	
	<div class="row center">	
		<button type="submit" >수정</button>	
	</div>
	
</form>

</body>
</html>