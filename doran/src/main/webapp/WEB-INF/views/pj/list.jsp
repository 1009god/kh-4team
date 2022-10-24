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
<h3>${pjListSearchVo}</h3>
<div class=row>
	
	<form action ="list" method = "get">
	
	<select name="sort" onchange="this.form.submit()"> <!-- 추후 js.43번 참고해서 하기 -->
		<option value=>정렬</option>
		<option value="pj_no">최신순</option>
		<option value="pj_likes_number">인기순</option>
		<option value="pj_funding_end_date-sysdate">마감임박순</option>
	</select>

		
<!-- 		<button name="sort" value="pj_likes_number">인기순</button> -->
<!-- 		<button name="sort" value="pj_funding_end_date-sysdate">마감임박순</button> -->
		
		<button name="category" value="패션/잡화">패션/잡화</button>
		<button name="category" value="뷰티">뷰티</button>
		<button name="category" value="푸드">푸드</button>
		<button name="category" value="홈/리빙">홈/리빙</button>
		<button name="category" value="테크/가전">테크/가전</button>
		<button name="category" value="기타">기타</button>
		
<!-- 		<select type="input" name="sort"> -->
<%-- 			<option value="pj_likes_number" <c:if test="${pjListSearchVo.sort=='pj_likes_number'}">selected</c:if>>인기순</option> --%>
<%-- 			<option value="pj_no" <c:if test="${pjListSearchVo.sort=='pj_no'}">selected</c:if>>최신순</option> --%>
<!-- 		</select> -->
	</form>
	
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
		<a href="list"><span>전체 프로젝트</span></a>
		<a href="#"><span>패션/잡화</span></a>
		<a href="#"><span>뷰티</span></a>
		<a href="#"><span>푸드</span></a>
		<a href="#"><span>홈/리빙</span></a>
		<a href="#"><span>테크/가전</span></a>
		<a href="#"><span>기타</span></a>
	</div>
	
	<div>
		<c:forEach var="pjDto" items="${list}">
			<div>*${pjDto.pjNo}</div>
			<div>[${pjDto.pjCategory}]<span> ♥ ${pjDto.pjLikesNumber}</span></div>
			<div><a href="detail?pjNo=${pjDto.pjNo}">
			${pjDto.pjName}</a></div>
		</c:forEach>
	</div>

	
	<!--페이지 네비게이터  -->
	<h3>
	
	<c:choose>
		<c:when test="${not pjListSearchVo.isFirst()}">
			<a href="list?p=${pjListSearchVo.firstBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&laquo;</a> 
		</c:when>
		<c:otherwise>
			<a href="#">&laquo;</a> 
		</c:otherwise>
	</c:choose>
	
	<!-- 이전을 누르면 이전 구간의 마지막 페이지로 안내 -->
	<c:choose>
		<c:when test="${pjListSearchVo.hasPrev()}">
			<a href="list?p=${pjListSearchVo.prevBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&lt;</a>
		</c:when>
		<c:otherwise>
			<a href="#">&lt;</a> 
		</c:otherwise>
	</c:choose>
	
	<c:forEach var="i" begin="${pjListSearchVo.startBlock()}" end="${pjListSearchVo.endBlock()}" step="1">
		<a href="list?p=${i}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">${i}</a>
	</c:forEach>
		
	<!-- 다음을 누르면 다음 구간의 첫 페이지로 안내 -->
	<c:choose>
		<c:when test="${pjListSearchVo.hasNext()}">
			<a href="list?p=${pjListSearchVo.nextBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&gt;</a>
		</c:when>
		<c:otherwise>
			<a href="#">&gt;</a> 
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${not pjListSearchVo.isLast()}">
			<a href="list?p=${pjListSearchVo.lastBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&raquo;</a>
		</c:when>
		<c:otherwise>
			<a href="#">&raquo;</a> 
		</c:otherwise>
	</c:choose>
		
	</h3>

</div>
</body>
</html>