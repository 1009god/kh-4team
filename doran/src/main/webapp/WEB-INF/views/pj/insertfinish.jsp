<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="${sellerdto.sellerMemNo} 판매자 정보" name="title" />
</jsp:include>


<h1>프로젝트 등록 완료</h1>
<span>심사중입니다... 기다려 주십시오</span>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>



