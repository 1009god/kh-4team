<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="login" value = "${loginId != null}"></c:set>
    
<html>
	<head>
		<title>
			<c:choose>
				<c:when test="${param.title != null}">
					${param.title}
				</c:when>
				<c:otherwise>
					홈페이지
				</c:otherwise>
			</c:choose>
		</title>
	</head>
	<body>
	<h2>loginId = ${sessionScope.loginId}</h2>
	<h2>로그인 상태 = ${loginId != null}</h2>
	<h2>세션넘버 = ${sessionScope.loginNo}</h2>
	
	<!-- 메뉴 절대경로 -->
		<div align="center">
			<c:choose>
				<c:when test = "${login}">
					<a href = "/">홈</a>
					<a href ="#">전체 프로젝트</a>
					<a href ="#">인기</a>
					<a href ="#">신규</a>
					<a href ="#">마감임박</a>
					<a href ="#">커뮤니티</a>
					<a href ="#">고객센터</a>
					<a href ="/mem/logout">로그아웃</a>
				</c:when>
				<c:otherwise>
					<a href = "/">홈</a>
					<a href ="#">전체 프로젝트</a>
					<a href ="#">인기</a>
					<a href ="#">신규</a>
					<a href ="#">마감임박</a>
					<a href ="#">커뮤니티</a>
					<a href ="#">고객센터</a>
					<a href="/mem/join">회원가입</a>
					<a href ="/mem/login">로그인</a>
				
				</c:otherwise>
			</c:choose>
			
		</div>
		
		<hr>
		<div align="center" style="min-height:400px">