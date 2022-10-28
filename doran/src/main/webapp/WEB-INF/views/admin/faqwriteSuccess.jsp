<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="faq작성 완료" name="title"/>
</jsp:include>

<div class="container-700 mt-40">
	<div class="row center">
		<h1>작성 완료 하셨습니다.</h1>
	</div>
</div>
	<div class="container-200 mt-40>">
	<div class="row">
		<a href="/" class="btn btn-neutral w-100">메인페이지</a>
	</div>
	<div class="row">
		<a href="faqlist" class="btn btn-neutral w-100">목록</a>
	</div>
</div>
<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>