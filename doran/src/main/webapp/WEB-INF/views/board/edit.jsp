<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="게시글 수정" name="title"/>
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
		$("[name=boardContent]").summernote({
			height:350,
			minHeight:350,
		});
	});
</script>

<form action="edit" method="post">
<!-- input[type=hidden] 은 form 안에 위치해야 한다 -->
<input type="hidden" name="boardPostNo" value="${boardDto.boardPostNo}">

<div class="container-800 mt-40">
	<div class="row left">
		<label>제목</label>
			<input class="input w-100 solid-lines" type="text" name="boardTitle" required value="${boardDto.boardTitle}" autocomplete="off">
	</div>
	<div class="row left">
		<label>내용</label>
		<!-- 
			textarea는 value 속성이 없고 시작태그와 종료태그 사이에 작성
			(주의) pre 태그와 동일하므로 엔터나 띄어쓰기 조심
		-->
		<textarea class="input w-100" name="boardContent" rows="10" 
				required>${boardDto.boardContent}</textarea>
	</div>

	<div class="row right">
		<a class="btn btn-neutral" href="list"><img src="/img/list.png" width="20" height="20"></a>
		<button class="btn btn-positive" type="submit"><img src="/img/edit.png" width="20" height="20"></button>
	</div>

</div>
</form>

<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
