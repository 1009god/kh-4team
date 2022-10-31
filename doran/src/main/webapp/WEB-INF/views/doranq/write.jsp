<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="1대1문의 작성" name="title"/>
</jsp:include>
    
<style>

</style>
    

<form action="write" method="post">
<div class="container-700 mt-40 mb-50">
	<div class="row">
		<h1>문의글 작성</h1>
	</div>
	<div class="row">
		<select name="doranQType">
			<option value="취소문의">취소문의</option>
			<option value="배송문의">배송문의</option>
			<option value="반품문의">반품문의</option>
			<option value="교환/환불문의">교환/환불문의</option>
			<option value="오류문의">오류문의</option>
			<option value="기타문의">기타문의</option>
		</select>
	</div>
	<div class="row">
		<input class="w-100"  type="text" name="doranQTitle" required placeholder="제목을 입력하세요">
	</div>
	<div class="row">
		<textarea name="doranQContent" class="w-100" rows="10" cols="80" required placeholder="내용을 입력하세요"></textarea>
	</div>
	<div class="row right">
		<button class="btn btn-positive" type="submit">글 작성</button>
	</div>
</div>

</form>