<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 현재 시간 구하기 -->
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<c:set var="today">
	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>
</c:set>
<style>
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
        
        ul.pagination {
            list-style: none;
            margin: 0;
            padding: 0;
            font-size: 16px;
            text-align: center;
        }
        ul.pagination > li {
            display: inline-block;
            border: 1px solid transparent;
            padding: 0.5em;
            line-height: 1em;/*글자 표시 높이 설정 */
            min-width: 2em;
            text-align: center;
            cursor: pointer;
        }
        ul.pagination > li.on,
        ul.pagination > li:hover {
            border-color: #b2bec3;
            color: #d63031;
        }
        ul.pagination > li > a {
            display: block;
            width:100%;
            color: inherit;
            text-decoration: none;
        }

        ul.pagination.pagination-big {
            font-size: 1.25em;
        }
        ul.pagination.pagination-small {
            font-size: 0.75em;
        }
</style>

<!-- 테스트용 데이터 출력 -->
<!-- <h3>${vo}</h3> -->

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
</jsp:include>

<div class="container-900 mt-40 mb-40">
<table class="table table-slit">
	<thead>
	<tr>
		<tr>
			<th width="45%">제목</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody align="center">
	<c:forEach var="noticeDto" items="${list}">
		<tr>
			<td align="left">
				<a href="detail?noticeNo=${noticeDto.noticeNo}">
					${noticeDto.noticeTitle}
				</a>
			</td>
			<td>
				<c:set var="current">
					<fmt:formatDate value="${noticeDto.noticeWriteTime}" pattern="yyyy-MM-dd"/>
				</c:set>
					<c:choose>
						<c:when test="${today == current}">
							<fmt:formatDate value="${noticeDto.noticeWriteTime}" 
								pattern="HH:mm"/>
						</c:when>
						<c:otherwise>
							<fmt:formatDate value="${noticeDto.noticeWriteTime}" 
								pattern="yyyy-MM-dd"/>
						</c:otherwise>
					</c:choose>
				</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<!-- 페이지 네비게이터 -->

<<div class="row center">
	<ul class="pagination">
		<c:choose>
			<c:when test="${not vo.isFirst()}">
				<li><a href="list?p=${vo.firstBlock()}&${vo.parameter()}">&laquo; </a></li>
			</c:when>
			<c:otherwise>
				<li><a href="#">&laquo;</a></li>
			</c:otherwise>
		</c:choose>
		
		<!-- 이전을 누르면 이전 구간의 마지막 페이지로 안내 -->
		
		<c:choose>
			<c:when test="${vo.hasPrev()}">
				<li><a href="list?p=${vo.prevBlock()}&${vo.parameter()}">&lt;</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="#">&lt;</a></li>
			</c:otherwise>
		</c:choose>
		
		
		
		<c:forEach var="i"  begin="${vo.startBlock()}" end="${vo.endBlock()}" step="1">
			<c:choose>
					<c:when test="${vo.p == i}">
						<li class="on"><a href="#">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="list?p=${i}&${vo.parameter()}">${i}</a></li>
					</c:otherwise>
				</c:choose>
		</c:forEach>
		
		<!-- 다음을 누르면 다음 구간의 첫 페이지로 안내 -->
		
		<c:choose>
			<c:when test="${vo.hasNext()}">
				<li><a href="list?p=${vo.nextBlock()}&${vo.parameter()}">&gt;</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="#">&gt;</a></li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${not vo.isLast()}">
				<li><a href="list?p=${vo.lastBlock()}&${vo.parameter()}">&raquo; </a></li>
			</c:when>
			<c:otherwise>
				<li><a href="#">&raquo;</a></li>
			</c:otherwise>
		</c:choose>
		</ul>
		</div>


<!-- 검색창 -->
<form action="list" method="get">
	<select name="type" required>
		<option value="notice_title" <c:if test="${vo.type == 'board_title' }" >selected</c:if>>제목</option>
	</select>

	<input type="search" name="keyword" placeholder="검색어" required value = "${param.keyword}">

	<button type="submit">검색</button>
</form>
</div>

<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>