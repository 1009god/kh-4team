<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
   <c:set var="login" value="${loginId != null}"></c:set>
	<c:set var="admin" value="${mg == '관리자'}"></c:set>


	<html>
		<head>
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
	    <meta name="generator" content="Hugo 0.104.2">
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

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/headers/">

    

    

<link href="/docs/5.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

    <!-- Favicons -->
<link rel="apple-touch-icon" href="/docs/5.2/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/5.2/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/5.2/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/5.2/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/5.2/assets/img/favicons/safari-pinned-tab.svg" color="#712cf9">
<link rel="icon" href="/docs/5.2/assets/img/favicons/favicon.ico">
<meta name="theme-color" content="#712cf9">

<style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
        
        

		
    </style>
    
     <!-- Custom styles for this template -->
    <link href="headers.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/reset.css">

  </head>
  <body>

  <header class="p-3 mb-3 border-bottom ">
  	<div class="container">
  		<div id="logo">
          <a href="/">
             <img src="/img/DoranMini.png" id="logo_img" width="170px" height="70px">
          </a>
          <c:choose>
			<c:when test = "${login}">
				<button type="submit" class="btn btn-neutral" onclick="location.href='/seller/sellerjoin'">판매자 신청</button>
			</c:when>
		</c:choose>    
  	</div>

    <div class="container mb-10">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="#">
        	<img src="/img/burger.png" width="25px" height="25px">
        </a>
		<ul class="nav nav-pills ">
		<li class="nav-item">
			<a class="nav-link " href="#">전체 프로젝트</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="#">인기</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="#">신규</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="#">마감임박</a>
		</li>
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">커뮤니티</a>
			 <ul class="dropdown-menu">
			  	<li><a class="dropdown-item" href="#">공지사항</a></li>
			    <li><a class="dropdown-item" href="#">도란도란</a></li>
			 </ul>
		</li>
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">고객센터</a>
			    <ul class="dropdown-menu">
			      <li><a class="dropdown-item" href="#">FAQ</a></li>
			      <li><a class="dropdown-item" href="#">1:1 문의</a></li>
			    </ul>
		</li>
	</ul>
</div>
</div>
</div>
</header>


    <script src="/docs/5.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>


  