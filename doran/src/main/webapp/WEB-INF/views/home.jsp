<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도란도란 - 펀딩 사이트</title>
</head>
<body>
	<h1>어서오세요!</h1>
	<!-- 로그인 상태 확인 -->
	<h2>loginId = ${sessionScope.loginId }</h2>
	<h2>로그인 상태 = ${loginId != null}</h2>
	<c:set var="login" value= "${loginId != null}"></c:set>
	
	<c:choose>
		<c:when test="${login}">
			<h2><a href="mem/logout">회원 로그아웃</a></h2>
			<h2><a href="mypage/profile">마이 페이지</a></h2>
		</c:when>
		<c:otherwise>
			<h2><a href="mem/login">회원 로그인</a></h2>
		</c:otherwise>
	</c:choose>


<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header.jsp">
	<jsp:param value="메인페이지" name="title"/>
</jsp:include>





<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
