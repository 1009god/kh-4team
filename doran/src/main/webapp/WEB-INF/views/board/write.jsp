<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="도란도란 게시글 작성" name="title"/>
</jsp:include>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<script>
	$(function(){
		$("[name=boardContent]").summernote({
			height:350,
			minHeight:350,
		});
	});
</script>

<form action="write" method="post" enctype="multipart/form-data">
<div class="container-800 mt-40">
	<div class="row left">
		<label>제목</label>
		<input class="input w-100" type="text" name="boardTitle" required>
	</div>
	<div class="row left">
		<label>내용</label>
		<!-- 
			여러 줄 입력이 가능한 도구
			- rows 는 기본 표시 줄 수
			- cols 는 기본 표시 칸 수 
		-->
		<textarea name="boardContent"></textarea>
	</div>
	<div class="row left">
		<label>첨부파일(1개당 1MB. 최대 10MB 가능)</label>
		<input class="input w-100" type="file" name="files" multiple>
	</div>
	
	<div class="row right">
		<a class="btn btn-neutral" href="list"><img src="/img/list.png" width="20" height="20"></a>
		<button class="btn btn-positive" type="submit"><img src="/img/pencil.png" width="20" height="20"></button>
	</div>
</div>
</form>

<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
    