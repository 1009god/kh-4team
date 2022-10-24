<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="login" value = "${loginId != null}"></c:set>
<html lang="en">
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
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<body>

	<!-- <h2>loginId = ${sessionScope.loginId}</h2>
	<h2>로그인 상태 = ${loginId != null}</h2>
	<h2>세션넘버 = ${sessionScope.loginNo}</h2> -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>


    <div class="b-example-divider"></div>

  <header class="p-3 mb-3 border-bottom">
    <div class="container mt-5">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
          <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
        </a>
        <img src="/img/logo.png" width="10%">
        <img src="/img/burger.png" width="2%">
        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="#" class="nav-link px-2 link-secondary">전체 프로젝트</a></li>
          <li><a href="#" class="nav-link px-2 link-dark">인기</a></li>
          <li><a href="#" class="nav-link px-2 link-dark">신규</a></li>
          <li><a href="#" class="nav-link px-2 link-dark">마감임박</a></li>
          <li><a href="#" class="nav-link px-2 link-dark ">커뮤니티</a></li>
          <li><a href="#" class="nav-link px-2 link-dark">고객센터</a></li>
        </ul>

        <button type="submit" class="btn btn-outline-secondary" onclick="location.href='#'">프로젝트 올리기</button>

        &nbsp;
        &nbsp;

       <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
          <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
        </form>

        <div class="dropdown text-end">
          <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">
          </a>
          <ul class="dropdown-menu text-small">
          	<c:choose>
         		<c:when test = "${login}">
	            <li><a class="dropdown-item" href="#">프로필</a></li>
	            <li><a class="dropdown-item" href="#">좋아요</a></li>
	            <li><a class="dropdown-item" href="#">1:1 문의</a></li>
	            <li><a class="dropdown-item" href="#">관심 프로젝트</a></li>
	            <li><a class="dropdown-item" href="#">후원한 프로젝트</a></li>
	            <li><a class="dropdown-item" href="#">설정</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="/mem/logout">로그아웃</a></li>
	          </c:when>
	          <c:otherwise>
	          	 <li><a class="dropdown-item" href="/mem/join">회원가입</a></li>
                  <li><a class="dropdown-item" href="/mem/login">로그인</a></li>
	          </c:otherwise>
      		</c:choose>
	          	
          	
          </ul>
        </div>
       </div>
      </div>
  </header>
 </body>
 </head>
</html>
   