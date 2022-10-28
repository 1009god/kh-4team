<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<c:set var="today">
	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>
</c:set>

<h1>공지사항</h1>

<table border="1" width="800">
	<thead>
		<tr>
			공지사항
			<th>제목</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody align="center">
		<c:forEach var="noticeDto" items="${list}">
		<tr>
			<td align="left">
				<a href="detail?noticeNo=${noticeDto.noticeNo})">
					${noticeDto.noticeTitle}
				</a>
			</td>
			<td>${noticeDto.noticeWriteTime}</td>
		</tr>
		</c:forEach>
	</tbody>
	<tfoot>
	</tfoot>
</table>

<!-- 페이지 네비게이터 -->
<h3> 

<c:choose>
	<c:when test="${not vo.isFirst()}">
		<a href="list?p=${vo.firstBlock()}&${vo.parameter()}">&laquo; </a>
	</c:when>
	<c:otherwise>
		<a href="#">&laquo;</a>
	</c:otherwise>
</c:choose>

<!-- 이전을 누르면 이전 구간의 마지막 페이지로 안내 -->

<c:choose>
	<c:when test="${vo.hasPrev()}">
		<a href="list?p=${vo.prevBlock()}&${vo.parameter()}">&lt;</a>
	</c:when>
	<c:otherwise>
		<a href="#">&lt;</a>
	</c:otherwise>
</c:choose>



<c:forEach var="i"  begin="${vo.startBlock()}" end="${vo.endBlock()}" step="1">
	<a href="list?p=${i}&${vo.parameter()}">${i}</a>
</c:forEach>

<!-- 다음을 누르면 다음 구간의 첫 페이지로 안내 -->

<c:choose>
	<c:when test="${vo.hasNext()}">
		<a href="list?p=${vo.nextBlock()}&${vo.parameter()}">&gt;</a>
	</c:when>
	<c:otherwise>
		<a href="#">&gt;</a>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${not vo.isLast()}">
		<a href="list?p=${vo.lastBlock()}&${vo.parameter()}">&raquo; </a>
	</c:when>
	<c:otherwise>
		<a href="#">&raquo;</a>
	</c:otherwise>
</c:choose>
</h3>

<div class="row center">
		<form action="list" method="get">
			<input type="hidden" name="size" value="${vo.size}">
			<select class="input" name="type" required>
				<option value="board_title" <c:if test="${vo.type == 'notice_title'}">selected</c:if>>제목</option>
			</select>
			
			<input class="input" type="search" name="keyword" placeholder="검색어" required value="${vo.keyword}">
			
			<button class="btn btn-positive" type="submit">검색</button>
		</form>
	</div>

