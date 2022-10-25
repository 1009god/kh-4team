<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>게시글 보기</h1>

<table border = "1" width = "500">
	<tbody>
		<tr>
			<th width = "25%">번호</th>
			<td>${boardDto.boardPostNo}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${boardDto.boardTitle}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${boardDto.boardMemNo}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>
				<fmt:formatDate value="${boardDto.boardWriteTime}" pattern="y년 M월 d일 E요일 a h시 m분 s초"/>
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${boardDto.boardViewCnt}</td>
		</tr>
		<tr height="200" valign="top"">
			<th>내용</th>
			<td>
				<!-- pre 태그 엔터, 띄어쓰기, 탭 키 그대로 표시 -->
				<pre>${boardDto.boardContent}</pre>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2" align="right">
				<a href="write">글쓰기</a>
				<a href="edit?boardPostNo=${boardDto.boardPostNo}">수정하기</a>
				<a href="delete?boardPostNo=${boardDto.boardPostNo}">삭제하기</a>
				<a href="list">목록으로</a>
			</td>
		</tr>
	</tfoot>
</table>
<table border="1" width="500">
	<!-- 댓글 목록 -->
	<tbody>
	<c:forEach var="replyDto" items="${replyList}">
		<tr>
			<td width="90%">
			<!-- 작성자 -->
			${replyDto.replyMemNo}
			<c:if test="${boardDto.boardMemNo == replyDto.replyMemNo}">
			(작성자)
			</c:if>
			
				<pre>${replyDto.replyContent}</pre>
				<br><br>
				<fmt:formatDate value="${replyDto.replyWriteTime}" pattern="yyyy-MM-dd HH:mm"/>
			</td>
			<th>
				<!-- 수정과 삭제는 현재 사용자가 남긴 댓글에만 표시 -->
				<c:if test="${loginNo == replyDto.replyMemNo}">
					수정
					<br>
					<a href="reply/delete?replyNo=${replyDto.replyNo}&replyBoardPostNo=${replyDto.replyBoardPostNo}">삭제</a>
				</c:if>
			</th>
		</tr>
		</c:forEach>
	</tbody>
</table>
<br>
<c:choose>
	<c:when test="${loginNo != null}">
		<form action="reply/write" method="post">
	<input type="hidden" name="replyBoardPostNo" value="${boardDto.boardPostNo}">
	<table border="1" width="500">
		<tbody>
			<tr>
				<th>
					<textarea name="replyContent" rows="5" cols="55" required placeholder="댓글 작성..."></textarea>
				</th>
				<th>
					<button type="submit">등록</button>
				</th>
			</tr>
		</tbody>
	</table>
	</form>
		
	</c:when>
	<c:otherwise>
		<table border="1" width="500">
			<tbody>
				<tr>
					<th>
						<textarea name="replyContent" rows="5" cols="55" placeholder="로그인 후 댓글 작성이 가능합니다" disabled></textarea>
					</th>
					<th>
						<button type="submit" disabled>등록</button>
					</th>
				</tr>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
