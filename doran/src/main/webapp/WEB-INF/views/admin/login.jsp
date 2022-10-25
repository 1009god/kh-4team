<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="관리자 로그인" name="title"/>
</jsp:include>

<form action="login" method="post">
<div class="container-300 mt-40">
	<div class="row center">
		<h1>관리자 로그인</h1>
	</div>
	
	<div class="row left">
		<label>아이디</label>
		<input class="input w-100" name="adminEmail" placeholder="이메일" type="text" required>
	</div>	
	
	<div class="row left mt-20">
		<label>패스워드</label>
		<input class="input w-100" name="adminPw" placeholder="비밀번호" type="password" required>
	</div>
	
</div>
	<div class="row center">
		<button class="btn btn-positive w-10">로그인</button>
	</div>

	<div class="row center">
		<a href="#">아이디 찾기</a>
	</div>
	
	<div class="row center">
		<a href="#">비밀번호 찾기</a>
	</div>
	
	<!-- 이상인 경우만 나오는 화면 -->
	<c:if test="${param.error != null}">
	<div class="row center">
		<h2 style="color:red">입력한 정보가 맞지 않습니다</h2>
	</div>
	</c:if>
</div>

</form>

<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>