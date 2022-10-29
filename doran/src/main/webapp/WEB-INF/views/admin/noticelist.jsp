<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="faq" name="title"/>
</jsp:include>
<!-- 현재 시간 구하기 -->
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<c:set var="today">
	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>
</c:set>
<!-- <style>
	 .table.table-slit {
            border: 3px solid gray;
            border-left: none;
            border-right: none;
        }
        .table.table-slit > thead {
            border-bottom: 2px solid gray;
        }
        .table.table-slit > tfoot {
            border-top: 2px solid gray;
        }
</style> -->

<!-- 테스트용 데이터 출력 -->
<!-- <h3>${vo}</h3> -->


<div class="container-900 mt-40 mb40">
	<h1 align="center">공지사항 관리</h1>
	<th align="right"> 
	
	<div align="right">
	<a class="btn btn-neutral"
	href="noticewrite">작성하기</a></th>
			</tr>
	</div>
	
	<div class="row">
	<table class="table table-hover table-slit">
		<thead>
			<tr>
				<th align="left" width="10%">번호</th>
				<th width="45%">제목</th>
				<th>작성일</th>
		</thead>
		<tbody align="center">
			<c:forEach var="noticeDto" items="${list}">
				<tr>

					<td align="left">${noticeDto.noticeNo}</td>

					<td align="left"><a
						href="noticedetail?noticeNo=${noticeDto.noticeNo}">
							${noticeDto.noticeTitle} </a></td>
					<td><c:set var="current">
							<fmt:formatDate value="${noticeDto.noticeWriteTime}"
								pattern="yyyy-MM-dd" />
						</c:set> <c:choose>
							<c:when test="${today == current}">
								<fmt:formatDate value="${noticeDto.noticeWriteTime}"
									pattern="HH:mm" />
							</c:when>
							<c:otherwise>
								<fmt:formatDate value="${noticeDto.noticeWriteTime}"
									pattern="yyyy-MM-dd" />
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</tbody>
		<!-- <tfoot>
		<tr>
			<td align = "right" colspan="6">
				<a href="write">글쓰기</a>
			</td>
		</tr>
	</tfoot> -->
	</table>
	</div>
</div>



<!-- 페이지 네비게이터 -->
<h3> 

<c:choose>
	<c:when test="${not vo.isFirst()}">
		<a href="noticelist?p=${vo.firstBlock()}&${vo.parameter()}">&laquo; </a>
	</c:when>
	<c:otherwise>
		<a href="#">&laquo;</a>
	</c:otherwise>
</c:choose>

<!-- 이전을 누르면 이전 구간의 마지막 페이지로 안내 -->

<c:choose>
	<c:when test="${vo.hasPrev()}">
		<a href="noticelist?p=${vo.prevBlock()}&${vo.parameter()}">&lt;</a>
	</c:when>
	<c:otherwise>
		<a href="#">&lt;</a>
	</c:otherwise>
</c:choose>



<c:forEach var="i"  begin="${vo.startBlock()}" end="${vo.endBlock()}" step="1">
	<a href="noticelist?p=${i}&${vo.parameter()}">${i}</a>
</c:forEach>

<!-- 다음을 누르면 다음 구간의 첫 페이지로 안내 -->

<c:choose>
	<c:when test="${vo.hasNext()}">
		<a href="noticelist?p=${vo.nextBlock()}&${vo.parameter()}">&gt;</a>
	</c:when>
	<c:otherwise>
		<a href="#">&gt;</a>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${not vo.isLast()}">
		<a href="noticelist?p=${vo.lastBlock()}&${vo.parameter()}">&raquo; </a>
	</c:when>
	<c:otherwise>
		<a href="#">&raquo;</a>
	</c:otherwise>
</c:choose>
</h3>

<!-- 검색창 -->
<form action="noticelist" method="get">
	<select name="type" required>
		<option value="notice_title" <c:if test="${vo.type == 'board_title' }" >selected</c:if>>제목</option>
	</select>
	
	<input type="search" name="keyword" placeholder="검색어" required value = "${param.keyword}">
	
	<button type="submit">검색</button>
</form>

<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>
