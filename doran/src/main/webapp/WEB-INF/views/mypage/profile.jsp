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
		<img src="https://w7.pngwing.com/pngs/987/118/png-transparent-computer-icons-login-user-profile-others-computer-logo-desktop-wallpaper-thumbnail.png" width="100" height="100">
		<h1 style="padding-left:20px">${memDto.memNo} 회원 정보</h1>
		<a href="/edit/profile"><button>수정</button></a>
	</div>
	<div class="row">
	${memDto.memNo} 회원 번호
	</div>
	<div class="row">
	${memDto.memNick} 회원 닉네임	
	</div>
	<div class="row">
	${memDto.memTel} 회원 번호
	</div>	
	<div class="row">
	${memDto.memJoinDate} 회원 가입일
	</div>	
	

</div>


</body>
</html>