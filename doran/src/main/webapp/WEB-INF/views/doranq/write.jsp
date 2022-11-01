<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="1대1문의 작성" name="title"/>
</jsp:include>
    
<style>
	.solid-lines {
	  border: 1px solid gray;
	}
</style>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<script>
	$(function(){
		$("[name=doranQContent]").summernote({
			height:350,
			minHeight:350,
		});
	});
</script>
    

<form action="write" method="post">
<div class="container-800 mt-40 mb-50">
	<div class="row">
		<h1>문의글 작성</h1>
	</div>
	<div class="row">
		<select class="input solid-lines" name="doranQType">
			<option value="취소문의">취소문의</option>
			<option value="배송문의">배송문의</option>
			<option value="반품문의">반품문의</option>
			<option value="교환/환불문의">교환/환불문의</option>
			<option value="오류문의">오류문의</option>
			<option value="기타문의">기타문의</option>
		</select>

	</div>
<div class="container-800">
	<div class="row left">
		<input class="input w-100 solid-lines"  type="text" name="doranQTitle" required placeholder="제목을 입력하세요">
	</div>
	<div class="row left">
		<textarea name="doranQContent"></textarea>
	</div>
	<div class="row right">
		<button class="btn btn-positive" type="submit"><img src="/img/pencil.png" width="20" height="20"></button>
	</div>
</div>
</div>


</form>