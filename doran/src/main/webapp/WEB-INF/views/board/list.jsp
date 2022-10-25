<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 현재 시간 구하기 -->
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<c:set var="today">
	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>
</c:set>

<!-- 테스트용 데이터 출력 -->
<h3>${vo}</h3>


<table border="1" width="800"">
	<thead>
	<tr>
		<td align = "right" colspan="6">
			<a href="write">글쓰기</a>
		</td>
		<tr>
			<th>번호</th>
			<th width="45%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>댓글수</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody align="center">
	<c:forEach var="boardDto" items="${list}">
		<tr>
			<td>${boardDto.boardPostNo}</td>
			<td align="left">
				<a href="detail?boardPostNo=${boardDto.boardPostNo}">
					${boardDto.boardTitle}
				</a>
					[${boardDto.boardReplyCnt}]
			</td>
			<td>${boardDto.boardMemNo }</td>
			<td>
				<c:set var="current">
					<fmt:formatDate value="${boardDto.boardWriteTime}" pattern="yyyy-MM-dd"/>
				</c:set>
					<c:choose>
						<c:when test="${today == current}">
							<fmt:formatDate value="${boardDto.boardWriteTime}" 
								pattern="HH:mm"/>
						</c:when>
						<c:otherwise>
							<fmt:formatDate value="${boardDto.boardWriteTime}" 
								pattern="yyyy-MM-dd"/>
						</c:otherwise>
					</c:choose>
				</td>
			<td>${boardDto.boardReplyCnt}</td>
			<td>${boardDto.boardViewCnt}</td>
		</tr>
	</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td align = "right" colspan="6">
				<a href="write">글쓰기</a>
			</td>
		</tr>
	</tfoot>
</table>

<!-- 페이지 네비게이터 -->
<h3> &laquo; &lt; 1 2 3 4 5 6 7 8 9 10 &gt; &raquo; </h3>

<!-- 검색창 -->
<form action="list" method="get">
	<select name="type" required>
		<option value="board_title" <c:if test="${vo.type == 'board_title' }" >selected</c:if>>제목</option>
		<option value="board_writer" <c:if test="${vo.type == 'board_mem_no' }" >selected</c:if>>작성자</option>
	</select>
	
	<input type="search" name="keyword" placeholder="검색어" required value = "${param.keyword}">
	
	<button type="submit">검색</button>
</form>
