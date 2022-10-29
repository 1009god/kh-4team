<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<h1>게시글 작성</h1>

<form action="write" method="post">
<table border = "1" width ="500">
	<tbody>
		<tr>
			<th>제목</th>
			<td>
				<input type="text" name="boardTitle" required>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name="boardContent" rows="10" cols="60" required></textarea>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td align="right" colspan="2">
				<a href = "/board/list">목록으로</a>
				<button type="submit">등록하기</button>
			</td>
		</tr>
	</tfoot>
</table>
</form>
    