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
		<select class="input" name="faqType">
			<option value="">선택</option>
			<option>회원정보</option>
			<option>운영정책</option>
			<option>이용문의</option>
			<option>기타</option>
		</select>
	</div>
	
	<div class="row left">
		<input class="input" name="faqTitle" placeholder="제목" required type="text">
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