<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>		


<h1>my page</h1>

		
<div class="container-800">
	<div class="row">
			<h1 style="padding-left:20px">${memDto.memEmail} 회원 정보</h1>
	</div>
	<div class="row">
	${memDto.memNo} 회원 번호
	</div>
	<div class="row">
	${memDto.memNick} 회원 닉네임	
	</div>
	<div class="row">
	${memDto.mem} 회원 닉네임	
	</div>
	<div class="row">
	${memDto.memJoinDate} 회원 가입일
	</div>

</div>


</body>
</html>