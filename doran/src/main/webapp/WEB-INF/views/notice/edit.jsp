<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>게시글 보기</h1>

<form action="edit" method="post">
<!-- input[type=hidden] 은 form 안에 위치해야 한다 -->
<input type="hidden" name="noticeNo" value="${noticeDto.noticeNo}">
<table border = "1" width ="500">
	<tbody>
		<tr>
			<th>제목</th>
			<td>
				<input type="text" name="noticeTitle" required value="${noticeDto.noticeTitle}">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name="noticeContent" rows="10" cols="60" required>${noticeDto.noticeContent}</textarea>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td align="right" colspan="2">
				<a href = "list">목록으로</a>
				<button type="submit">수정하기</button>
			</td>
		</tr>
	</tfoot>
</table>


</form>
