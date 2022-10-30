<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
</jsp:include>

<div class="container-800">
<h1>seller Q 글작성 페이지 </h1>


<div>
	프로젝트 NO. ${PjDto.pjNo}
</div>

<form methode = "post">
<div>
	작성자 : <input name="sellerQMemNo" value="${sessionScope.loginNo}" > 	
</div>

<div>
	제목 : <input class="input" name="sellerQTitle" placeholder="제목" required type="text">
</div>

<div>		
	내용 : <textarea class="input w-100" name="sellerQContent" placeholder="내용" required rows="8"></textarea>
</div>

<button class="btn btn-positive" type="submit">작성</button>

</form>

</div>









<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

    