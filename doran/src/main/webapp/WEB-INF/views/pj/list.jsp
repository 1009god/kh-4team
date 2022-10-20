<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pj/list</title>
</head>
<body>
<h1>상품 목록</h1>
<div class=row>
	<!-- 검색창 -->
	<form action = "list" method = "get">
		<select name="type">
			<option value="pj_name" <c:if test="${pjListSearchVo.type=='pj_name'}">selected</c:if>>프로젝트 이름</option>
			<option value="pj_category" <c:if test="${pjListSearchVo.type=='pj_category'}">selected</c:if>>프로젝트 카테고리</option>
		</select>
		
		<input type="search" name="keyword" placeholder="검색어" required value="${pjListSearchVo.keyword}">
		<button type="submit">검색</button>
	</form>
	<div>
		<c:forEach var="pjDto" items="${list}">
			<div>${pjDto.pjCategory}</div>
			<div><a href="detail?pjNo=${pjDto.pjNo}">
			${pjDto.pjName}</a></div>
		</c:forEach>
	</div>
	
	<!--임시 페이지 네비게이터  -->
	<h3>&laquo; &lt; 1 2 3 4 5 6 7 8 9 10 &gt; &raquo;</h3>

</div>
</body>
</html>