<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="회원 정보 수정" name="title"/>
</jsp:include>

<form action="change" method="post">
<input name="" value="${memdto.memNo}" type="hidden">

<div class="container-300 mt-40">
	<div class="row center">
		<h1>회원 정보 수정</h1>
	</div>
	
	<div class="row left">
		<label>회원 닉네임</label>
		<input class="input w-100" name="name" value="${dto.memNick}" autocomplete="off">
	</div>
	
</div>
</form>

<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>
