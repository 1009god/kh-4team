<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>


<table border="1" width="800"">
	<thead>
	<tr>
		<td align = "right" colspan="6">
			<a href="#">글쓰기</a>
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
			<td>${boardDto.boardTitle}</td>
			<td>?</td>
			<td>${boardDto.boardWriteTime}</td>
			<td>${boardDto.boardReplyCnt}</td>
			<td>${boardDto.boardViewCnt}</td>
		</tr>
	</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td align = "right" colspan="6">
				<a href="#">글쓰기</a>
			</td>
		</tr>
	</tfoot>
</table>

<!-- 페이지 네비게이터 -->
<h3> &laquo; &lt; 1 2 3 4 5 6 7 8 9 10 &gt; &raquo; </h3>


