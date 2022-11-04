<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="회원 가입 완료" name="title"/>
</jsp:include>

<div class="container-700 mt-40">
	<div class="row center">
		<h1>회원 가입이 완료되었습니다</h1>
	</div>
</div>
	<div class="container-200 mt-40>">
	<div class="row">
		<a href="${pageContext.request.contextPath}/" class="btn btn-neutral w-100">메인페이지</a>
	</div>
	<div class="row">
		<a href="login" class="btn btn-neutral w-100">관리자 로그인</a>
	</div>
</div>
<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>