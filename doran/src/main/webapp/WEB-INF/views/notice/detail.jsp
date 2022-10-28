<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<c:set var="today">
	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>
</c:set>

<table border="1" width="500">
	<tbody>
		<tr>
			<th width="25%">${noticeDto.noticeTitle}</th>
		</tr>
		<tr>
			<td>${noticeDto.noticeWriteTime}</td>
		</tr>
		<tr>
			<td>
				<pre>${noticeDto.noticeContent}</pre>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2" align="right">
				<a href="write">공지 작성</a>
				<a href="write">공지 수정</a>
				<a href="delete?noticeNo=${noticeDto.noticeNo}">공지 삭제</a>
			</td>
		</tr>
	</tfoot>

<button type="submit">목록보기</button>

</table>