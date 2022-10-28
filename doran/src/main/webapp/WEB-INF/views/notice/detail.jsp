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

<button type="submit">목록보기</button>

</table>