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
				<pre></pre>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2" align="right">
				<a href="write">글쓰기</a>
				<a href="#">수정하기</a>
				<a href="#">삭제하기</a>
				<a href="list">목록으로</a>
			</td>
		</tr>
	</tfoot>
</table>
