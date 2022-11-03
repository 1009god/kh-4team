<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="회원 탈퇴" name="title"/>
</jsp:include>

<style>
	.solid-lines {
	  border: 1px solid gray;
	}
	
	.w-30 {
    width:30%;
	}
	
	.mt-100 { margin-top: 100px;}
	.p-80 {padding:80;}
</style>

<div class="container-300 mt-100solid-lines p-80 w-30">
<h1 align="center">회원 탈퇴</h1>
<form action="goodbye" method="post">
	<div class="row center">
		<input class="input w-100 solid-lines mt-30"  type="password" name="memPw" placeholder="비밀번호를 입력해 주세요" required>
	</div>
	<div class="row center">
		<button class="btn btn-positive w-100" type="submit">탈퇴하기</button>
	</div>
</form>
</div>

<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
