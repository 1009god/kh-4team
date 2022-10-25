<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="login" value="${loginId != null}"></c:set>
<c:set var="admin" value="${mg == '관리자'}"></c:set>

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
	</c:choose></title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">

<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/commons.css">
<link rel="stylesheet" type="text/css" href="/css/layout.css">
<!-- <link rel="stylesheet" type="text/css" href="/css/bootstrap.css"> -->

<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />

<script src="/js/checkbox.js"></script>

</head>
<body>
	<main>
		<header>
			<div class="container-700">
				<div class="row center">
					<h1>도란도란 관리자 전용 페이지</h1>
				</div>
			</div>
		</header>
    	<!-- 메뉴 -->
		<nav>
			<ul class="dropdown-menu">
				<c:choose>
					<c:when test="${login}">
						<li><a href="/admin">홈</a></li>
						<li class="right-menu"><a href="#">${loginId} 님</a>
							<ul>
								<!-- 관리자 기능 -->
								<c:if test="${login && admin}">
									<li><a href="/admin/home">관리자페이지</a></li>
								</c:if>

								<li><a href="/admin/logout">로그아웃</a></li>
							</ul></li>
					</c:when>
					<c:otherwise>
						<li><a href="/admin">홈</a></li>

						<li class="right-menu"><a href="/admin/login">로그인</a>
							<ul>
								<li><a href="/admin/insert">회원가입</a></li>
							</ul></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>

		<div class="float-container">
			<!-- 관리자 메뉴 추가 -->
			<aside>
				<div class="container-200">
					<div class="row">
						<h2>관리자메뉴</h2>
					</div>
					<div class="row">
						<a href="/admin/pjlist">프로젝트 관리</a>
					</div>
					<div class="row">
						<a href="/admin/memlist">전체 회원 관리</a>
					</div>
					<div class="row">
						<a href="/admin/sellerlist">판매자 관리</a>
					</div>
					<div class="row">
						<a href="#">공지사항 관리</a>
					</div>
					<div class="row">
						<a href="#">1:1문의 관리</a>
					</div>
					<div class="row">
						<a href="#">FAQ 작성</a>
					</div>

				</div>
		
		</aside>
		<section>