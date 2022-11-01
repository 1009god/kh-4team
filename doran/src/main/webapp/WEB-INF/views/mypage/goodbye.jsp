<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="회원 탈퇴" name="title"/>
</jsp:include>

<div class="container-500">
 	<div class="row center">

<h1>비밀번호 확인</h1>

<form action="goodbye" method="post">
	<input type ="password"  name="memPw" required>
	<button type="submit">탈퇴하기</button>
</form>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
