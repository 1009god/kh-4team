<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="회원 정보 수정" name="title"/>
</jsp:include>

<form action="change" method="post">
<input name="memNo" value="${memdto.memNo}" type="hidden">

<div class="container-300 mt-40">
	<div class="row center">
		<h1>회원 정보 수정</h1>
	</div>
	
	<div class="row left">
		<label>회원 닉네임</label>
		<input class="input w-100" type="text" name="memNick" value="${memdto.memNick}" autocomplete="off">
	</div>
	<div class="row right">
		<a class="btn btn-neutral" href="/admin/memlist">목록으로</a>
		<button class="btn btn-positive" type="submit">수정하기</button>
	</div>
</div>
	
</div>
</form>

<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>
