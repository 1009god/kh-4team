<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="주문완료" name="title" />
</jsp:include>


	<div class="center">
	    <h2>프로젝트 후원 신청이 완료되었습니다</h2>
	    <a href="http://localhost:8888/mypage/profile">마이페이지로</a>
	    <a href="http://localhost:8888/"><i class="fa-solid fa-house"></i> 홈으로</a>
	</div>


    <jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>