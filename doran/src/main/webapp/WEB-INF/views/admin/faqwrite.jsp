<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="faq작성" name="title"/>
</jsp:include>

<form action="faqwrite" method="post">
<div class="container-500 mt-40">
	<div class="row left">
		<h1>FAQ 작성</h1>
	</div>
	<div class="row left">
		<select class="input w-100" name="faqType">
			<option value="">선택</option>
			<option <c:if test="${faqDto.faqType == '회원정보'}"></c:if>>정보</option>
			<option <c:if test="${faqDto.faqType == '운영정책'}"></c:if>>운영정책</option>
			<option <c:if test="${faqDto.faqType == '이용문의'}"></c:if>>이용문의</option>
			<option <c:if test="${faqDto.faqType == '기타'}"></c:if>>기타</option>
		</select>
	</div>
	
	<div class="row left">
		<input class="input" name="faqtitle" placeholder="제목" required type="text" autocomplete="off">
	</div>
	<div class="row center">
		<textarea class="input w-100" name="faqContent" placeholder="내용" required rows="8"></textarea>
	</div>
	
	
	<div class="row right">
		<a class="btn btn-neutral" href="faqlist">목록</a>
		<button class="btn btn-positive" type="submit">작성</button>
	</div>
</div>
</form>

<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>  