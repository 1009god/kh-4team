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

<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/commons.css">


<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />

<!-- <link rel="stylesheet" type="text/css" href="/css/test.css"> -->

<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
           

<style>

.Login-btn.btn-neutral1:hover {
    border: 1px solid black;
    color: black;
}
* {
    box-sizing: border-box;
}
.Login-btn.btn-neutral1 {
    border: 2px solid #e9ecef;
    background-color: white;
    color: gray;
}
.login-btn {
    border-radius: 50px;
    font-size: 15px;
    padding: 0.75em;
    cursor: pointer;
    display: inline-block;
    text-align: center;
    text-decoration: none;
}


header {
	width: 100%;
	position: relative;
	border-bottom: 1px;
}

#top_menu {
	float: right;
	position: relative;
	right: 10px;
	top: 5px;
	padding: 3px; /* 두께 */
}

nav {
	border: none;
}

nav li {
	float: left;
	line-height: 3em;
	padding-left: 20px;
	list-style: none;
}

nav li a {
	color: black;
	display: block;
	text-align: center;
	text-decoration: none;
}

nav li a:hover {
	color: #0072b2;
}

#logo_img {
	margin: 10px;
}

#logo {
	display: inline-block;
	height: 70px;
}

#top_menu>a {
	color: black;
	display: inline-block;
	text-align: center;
	text-decoration: none;
}

#login_menu {
	border: 1px;
}

.ghost  {
	                align-items:  center;
	                display:  inline-block;
	                padding:  25px 40px;
	                margin:   320px 70px;
	                border:  3px solid  #d9cbb3;
	                color:  black;
	                text-align:  center;
	 
	            
}

            .ghost:hover  {
	                background-color: #d9cbb3;
	                color: black;
	            
}

</style>
</head>
<body>

	<!-- main은 영역의 시작과 끝을 의미 -->

	<!-- 상단 헤더 -->
	<header>
		<div class="container-1400">
			<div id="logo">
				<a href="/"> <img src="/img/DoranMini.png" id="logo_img"
					width="170px" height="70px">
				</a>
			</div>

				
			<div id="top_menu">
			
					<c:if test="${login}">
						<button type="submit" class="ghost"
							onclick="location.href='/pj/insert'">프로젝트 올리기</button>
							
						<button type="submit" class="ghost"
							onclick="location.href='/seller/sellerjoin'">판매자 신청</button>
					</c:if>
				


				<c:choose>
					<c:when test="${login}">
						<a href="#">${loginId} 님</a>
					<a href="/like"><i class="fa-solid fa-heart" style="color:#0072b2"></i></a>
						<a class="dropdown-item" href="/mem/logout">로그아웃</a>
						
					</c:when>
					<c:otherwise>
						<button class="Login-btn btn-neutral1">
							<a href="/mem/login">로그인</a> / <a href="/mem/joinContent">회원가입</a>
						</button>
					</c:otherwise>
				</c:choose>


			</div>
		</div>
	</header>

	<div class="container-1400">
		<nav>
			<ul class="dropdown1-menu">
				<li><a href="/pj/list"><i class="fa-solid fa-bars"></i>&nbsp;전체</a></li>
				<li><a
					href="http://localhost:8888/pj/list?sort=pj_likes_number">인기</a></li>
				<li><a href="http://localhost:8888/pj/list?sort=pj_no">신규</a></li>
				<li><a
					href="http://localhost:8888/pj/list?sort=pj_funding_end_date-sysdate">마감임박</a></li>	
					
					<li><a href="#">커뮤니티</a>
					<ul class="dropdown1-menu">
						<li><a href="/notice/list">공지사항</a></li>
						<li><a href="/board/list">도란도란</a></li>
					</ul>
					</li>
						</ul>
								
				<ul class="dropdown1-menu">
					<li><a href="#">고객센터</a>
							<ul class="dropdown1-menu">
								<li><a href="/faq/list">FAQ</a></li>
								<li><a href="/doran-q/list">1:1 문의</a></li>
							</ul>
						</li>
					</ul>
                            
                            
							     <!-- 검색창 -->
                                <div class="row right">
							        <form action="/pj/list" method="get">
							            <select style="height:21px" name="type">
							                <option value="pj_name"
							<c:if test="${pjListSearchVo.type=='pj_name'}">selected</c:if>>프로젝트 이름</option>
							                <option value="pj_category"
							<c:if test="${pjListSearchVo.type=='pj_category'}">selected</c:if>>프로젝트 카테고리</option>
							            </select>
							                
							            <input type="search" name="keyword" autocomplete="off" placeholder="검색어" style="width:"
						required value="${pjListSearchVo.keyword}">
							            <button style = "background-color:#F0F8FF; border:solid 1px lightgray;" type="submit">검색</button>
							        </form>
  								</div>    
                        
                        	

                    </div>
                    <hr style="border:1px color= silver;" width="1400px">
                    <body>
                        
                    

