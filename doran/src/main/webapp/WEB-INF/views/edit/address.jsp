<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>배송지 수정페이지</h1>

<h2>등록된 배송지</h2>

(배송지목록 출력)

<hr>

<h2>배송지 추가</h2>
<form method ="post">
	<div>
	<label>해당 회원 번호
		<input name="addressMemNo" value="${sessionScope.loginNo}" > 
	</label>
	
	<label>받는 사람
		<input name = "addressName" type="text" required>
	</label>
	</div>
	
	<div>	
	<label>우편주소
		<input name = "addressPost" type="text" required>
	</label>
	</div>
	
	<div>
	<label>주소
		<input name = "addressBasic" type="text" required>
	</label>
	</div>
	
	<div>
	<label>상세주소
		<input name = "addressDetail" type="text" required>
	</label>
	</div>
	
	<div>
	<label>받는 사람 휴대폰 번호
		<input name = "addressTel" type="text" required>
	</label>
	</div>
	
	<div>
	<button type="submit">등록완료</button>
	</div>
	
</form>

</body>
</html>

