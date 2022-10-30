<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	
	<jsp:param value="1대1문의" name="title"/>
</jsp:include>
    
<style>

</style>
    

<form action="write" method="post">
<div class="container-700 mt-40 mb-50">
	<div class="row">
		<h1>문의글 답변 작성</h1>
	</div>
	<input type="hidden" name="doranQParent" value="${param.doranQParent}">

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