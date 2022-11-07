<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="login" value="${loginId != null}"></c:set>
<c:set var="admin" value="${mg == '관리자'}"></c:set>

<html>
<head>
<title><c:choose>
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

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/commons.css">


<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />



<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
           

<style>
.header1 {
	width: 100%;
    height: 65px;
    background-color: rgb(255, 255, 255);
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    position: relative;
    box-shadow: rgb(10 10 10 / 10%) 0px 1px 2px 0px;
    z-index: 999;
}



.headpost {
	font-size: 16px;
	color:#3d3d3d;
    line-height: 27px;
    letter-spacing: -0.02em;
    margin: 0px 0px 0px 12px;
}

#logo_img {
	margin: 10px;
}


</style>
</head>
<body>

	<!-- main은 영역의 시작과 끝을 의미 -->

	<!-- 상단 헤더 -->
	<header>
		<div style="width:100%; margin:0 auto;">
			<div class="header1" style="display:flex;">
				<div style="margin-left: 200px;">
				<a href="${pageContext.request.contextPath}/"> <img src="${pageContext.request.contextPath}/img/DoranMini.png" id="logo_img"
					width="170px" height="70px">
				</a>
				</div>
			</div>


                    <body>
                        
                    
